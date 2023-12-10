package fun.songbo.web.tools;

import com.bigonelab.dashboard.common.enums.ResponseCodeEnum;
import com.bigonelab.dashboard.common.exception.SystemException;
import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jack
 */
public class ListCopyTool {

    public static List copyTo(List fromList, Class toClass) {
        try {
            List toList = new ArrayList();
            Object tempObj;
            for (Object aFromList : fromList) {
                tempObj = toClass.newInstance();
                BeanUtils.copyProperties(aFromList, tempObj, toClass);
                toList.add(tempObj);
            }
            if (fromList instanceof Page) {
                fromList.clear();
                fromList.addAll(toList);
                return fromList;
            } else {
                return toList;
            }
        } catch (Exception e) {
            throw new SystemException(ResponseCodeEnum.OBJECT_DATA_COPY_ERROR, e);
        }
    }
}