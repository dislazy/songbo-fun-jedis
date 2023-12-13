package fun.songbo.web.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

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


    /**
     * 获取当前日期
     */
    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SQL_DATE);
        return LocalDate.now().format(formatter);
    }

    /**
     * 获取当前日期
     */
    public static String getCurrentDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.now().format(formatter);
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SQL_TIME);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentDateTime(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * Date转LocalDateTime
     * 使用系统时区
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime转Date
     * 使用系统时区
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * LocalDateTime日期转字符串
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTimeToStr(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    public static String localDateToStr(LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    /**
     * LocalDateTime日期转字符串
     *
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToStr(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SQL_DATE);
        return localDateTime.format(formatter);
    }

    /**
     * Date日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * Date字符串转字符串
     *
     * @param str
     * @return
     */
    public static String strToStr(String str) {
        Date date = strToDate(str);
        SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE);
        return sdf.format(date);
    }

    /**
     * Date日期转字符串
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SQL_TIME);
        return sdf.format(date);
    }

    /**
     * 字符转转Date日期
     *
     * @param dateStr
     * @return
     */
    public static Date strToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将字符串日期解析为java.time.LocalDateTime
     *
     * @param dateTimeStr
     * @param pattern
     * @return
     */
    public static LocalDateTime strToLocalDateTime(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     * 开始日期，补齐" 00:00:00"
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getStartDateTimeWithHMS(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 结束日期，补齐" 23:59:59"
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getEndDateWithHMS(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
    }


    public static LocalDateTime getAfterYears(LocalDateTime localDateTime, int count) {
        return localDateTime.plusYears(count);
    }
    public static LocalDateTime getAfterMonths(LocalDateTime localDateTime, int count) {
        return localDateTime.plusMonths(count);
    }

    public static LocalDate getAfterMonths(LocalDate localDate, int count) {
        return localDate.plusMonths(count);
    }

    public static String getBeforeMonths(String monthStr, int count) {
        monthStr += "-01";
        return localDateToStr(getAfterMonths(LocalDate.parse(monthStr), -count), SQL_MONTH);
    }

    public static LocalDateTime getAfterDays(LocalDateTime localDateTime, int count) {
        return localDateTime.plusDays(count);
    }
    public static Date getAfterDays(Date date, int count) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, count);
        return calendar.getTime();
    }

    public static LocalDateTime getAfterMinutes(LocalDateTime localDateTime, int count) {
        return localDateTime.plusMinutes(count);
    }

    public static LocalDateTime getAfterSeconds(LocalDateTime localDateTime, int count) {
        return localDateTime.plusSeconds(count);
    }
    /**
     * 获得当前年的第一天
     *
     * @param
     * @return
     */
    public static LocalDateTime getYearFirstDay(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.firstDayOfYear());
    }


    /**
     * 获得当前年的最后一天
     *
     * @param
     * @return
     */
    public static LocalDateTime getYearLastDay(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.lastDayOfYear());
    }


    /**
     * 获得当前月的第一天
     *
     * @param
     * @return
     */
    public static LocalDateTime getMonthFirstDay(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获得当前月的最后一天
     *
     * @param
     * @return
     */
    public static LocalDateTime getMonthLastDay(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获得当前星期的第一天
     *
     * @param localDateTime
     * @param locale        默认Locale.CHINA 周日为一周的第一天
     * @return
     */
    public static LocalDateTime getWeekFirstDay(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.with(WeekFields.of(locale == null ? Locale.CHINA : locale).dayOfWeek(), 1);
    }
    /**
     * 获得当前星期的最后一天
     *
     * @param localDateTime
     * @param locale        默认默认Locale.CHINA 周日为一周的第一天
     * @return
     */
    public static LocalDateTime getWeekLastDay(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.with(WeekFields.of(locale == null ? Locale.CHINA : locale).dayOfWeek(), 7);
    }


    /**
     * 计算两个日期之间相差年数
     *
     * @param smallDateTime 较小的时间
     * @param bigDateTime   较大的时间
     * @return 相差年数
     */
    public static int getYearDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
        return (int) smallDateTime.until(bigDateTime, ChronoUnit.YEARS);
    }

    /**
     * 计算两个日期之间相差月数
     *
     * @param smallDateTime 较小的时间
     * @param bigDateTime   较大的时间
     * @return 相差月数
     */
    public static int getMonthDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
        return (int) smallDateTime.until(bigDateTime, ChronoUnit.MONTHS);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smallDateTime 较小的时间
     * @param bigDateTime   较大的时间
     * @return 相差天数
     */
    public static int getDayDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
        return (int) smallDateTime.until(bigDateTime, ChronoUnit.DAYS);
    }

    /**
     * 计算两个日期之间相差小时数
     *
     * @param smallDateTime 较小的时间
     * @param bigDateTime   较大的时间
     * @return 相差小时数
     */
    public static int getHourDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
        return (int) smallDateTime.until(bigDateTime, ChronoUnit.HOURS);
    }

    /**
     * 计算两个日期之间相差分钟数
     *
     * @param smallDateTime
     * @param bigDateTime
     * @return 相差分钟数
     */
    public static int getMinutesDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
        return (int) smallDateTime.until(bigDateTime, ChronoUnit.MINUTES);
    }

    /**
     * 计算两个日期之间相差秒数
     *
     * @param smallDateTime
     * @param bigDateTime
     * @return 相差秒数
     */
    public static int getSecondsDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
        return (int) smallDateTime.until(bigDateTime, ChronoUnit.SECONDS);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }
    /**
     * 时间时期转换为豪秒
     *
     * @param text
     * @return
     */
    public static long stringToMilliSecond(String text) {
        long milli = 0;
        try {
            Date date = strToDate(text);
            if (date == null) {
                return 0;
            }
            LocalDateTime localDateTime = dateToLocalDateTime(date);
            milli = LocalDateTime.from(localDateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        } catch (Exception e) {
            return 0;
        }
        return milli;
    }


    /**
     * 一个月对应的毫秒数
     *
     * @return
     */
    public static long getMonth() {
        return 60 * 60 * 24 * 30 * 1000L;
    }

    /**
     * 计算两个日期之间相差天数，结果保留2位小数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public static Double diffBetweenDays(String start, String end) {
        double milli = 0;
        try {
            // 字符串转换日期
            Date dateStart = strToDate(start);
            Date dateEnd = strToDate(end);
            if (dateStart == null || dateEnd == null) {
                return 0.0;
            }
            // 日期转换为 localdate
            LocalDateTime localDateTimeStart = dateToLocalDateTime(dateStart);
            LocalDateTime localDateTimeEnd = dateToLocalDateTime(dateEnd);
            // 计算相差天数
            int dayDiff = getDayDiff(localDateTimeStart, localDateTimeEnd);
            milli = (double) dayDiff / 30;
        } catch (Exception e) {
            return 0.0;
        }
        return milli;
    }

    /**
     * 获取日期开始时间
     *
     * @param dateRangeBeginType
     * @param now
     * @return
     */
    public static String getDateRangeBegin(Integer dateRangeBeginType, LocalDate now) {
        // 0-全部，1-1M，2-3M,3-6M，4-YTD，5-1Y，默认0-全部
        if (Objects.isNull(dateRangeBeginType) || dateRangeBeginType == 0) {
            return null;
        }
        int count;
        switch (dateRangeBeginType) {
            case 1:
                count = -1;
                break;
            case 2:
                count = -3;
                break;
            case 3:
                count = -6;
                break;
            case 4:
                count = (int) now.until(LocalDate.of(now.getYear(), 1, 1), ChronoUnit.MONTHS);
                break;
            case 5:
                count = -12;
                break;
            default:
                return null;
        }
        return now.plusMonths(count).toString();
    }

    /**
     * 字符串转 LocalDate
     * @param content
     * @return
     */
    public static LocalDate strToLocalDate(String content) {
        return LocalDate.parse(content, DateTimeFormatter.ofPattern(SQL_DATE));
    }

}

