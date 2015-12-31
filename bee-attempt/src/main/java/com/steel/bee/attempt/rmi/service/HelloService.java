package com.steel.bee.attempt.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by gang.qin on 2015/8/27.
 */
public interface HelloService extends Remote {

    public String sayHello(String name) throws RemoteException;
}
