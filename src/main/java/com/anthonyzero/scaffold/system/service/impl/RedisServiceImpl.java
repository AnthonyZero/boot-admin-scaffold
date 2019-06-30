package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.exception.RedisConnectException;
import com.anthonyzero.scaffold.common.function.JedisExecutor;
import com.anthonyzero.scaffold.system.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 处理jedis请求
     * @param j  处理逻辑，通过 lambda行为参数化
     * @param <T> 返回结果
     * @return
     * @throws RedisConnectException
     */
    private <T> T excuteByJedis(JedisExecutor<Jedis,T> j) throws RedisConnectException {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.excute(jedis); //返回执行结果
        } catch (Exception e) {
            throw new RedisConnectException(e.getMessage());
        }
    }

    @Override
    public Set<String> getKeys(String pattern) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.keys(pattern));
    }

    @Override
    public String get(String key) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.get(key));
    }

    @Override
    public String set(String key, String value) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.set(key, value));
    }

    @Override
    public String set(String key, String value, int seconds) throws RedisConnectException {
        String result = excuteByJedis(jedis -> jedis.set(key, value));
        expire(key, seconds);
        return result;
    }

    @Override
    public Long del(String... key) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.del(key));
    }

    @Override
    public Boolean exists(String key) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.exists(key));
    }

    @Override
    public Long ttl(String key) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.ttl(key));
    }

    @Override
    public Long expire(String key, int seconds) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.expire(key, seconds));
    }

    @Override
    public Long zadd(String key, Double score, String member) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.zadd(key, score, member));
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) throws RedisConnectException {
        return excuteByJedis(jedis -> jedis.zrangeByScore(key, min, max));
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zremrangeByScore(key, start, end));
    }

    @Override
    public Long zrem(String key, String... members) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zrem(key, members));
    }
}
