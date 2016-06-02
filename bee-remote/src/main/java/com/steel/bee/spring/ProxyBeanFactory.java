package com.steel.bee.spring;

import com.google.gson.Gson;
import com.steel.bee.common.util.ClassUtils;
import com.steel.bee.config.ConfigManager;
import com.steel.bee.config.loader.ConfigManagerLoader;
import com.steel.beeremote.ServiceFactory;
import com.steel.beeremote.common.util.Constants;
import com.steel.beeremote.invoker.config.InvokerConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by gang.qin on 2016/6/1.
 */
public class ProxyBeanFactory implements FactoryBean {

    private ConfigManager configManager = ConfigManagerLoader.getConfigManager();

    //FactoryBean 需要的字段
    private Object obj;
    private Class<?> objType;

    //spring schema配置字段
    private String serviceName;
    private String iface;
    private String serialize = Constants.SERIALIZE_HESSIAN;
    private String callMethod = Constants.CALL_SYNC;
    private int timeout = Constants.DEFAULT_TIMEOUT;
    private String cluster = Constants.CLUSTER_FAILFAST;
    private String protocol = Constants.PROTOCOL_DEFAULT;

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

    // 解析客户端服务的spring配置入口函数
    public void init() throws Exception {
        if (StringUtils.isBlank(iface)) {
            return;
        }
        objType = ClassUtils.loadClass(iface.trim());
        InvokerConfig invokerConfig = new InvokerConfig(objType, serviceName, serialize, callMethod, timeout, cluster, protocol);
        obj = ServiceFactory.getService(invokerConfig);

        // TODO load balanceManager.register
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, ProxyBeanFactory.class);
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
