package com.steel.bee.attempt.rmi.client;

import com.steel.bee.attempt.rmi.service.HelloService;

import java.rmi.Naming;

/**
 * Created by gang.qin on 2015/8/27.
 */
public class RmiClient {

    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost:1099/com.steel.rmi.HelloServiceImpl";
        HelloService helloService = (HelloService)Naming.lookup(url);
        String result = helloService.sayHello("sdf水电费");
        System.out.println(result);
    }
}
