package com.yh.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 客户端注册监听server节点变化
 *
 */
public class AppMaster 
{
    private String clusterNode = "Locks";
    private ZooKeeper zk;
    private volatile List<String> serverList;
    private static String ip = "127.0.0.1:2181" ;

    public void connectZookeeper() throws Exception 
    {
        // 注册全局默认watcher
        zk = new ZooKeeper(ip, 5000, new Watcher()
        {
            public void process(WatchedEvent event) 
            {
                if (event.getType() == EventType.NodeChildrenChanged 
                        && ("/" + clusterNode).equals(event.getPath())) 
                {
                    try 
                    {
                        updateServerList();
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        updateServerList();
    }

    private void updateServerList() throws Exception 
    {
        List<String> newServerList = new ArrayList<String>();

        // watcher注册后，只能监听事件一次，参数true表示继续使用默认watcher监听事件
        if (null!=zk.exists("/" + clusterNode, false)) {
            List<String> subList = zk.getChildren("/" + clusterNode, true);
            for (String subNode : subList) {
                // 获取节点数据
                byte[] data = zk.getData("/" + clusterNode + "/" + subNode, false, null);
                newServerList.add(new String(data, "utf-8"));
            }
        }

        serverList = newServerList;
        System.out.println("server list updated: " + serverList);
    }

    public static void main(String[] args) throws Exception 
    {
        AppMaster ac = new AppMaster();
        ac.connectZookeeper();
        Thread.sleep(Long.MAX_VALUE);
    }
}