package com.yh.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * 服务节点启动后注册到zookeeper
 *
 */
public class AppServer extends Thread
{
    private String clusterNode = "Locks";
    private String serverNode = "mylock";
    private String serverName;
    private long sleepTime;
    private static String ip = "127.0.0.1:2181" ;

    public void run()
    {
        try 
        {
            connectZookeeper(serverName);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public void connectZookeeper(String address) throws Exception {
        ZooKeeper zk = new ZooKeeper(ip, 5000, new Watcher() {
            public void process(WatchedEvent event) {
            }
        });

        // 关键方法，创建包含自增长id名称的目录，这个方法支持了分布式锁的实现
        // 四个参数：
        // 1、目录名称 2、目录文本信息 
        // 3、文件夹权限，Ids.OPEN_ACL_UNSAFE表示所有权限 
        // 4、目录类型，CreateMode.EPHEMERAL_SEQUENTIAL表示创建临时目录，session断开连接则目录自动删除
        if (zk.exists("/" + clusterNode + "/" + serverNode, false) == null){
            String createdPath = zk.create(
                    "/" + clusterNode + "/" + serverNode,
                    address.getBytes("utf-8"),
                    Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("create: " + createdPath);
    }
        Thread.sleep(sleepTime);



       /* System.out.println("=========创建节点===========");
        if(zk.exists("/test", false) == null)
        {
            zk.create("/test", "znode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("=============查看节点是否安装成功===============");
        System.out.println(new String(zk.getData("/test", false, null)));

        System.out.println("=========修改节点的数据==========");
        zk.setData("/test", "zNode2".getBytes(), -1);
        System.out.println("========查看修改的节点是否成功=========");
        System.out.println(new String(zk.getData("/test", false, null)));

        System.out.println("=======删除节点==========");
        zk.delete("/test", -1);
        System.out.println("==========查看节点是否被删除============");
        System.out.println("节点状态：" + zk.exists("/test", false));
        zk.close();*/
    }
    
    public AppServer(String serverName, long sleepTime)
    {
        this.serverName = serverName;
        this.sleepTime = sleepTime;
    }
}