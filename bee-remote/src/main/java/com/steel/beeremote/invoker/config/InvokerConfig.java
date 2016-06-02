package com.steel.beeremote.invoker.config;

import com.google.gson.Gson;
import com.steel.beeremote.common.util.Constants;

/**
 * Created by gang.qin on 2016/6/1.
 */
public class InvokerConfig<T> {

    private Class<?> iface;
    private String url;
    private String serialize = Constants.SERIALIZE_HESSIAN;
    private String callMethod = Constants.CALL_SYNC;
    private int timeout = Constants.DEFAULT_TIMEOUT;
    private String cluster = Constants.CLUSTER_FAILFAST;
    private String protocol = Constants.PROTOCOL_DEFAULT;

    public InvokerConfig(Class<T> iface, String url, String serialize, String callMehtod, int timeout, String cluster, String protocol) {
        this.iface = iface;
        this.url = url;
        this.serialize = serialize;
        this.callMethod = callMehtod;
        this.timeout = timeout;
        this.cluster = cluster;
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, InvokerConfig.class);
    }

    public Class<?> getIface() {
        return iface;
    }

    public void setIface(Class<?> iface) {
        this.iface = iface;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
