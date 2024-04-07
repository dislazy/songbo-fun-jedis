package utils;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2024-04-05 14:27
 * @since
 */
public class StringUtils {

    /**
     * 判断字符串是否为空或仅包含空白字符
     *
     * @param str 要检查的字符串
     * @return 如果字符串为空或仅包含空白字符，则返回true；否则返回false
     */
    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    /**
     * 判断字符串是否不为空且不包含空白字符
     *
     * @param str 要检查的字符串
     * @return 如果字符串不为空且不包含空白字符，则返回true；否则返回false
     */
    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 将字符串转换为大写
     *
     * @param str 要转换的字符串
     * @return 转换为大写后的字符串
     */
    public static String toUpperCase(String str) {
        return str != null ? str.toUpperCase() : null;
    }

    /**
     * 将字符串转换为小写
     *
     * @param str 要转换的字符串
     * @return 转换为小写后的字符串
     */
    public static String toLowerCase(String str) {
        return str != null ? str.toLowerCase() : null;
    }

    /**
     * 去除字符串两端的空白字符
     *
     * @param str 要处理的字符串
     * @return 去除两端空白字符后的字符串
     */
    public static String trim(String str) {
        return str != null ? str.trim() : null;
    }

    /**
     * 检查两个字符串是否相等
     *
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 如果两个字符串相等，则返回true；否则返回false
     */
    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    /**
     * 检查两个字符串是否忽略大小写相等
     *
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 如果两个字符串忽略大小写相等，则返回true；否则返回false
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return StringUtils.equalsIgnoreCase(str1, str2);
    }

}
