package fun.songbo.web;

import fun.songbo.web.commons.EXPX;
import fun.songbo.web.config.RedisConfig;
import junit.framework.TestCase;

import java.util.HashMap;

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
}