package com.steel.bee.config.impl;

import com.steel.bee.common.log.LoggerLoader;
import com.steel.bee.config.ConfigManager;
import com.steel.bee.config.loader.LocalConfigLoader;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gang.qin on 2016/1/5.
 */
public abstract class DefaultConfigManager implements ConfigManager {

    private static Logger logger = LoggerLoader.getLogger(DefaultConfigManager.class);

    protected Map<String, Object> localCahe = new HashMap<>();

    public DefaultConfigManager() {
        Map<String, Object> properties = LocalConfigLoader.load(this);
        localCahe = properties;
    }

    public abstract String doGetProperty(String key) throws Exception;
    public abstract String doGetLocalProperty(String key) throws Exception;
    public abstract String doGetEnv() throws Exception;
    public abstract String doGetLocalIp() throws Exception;
    public abstract String doGetGroup() throws Exception;

    public abstract void   doSetStringValue(String key, String value) throws Exception;
    public abstract void   doDeleteKey(String key) throws Exception;

    public String  getStringValue(String key) {
        return getProperty(key, String.class);
    }
    public String  getStringValue(String key, String defaultValue) {
        String vaule = getStringValue(key);
        return vaule == null ? defaultValue : vaule;
    }
    public Integer getIntValue(String key) {
        return getProperty(key, Integer.class);
    }
    public int     getIntValue(String key, int defaultVaule) {
        Integer value = getIntValue(key);
        return value == null ? defaultVaule : value;
    }
    public Long    getLongValue(String key) {
        return getProperty(key, Long.class);
    }
    public long    getLongValue(String key, long defaultVaule) {
        Long value = getProperty(key, Long.class);
        return value == null ? defaultVaule : value;
    }
    public Float   getFloatVaule(String key) {
        return getProperty(key, Float.class);
    }
    public float   getFloatVaule(String key, float defaultVaule) {
        Float value = getFloatVaule(key);
        return value == null ? defaultVaule : value;
    }
    public Double  getDoubleVaule(String key) {
        return getProperty(key, Double.class);
    }
    public double  getDoubleVaule(String key, double defaultVaule) {
        Double vaule = getDoubleVaule(key);
        return vaule == null ? defaultVaule : vaule;
    }
    public Boolean getBooleanVaule(String key) {
        return getProperty(key, Boolean.class);
    }
    public boolean getBooleanVaule(String key, boolean defaultVaule){
        Boolean value = getBooleanVaule(key);
        return value == null ? defaultVaule : value;
    }

    private <T> T getProperty(String key, Class<T> type) {
        //TODO
        return null;
    }
}
