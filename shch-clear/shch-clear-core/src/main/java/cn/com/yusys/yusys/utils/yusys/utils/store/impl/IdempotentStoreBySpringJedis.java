package cn.com.yusys.yusys.utils.yusys.utils.store.impl;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

import cn.com.yusys.yusys.utils.idempotent.YusysIdempotentException;
import cn.com.yusys.yusys.utils.idempotent.YusysIdempotentStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Component
public class IdempotentStoreBySpringJedis implements YusysIdempotentStore {

    @Autowired
    private RedisTemplate redisTemplate;
    private RedisSerializer stringSerializer = new StringRedisSerializer();
    private  RedisSerializer mapSerializer = new FastJsonRedisSerializer(Map.class);
    public void setRedisTemplate(RedisTemplate redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean write(String key, String value, Long ttl) throws YusysIdempotentException {
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public boolean update(String key, String value, Long ttl) {
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public String read(String key) {

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        if (redisTemplate.opsForValue().get(key) != null)
            return redisTemplate.opsForValue().get(key).toString();
        return null;

    }

    @Override
    public boolean delete(String key) {
        DefaultRedisScript<Map> getRedisScript = new DefaultRedisScript<>();
        getRedisScript.setResultType(Map.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/idempotent_delete.lua")));

        List keys = new ArrayList();
        keys.add(key);
        Map<String, Object> argvMap = new HashMap<String, Object>();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(mapSerializer);
        Map response = (Map) redisTemplate.execute(getRedisScript, keys, argvMap);
        String result = response.get("result").toString();
        if (result.equals("idempotent-biz-process"))
            throw new YusysIdempotentException("操作正在执行，请稍后再试");
        else if (result.equals("success")) {
            return true;
        }
        return false;

       /* String valueStored = null;
        if (redisTemplate.opsForValue().get(key) != null) {
            valueStored = redisTemplate.opsForValue().get(key).toString();
            if (valueStored.equals("idempotent-biz-process")) {
                throw new YusysIdempotentException("操作正在执行，请稍后再试");
            } else if (!valueStored.equals("idempotent-biz-error")) {
                redisTemplate.delete(key);
            }
        }*/

    }
}
