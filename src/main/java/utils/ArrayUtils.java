package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private static final Logger log = LoggerFactory.getLogger(ArrayUtils.class);

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
        return new ArrayList<>(Arrays.asList(array));
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


    /**
     * 使用分隔符分割数组
     *
     * @param arrays    数组
     * @param separator 分隔符 不配置,默认逗号
     * @return 结果
     */
    public static String join(String[] arrays, String... separator) {
        return join(asList(arrays), separator);
    }
    /**
     * 使用分隔符分割数组
     *
     * @param arrays    数组
     * @param separator 分隔符 不配置,默认逗号
     * @return 结果
     */
    public static String join(Long[] arrays, String... separator) {
        if (arrays == null || arrays.length == 0) {
            return "";
        }
        return join(asStrArray(arrays), separator);
    }

    /**
     * 使用分隔符分割数组
     *
     * @param arrays    数组
     * @param separator 分隔符 不配置,默认逗号
     * @return 结果
     */
    public static String join(Integer[] arrays, String... separator) {
        if (arrays == null || arrays.length == 0) {
            return "";
        }
        return join(asStrArray(arrays), separator);
    }

    /**
     * 使用分隔符分割List
     *
     * @param strList   list
     * @param separator 分隔符 不配置,默认逗号
     * @return 结果
     */
    public static String join(List<String> strList, String... separator) {
        if (strList == null || strList.isEmpty()) {
            return "";
        }
        String spt = ",";
        if (separator != null && separator.length > 0) {
            spt = separator[0];
        }
        String result = null;
        for (String str : strList) {
            if (str == null) {
                continue;
            }
            if (result == null) {
                result = str;
            } else {
                result += spt + str;
            }
        }
        return result;
    }

    /**
     * 字符串分割成list
     *
     * @param str   字符串
     * @param regex 分隔正则,默认逗号
     * @return list
     */
    public static List<String> split(String str, String... regex) {
        if (str == null || str.isEmpty() || " ".equals(str))  {
            return null;
        }
        String spt = ",";
        if (regex != null && regex.length > 0) {
            spt = regex[0];
        }
        return asList(str.split(spt));
    }

    /**
     * 数组末尾追加
     *
     * @param objects
     * @param object
     * @return
     */
    public static Object[] append(Object[] objects, Object object) {
        if (objects == null) {
            objects = new Object[]{};
        }
        Object[] newObjects = new Object[objects.length + 1];
        System.arraycopy(objects, 0, newObjects, 0, newObjects.length - 1);
        newObjects[objects.length] = object;
        return newObjects;
    }

    public static String[] append(String[] objects, String object) {
        if (objects == null) {
            objects = new String[]{};
        }
        String[] newObjects = new String[objects.length + 1];
        System.arraycopy(objects, 0, newObjects, 0, newObjects.length - 1);
        newObjects[objects.length] = object;
        return newObjects;
    }


    /**
     * 字符串切割成 Long 数组
     *
     * @param str   字符串
     * @param regex 切割正则，默认逗号
     * @return Long 数组
     */
    public static Long[] splitToLongs(String str, String... regex) {
        List<String> list = split(str, regex);
        if (list == null) {
            return null;
        }
        Long[] longs = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            longs[i] = getLong(list.get(i));
        }
        return longs;
    }
    /**
     * 对象转Long
     *
     * @param obj
     * @return
     */
    public static Long getLong(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return Long.valueOf(obj.toString());
        } catch (Exception e) {
            log.error("[getLong]error: ",e);
        }
        return null;
    }

    /**
     * 反转数组
     *
     * @param array 要反转的数组
     * @param <T>   数组类型
     * @return 反转后的数组
     */
    public static <T> T[] reverse(T[] array) {
        if (array == null) {
            return null;
        }
        List<T> list = Arrays.asList(array);
        Collections.reverse(list);
        return list.toArray(array);
    }

    /**
     * 查找数组中的最大元素
     *
     * @param array 要查找的数组
     * @return 数组中的最大元素
     */
    public static Integer findMax(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Integer max = array[0];
        for (Integer num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * 查找数组中的最小元素
     *
     * @param array 要查找的数组
     * @return 数组中的最小元素
     */
    public static Integer findMin(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Integer min = array[0];
        for (Integer num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}
