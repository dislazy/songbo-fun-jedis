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
        }
        return null;
    }

    /**
     * 对象转Double
     *
     * @param obj
     * @return
     */
    public static Double getDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return Double.valueOf(obj.toString());
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 对象转Boolean
     *
     * @param obj
     * @return
     */
    public static Boolean getBoolean(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return Boolean.valueOf(obj.toString());
        } catch (Exception e) {
        }
        return null;
    }

    

}
