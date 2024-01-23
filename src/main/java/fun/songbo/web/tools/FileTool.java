package fun.songbo.web.tools;


import com.bigonelab.dashboard.common.constants.BusinessConstants;
import com.bigonelab.dashboard.common.enums.ResponseCodeEnum;
import com.bigonelab.dashboard.common.exception.SystemException;
import com.bigonelab.dashboard.common.message.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件工具工具类
 *
 * @author vincent@bigonelab.com
 */
@Component
@Slf4j
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
                throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_ERROR);
            }
            fi.close();
            return buffer;
        } catch (IOException e) {
            throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_ERROR, e);
        }
    }

        /**
     * 方法名 saveFile
     * 参数 [file]
     * 返回值 void
     * 描述 保存文件
     */
    public static void saveFile(MultipartFile file, String fileSrc) {
        try {
            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            File newFile = new File(filePath + File.separator + fileSrc);
//            if (!newFile.exists()) {
//                newFile.createNewFile();
//            }
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_ERROR, e);
        }
    }
}
