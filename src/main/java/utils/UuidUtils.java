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

}
