package com.yh.zookeeper;


import org.junit.Test;

public class TestServer
{
    @Test
    public  void test1( ) throws Exception
    {
        AppServer server1 = new AppServer("Server1", 5000);
        server1.start();
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public   void test2() throws Exception
    {
        AppServer server1 = new AppServer("Server2", 10000);
        server1.start();
        Thread.sleep(Long.MAX_VALUE);
    }
}