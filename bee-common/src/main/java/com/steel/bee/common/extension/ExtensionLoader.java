package com.steel.bee.common.extension;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gang.qin on 2015/12/31.
 * Java SPI 机制(服务发现机制)
 */
public final class ExtensionLoader {
    private static Map<Class<?>, Object> extensionMap = new ConcurrentHashMap<Class<?>, Object>();
    private static Map<Class<?>, List<?>> extensionListMap = new ConcurrentHashMap<Class<?>, List<?>>();

    public static <T> T getExtension(Class<T> clazz) {
        T extension = (T) extensionMap.get(clazz);
        if (extension == null) {
            extension = newExtension(clazz);
            if (extension != null) {
                extensionMap.put(clazz, extension);
            }
        }
        return extension;
    }


    private static <T> T newExtension(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        for (T service : serviceLoader) {
            return  service;
        }
        return null;
    }

    //TODO
}
