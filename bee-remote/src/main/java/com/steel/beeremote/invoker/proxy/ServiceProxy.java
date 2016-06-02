package com.steel.beeremote.invoker.proxy;

import com.steel.beeremote.invoker.config.InvokerConfig;

import java.util.Map;

/**
 * Created by gang.qin on 2016/6/2.
 */
public interface ServiceProxy {
    public void init();
    public <T> T getProxy(InvokerConfig<T> invokerConfig);
    public Map<InvokerConfig<?>, Object> getAllServiceInvokers();
}
