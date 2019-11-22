package cn.com.yusys.yusp.utils.idempotent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.yusys.yusp.utils.annotation.YusysIdempotent;

@Aspect
@Component
public class YusysIdempotentAop {

    @Autowired
    YusysIdempotentProperties yusysIdempotentProperties;

    @Autowired
    YusysIdempotentStore yusysIdempotentStore;

    @Pointcut("@annotation(cn.com.yusys.yusp.utils.annotation.YusysIdempotent)")
    public void idempotent() {
    }

/*    @Before("idempotent()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        System.out.println("进入方法前执行.....");

    }*/

    @Around("idempotent()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws RuntimeException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        YusysIdempotent action = method.getAnnotation(YusysIdempotent.class);
        String name = signature.getDeclaringTypeName() + "." + signature.getName();

        //如果指定请求参数中的字段作为幂等判断，拼接key值，包名+类名+方法名+“：”+幂等字段值
        Object[] args = joinPoint.getArgs();
        String actionField = action.field();
        String fieldValue = "";
        int isRepeat = 0;
        if (!actionField.trim().equals("") && args != null) {
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            for (int i = 0, l = args.length; i < l; i++) {
                if (args[i] != null && JSONObject.isValidObject(JSONObject.toJSONString(args[i]))) {
                    JSONObject argsJson = JSONObject.parseObject(JSONObject.toJSONString(args[i]));
                    if (argsJson.getString(action.field()) != null)
                        fieldValue = argsJson.getString(action.field());
                    if (argsJson.getString(action.repeatField()) != null && argsJson.getString(action.repeatField()).equals("r")) {
                        isRepeat = 1;
                    }
                } else {
                    if (args[i] != null && parameterNames[i].equals(action.field())) {
                        fieldValue = args[i].toString();
                    }
                    if (args[i] != null && parameterNames[i].equals(action.repeatField())) {
                        if (args[i].toString().equals("r"))
                            isRepeat = 1;
                    }
                }
            }
        }

        //如果未指定请求参数中的字段作为幂等判断，则md5加密整个请求参数
        if (fieldValue != null && !fieldValue.trim().equals("")) {
            name = name + ":" + fieldValue;
        } else {
            String jsonStr = "";
            try {
                for (int i = 0, l = args.length; i < l; i++) {
                    if (args[i] != null && JSONObject.isValidObject(JSONObject.toJSONString(args[i]))) {
                        jsonStr += URLEncoder.encode(JSONObject.toJSONString(args[i]), "utf-8");
                    } else {
                        if (args[i] != null)
                            jsonStr += URLEncoder.encode(args[i].toString(), "utf-8");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            name = name + ":" + DigestUtils.md5DigestAsHex(jsonStr.getBytes());
        }

        if (isRepeat == 1) {
            try {
                yusysIdempotentStore.delete(name);
            } catch (IOException e) {
                throw new YusysIdempotentException("幂等key删除失败:" + e.getMessage());
            }
        }

        System.out.println("---------存入数据源中的key值为-------------:" + name);
        //时间格式的转换，传入时间要求“数字+d/h/m/s”,转换为long类型的值
        Object res = null;
        String ttlStr = action.ttl();
        if (ttlStr == null || ttlStr.trim().equals(""))
            ttlStr = yusysIdempotentProperties.getTtl();
        int ttlNum = Integer.valueOf(ttlStr.substring(0, ttlStr.length() - 1));
        long ttl = 0L;
        if (ttlStr.toLowerCase().endsWith("d")) {
            ttl = ttlNum * 24 * 60 * 60;
        } else if (ttlStr.toLowerCase().endsWith("h")) {
            ttl = ttlNum * 60 * 60;
        } else if (ttlStr.toLowerCase().endsWith("m")) {
            ttl = ttlNum * 60;
        } else if (ttlStr.toLowerCase().endsWith("s")) {
            ttl = ttlNum;
        }

        //先读取数据，一旦存在，返回上次结果数据
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        String valueStored = yusysIdempotentStore.read(name);
        if (valueStored != null) {
            if (valueStored.equals("idempotent-biz-process")) {
                throw new YusysIdempotentException("操作正在执行，请稍后再试");
            } else if (!valueStored.equals("idempotent-biz-error")) {
                //从redis中获取状态信息
                if (JSON.isValid(valueStored)) {
                    res = JSON.parseObject(valueStored, returnType);
                } else {
                    if (valueStored.equals("null"))
                        res = null;
                    else
                        res = valueStored;
                }
            }
        }

        //如果未读到数据，则证明是首次请求，允许调用业务方法，但是需要注意一下并发问题
        if (valueStored == null || valueStored.equals("idempotent-biz-error")) {
            //try {
            if (valueStored == null) {  //需要实现分布式锁
                boolean writeStatus = false;
                try {
                    writeStatus = yusysIdempotentStore.write(name, "idempotent-biz-process", ttl);
                } catch (Exception e) {
                    throw new YusysIdempotentException("并发执行太快，分布式锁控制", e);
                }
                if (!writeStatus)
                    throw new YusysIdempotentException("操作正在执行，请稍后再试");
            }
            try {
                res = joinPoint.proceed();
                if (res != null) {
                    if (JSON.isValid(JSONObject.toJSONString(res)))   //业务方法返回结果可以转换json的情况
                        yusysIdempotentStore.update(name, JSONObject.toJSONString(res), ttl);
                    else  //业务方法返回结果是基本类型的情况
                        yusysIdempotentStore.update(name, res.toString(), ttl);
                } else {
                    if (returnType.getName().equals("void")) {   //业务方法返回类型是void的情况
                        yusysIdempotentStore.update(name, "idempotent-biz-void", ttl);
                    } else {  //业务方法返回结果为null的情况
                        yusysIdempotentStore.update(name, "null", ttl);
                    }
                }
            } catch (Throwable e) {
                yusysIdempotentStore.update(name, "idempotent-biz-error", ttl);
                throw new YusysIdempotentException("被幂等方法出现异常或错误", e);
            }

        }
        return res;
    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     */
    //@AfterReturning(returning = "ret", pointcut = "idempotent()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    //@AfterThrowing("idempotent()")
    public void throwss(JoinPoint jp) {
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
   /* @After("idempotent()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }*/
}
