package com.steel.bee.attempt.rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by gang.qin on 2015/8/27.
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    protected HelloServiceImpl() throws RemoteException{

    }

    public String sayHello(String name) throws RemoteException {
        return String.format("Hello %s", name);
    }
}
