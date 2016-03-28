package com.steel.bee.config;

import java.util.List;
import java.util.Properties;

/**
 * Created by gang.qin on 2015/12/31.
 */
public interface ConfigManager {
    //TODO
    public String  getEnv();
    public String  getConfigServerAddress();
    public String  getAppName();
    public String  getLocalIp();
    public String  getGroup();

    public String  getStringValue(String key);
    public String  getStringValue(String key, String defaultValue);
    public Integer getIntValue(String key);
    public int     getIntValue(String key, int defaultVaule);
    public Long    getLongValue(String key);
    public long    getLongValue(String key, long defaultVaule);
    public Float   getFloatVaule(String key);
    public float   getFloatVaule(String key, float defaultVaule);
    public Double  getDoubleVaule(String key);
    public double  getDoubleVaule(String key, double defaultVaule);
    public Boolean getBooleanVaule(String key);
    public boolean getBooleanVaule(String key, boolean defaultVaule);

    public void    init();
    public void    init(Properties properties);

    public String  doGetProperty(String key) throws Exception;

    public String  getLocalStringVaule(String key);
    public void    setLocalStringVaule(String key, String vaule);
    public void    setStringVaule(String key, String value);

    public void    deleteKey(String key);

    public void registerConfigChangeListener(ConfigChangeListener configChangeListener);
    public List<ConfigChangeListener> getConfigChangeListener();
}
