package com.steel.rmi.service;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by gang.qin on 2015/8/27.
 */
public class RmiServer {

    public static void main(String[] args) throws Exception{
        int port = 1099;
        String url = "rmi://localhost:1099/com.steel.rmi.HelloServiceImpl";

        LocateRegistry.createRegistry(port);
        Naming.rebind(url, new HelloServiceImpl());
    }
}
