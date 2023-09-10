package fun.songbo.web.tools;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2023-09-10 13:46
 * @since
 */
public class DateUtil {
    // 数据库格式的日期
    public static final String SQL_MONTH = "yyyy-MM";
    public static final String SQL_DATE = "yyyy-MM-dd";
    public static final String SQL_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String SQL_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SS";

    // 斜杠格式的日期
    public static final String DATE = "yyyy/MM/dd";
    public static final String TIMESTAMP = "yyyy/MM/dd HH:mm:ss.SS";
    public static final String TIMESTAMP_SHORT = "yyyy/MM/dd HH:mm";
    public static final String TIME = "HH:mm:ss";
    public static final String TIME_SHORT = "HH:mm";

    // 不常用日期格式
    public static final String CHINESEDATE = "yyyy年MM月dd日";
    public static final String DATE_TIME = "yyyyMMddHHmmss";
    public static final String DATE_TIME_DETAIL = "yyyyMMddHHmmssSS";
    public static final String DATE_DAY = "yyyyMMdd";
    public static final String DATE_HOUR = "yyyyMMddHH";
}
