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

        /**
     * 删除文件
     * @param fileSrc
     */
    public static void deleteFile(String fileSrc) {
        File file = new File(filePath+fileSrc);
        if (!file.exists()) {
            log.warn("物理文件{}不存在，删除失败!",fileSrc);
        }
        file.delete();
        log.warn("物理文件{}删除成功!",fileSrc);
    }

        /**
     * 方法名 downloadFile
     * 参数 [fileName, fileContent, request, response]
     * 返回值 cn.com.chinaventure.common.message.JsonMessageTsVO
     * 描述:文件下载
     */
    public static JsonMessage downloadFile(String fileName, String fileSrc, HttpServletRequest request, HttpServletResponse response) throws SystemException {
        try {
            File file = new File(filePath + File.separator + fileSrc);
            request.setCharacterEncoding(BusinessConstants.GBK);
            String userAgent = request.getHeader("User-Agent");
            OutputStream out = response.getOutputStream();
            if (userAgent.contains(SAFARI) && !userAgent.contains(EDGE)) {
                log.info("进入 Safari 下载");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            } else if ((userAgent.contains(MSIE) || userAgent.contains(TRIDENT))) {
                if(!userAgent.contains(EDGE)){
                    log.info("进入 IE 下载");
                    request.setCharacterEncoding(BusinessConstants.UTF8);
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(BusinessConstants.GBK), StandardCharsets.ISO_8859_1));
                }
            } else if (userAgent.contains(EDGE)) {
                log.info("进入 edge 下载");
                request.setCharacterEncoding(BusinessConstants.UTF8);
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, BusinessConstants.UTF8));
            } else {
                log.info("其他 类型 下载");
                request.setCharacterEncoding(BusinessConstants.GBK);
                response.setContentType("text/html;charset=gbk");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            }
            byte[] fileContent = getFileByte(file);
            out.write(fileContent, 0, fileContent.length);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonMessage(null, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getCnName());
    }

        /**
     * 方法： downloadTemplate
     * 参数： [response, request, fileName]
     * 返回值：void
     */
    public static void downloadTemplate(HttpServletResponse response, HttpServletRequest request, String fileName) {
        try {
            if (fileName != null && !fileName.endsWith(BusinessConstants.POINT + BusinessConstants.XLSX)) {
                fileName = fileName + ".xlsx";
            }
            String ftlPath = "template/";
            InputStream fis = getResourcesFileInputStream(ftlPath+fileName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            request.setCharacterEncoding(BusinessConstants.GBK);
            String userAgent = request.getHeader("User-Agent");
            OutputStream out = response.getOutputStream();
            if (userAgent.contains(SAFARI) && !userAgent.contains(EDGE)) {
                log.info("进入 Safari 下载");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            } else if ((userAgent.contains(MSIE) || userAgent.contains(TRIDENT))) {
                if(!userAgent.contains(EDGE)){
                    log.info("进入 IE 下载");
                    request.setCharacterEncoding(BusinessConstants.UTF8);
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(BusinessConstants.GBK), StandardCharsets.ISO_8859_1));
                }
            } else if (userAgent.contains(EDGE)) {
                log.info("进入 edge 下载");
                request.setCharacterEncoding(BusinessConstants.UTF8);
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, BusinessConstants.UTF8));
            } else {
                log.info("其他 类型 下载");
                request.setCharacterEncoding(BusinessConstants.GBK);
                response.setContentType("text/html;charset=gbk");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            }
            out.write(buffer, 0, buffer.length);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static InputStream getResourcesFileInputStream(String fileRelativePath){
        //获取容器资源解析器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //获取所有匹配的文件
            Resource[] resources = resolver.getResources(fileRelativePath);
            Resource resource = null;
            if(resources != null && resources.length > 0){
                resource = resources[0];
            }
            //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
            InputStream stream = resource.getInputStream();
            return stream;
        } catch (IOException e) {
            log.warn("读取文件流失败！" + e);
        }
        return null;
    }

}
