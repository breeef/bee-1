package com.steel.zookeeper.common;

/**
 * Created by gang.qin on 2015/8/28.
 */
public interface Constant {
    String ZK_CONNECTION_STRING = "localhost:2181";
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTEY_PATH = "/registry";
    String ZK_PROVIDER_PATH = ZK_REGISTEY_PATH + "/provider";
}
