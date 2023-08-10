package fun.songbo.web.tools;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * <p>
 *
 * </p>
 *
 * @author jack
 * @date 2023-08-10 13:43
 * @since
 */
public class Base64ConvertTool {

    private Base64ConvertTool() {
    }

    /**
     * 加密JDK1.8
     *
     * @param str
     * @return java.lang.String
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getUrlEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * 解密JDK1.8
     *
     * @param str
     * @return java.lang.String
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getUrlDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }
}
