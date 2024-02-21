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
}
