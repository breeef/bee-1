package com.steel.zookeeper.provider;

import com.steel.zookeeper.common.Constant;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * Created by gang.qin on 2015/8/28.
 */
public class ServiceProvider {
    private static  final Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);

    //用于等待SyncConnected事件后继续执行当前线程
    private CountDownLatch latch = new CountDownLatch(1);

    //发布RMI服务并注册RMI地址到Zookeeper中
    public void publish(Remote remote, String host, int port) {
        String url = publishSerivce(remote, host, port); //发布RMI服务并返回RMI地址
        if (url != null) {
            ZooKeeper zk = connectServer(); //连接ZooKeeper服务器并获取Zookeeper对象
            if (zk != null) {
                createNode(zk, url); //创建ZNode并将RMI地址放入ZNode上
            }
        }
    }

    //创建ZNode
    private void createNode(ZooKeeper zk, String url) {
        try {
            byte[] data = url.getBytes();
            String path = zk.create(Constant.ZK_PROVIDER_PATH, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL); //创建一个临时性且有序的ZNode
            LOGGER.debug("create zookeeper node ({} => {})", path, url);
        } catch (KeeperException | InterruptedException e) {
            LOGGER.error("create zookeeper node error", e);
        }

    }

    //连接ZooKeeper服务器
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();// 唤醒当前正在执行的线程
                    }
                }
            });
            latch.await(); //使当前线程处于等待状态
        } catch (IOException | InterruptedException e) {
            LOGGER.error("connectServer error", e);
        }
        return zk;
    }

    // 发布RMI服务
    private String publishSerivce(Remote remote, String host, int port) {
        String url = null;
        try {
            url = String.format("rmi://%s:%d/%s", host, port, remote.getClass());
            LocateRegistry.createRegistry(port);
            Naming.rebind(url, remote);
            LOGGER.debug("publish rmi service (url:{})", url);
        } catch (RemoteException | MalformedURLException e) {
            LOGGER.error("publish rmi service error", e);
        }
        return url;
    }
}
