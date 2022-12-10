package fun.songbo.web.config;

/**
 * @author Jack
 */
public class RedisConfig {

    /**
     * host地址
     */
    private String redisHost = "127.0.0.1";

    /**
     * 端口号
     */
    private int redisPort = 6379;
    /**
     * 密码
     */
    private String redisPwd;
    /**
     * 资源池中的最大连接数
     *
     * maxTotal（最大连接数）
     *
     * 想合理设置maxTotal（最大连接数）需要考虑的因素较多，如：
     *
     * 业务希望的Redis并发量；
     * 客户端执行命令时间；
     * Redis资源，例如nodes （如应用节点个数等） * maxTotal不能超过Redis的最大连接数（可在实例详情页面查看）；
     * 资源开销，例如虽然希望控制空闲连接，但又不希望因为连接池中频繁地释放和创建连接造成不必要的开销。
     * 假设一次命令时间，即borrow|return resource加上Jedis执行命令 （ 含网络耗时）的平均耗时约为1ms，一个连接的QPS大约是1s/1ms = 1000，而业务期望的单个Redis的QPS是50000（业务总的QPS/Redis分片个数），那么理论上需要的资源池大小（即MaxTotal）是50000 / 1000 = 50。
     *
     * 但事实上这只是个理论值，除此之外还要预留一些资源，所以maxTotal可以比理论值大一些。这个值不是越大越好，一方面连接太多会占用客户端和服务端资源，另一方面对于Redis这种高QPS的服务器，如果出现大命令的阻塞，即使设置再大的资源池也无济于事。
     *
     */
    private int maxTotal = 1024;
    /**
     * 资源池允许的最大空闲连接数
     *
     * maxIdle与minIdle
     *
     * maxIdle实际上才是业务需要的最大连接数，maxTotal 是为了给出余量，所以 maxIdle 不要设置得过小，否则会有new Jedis（新连接）开销，而minIdle是为了控制空闲资源检测。
     *
     * 连接池的最佳性能是maxTotal=maxIdle，这样就避免了连接池伸缩带来的性能干扰。如果您的业务存在突峰访问，建议设置这两个参数的值相等；如果并发量不大或者maxIdle设置过高，则会导致不必要的连接资源浪费。
     *
     * 您可以根据实际总QPS和调用Redis的客户端规模整体评估每个节点所使用的连接池大小。
     */
    private int maxIdle = 8;
    /**
     * 资源池确保的最少空闲连接数
     */
    private int minIdle = 1;
    /**
     * 当资源池用尽后，调用者是否要等待。只有当值为true时，下面的maxWaitMillis才会生效。
     */
    private boolean blockWhenExhausted = true;

    /**
     * 当资源池连接用尽后，调用者的最大等待时间（单位为毫秒）。-1 代表永不过时直到获取链接;默认为10S
     */
    private int maxWaitMillis = 10000;
    /**
     * 连接超时又是读写超时
     */
    private int timeout = 20000;
    /**
     * 向资源池借用连接时是否做连接有效性检测（ping）。检测到的无效连接将会被移除。	 业务量很大时候建议设置为false，减少一次ping的开销。
     */
    private boolean testOnBorrow = false;
    /**
     * 向资源池归还连接时是否做连接有效性检测（ping）。检测到无效连接将会被移除。	业务量很大时候建议设置为false，减少一次ping的开销。
     */
    private boolean testOnReturn = false;
    /**
     * 默认false，create的时候检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取
     */
    private boolean testOnCreate = false;
    /**
     * 是否在空闲资源监测时通过ping命令监测连接有效性，无效连接将被销毁。	建议开启
     */
    private boolean testWhileIdle = false;
    /**
     * 是否开启JMX监控	建议开启，请注意应用本身也需要开启。
     */
    private boolean jmxEnabled = true;
    /**
     * 空闲资源的检测周期（单位为毫秒）	建议设置，周期自行选择，可考虑为30000 即30S。
     */
    private Long timeBetweenEvictionRuns = 30000L;
    /**
     * 资源池中资源的最小空闲时间（单位为毫秒），达到此值后空闲资源将被移除。	可根据自身业务决定，一般默认值即可，也可考虑为60000。
     */
    private Long minEvictableIdleTimeMillis = 60000L;
    /**
     * 做空闲资源检测时，每次检测资源的个数。	可根据自身应用连接数进行微调，如果设置为 -1，就是对所有连接做空闲监测。
     */
    private int numTestsPerEvictionRun;


    public RedisConfig() {
    }

    public RedisConfig(String redisHost, int redisPort, String redisPwd) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.redisPwd = redisPwd;
    }

    public RedisConfig(String redisHost, int redisPort, String redisPwd, int maxTotal, int maxIdle, int minIdle, int maxWaitMillis, int timeout) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.redisPwd = redisPwd;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.minIdle = minIdle;
        this.maxWaitMillis = maxWaitMillis;
        this.timeout = timeout;
    }

    public RedisConfig(String redisHost, int redisPort, String redisPwd, int maxTotal, int maxIdle, int minIdle, boolean blockWhenExhausted, int maxWaitMillis, int timeout, boolean testOnBorrow, boolean testOnReturn, boolean testOnCreate, boolean testWhileIdle, boolean jmxEnabled, Long timeBetweenEvictionRuns, Long minEvictableIdleTimeMillis, int numTestsPerEvictionRun) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.redisPwd = redisPwd;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.minIdle = minIdle;
        this.blockWhenExhausted = blockWhenExhausted;
        this.maxWaitMillis = maxWaitMillis;
        this.timeout = timeout;
        this.testOnBorrow = testOnBorrow;
        this.testOnReturn = testOnReturn;
        this.testOnCreate = testOnCreate;
        this.testWhileIdle = testWhileIdle;
        this.jmxEnabled = jmxEnabled;
        this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }



}
