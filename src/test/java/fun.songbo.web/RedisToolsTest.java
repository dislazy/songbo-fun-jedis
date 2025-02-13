package fun.songbo.web;

import fun.songbo.web.commons.EXPX;
import fun.songbo.web.config.RedisConfig;
import junit.framework.TestCase;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.resps.StreamEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2022-12-04 10:55
 * @since
 */
public class RedisToolsTest extends TestCase {


    RedisTools redisTools = null;


    public void initRedisTools() {
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setRedisHost("127.0.0.1");
        redisConfig.setRedisPort(6379);
        redisConfig.setRedisPwd("test");
        //此处为需要创建的redis db实例
        redisTools =  new RedisTools(redisConfig, 5);
    }


    public void testGet(){
        initRedisTools();
        redisTools.get("test");
    }

    public void testSet(){
        initRedisTools();
        redisTools.set("","");
//        redisTools.set("","", EXPX.MILLISECONDS)
    }

    public void testMset(){
        initRedisTools();
        redisTools.hmset("abc",new HashMap<>());
    }

    public void testMget(){
        initRedisTools();
        redisTools.hmget("abc","map data");
    }

    public void testName() {
    }

    public void testZadd() {
        initRedisTools();
        Long result = redisTools.zadd("testZSet", 1.0, "member1");
        assertTrue(result > 0);
    }

    public void testZrange() {
        initRedisTools();
        Set<String> range = redisTools.zrange("testZSet", 0, -1);
        assertNotNull(range);
    }

    public void testXadd() {
        initRedisTools();
        Map<String, String> fields = new HashMap<>();
        fields.put("field1", "value1");
        StreamEntryID id = redisTools.xadd("testStream", fields);
        assertNotNull(id);
    }

    public void testXread() {
        initRedisTools();
        List<StreamEntry> entries = redisTools.xread("testStream", new StreamEntryID("0-0"), new StreamEntryID("9999999999999-0"), 10);
        assertNotNull(entries);
    }



    public void testLpush() {
        initRedisTools();
        Long result = redisTools.lpush("testList", "value1", "value2");
        assertTrue(result > 0);
    }

    public void testLpop() {
        initRedisTools();
        String value = redisTools.lpop("testList");
        assertNotNull(value);
    }

    public void testSadd() {
        initRedisTools();
        Long result = redisTools.sadd("testSet", "member1", "member2");
        assertTrue(result > 0);
    }

    public void testSmembers() {
        initRedisTools();
        Set<String> members = redisTools.smembers("testSet");
        assertNotNull(members);
    }
}