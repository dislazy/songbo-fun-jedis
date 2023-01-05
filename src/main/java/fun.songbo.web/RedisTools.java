package fun.songbo.web;

import fun.songbo.web.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

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
}
