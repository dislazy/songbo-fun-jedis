package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * <p>
 * Object Base Uitls
 * </p>
 *
 * @author songbo
 * @date 2023-09-22 14:36
 * @since
 */
public class ObjectBaseUtils {

    private static final Logger log = LoggerFactory.getLogger(ObjectBaseUtils.class);

    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */

    @SuppressWarnings("unchecked")

    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            log.error("[getGetMethod] error: ",e);
        }
        return null;
    }
}
