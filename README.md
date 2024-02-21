## 简单介绍
songbo-fun-jedis是一款基于JedisPool的动态单redisDB获取实例，执行完命令自动回收资源的简单工具，旨在让redis的接入更简单便捷。

## 基本原理
利用JedisPool的池化技术，做单个DB的使用，并且简化jedis的命令回收。

## 引入songbo-fun-jedis使用redisClient（手工配置）

### 引入依赖
```
<dependency>
  <groupId>songbo.fun.app</groupId>
  <artifactId>songbo-fun-jedis</artifactId>
  <version>{maven仓库中最新版本即可}</version>
</dependency>
#如果不生效可以引用以下的包
<dependency>
    <groupId>org.apache.commons</groupId>
	<artifactId>commons-pool2</artifactId>
	<version>2.11.1</version>
</dependency>
```

### 初始化对象

```
@Configuration
public class RedisConfiguration {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port}")
    private int port;
    @Value(value = "${spring.redis.password}")
    private String password;
    //需要实例化的db列表(必须包含默认db)
    @Value(value = "${spring.redis.indexes}")
    private String indexes;
    //默认db
    @Value(value = "${spring.redis.default.index}")
    private int defaultIndex;

    @Bean("redisClient")
    public RedisClient redisClient() {
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setRedisHost("127.0.0.1");
        redisConfig.setRedisPort(6379);
        redisConfig.setRedisPwd("test");
         //此处为需要创建的redis db实例
        return new RedisClient(redisConfig, index);
    }
}
```

## 使用RedisClient

```
    @Autowired
    private RedisClient redisClient;
     //使用默认的db相关方法
    //String data = redisClient.get(redisKey);
    //get指定db相关方法
    //String data = redisClient.get(redisKey);
```
感谢jetbeans的授权
