package fun.songbo.web.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 消息通知
 */
public class NoticeTool {

    private static final Logger log = LoggerFactory.getLogger(NoticeTool.class);
    private static String url = "https://open.feishu.cn/open-apis/bot/v2/hook/";


    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            log.error(e.toString());
        }
        return null;

    }
}
