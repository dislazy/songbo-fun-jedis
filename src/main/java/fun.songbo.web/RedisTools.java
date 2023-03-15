package fun.songbo.web;

import fun.songbo.web.commons.EXPX;
import fun.songbo.web.commons.Expire;
import fun.songbo.web.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.resps.ScanResult;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2023-01-05 18:20
 * @since
 */
public class RedisTools {

    private JedisPool pool;

    public RedisTools(RedisConfig redisConfig, int index) {
        if (redisConfig == null) {
            throw new RuntimeException("redisConfig is null on pop redis");
        }
        if (!checkDbIndex(index)) {
            throw new IllegalArgumentException("not allow db indexes is empty");
        }
        pool = newRedisPool(redisConfig, index);
    }
    /**
     * 创建一个redis连接池
     *
     * @param dbIndex dbIndex
     *
     * @return JedisPool
     */
    private JedisPool newRedisPool(RedisConfig redisConfig, int dbIndex) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getMaxTotal());
        config.setMaxIdle(redisConfig.getMaxIdle());
        config.setMinIdle(redisConfig.getMinIdle());
        config.setBlockWhenExhausted(redisConfig.isBlockWhenExhausted());
        config.setMaxWait(Duration.ofMillis(redisConfig.getMaxWaitMillis()));
        config.setTestOnBorrow(redisConfig.isTestOnBorrow());
        config.setTestOnReturn(redisConfig.isTestOnReturn());
        config.setTestOnCreate(redisConfig.isTestOnCreate());
        config.setTestWhileIdle(redisConfig.isTestWhileIdle());
        config.setJmxEnabled(redisConfig.isJmxEnabled());
        config.setTimeBetweenEvictionRuns(Duration.ofMillis(redisConfig.getTimeBetweenEvictionRuns()));
        config.setMinEvictableIdleTime(Duration.ofMillis(redisConfig.getMinEvictableIdleTimeMillis()));
        config.setNumTestsPerEvictionRun(redisConfig.getNumTestsPerEvictionRun());
        return new JedisPool(config, redisConfig.getRedisHost(), redisConfig.getRedisPort(), redisConfig.getTimeout(), redisConfig.getRedisPwd(), dbIndex, null);
    }


    private boolean checkDbIndex(int index) {
        return index >= 0 && index <= 254;
    }

    private Jedis getJedis() {
        if (pool == null) {
            throw new RuntimeException("jedis not init");
        }
        return pool.getResource();
    }

    public String get(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.get(key);
        }
    }


    public List<String> mget(String... keys) {
        try (Jedis jedis = getJedis()) {
            return jedis.mget(keys);
        }
    }


    public long del(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.del(key);
        }
    }


    public String rename(String oldKey, String newKey) {
        try (Jedis jedis = getJedis()) {
            if (jedis.exists(oldKey)) {
                return jedis.rename(oldKey, newKey);
            }
        }
        return null;
    }


    public String set(String key, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value);
        }
    }


    public String set(byte[] key, byte[] value) {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value);
        }
    }


    public String set(String key, String value, EXPX expx, long exp) {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value, setParams(expx, exp));
        }
    }


    public String set(byte[] key, byte[] value, EXPX expx, long exp) {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value, setParams(expx, exp));
        }
    }

    /**
     * @param expx 单位 秒或者毫秒
     * @param exp  过期时间
     * @return
     */
    private static SetParams setParams(EXPX expx, long exp) {
        SetParams setParams = new SetParams();
        if (expx.equals(EXPX.SECONDS)) {
            setParams.ex(exp);
        } else if (expx.equals(EXPX.MILLISECONDS)) {
            setParams.px(exp);
        }
        return setParams;
    }

    private static SetParams setParams(EXPX expx, long exp,boolean nx) {
        SetParams setParams = new SetParams();
        if (nx) {
            setParams.nx();
        } else {
            setParams.xx();
        }
        if (expx.equals(EXPX.SECONDS)) {
            setParams.ex(exp);
        } else if (expx.equals(EXPX.MILLISECONDS)) {
            setParams.px(exp);
        }
        return setParams;
    }


    public String set(String key, String value, Expire exp) {
        if (exp == null) {
            return "not ok";
        }
        SetParams params = setParams(EXPX.SECONDS, exp.getTime());
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value,params);
        }
    }


    public String set(byte[] key, byte[] value, Expire exp) {
        if (exp == null) {
            return "not ok";
        }
        return set(key, value, EXPX.SECONDS, (long) exp.getTime());
    }


    public String set(String key, String value, boolean ex, int time, boolean nx) {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value, setParams(ex ? EXPX.SECONDS : EXPX.MILLISECONDS, time, nx));
        }
    }


    public String setex(String key, long seconds, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.setex(key, seconds, value);
        }
    }


    public boolean setnx(String key, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.setnx(key, value) > 0L;
        }
    }


    public boolean exists(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.exists(key);
        }
    }


    public Long expire(String key, Expire exp) {
        try (Jedis jedis = getJedis()) {
            return jedis.expire(key, exp.getTime());
        }
    }


    public Long expire(byte[] key, Expire exp) {
        try (Jedis jedis = getJedis()) {
            return jedis.expire(key, exp.getTime());
        }
    }


    public Long expire(String key, long seconds) {
        try (Jedis jedis = getJedis()) {
            return jedis.expire(key, seconds);
        }
    }


    public Long expireAt(String key, long unixTime) {
        try (Jedis jedis = getJedis()) {
            return jedis.expireAt(key, unixTime);
        }
    }


    public Long expireAt(byte[] key, long unixTime) {
        try (Jedis jedis = getJedis()) {
            return jedis.expireAt(key, unixTime);
        }
    }


    public Long ttl(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.ttl(key);
        }
    }

    public Long incr(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.incr(key);
        }
    }


    public Long incrby(String key, long increment) {
        try (Jedis jedis = getJedis()) {
            return jedis.incrBy(key, increment);
        }
    }
    public Set<String> keys(String pattern) {
        try (Jedis jedis = getJedis()) {
            return jedis.keys(pattern);
        }
    }


    public ScanResult<String> scan(String cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.scan(cursor);
        }
    }


    public ScanResult<byte[]> scan(byte[] cursor) {
        try (Jedis jedis = getJedis()) {
            return jedis.scan(cursor);
        }
    }

    public Long hset(String key, String field, String value) {
        return hset(key, field, value, null);
    }


    public Long hset(String key, String field, String value, Expire exp) {
        Long opCount = null;
        try (Jedis jedis = getJedis()) {
            opCount = jedis.hset(key, field, value);
            if (exp != null) {
                jedis.expire(key, exp.getTime());
            }
        }
        return opCount;
    }


    public Long hset(byte[] key, byte[] field, byte[] value) {
        return hset(key, field, value, null);
    }


    public Long hset(byte[] key, byte[] field, byte[] value, Expire exp) {
        Long opCount = null;
        try (Jedis jedis = getJedis()) {
            opCount = jedis.hset(key, field, value);
            if (exp != null) {
                jedis.expire(key, exp.getTime());
            }
        }
        return opCount;

    }


    public boolean hsetnx(String key, String field, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.hsetnx(key, field, value) > 0L;
        }
    }


    public String hget(String key, String field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hget(key, field);
        }
    }


    public byte[] hget(byte[] key, byte[] field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hget(key, field);
        }
    }

    /**
     * 该命令不建议使用，所以标记过期
     *
     * @param key
     * @return
     */

    @Deprecated
    public Map<String, String> hgetAll(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hgetAll(key);
        }
    }


}
