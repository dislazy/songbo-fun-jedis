package fun.songbo.web.tools;


import java.util.UUID;

/**
 * @author jack
 */
public class ListCopyTool {
    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

}