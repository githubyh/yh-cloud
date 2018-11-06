package com.yh.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 * Test Method {create/exists/delete/getChildren/setData/getData/addAuthInfo/setACL/getACL}
 *
 */
public class ZooJavaApi {

    private static final int SESSION_TIMEOUT = 1000;
    
    public static final Logger LOGGER = LoggerFactory.getLogger(ZooJavaApi.class);
    
    public static final String HOST = "localhost:2181";
    
    private Watcher watcher = new Watcher() {
        public void process(WatchedEvent we) {
            LOGGER.info("process:" + we.getType());
        }
    };
    
    private ZooKeeper zookeeper;
    
    /*
     * 开启服务器连接
     */
    @Before
    public void connect() throws IOException {
        zookeeper = new ZooKeeper(HOST, SESSION_TIMEOUT, watcher);
    }
    
    /*
     * 关闭服务器连接
     */
    @After
    public void close() {
        try {
            zookeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * 测试创建节点方法；
     * 调用zookeeper的 String create(final String path, byte data[], List<ACL> acl,CreateMode createMode)方法；
     * path 创建节点名；
     * data[] 节点的数据信息，字节数组类型；
     * acl 访问权限，List数组类型；
     * createMode 节点持久化类型；
     * Assert.fail() 方法加在期望中不可能到达的地方，一旦到达，表明测试失败，结果与预期不同
     */
    @Test
    public void testCreate() {
        String createNode = "/zk001";
        String createNodeData = "zk001Data";
        String result = null;
        try {
            result = zookeeper.create(createNode, createNodeData.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        System.out.println("成功创建了NODE：" + result);
    }
    
    /*
     * 测试删除节点方法;
     * 调用zookeeper的delete(final String path, int version)方法;
     * path 节点名；
     * version 节点版本；
     * 版本为 -1 的话匹配所有版本
     */
    @Test
    public void testDelete() {
        String deleteNode = "/zk001";
        try {
            zookeeper.delete(deleteNode, -1);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();  
        }
        System.out.println("成功删除节点：" + deleteNode);
    }
    
    /*
     * 测试获取节点数据方法；
     * 调用zookeeper的getData(final String path, Watcher watcher, Stat stat)方法;
     * path 节点名称；
     * watcher 获取节点信息时设置Watcher;
     * stat 数据的版本等信息可以通过 stat 来指定;
     */
    @Test
    public void testGetData() {
        String getDataNode = "/zk001";
        String result = null;
        try {
            byte[] bytes = zookeeper.getData(getDataNode, null, null);
            result = new String(bytes);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        System.out.println("获取节点数据：" + result);
    }
    
    /*
     * 测试设置节点数据方法；
     * 调用zookeeper的setData(final String path, byte data[], int version)方法
     * path 节点名称；
     * data[] 节点数据；
     * version 节点版本；
     * 版本设为-1则匹配所有版本;
     * 返回 Stat 类型实例，通过Stat可以获取节点各种信息，例如版本
     */
    @Test
    public void testSetData() {
        String setDataNode = "/zk001";
        String setNodeData = "/zk001DataSetTest";
        Stat tempStat = null;
        try {
            tempStat = zookeeper.setData(setDataNode, setNodeData.getBytes(), -1);
            
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        System.out.println("成功设置数据：" + setNodeData + "; 设置后的版本为：" + tempStat.getVersion());
    }
    
    /*
     * 测试判断节点是否存在方法；
     * 调用zookeeper的exists(String path, boolean watch)方法；
     * path 节点名；
     * watch 是否监听;
     * 返回Stat类型的实例，可以获取各种信息;
     */
    @Test
    public void testExists() {
        String existsNode = "/zk001";
        Stat tempStat = null; 
        try {
            tempStat = zookeeper.exists(existsNode, false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        System.out.println(tempStat.getCzxid() == 0 ? "节点是否存在:否":"节点是否存在:是");
    }
    
    /*
     * 测试获取子节点方法;
     * 调用zookeeper的getChildren(String path, boolean watch)方法；
     * path 节点名称；
     * watch 是否设置监听;
     * 返回值为一个String类型的List;
     */
    @Test
    public void testGetChildren() {
        String parentNode = "/zk001";
        List<String> list = null; 
        try {
            list = zookeeper.getChildren(parentNode, false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        if(list.isEmpty()) {
            System.out.println(parentNode + "无子节点");
        }else {
            System.out.print(parentNode + "的子节点有：");
            for(String str : list) {
                System.out.print(" " + str);
            }
        }
    }
    
    /*
     * 测试设置节点访问权限方法；
     * 调用zookeeper的setACL(final String path, List<ACL> acl, int version)方法；
     * path 节点名称；
     * acl 设置的权限信息；
     * version 设置权限的版本；
     * 返回Stat类型的尸体，可以查看节点信息；
     */
    @Test
    public void testSetAcl() {
        String aclNode = "/zk001/zk002";
        String scheme = "digest";
        String authInfo = "admln:admln";
        List<ACL> acls = new ArrayList<ACL>();
        try {
            Id id1 = new Id(scheme,DigestAuthenticationProvider.generateDigest(authInfo));
            ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
            acls.add(acl1);
            Id id2 = new Id(scheme,DigestAuthenticationProvider.generateDigest("guest:guest"));
            ACL acl2 = new ACL(ZooDefs.Perms.READ, id2);
            acls.add(acl2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            zookeeper.setACL(aclNode, acls, -1);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        System.out.println("成功为" + aclNode + "添加了ACL,scheme为：" + scheme + ",验证信息为：" + authInfo);
    }
    
    /*
     * 测试获取节点权限信息方法；
     * 调用zookeeper的getACL(final String path, Stat stat)方法；
     * path 节点名称;
     * stat 节点状态信息；
     * 返回一个ACL列表;
     */
    @Test
    public void testGetAcl() {
        String getAclNode = "/zk001/zk002";
        List<ACL> list = null;
        try {
            list = zookeeper.getACL(getAclNode, new Stat());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        if(list.isEmpty()) {
            System.out.println(getAclNode + " 没有ACL权限设置");
        }else {
            System.out.println(getAclNode + " 的ACL权限信息： ");
            for(ACL acl : list) {
                System.out.print("\t" + acl.toString());
            }
        }
    }
    /*
     * 测试权限验证方法；
     * 调用zookeeper的addAuthInfo(String scheme, byte auth[])方法；
     * scheme 权限类型；
     * auth[] 权限信息；
     */
    @Test
    public void testAddAuthInfo() {
        String addAuthInfoNode = "/zk001/zk002";
        String scheme = "digest";
        String authInfo = "admln:admln";
        String result = null;
        try {
            byte[] bytes = zookeeper.getData(addAuthInfoNode, null, null);
            result = new String(bytes);
        } catch (Exception e) {
            System.out.println("没有提供验证信息前获取节点信息：" + result + " ,返回错误信息：" + e.getMessage());
        }
        zookeeper.addAuthInfo(scheme, authInfo.getBytes());
        try {
            byte[] bytes = zookeeper.getData(addAuthInfoNode, null, null);
            result = new String(bytes);
        } catch (Exception e) {
            System.out.println("没有提供验证信息前获取节点信息：" + result + " ,返回错误信息：" + e.getMessage());
        }
        System.out.println("提供验证信息后获取节点信息：" + result);
    }
}