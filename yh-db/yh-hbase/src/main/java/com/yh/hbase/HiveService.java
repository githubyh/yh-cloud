package com.yh.hbase;

import java.sql.*;
import java.text.SimpleDateFormat;

public class HiveService {
    private static final String URLHIVE = "jdbc:hive://192.168.1.9:5000/default";
    private static Connection connection = null;

    public static Connection getHiveConnection() {
        if (null == connection) {
            synchronized (HiveService.class) {
                if (null == connection) {
                    try {
                        Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
                        connection = DriverManager.getConnection(URLHIVE, "", "");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

   /* public static void createTable() throws SQLException {
        String tweetTableSql = "DROP TABLE IF EXISTS hive_crm_tweet2222";
        String createTable1 = "CREATE EXTERNAL TABLE hive_crm_tweet2222(tweet_id string, cuser_id string, created_at bigint, year bigint, month bigint, day bigint, hour bigint, text string, comments_count bigint, reposts_count bigint, source string, retweeted_id string, post_type string, sentiment string, positive_tags_string string, predict_tags_string string, tags_string string) STORED BY 'org.apache.hadoop.hive.dynamodb.DynamoDBStorageHandler' TBLPROPERTIES (\"dynamodb.table.name\" = \"crm_tweet\",\"dynamodb.column.mapping\" = \"tweet_id:tweet_id,cuser_id:cuser_id,created_at:created_at,year:year,month:month,day:day,hour:hour,text:text,comments_count:comments_count,reposts_count:reposts_count,source:source,retweeted_id:retweeted_id,post_type:post_type,sentiment:sentiment,positive_tags_string:positive_tags_string,predict_tags_string:predict_tags_string,tags_string:tags_string\")";
        String commentTableSql = "DROP TABLE IF EXISTS hive_tweet_comment2222";
        String createTable2 = "CREATE EXTERNAL TABLE hive_tweet_comment2222(tweet_id string,comment_id string, cuser_id string, user_id string, created_at bigint, year bigint, month bigint, day bigint, hour bigint, text string, comments_count bigint, reposts_count bigint, source string, topic_id string, post_type string, sentiment string) STORED BY 'org.apache.hadoop.hive.dynamodb.DynamoDBStorageHandler' TBLPROPERTIES (\"dynamodb.table.name\" = \"crm_tweet_comment\",\"dynamodb.column.mapping\" = \"tweet_id:tweet_id,comment_id:comment_id,cuser_id:cuser_id,user_id:user_id,created_at:created_at,year:year,month:month,day:day,hour:hour,text:text,comments_count:comments_count,reposts_count:reposts_count,source:source,topic_id:tweet_id,post_type:post_type,sentiment:sentiment\")";
        String retweetTableSql = "DROP TABLE IF EXISTS hive_tweet_retweet2222";
        String createTable3 = "CREATE EXTERNAL TABLE hive_tweet_retweet2222(tweet_id string, cuser_id string, user_id string, retweet_id string, created_at BIGINT, year BIGINT, month BIGINT, day BIGINT, hour BIGINT, text string, comments_count BIGINT, reposts_count BIGINT, source string, topic_id string, verified_type BIGINT, post_type string, sentiment string) STORED BY 'org.apache.hadoop.hive.dynamodb.DynamoDBStorageHandler' TBLPROPERTIES (\"dynamodb.table.name\" = \"crm_tweet_retweet\",\"dynamodb.column.mapping\" = \"tweet_id:tweet_id,cuser_id:cuser_id,user_id:user_id,retweet_id:retweet_id,created_at:created_at,year:year,month:month,day:day,hour:hour,text:text,comments_count:comments_count,reposts_count:reposts_count,source:source,topic_id:tweet_id,verified_type:verified_type,post_type:post_type,sentiment:sentiment\")";

        Statement stmt = getHiveConnection().createStatement();
        stmt.executeQuery(tweetTableSql);
        stmt.executeQuery(createTable1);
        stmt.executeQuery(commentTableSql);
        stmt.executeQuery(createTable2);
        stmt.executeQuery(retweetTableSql);
        stmt.executeQuery(createTable3);
    }*/

    public static void selectTweet() throws SQLException {
        long aaa = System.currentTimeMillis();
        //long start = DateUtils.getNDaysAgo(DateUtils.getMidNight(), 15).getTime().getTime();  
        //long end = DateUtils.getNDaysAgo(DateUtils.getMidNight(), 13).getTime().getTime();  
        long start = new java.util.Date().getTime();
        long end = start + 2 * 60 * 60 * 1000;
        String sql = "select cuser_id, count(*) as tw_hour, year, month, day from hive_crm_tweet2222 where created_at > ? and created_at < ? and cuser_id = ? group by cuser_id, year, month, day, hour";
        PreparedStatement pstm = getHiveConnection().prepareStatement(sql);
        pstm.setLong(1, start);
        pstm.setLong(2, end);
        pstm.setString(3, "2176270443");
        ResultSet rss = pstm.executeQuery();
        while (rss.next()) {
            System.out.println("1: " + rss.getString("cuser_id") + "   2: "
                    + rss.getInt("tw_hour") + "   3: " + rss.getInt("year")
                    + "   4: " + rss.getInt("month") + "   5: "
                    + rss.getInt("day"));
        }

        System.out.println(System.currentTimeMillis() - aaa);

    }

    public static void selectTweet22() throws SQLException {
        long aaa = System.currentTimeMillis();
        //long start = DateUtils.getNDaysAgo(DateUtils.getMidNight(), 15).getTime().getTime();  
        //long end = DateUtils.getNDaysAgo(DateUtils.getMidNight(), 13).getTime().getTime();  
        long start = new java.util.Date().getTime();
        long end = start + 2 * 60 * 60 * 1000;

        String sql = "select cuser_id, created_at, tweet_id from hive_crm_tweet2222 where created_at > ? and created_at < ? and cuser_id = ?";
        PreparedStatement pstm = getHiveConnection().prepareStatement(sql);
        pstm.setLong(1, start);
        pstm.setLong(2, end);
        pstm.setString(3, "2176270443");
        ResultSet rss = pstm.executeQuery();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        while (rss.next()) {
            long cc = Long.valueOf(String.valueOf(rss.getInt("created_at"))
                    + "000");
            java.util.Date date = new java.util.Date(cc);
            System.out.println(dateFormat.format(date));
            System.out.println(rss.getString("cuser_id") + " "
                    + rss.getString("tweet_id"));
        }

        System.out.println(System.currentTimeMillis() - aaa);

    }

    public static void main(String[] args) throws ClassNotFoundException,
            SQLException {
        Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
        String querySQL = "SELECT a.* FROM student a";
        Connection con = DriverManager.getConnection(URLHIVE, "", "");
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(querySQL); // 执行查询语句  
        while (res.next()) {
            System.out.println("Result: key:" + res.getString(1) + "  –>  value:" + res.getString(2));
        }
        //createTable();  
        //selectTweet22();  

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        System.out.println(dateFormat.format(new java.util.Date()));
    }
}  