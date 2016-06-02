package com.steel.beeremote.invoker.proxy;

import com.steel.beeremote.invoker.config.InvokerConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gang.qin on 2016/6/2.
 */
public class DefaultServiceProxy implements ServiceProxy {

    protected static Map<InvokerConfig<?>, Object> services = new ConcurrentHashMap<>();

    @Override
    public void init() {}

    @Override
    public <T> T getProxy(InvokerConfig<T> invokerConfig) {
        return null;// TODO
    }

    @Override
    public Map<InvokerConfig<?>, Object> getAllServiceInvokers() {
        return services;
    }
}
