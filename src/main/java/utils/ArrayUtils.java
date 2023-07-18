package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * array utils
 * </p>
 *
 * @author jack
 * @date 2023-06-12 14:28
 * @since
 */
public class ArrayUtils {
    /**
     * 合并数组
     *
     * @param first
     * @param rest
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> T[] merge(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            if (array == null || array.length == 0) {
                continue;
            }
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            if (array == null || array.length == 0) {
                continue;
            }
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /**
     * 数组转list List<String>
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> asList(T[] array) {
        if (array == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (T t : array) {
            list.add(t);
        }
        return list;
    }

    /**
     * 转成字符串数组
     *
     * @param arrays 数组
     * @param <T>    类型
     * @return 字符串数组
     */
    public static <T> String[] asStrArray(T[] arrays) {
        if (arrays == null) {
            return null;
        }
        String[] strs = new String[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            strs[i] = arrays[i] == null ? null : arrays[i].toString();
        }
        return strs;
    }

}
