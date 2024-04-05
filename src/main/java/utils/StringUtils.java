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

}
