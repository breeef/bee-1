package com.steel.beeremote.invoker.proxy;

import com.steel.bee.common.extension.ExtensionLoader;

/**
 * Created by gang.qin on 2016/6/2.
 */
public class ServiceProxyLoader {
    private static ServiceProxy serviceProxy = ExtensionLoader.getExtension(ServiceProxy.class);

    static {
        if (serviceProxy == null) {
            serviceProxy = new DefaultServiceProxy();// TODO
        }
    }
    public static ServiceProxy getServiceProxy() {
        return serviceProxy;
    }
}
