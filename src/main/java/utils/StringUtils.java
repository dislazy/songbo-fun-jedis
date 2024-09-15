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

    /**
     * 移除字符串中的指定字符
     *
     * @param str 要处理的字符串
     * @param ch  要移除的字符
     * @return 移除指定字符后的字符串
     */
    public static String removeChar(String str, char ch) {
        if (str == null) {
            return null;
        }
        return str.replace(String.valueOf(ch), "");
    }

    /**
     * 检查字符串是否只包含数字
     *
     * @param str 要检查的字符串
     * @return 如果字符串只包含数字，则返回true；否则返回false
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 反转字符串中的单词顺序
     *
     * @param str 要处理的字符串
     * @return 反转单词顺序后的字符串
     */
    public static String reverseWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i != 0) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    /**
     * 将字符串转换为标题大小写
     *
     * @param str 要转换的字符串
     * @return 转换为标题大小写后的字符串
     */
    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return titleCase.toString().trim();
    }

    /**
     * 检查字符串是否为有效的电子邮件地址
     *
     * @param str 要检查的字符串
     * @return 如果字符串是有效的电子邮件地址，则返回true；否则返回false
     */
    public static boolean isValidEmail(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return str.matches(emailRegex);
    }

    /**
     * 移除字符串中的所有非字母数字字符
     *
     * @param str 要处理的字符串
     * @return 移除非字母数字字符后的字符串
     */
    public static String removeNonAlphanumeric(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("[^A-Za-z0-9]", "");
    }

    /**
     * 检查字符串是否忽略大小写为回文
     *
     * @param str 要检查的字符串
     * @return 如果字符串忽略大小写是回文，则返回true；否则返回false
     */
    public static boolean isPalindromeIgnoreCase(String str) {
        if (str == null) {
            return false;
        }
        String lowerStr = str.toLowerCase();
        String reversed = new StringBuilder(lowerStr).reverse().toString();
        return lowerStr.equals(reversed);
    }

    /**
     * 统计字符串中的单词数量
     *
     * @param str 要检查的字符串
     * @return 单词数量
     */
    public static int countWords(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String[] words = str.split("\\s+");
        return words.length;
    }

    /**
     * 移除字符串中的所有标点符号
     *
     * @param str 要处理的字符串
     * @return 移除标点符号后的字符串
     */
    public static String removePunctuation(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("\\p{Punct}", "");
    }

    /**
     * 将字符串转换为驼峰命名法
     *
     * @param str 要转换的字符串
     * @return 转换为驼峰命名法后的字符串
     */
    public static String toCamelCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder camelCase = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            camelCase.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1).toLowerCase());
        }
        return camelCase.toString();
    }

    /**
     * 将字符串转换为帕斯卡命名法
     *
     * @param str 要转换的字符串
     * @return 转换为帕斯卡命名法后的字符串
     */
    public static String toPascalCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder pascalCase = new StringBuilder();
        for (String word : words) {
            pascalCase.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase());
        }
        return pascalCase.toString();
    }

    /**
     * 检查字符串是否为有效的IP地址
     *
     * @param str 要检查的字符串
     * @return 如果字符串是有效的IP地址，则返回true；否则返回false
     */
    public static boolean isValidIPAddress(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String ipRegex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return str.matches(ipRegex);
    }

    /**
     * 检查字符串是否只包含字母
     *
     * @param str 要检查的字符串
     * @return 如果字符串只包含字母，则返回true；否则返回false
     */
    public static boolean isAlpha(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符串转换为交替大小写
     *
     * @param str 要转换的字符串
     * @return 转换为交替大小写后的字符串
     */
    public static String toAlternatingCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder alternatingCase = new StringBuilder();
        boolean upper = true;
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                if (upper) {
                    alternatingCase.append(Character.toUpperCase(c));
                } else {
                    alternatingCase.append(Character.toLowerCase(c));
                }
                upper = !upper;
            } else {
                alternatingCase.append(c);
            }
        }
        return alternatingCase.toString();
    }

    /**
     * 移除字符串中的重复字符
     *
     * @param str 要处理的字符串
     * @return 移除重复字符后的字符串
     */
    public static String removeDuplicates(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder noDuplicates = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (noDuplicates.indexOf(String.valueOf(c)) == -1) {
                noDuplicates.append(c);
            }
        }
        return noDuplicates.toString();
    }


    /**
     * 将字符串转换为二进制表示
     *
     * @param str 要转换的字符串
     * @return 二进制表示的字符串
     */
    public static String toBinary(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder binary = new StringBuilder();
        for (char c : str.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
        }
        return binary.toString();
    }

    /**
     * 将二进制字符串转换为文本
     *
     * @param binaryStr 要转换的二进制字符串
     * @return 转换后的文本
     */
    public static String fromBinary(String binaryStr) {
        if (binaryStr == null || binaryStr.isEmpty()) {
            return binaryStr;
        }
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binaryStr.length(); i += 8) {
            String byteStr = binaryStr.substring(i, i + 8);
            text.append((char) Integer.parseInt(byteStr, 2));
        }
        return text.toString();
    }

}
