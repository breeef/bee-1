package com.steel.bee.common.log;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Created by gang.qin on 2016/1/4.
 */
public class LoggerLoader {
    private static LoggerContext context = null;
    static {
        init();
    }

    public static synchronized void init() {
        //TODO
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String name) {
        if (context == null) {
            init();
        }
        return context.getLogger(name);
    }
}
