package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
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


    /**
     * 获取字段值
     *
     * @param object 目标对象
     * @param fieldName 字段名称
     * @return 字段值
     */
    public static Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            log.error("[getFieldValue] error: ", e);
        }
        return null;
    }

    /**
     * 检查类是否具有指定方法
     *
     * @param clazz 类
     * @param methodName 方法名称
     * @param parameterTypes 方法参数类型
     * @return 如果类具有指定方法，则返回true；否则返回false
     */
    public static boolean hasMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        try {
            clazz.getMethod(methodName, parameterTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * 复制属性
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        try {
            Class<?> sourceClass = source.getClass();
            Class<?> targetClass = target.getClass();
            Field[] fields = sourceClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(source);
                Field targetField = targetClass.getDeclaredField(field.getName());
                targetField.setAccessible(true);
                targetField.set(target, value);
            }
        } catch (Exception e) {
            log.error("[copyProperties] error: ", e);
        }
    }

}
