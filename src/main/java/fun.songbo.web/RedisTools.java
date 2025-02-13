package fun.songbo.web;

import fun.songbo.web.commons.EXPX;
import fun.songbo.web.commons.Expire;
import fun.songbo.web.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.resps.ScanResult;
import redis.clients.jedis.resps.StreamEntry;
import redis.clients.jedis.resps.Tuple;
import java.time.Duration;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author jack
 * @date 2022-12-07 10:37
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


    public Long hdel(String key, String field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hdel(key, field);
        }
    }


    public Long hdel(byte[] key, byte[] field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hdel(key, field);
        }
    }


    public Boolean hexists(byte[] key, byte[] field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hexists(key, field);
        }
    }


    public Boolean hexists(String key, String field) {
        try (Jedis jedis = getJedis()) {
            return jedis.hexists(key, field);
        }
    }


    public Set<String> hkeys(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hkeys(key);
        }
    }


    public Set<byte[]> hkeys(byte[] key) {
        try (Jedis jedis = getJedis()) {
            return jedis.keys(key);
        }
    }


    public Long hlen(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hlen(key);
        }
    }


    public Long hlen(byte[] key) {
        try (Jedis jedis = getJedis()) {
            return jedis.hlen(key);
        }
    }


    public Long lpush(String key, String... values) {
        try (Jedis jedis = getJedis()) {
            return jedis.lpush(key, values);
        }
    }


    public Long lpush(byte[] key, byte[]... values) {
        try (Jedis jedis = getJedis()) {
            return jedis.lpush(key, values);
        }
    }


    public String lpop(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.lpop(key);
        }
    }


    public List<String> lrange(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.lrange(key, start, end);
        }
    }


    public List<byte[]> lrange(byte[] key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.lrange(key, start, end);
        }
    }


    public Long llen(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.llen(key);
        }
    }


    public byte[] lpop(byte[] key) {
        try (Jedis jedis = getJedis()) {
            return jedis.lpop(key);
        }
    }


    public Long rpush(String key, String... values) {
        try (Jedis jedis = getJedis()) {
            return jedis.rpush(key, values);
        }
    }


    public String rpop(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.rpop(key);
        }
    }


    public Long lrem(String key, long count, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.lrem(key, count, value);
        }
    }


    public Long lrem(byte[] key, long count, byte[] value) {
        try (Jedis jedis = getJedis()) {
            return jedis.lrem(key, count, value);
        }
    }


    public String ltrim(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.ltrim(key, start, end);
        }
    }


    public String lindex(String key, long index) {
        try (Jedis jedis = getJedis()) {
            return jedis.lindex(key, index);
        }
    }


    public byte[] lindex(byte[] key, long index) {
        try (Jedis jedis = getJedis()) {
            return jedis.lindex(key, index);
        }
    }


    public Long sadd(String key, String... members) {
        try (Jedis jedis = getJedis()) {
            return jedis.sadd(key, members);
        }
    }


    public Long sadd(byte[] key, byte[]... members) {
        try (Jedis jedis = getJedis()) {
            return jedis.sadd(key, members);
        }
    }


    public Long scard(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.scard(key);
        }
    }


    public Long scard(byte[] key) {
        try (Jedis jedis = getJedis()) {
            return jedis.scard(key);
        }
    }


    public Long srem(String key, String... members) {
        try (Jedis jedis = getJedis()) {
            return jedis.srem(key, members);
        }
    }


    public Long srem(byte[] key, byte[]... members) {
        try (Jedis jedis = getJedis()) {
            return jedis.srem(key, members);
        }
    }


    public Set<String> smembers(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.smembers(key);
        }
    }


    public Boolean sismember(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.sismember(key, member);
        }
    }


    public Boolean sismember(byte[] key, byte[] member) {
        try (Jedis jedis = getJedis()) {
            return jedis.sismember(key, member);
        }
    }


    public String spop(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.spop(key);
        }
    }


    public byte[] spop(byte[] key) {
        try (Jedis jedis = getJedis()) {
            return jedis.spop(key);
        }
    }


    public Long zadd(String key, double score, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zadd(key, score, member);
        }
    }


    public Long zadd(byte[] key, double score, byte[] member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zadd(key, score, member);
        }
    }


    public Long zadd(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = getJedis()) {
            return jedis.zadd(key, scoreMembers);
        }
    }


    public Long zrem(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrem(key, member);
        }
    }


    public Long zrem(byte[] key, byte[] member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrem(key, member);
        }
    }


    public Long zRemRangeByRank(String key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zremrangeByRank(key, start, end);
        }
    }


    public Long zRemRangeByRank(byte[] key, long start, long end) {
        try (Jedis jedis = getJedis()) {
            return jedis.zremrangeByRank(key, start, end);
        }
    }


    public Long zremrangeByScore(String key, long min, long max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zremrangeByScore(key, min, max);
        }
    }


    public Long zrank(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrank(key, member);
        }
    }


    public Long zrank(byte[] key, byte[] member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrank(key, member);
        }
    }


    public Long zrevrank(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrank(key, member);
        }
    }


    public Long zrevrank(byte[] key, byte[] member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrevrank(key, member);
        }
    }


    public Set<String> zrange(String key, long min, long max) {
        List<String> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrange(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<byte[]> zrange(byte[] key, long min, long max) {
        List<byte[]> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrange(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<String> zrevrange(String key, long min, long max) {
        List<String> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrange(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<byte[]> zrevrange(byte[] key, long min, long max) {
        List<byte[]> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrange(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrangeWithScores(String key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrangeWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrangeWithScores(byte[] key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrangeWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrangeByScoreWithScores(String key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrangeByScoreWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<String> zrevrangebyscore(String key, double max, double min, int offset, int count) {
        List<String> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrangeByScore(key, max, min, offset, count);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public List<String> zrangeByScore(String key, long min, long max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zrangeByScore(key, min, max);
        }
    }


    public Set<Tuple> zrangeByScoreWithScores(byte[] key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrangeByScoreWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrevrangeWithScores(String key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrangeWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrevrangeWithScores(byte[] key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrangeWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrevrangeByScoreWithScores(String key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrangeByScoreWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, long min, long max) {
        List<Tuple> result = null;
        try (Jedis jedis = getJedis()) {
            result = jedis.zrevrangeByScoreWithScores(key, min, max);
        }
        if (Objects.nonNull(result) && !result.isEmpty()) {
            return new HashSet<>(result);
        }
        return null;
    }


    public Double zincrby(String key, double incrScore, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zincrby(key, incrScore, member);
        }
    }


    public Double zincrby(byte[] key, double incrScore, byte[] member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zincrby(key, incrScore, member);
        }
    }


    public Double zscore(String key, String member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zscore(key, member);
        }
    }


    public Double zscore(byte[] key, byte[] member) {
        try (Jedis jedis = getJedis()) {
            return jedis.zscore(key, member);
        }
    }


    public Long zcard(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.zcard(key);
        }
    }


    public Long zcard(byte[] key) {
        try (Jedis jedis = getJedis()) {
            return jedis.zcard(key);
        }
    }


    public Long zcount(String key, double min, double max) {
        try (Jedis jedis = getJedis()) {
            return jedis.zcount(key, min, max);
        }
    }


    public Long hincrBy(String key, String field, long value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(field);
        try (Jedis jedis = getJedis()) {
            return jedis.hincrBy(key, field, value);
        }
    }


    public String hmset(String key, Map<String, String> hash) {
        try (Jedis jedis = getJedis()) {
            return jedis.hmset(key, hash);
        }
    }


    public List<String> hmget(String key, String... fields) {
        try (Jedis jedis = getJedis()) {
            return jedis.hmget(key, fields);
        }
    }


    public Long decr(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.decr(key);
        }
    }


    public Long decrBy(String key, long decrement) {
        try (Jedis jedis = getJedis()) {
            return jedis.decrBy(key, decrement);
        }
    }

    /**
     * 获取所有匹配模式的键
     *
     * @param pattern 模式
     * @return 匹配的键集合
     */
    public Set<String> getKeysByPattern(String pattern) {
        try (Jedis jedis = getJedis()) {
            return jedis.keys(pattern);
        }
    }

    /**
     * 获取键的类型
     *
     * @param key 键
     * @return 键的类型
     */
    public String getKeyType(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.type(key);
        }
    }

    /**
     * 获取列表的长度
     *
     * @param key 键
     * @return 列表的长度
     */
    public Long getListLength(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.llen(key);
        }
    }

    /**
     * 获取键的剩余生存时间（毫秒）
     *
     * @param key 键
     * @return 剩余生存时间（毫秒）
     */
    public Long pttl(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.pttl(key);
        }
    }

    /**
     * 设置键的值并指定过期时间（毫秒）
     *
     * @param key 键
     * @param value 值
     * @param milliseconds 过期时间（毫秒）
     * @return 设置结果
     */
    public String psetex(String key, long milliseconds, String value) {
        try (Jedis jedis = getJedis()) {
            return jedis.psetex(key, milliseconds, value);
        }
    }

    /**
     * 获取集合中的一个随机成员
     *
     * @param key 键
     * @return 随机成员
     */
    public String srandmmber(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.srandmember(key);
        }
    }

    /**
     * 获取集合中的多个随机成员
     *
     * @param key 键
     * @param count 成员数量
     * @return 随机成员集合
     */
    public List<String> srandmember(String key, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.srandmember(key, count);
        }
    }


    /**
     * 添加条目到流
     *
     * @param key 流的键
     * @param fields 字段和值的映射
     * @return 新条目的ID
     */
    public StreamEntryID xadd(String key, Map<String, String> fields) {
        try (Jedis jedis = getJedis()) {
            return jedis.xadd(key, StreamEntryID.NEW_ENTRY, fields);
        }
    }

    /**
     * 读取流中的条目
     *
     * @param key 流的键
     * @param start 开始ID
     * @param end 结束ID
     * @param count 条目数量
     * @return 条目列表
     */
    public List<StreamEntry> xread(String key, StreamEntryID start, StreamEntryID end, int count) {
        try (Jedis jedis = getJedis()) {
            return jedis.xrange(key, start, end, count);
        }
    }

    /**
     * 删除流中的条目
     *
     * @param key 流的键
     * @param ids 条目ID数组
     * @return 删除的条目数量
     */
    public Long xdel(String key, StreamEntryID... ids) {
        try (Jedis jedis = getJedis()) {
            return jedis.xdel(key, ids);
        }
    }

    /**
     * 修剪流到指定长度
     *
     * @param key 流的键
     * @param maxLen 最大长度
     * @return 修剪的条目数量
     */
    public Long xtrim(String key, long maxLen) {
        try (Jedis jedis = getJedis()) {
            return jedis.xtrim(key, maxLen, true);
        }
    }

    /**
     * 获取流的长度
     *
     * @param key 流的键
     * @return 流的长度
     */
    public Long xlen(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.xlen(key);
        }
    }


    /**
     * 获取流的最后一个条目
     *
     * @param key 流的键
     * @return 最后一个条目
     */
    public StreamEntry xlast(String key) {
        try (Jedis jedis = getJedis()) {
            List<StreamEntry> entries = jedis.xrevrange(key, (StreamEntryID) null, null,1);
            return entries.isEmpty() ? null : entries.get(0);
        }
    }

}
