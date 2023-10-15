package fun.songbo.web.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
}
