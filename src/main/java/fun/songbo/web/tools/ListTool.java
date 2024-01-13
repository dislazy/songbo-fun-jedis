package fun.songbo.web.tools;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

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
        if(CollectionUtils.isEmpty(data)){
            return null;
        }
        return Joiner.on(symbol).join(data);
    }


        /**
     * @Description:    字符串转数组
     */
    public static List<String> string2List(String data, String symbol){
        if(StringUtils.isBlank(data))
        {
            return new ArrayList<>();
        }
        return Lists.newArrayList(Splitter.on(symbol)
                .trimResults()
                .omitEmptyStrings()
                .split(data));
    }

}
