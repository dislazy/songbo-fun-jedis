package fun.songbo.web.tools;


import java.io.*;

/**
 * 文件工具工具类
 *
 * @author jack
 */
public class FileTool {
    private final static String SAFARI = "Safari";
    private final static String EDGE = "Edge";
    private final static String MSIE = "MSIE";
    private final static String TRIDENT = "Trident";

    private static String filePath;


    /**
     * 方法名 getFileByte
     * 参数 [file]
     * 返回值 byte[]
     * 描述 获取文件流
     */
    public static byte[] getFileByte(File file) {
        try {
            long fileSize = file.length();
            if (fileSize > Integer.MAX_VALUE) {
                return null;
            }
            FileInputStream fi = new FileInputStream(file);
            byte[] buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead;
            while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new RuntimeException("文件未读取完整");
            }
            fi.close();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败");
        }
    }


}
