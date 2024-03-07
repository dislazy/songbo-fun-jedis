package fun.songbo.web.tools;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author jack
 * @date 2023-04-02 10:41
 * @since
 */
public class HttpTools {

    /**
     * 驼峰字符串改为_分割
     *
     * @return
     */
    public static String camel2underscore(String camelName) {
        //先把第一个字母大写
        camelName = capitalize(camelName);
        String regex = "([A-Z][a-z]+)";
        String replacement = "$1_";
        String underscoreName = camelName.replaceAll(regex, replacement);
        //output: Pur_Order_Id_ 接下来把最后一个_去掉，然后全部改小写
        underscoreName = underscoreName.toLowerCase().substring(0, underscoreName.length() - 1);
        return underscoreName;
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return new StringBuilder(str.length())
                .append(Character.toUpperCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCase(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return new StringBuilder(str.length())
                .append(Character.toLowerCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    /**
     * 字符串数组转成字符串
     *
     * @param strs [a,b,c]
     * @return a, b, c
     */
    public static String toString(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str).append(",");
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }
    /**
     * 返回百分比数据
     *
     * @param str
     * @return
     */
    public static String percentData(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        return str.equals("nan") || str.equals("inf")  ? null :str+"%";
    }
}
