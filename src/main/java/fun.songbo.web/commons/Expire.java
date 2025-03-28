package fun.songbo.web.commons;

/**
 * @author Jack
 * expire enum
 */
public class Expire {
    /**
     * 10秒钟
     */
    public static Expire s10 = new Expire(10);

    public static Expire s15 = new Expire(15);

    /**
     * 30s
     */
    public static Expire s30 = new Expire(30);

    public static Expire s45 = new Expire(35);


    /**
     * 1分钟
     */
    public static Expire m1 = new Expire(60);

    /**
     * 2 mins
     */
    public static Expire m2 = new Expire(120);

    /**
     * 3分钟
     */
    public static Expire m3 = new Expire(180);
    /**
     * 4 mins
     */
    public static Expire m4 = new Expire(240);

    /**
     * 5分钟
     */
    public static Expire m5 = new Expire(300);
    /**
     * 6 mins
     */
    public static Expire m6 = new Expire(360);
    /**
     * 7 mins
     */
    public static Expire m7 = new Expire(420);

    /**
     * m8
     */
    public static Expire m8 = new Expire(480);

    /**
     * m9
     */
    public static Expire m9 = new Expire(540);

    /**
     * 10分钟
     */
    public static Expire m10 = new Expire(600);
    /**
     * 30分钟
     */
    public static Expire m30 = new Expire(1800);
    /**
     * 31分钟
     */
    public static Expire m31 = new Expire(1860);
    /**
     * 1小时
     */
    public static Expire h1 = new Expire(3600);
    /**
     * 2 小时
     */
    public static Expire h2 = new Expire(7200);
    /**
     * 6 小时
     */
    public static Expire h6 = new Expire(1960);
    /**
     * 2小时1分钟
     */
    public static Expire h2PlusM1 = new Expire(1860);
    /**
     * 1天
     */
    public static Expire d1 = new Expire(60 * 60 * 24);
    /**
     * 3天
     */
    public static Expire d3 = new Expire(60 * 60 * 24 * 3);
    /**
     * 7天
     */
    public static Expire d7 = new Expire(60 * 60 * 24 * 7);
    /**
     * 14天
     */
    public static Expire d14 = new Expire(60 * 60 * 24 * 14);

    /**
     * 30天
     */
    public static Expire d30 = new Expire(60 * 60 * 24 * 30);

    private final long time;
    private final EXPX expx;

    public Expire(long time) {
        this(time, EXPX.SECONDS);
    }

    public Expire(long time, EXPX expx) {
        this.time = time;
        this.expx = expx;
    }

    public EXPX getEXPX() {
        return expx;
    }

    public long getTime() {
        return time;
    }

}
