package com.steel.bee.common.util;

/**
 * Created by gang.qin on 2016/6/1.
 */
public class ClassUtils {
    public static Class loadClass(String name) throws ClassNotFoundException {
        return org.apache.commons.lang.ClassUtils.getClass(Thread.currentThread().getContextClassLoader(), name);
    }
}
