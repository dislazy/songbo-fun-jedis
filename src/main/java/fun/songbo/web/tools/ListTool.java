package fun.songbo.web.tools;


import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author jack
 */
public class ListTool {
    /**
     * 函数式接口 T -> bollean
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>(16);
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void removeNullElemnet(List list) {
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            if(Objects.isNull(iterator.next())){
                iterator.remove();
            }
        }
    }

        /**
     * @Description:    数组转字符串
     */
    public static String list2String(List<String> data, String symbol){
        if(data.isEmpty()){
            return null;
        }
        return String.join(symbol, data);
    }


        /**
     * @Description:    字符串转数组
     */
    public static List<String> string2List(String data, String symbol){
        if(StringUtils.isBlank(data))
        {
            return new ArrayList<>();
        }
       return Arrays.stream(data.split(symbol)).collect(Collectors.toList());
    }

}
