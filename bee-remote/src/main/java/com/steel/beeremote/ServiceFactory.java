package com.steel.beeremote;

import com.steel.beeremote.invoker.config.InvokerConfig;
import com.steel.beeremote.invoker.proxy.ServiceProxy;
import com.steel.beeremote.invoker.proxy.ServiceProxyLoader;

/**
 * Created by gang.qin on 2015/12/30.
 */
public class ServiceFactory {

    static ServiceProxy serviceProxy = ServiceProxyLoader.getServiceProxy(); // 单利模式获取serviceProxy

    public static <T> T getService(InvokerConfig<T> invokerConfig) throws Exception {
        return serviceProxy.getProxy(invokerConfig);
    }

}
