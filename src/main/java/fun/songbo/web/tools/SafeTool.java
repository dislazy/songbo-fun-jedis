package com.bigonelab.dashboard.common.tools;

/**
 * @author jack
 */
public class SafeTool {

    /**
     * 对象转Integer
     *
     * @param obj
     * @return
     */
    public static Integer getInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return Integer.valueOf(obj.toString());
        } catch (Exception e) {
        }
        return null;
    }


}
