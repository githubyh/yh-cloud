/*
package com.yh.util;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.yanxintec.nga.mongodb.core.MongoDBCollectionOperation;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TsMongoUtils
{
  public static void main(String[] args)
    throws Exception
  {
    String ip = "2342234";
    int port = 234234;
    String user = "23423";
    String pwd = "234(234";
    String dbName = "db";
    String collName = "ffs";

    MongoDBCollectionOperation mongos = getMongoOperation(ip, port, user, pwd, dbName, collName);

    DBObject dBObject = mongos.findById("58adb580e4b0d89fefc6d568");
    if (dBObject != null) {
      Map map = (Map)dBObject;
      Iterator iterator = map.keySet().iterator();
      while (iterator.hasNext())
        System.out.println((String)iterator.next());
    }
    else {
      System.out.println("no data");
    }
  }

  public static MongoDBCollectionOperation getMongoNoPwd(String ip, int port, String dbName, String collName)
  {
    MongoClient mongoClient = getMongoDBConn(ip, port, null, null, dbName, collName);
    return new MongoDBCollectionOperation(mongoClient, dbName, collName);
  }

  public static MongoDBCollectionOperation getMongoOperation(String ip, int port, String user, String pwd, String dbName, String collName)
  {
    MongoClient mongoClient = getMongoDBConn(ip, port, user, pwd, dbName, collName);
    return new MongoDBCollectionOperation(mongoClient, dbName, collName);
  }

  public static MongoClient getMongoDBConn(String ip, int port, String user, String pwd, String dbName, String collName)
  {
    MongoClient mongoClient = null;
    try
    {
      ServerAddress serverAddress = new ServerAddress(ip, port);
      List addrs = new ArrayList();
      addrs.add(serverAddress);

      if (StringUtils.isNotEmpty(pwd))
      {
        MongoCredential credential = MongoCredential.createScramSha1Credential(user, dbName, pwd.toCharArray());

        List credentials = new ArrayList();
        credentials.add(credential);

        mongoClient = new MongoClient(addrs, credentials);
      } else {
        mongoClient = new MongoClient(addrs);
      }
      System.out.println("Connect to database successfully");
    }
    catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return mongoClient;
  }
}*/
