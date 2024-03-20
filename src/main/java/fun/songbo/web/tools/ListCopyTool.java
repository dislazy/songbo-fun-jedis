package fun.songbo.web.tools;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * @author jack
 */
public class ListCopyTool {

    static Random random = new Random();
    /**
     * 本地格式化的解析器
     */
    static NumberFormat numberFormat = NumberFormat.getNumberInstance();
    /**
     * 验证码的数据范围
     */
    static String str = "abcedfghijklmnopqrstuvwxyz1234567890";

    public static final BigDecimal RATE_USD = BigDecimal.valueOf(6.8);

    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, 10);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = BigDecimal.valueOf(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 计算同比、环比 = (分子-分母)*100 / 分母 = 百分比
     * @param molecular 分子
     * @param denominator 分母
     * @return 除了%号之外的百分比
     */
    public static Double initDoubleValue(long molecular, long denominator) {
        return denominator == 0L ? null :
                new BigDecimal((molecular - denominator) * 100)
                        .divide(new BigDecimal(denominator), 2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 组装对应的value
     * @param value 真实数据
     * @return 0 或者 真实数据
     */
    public static Long initCountValue(Long value) {
        return Objects.isNull(value) ? 0L : value;
    }



    /**
     * 生成随机邮件验证码
     *
     * @param codeLength
     * @return
     */
    public static String randomEmailCode(Integer codeLength) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int length = str.length();
        for (int i = 0; i < codeLength; i++) {
            int number = random.nextInt(length);
            builder.append(str.charAt(number));
        }
        return builder.toString();
    }

    /**
     * 根据最大值与最小值随机获取
     *
     * @param min
     * @param max
     * @return
     */
    public static int randDom(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }


    /**
     * 字符串转金额,可以处理金额 如 5000万,15亿,1.4 亿
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static BigDecimal strToBigDecimal(String str) throws ParseException {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //统一成大写形式
        str = str.toUpperCase().trim();
        if (ArrayUtils.contains(new String[]{"非公开", "-", "--", "- -", "---"}, str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        int coefficient = 1;
        int subIndex = 0;
        for (char c : chars) {
            if (Character.isDigit(c) || ArrayUtils.contains(new char[]{',', '.'}, c)) {
                subIndex++;
                continue;
            } else {
                switch (c) {
                    case '十':
                        coefficient *= 10;
                        break;
                    case '百':
                        coefficient *= 100;
                        break;
                    case '千':
                        coefficient *= 1000;
                        break;
                    case '万':
                        coefficient *= 10000;
                        break;
                    case 'M':
                        coefficient *= 1000000;
                        break;
                    case '亿':
                        coefficient *= 100000000;
                        break;
                    case ' ':
                        break;
                    default:
                        throw new NumberFormatException("Unsupported string conversion!");
                }
            }
        }
        return BigDecimal.valueOf(numberFormat.parse(new String(ArrayUtils.subarray(chars, 0, subIndex))).doubleValue() * coefficient);
    }

    /**
     * 百分比数字转 BigDecimal
     *
     * @param str
     * @return
     */
    public static BigDecimal percentStrToBigDecimal(String str) {
        try {
            NumberFormat nf = NumberFormat.getPercentInstance();
            return BigDecimal.valueOf(nf.parse(str).doubleValue());
        } catch (Exception e) {
            return null;
        }
    }

    public static Double strToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer strToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 元->万元
     *
     * @param str
     * @return java.math.BigDecimal
     */
    public static BigDecimal turnWan(BigDecimal str) {
        return turnWan(str, 6);
    }


    public static Double turnWan(Double str) {
        return turnWan(str, 6);
    }

    public static String turnWan(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = b.divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    public static String roundHalfUp(String str, Integer scale) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    public static String roundHalfUp(Double str, Integer scale) {
        if (str == null) {
            return "--";
        }
        BigDecimal b = BigDecimal.valueOf(str);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal turnWan(BigDecimal str, Integer scale) {
        if (str == null) {
            return BigDecimal.ZERO;
        }
        str = str.divide(new BigDecimal(10000), scale, BigDecimal.ROUND_HALF_UP);
        return str;
    }
    public static Double turnWan(Double str, Integer scale) {
        if (str == null) {
            return BigDecimal.ZERO.doubleValue();
        }
        BigDecimal b = BigDecimal.valueOf(str);
        str = b.divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return str;
    }

    public static String turnWan(String str, Integer scale) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = b.divide(new BigDecimal(10000), scale, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    /**
     * 校验值，为空返回 --
     */
    public static String checkVal(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        return str;
    }

    /**
     * 校验参数保留两位小数，添加%
     */
    public static String addPercentage(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
        return str + "%";
    }

    /**
     * 保留两位小数
     * @param content
     * @return
     */
    public static Object twoPoint(String content) {
        if (StringUtils.isBlank(content) || !NumberUtils.isCreatable(content)) {
            return null;
        }
        return new BigDecimal(content).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    /**
     * 校验参数，百分比换算
     */
    public static String toPercentage(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        return b.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP) + "%";
    }

}