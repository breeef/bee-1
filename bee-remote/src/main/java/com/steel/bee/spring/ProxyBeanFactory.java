package com.steel.bee.spring;

import com.steel.bee.common.util.ClassUtils;
import com.steel.bee.config.ConfigManager;
import com.steel.bee.config.loader.ConfigManagerLoader;
import com.steel.bee.remote.ServiceFactory;
import com.steel.bee.remote.common.util.Constants;
import com.steel.bee.remote.invoker.config.spring.InvokerConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by gang.qin on 2016/6/1.
 */
public class ProxyBeanFactory implements FactoryBean {

    private ConfigManager configManager = ConfigManagerLoader.getConfigManager();

    private Object obj;
    private Class<?> objType;

    private String serviceName;
    private String iface;
    private String serialize = Constants.SERIALIZE_HESSIAN;
    private String callMethod = Constants.CALL_SYNC;
    private int timeout = Constants.DEFAULT_TIMEOUT;
    private String cluster = Constants.CLUSTER_FAILFAST;

    @Override
    public Object getObject() throws Exception {
        return obj;
    }

    @Override
    public Class<?> getObjectType() {
        return objType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void init() throws Exception {
        if (StringUtils.isBlank(iface)) {
            return;
        }
        objType = ClassUtils.loadClass(iface.trim());
        InvokerConfig invokerConfig = new InvokerConfig(); // TODO
        obj = ServiceFactory.getService(invokerConfig);
    }


    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }

    public String getSerialize() {
        return serialize;
    }

    public void setSerialize(String serialize) {
        this.serialize = serialize;
    }

    public String getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(String callMethod) {
        this.callMethod = callMethod;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
