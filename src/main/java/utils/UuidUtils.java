package utils;

import java.util.UUID;

/**
 * <p>
 * Uuid utils
 * </p>
 *
 * @author songbo
 * @date 2023-08-05 14:34
 * @since
 */
public class UuidUtils {
    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    /**
     * 生成带有连字符的UUID
     *
     * @return 带有连字符的UUID
     */
    public static String UUIDWithDashes() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成短UUID
     *
     * @return 短UUID
     */
    public static String ShortUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }


    /**
     * 验证UUID
     *
     * @param uuid 要验证的UUID字符串
     * @return 如果字符串是有效的UUID，则返回true；否则返回false
     */
    public static boolean isValidUUID(String uuid) {
        if (uuid == null) {
            return false;
        }
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
