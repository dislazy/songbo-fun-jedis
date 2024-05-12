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

    /**
     * 检查字符串是否包含子字符串
     *
     * @param str       要检查的字符串
     * @param substring 子字符串
     * @return 如果包含子字符串，则返回true；否则返回false
     */
    public static boolean contains(String str, String substring) {
        return StringUtils.contains(str, substring);
    }

    /**
     * 检查字符串是否以指定前缀开头
     *
     * @param str    要检查的字符串
     * @param prefix 前缀
     * @return 如果以指定前缀开头，则返回true；否则返回false
     */
    public static boolean startsWith(String str, String prefix) {
        return StringUtils.startsWith(str, prefix);
    }

    /**
     * 检查字符串是否以指定后缀结尾
     *
     * @param str    要检查的字符串
     * @param suffix 后缀
     * @return 如果以指定后缀结尾，则返回true；否则返回false
     */
    public static boolean endsWith(String str, String suffix) {
        return StringUtils.endsWith(str, suffix);
    }

    /**
     * 将字符串的首字母大写
     *
     * @param str 要转换的字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalize(String str) {
        return StringUtils.capitalize(str);
    }

    /**
     * 将字符串的首字母小写
     *
     * @param str 要转换的字符串
     * @return 首字母小写后的字符串
     */
    public static String uncapitalize(String str) {
        return StringUtils.uncapitalize(str);
    }

    /**
     * 反转字符串
     *
     * @param str 要反转的字符串
     * @return 反转后的字符串
     */
    public static String reverse(String str) {
        return StringUtils.reverse(str);
    }

    /**
     * 重复字符串指定次数
     *
     * @param str     要重复的字符串
     * @param repeat  重复次数
     * @return 重复后的字符串
     */
    public static String repeat(String str, int repeat) {
        return StringUtils.repeat(str, repeat);
    }

    /**
     * 检查字符串是否为回文
     *
     * @param str 要检查的字符串
     * @return 如果字符串是回文，则返回true；否则返回false
     */
    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    /**
     * 统计字符在字符串中出现的次数
     *
     * @param str 要检查的字符串
     * @param ch  要统计的字符
     * @return 字符出现的次数
     */
    public static int countOccurrences(String str, char ch) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    /**
     * 移除字符串中的所有空白字符
     *
     * @param str 要处理的字符串
     * @return 移除空白字符后的字符串
     */
    public static String removeWhitespace(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("\\s+", "");
    }

    /**
     * 缩写字符串到指定长度
     *
     * @param str    要缩写的字符串
     * @param length 缩写后的长度
     * @return 缩写后的字符串
     */
    public static String abbreviate(String str, int length) {
        if (str == null) {
            return null;
        }
        return StringUtils.abbreviate(str, length);
    }
}
