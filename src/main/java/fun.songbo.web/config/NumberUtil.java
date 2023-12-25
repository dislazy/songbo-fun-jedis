package fun.songbo.web.config;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2023-12-22 21:10
 * @since
 */
public class NumberUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberUtil.class);
    public static final BigDecimal RATE_USD = BigDecimal.valueOf(6.8);
    static Random random = new Random();
    static NumberFormat numberFormat = NumberFormat.getNumberInstance();


    public static int randDom(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static BigDecimal strToBigDecimal(String str) throws ParseException {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return null;
        } else {
            str = str.toUpperCase().trim();
            if (ArrayUtils.contains(new String[]{"非公开", "-", "--", "- -", "---"}, str)) {
                return null;
            } else {
                char[] chars = str.toCharArray();
                int coefficient = 1;
                int subIndex = 0;
                char[] var4 = chars;
                int var5 = chars.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    char c = var4[var6];
                    if (!Character.isDigit(c) && !ArrayUtils.contains(new char[]{',', '.'}, c)) {
                        switch (c) {
                            case ' ':
                                break;
                            case 'M':
                                coefficient *= 1000000;
                                break;
                            case '万':
                                coefficient *= 10000;
                                break;
                            case '亿':
                                coefficient *= 100000000;
                                break;
                            case '十':
                                coefficient *= 10;
                                break;
                            case '千':
                                coefficient *= 1000;
                                break;
                            case '百':
                                coefficient *= 100;
                                break;
                            default:
                                throw new NumberFormatException("Unsupported string conversion!");
                        }
                    } else {
                        ++subIndex;
                    }
                }

                return BigDecimal.valueOf(numberFormat.parse(new String(ArrayUtils.subarray(chars, 0, subIndex))).doubleValue() * (double)coefficient);
            }
        }
    }


    public static BigDecimal percentStrToBigDecimal(String str) {
        try {
            NumberFormat nf = NumberFormat.getPercentInstance();
            return BigDecimal.valueOf(nf.parse(str).doubleValue());
        } catch (Exception var2) {
            return null;
        }
    }

    public static Double strToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception var2) {
            return null;
        }
    }

    public static Integer strToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception var2) {
            return null;
        }
    }

    public static BigDecimal turnWan(BigDecimal str) {
        return turnWan(str, 6);
    }

    public static Double turnWan(Double str) {
        return turnWan(str, 6);
    }

    public static String turnWan(String str) {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return "--";
        } else {
            BigDecimal b = new BigDecimal(str);
            if (b.compareTo(BigDecimal.ZERO) == 0) {
                return "--";
            } else {
                str = b.divide(new BigDecimal(10000), 6, 4).toString();
                return str;
            }
        }
    }
    public static Double turnWan(Double str, Integer scale) {
        if (str == null) {
            return BigDecimal.ZERO.doubleValue();
        } else {
            BigDecimal b = BigDecimal.valueOf(str);
            str = b.divide(new BigDecimal(10000), 6, 4).doubleValue();
            return str;
        }
    }


    public static BigDecimal driveOneHundred(String str, Integer scale) {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return null;
        } else if ("null".equals(str)) {
            return null;
        } else {
            BigDecimal bigDecimal = null;

            try {
                bigDecimal = (new BigDecimal(str)).divide(new BigDecimal("100"), scale, 4);
            } catch (Exception var4) {
                LOGGER.error("[driveOneHundred] str: {},error :", str, var4);
            }

            return bigDecimal;
        }
    }

    public static String checkVal(String str) {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return "--";
        } else {
            BigDecimal b = new BigDecimal(str);
            return b.compareTo(BigDecimal.ZERO) == 0 ? "--" : str;
        }
    }

    public static String addPercentage(String str) {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return "--";
        } else {
            BigDecimal b = new BigDecimal(str);
            if (b.compareTo(BigDecimal.ZERO) == 0) {
                return "--";
            } else {
                str = (new BigDecimal(str)).setScale(2, 5).toString();
                return str + "%";
            }
        }
    }
}
