package cn.com.yusys.yusp.service.mq;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cn.com.yusys.yusp.config.ClusterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.service.CashClient;
import cn.com.yusys.yusp.service.MoneyDto;
import org.springframework.web.client.RestTemplate;

@Service
public class PayMQListener {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(PayMQListener.class);

    @Autowired
    private ClusterConfig clusterConfig;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private CashClient cashClient;

    @RabbitListener(queues = {"to_shch"})
    @RabbitHandler
    public void receiveQueue(String message) {
        System.out.println(message);
        String msg = null;
        try {

            String[] items = message.split(",");
            byte[] bytes = new byte[items.length];
            for (int i = 0; i < items.length; i++) {
                bytes[i] = Byte.parseByte(items[i]);
            }
            msg = new String(bytes, "UTF-8");
            Map data = objectMapper.readValue(msg, Map.class);
            logger.info("接收到人行报文：" + msg);

            if (data.get("msgType").equals("HVPS.111.001.01")) {

                //ResultDto<String> result = cashClient.pay(data);
                // 根据收取报文中的env环境,判断是调用哪个中心的cash服务。
                String env = data.get("env").toString();
                logger.info("原请求人行的服务,所在中心为：" + env);
                dispatch(env, data);

            } else if (data.get("msgType").equals("HVPS.112.001.01")) {
                MoneyDto money = new MoneyDto();
                money.setCashAccount(data.get("cashAccount").toString());
                money.setCurrencyAmt(new BigDecimal(data.get("currencyAmt").toString()));
                money.setHolderAccount(data.get("holderAccount").toString());
                money.setMemCode(data.get("memCode").toString());
                money.setTitleCode(data.get("titleCode").toString());
                cashClient.add(money);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("支付处理接收报文格式化对象失败：" + msg);
        }
    }

    private void dispatch(String env, Map data) throws Exception {
        Map<String, String> envs = new HashMap<>();

        envs.put("DEV", clusterConfig.getDev());
        envs.put("PRO", clusterConfig.getPro());

        String url = envs.get(env);

        logger.info("调用{}环境,cash服务地址: {}", env,  url);
        logger.info("调用{}环境,cash服务数据: {}", env,  data.toString());

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());

        String msgJson = objectMapper.writeValueAsString(data);

        HttpEntity<String> formEntity = new HttpEntity<>(msgJson, headers);

        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.postForObject(url, formEntity, Object.class);
        logger.info("调用{}环境,cash服务返回应答:{}", env, result.toString());
    }
}
