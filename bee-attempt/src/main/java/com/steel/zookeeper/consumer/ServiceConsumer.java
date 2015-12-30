package com.steel.zookeeper.consumer;

import com.steel.zookeeper.common.Constant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by gang.qin on 2015/8/28.
 */
public class ServiceConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConsumer.class);
    private CountDownLatch latch = new CountDownLatch(1);//用于等待SyncConnected事件
    private volatile List<String> urlList = new ArrayList<>();

    public ServiceConsumer() {
        //连接ZooKeeper服务器并获取Zookeeper对象
        ZooKeeper zk = connectService();
        if (zk != null) {
            watchNode(zk); //观察 /registry节点的所有子节点并更新urlList成员变量
        }
    }

    //观察 /registry节点下所有子节点是否有变化
    private void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(Constant.ZK_REGISTEY_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        watchNode(zk);//若子节点有变化，则重新调用改方法（为了获取最新子节点中的数据）
                    }
                }
            });
            List<String> dataList = new ArrayList<>();//用于存放/registry所有子节点中的数据
            for (String node : nodeList) {
                //获取/registry的子节点中的数据
                byte[] data = zk.getData(Constant.ZK_REGISTEY_PATH + "/" + node, false, null);
            }
            LOGGER.debug("node data:{}", dataList);
        } catch (KeeperException | InterruptedException e) {
            LOGGER.error("watch node error", e);
        }
    }

    private ZooKeeper connectService() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();//唤醒当前正在执行的线程
                    }
                }
            });
        } catch (IOException e) {
            LOGGER.error("connect zookeeper error", e);
        }
        return zk;
    }

    //查找RMI服务
    public <T extends Remote> T lookup() {

        T service = null;
        int size = urlList.size();
        if (size > 0) {
            String url;
            if (size == 1) {
                //若urlList中只有一个元素，则直接获取改元素
                url = urlList.get(0);
                LOGGER.debug("using only url:{}", url);
            } else {
                //若urlList中有多个，则随机选取一个
                url = urlList.get(ThreadLocalRandom.current().nextInt(size));
                LOGGER.debug("using randon url:{}", url);
            }
            //从JNDI中查找RMI服务
            service = lookupService(url);
        }
        return service;
    }

    //在JNDI中查找RMI远程服务对象
    private <T extends Remote> T lookupService(String url) {
        T remote = null;
        try {
            remote = (T) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            if (e instanceof ConnectException) {
                //若连接中断，则使用URLList中第一个RMI地址来查找（这是一种最简单的重试方式，确保不会抛出异常）
                LOGGER.error("ConnectException => url:{}", url);
                if (urlList.size() != 0) {
                    url = urlList.get(0);
                    return  lookupService(url);
                }
            }
            LOGGER.error("lookup service error", e);
        }
        return remote;
    }
}
