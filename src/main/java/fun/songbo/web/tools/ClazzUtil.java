package fun.songbo.web.tools;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2023-08-10 13:44
 * @since
 */
public class ClazzUtil {
    // 判断一个类是否基本类型的包装类型

    public static boolean isWrapClass(Class clz) {

        try {
            boolean bool = clz.getName().equals("java.lang.String");
            if (bool) {
                return true;
            }
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
