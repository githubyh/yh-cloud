package com.yh.hbase;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiveUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HiveUtil.class);
    private String url = "jdbc:hive2://10.0.186.11:10000/default";
    private String driver = "org.apache.hive.jdbc.HiveDriver";
    private String userName = "root";
    private String passWord = "root";

    private static HiveUtil hiveUtil;
    private Connection con = null;
    private static Object o = new Object();

    private HiveUtil() throws Exception {
        init();
    }

    private void init() throws Exception {
        if (!checkDataConfig()) {
            throw new Exception("参数不完整！" + toString());
        }
        try {
            Class.forName(driver);
            getConnection();
        } catch (ClassNotFoundException e) {
            LOGGER.error("驱动加载失败！", e);
        }
    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed())
                con = DriverManager.getConnection(url, userName, passWord);
            return con;
        } catch (SQLException e) {
            LOGGER.error("获取连接失败！", e);
        }
        return null;
    }

    public void closeCon(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.error("回收连接失败！", e);
        }
    }


    private boolean checkDataConfig() {
        return !StringUtils.isBlank(url) || !StringUtils.isBlank(driver) || !StringUtils.isBlank(userName) || !StringUtils.isBlank(passWord);
    }

    public static HiveUtil getInstance() {
        if (hiveUtil == null) {
            synchronized (HiveUtil.class) {
                if (hiveUtil == null) {
                    try {
                        hiveUtil = new HiveUtil();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return hiveUtil;
    }


    @Override
    public String toString() {
        return "HiveUtil{" +
                "url='" + url + '\'' +
                ", driver='" + driver + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }


    public List<Map<String, Object>> executeQuery(String sql, boolean isCloseConn) {
        return executeQuery(getConnection(), sql, null, isCloseConn);
    }

    public List<Map<String, Object>> executeQuery(String sql) {
        return executeQuery(getConnection(), sql, null, true);
    }


    public List<Map<String, Object>> executeQuery(String sql, List params) {
        return executeQuery(getConnection(), sql, params, true);
    }

    public List<Map<String, Object>> executeQuery(String sql, List params,boolean isCloseCon) {
        return executeQuery(getConnection(), sql, params, isCloseCon);
    }

    public List<Map<String, Object>> executeQuery(Connection con, String sql) {
        return executeQuery(con, sql, null, true);
    }

    public List<Map<String, Object>> executeQuery(Connection con, String sql, List params) {
        return executeQuery(con, sql, params, true);
    }

    public void setParameter(List<Object> params, PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            Object p = params.get(i);
            if (p instanceof Integer) {
                preparedStatement.setInt(i + 1, (Integer) p);
            } else if (p instanceof String) {
                preparedStatement.setString(i + 1, (String) p);
            }
        }
    }

    public List<Map<String, Object>> executeQuery(Connection con, String sql, List params, boolean isCloseConn) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rss = null;
        try {
            pstm = con.prepareStatement(sql);
            if (params != null)
                setParameter(params, pstm);
            rss = pstm.executeQuery();
            int row = 0;
            ResultSetMetaData rsm = rss.getMetaData(); //获得列集
            int col = rsm.getColumnCount();   //获得列的个数
            String colName[] = new String[col];

            //取结果集中的表头名称, 放在colName数组中
            for (int i = 0; i < col; i++) {   //-->第一列,从1开始.所以获取列名,或列值,都是从1开始
                colName[i] = rsm.getColumnName(i + 1);// -->获得列值的方式一:通过其序号
            }

            Map<String, Object> result = null;
            while (rss.next()) {
                //取结果集中的数据, 放在data数组中
                result = new HashMap<>();
                for (int j = 0; j < col; j++) {
                    String colNameStr = colName[j];
                    result.put(colNameStr.substring(colNameStr.indexOf(".")+1), rss.getObject(colNameStr));
                }
                resultList.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch ( Exception e) {
            e.printStackTrace();
        } finally {
            if (rss != null)
                try {
                    rss.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (pstm != null)
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (isCloseConn)
                closeCon(con);
        }
        return resultList;
    }

    public static void main(String[] args) throws Exception {
        HiveUtil hiveUtil = HiveUtil.getInstance();
        long start = System.currentTimeMillis();
        String sql = "select `_id`,applyid,userid,creditbillid,operatetime,idcard from  real_time_mongo.credit_creditlog  limit ?";
        List params = new ArrayList();
        params.add(20);
        List<Map<String, Object>> list = hiveUtil.executeQuery(sql, params);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                System.out.println(map.toString());
            }
        }
        long start2 = System.currentTimeMillis();
        list = hiveUtil.executeQuery("select `_id`,applyid,userid,creditbillid,operatetime,idcard from  real_time_mongo.credit_creditlog  limit 10");
        System.out.println((start2 - start) + "===" + (System.currentTimeMillis() - start2));

    }

    private static void execute() throws ClassNotFoundException {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        //default为数据库名
//        String url = "http://10.0.186.11:8888";
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hive2://10.0.186.11:10000/default", "yangou", "yangou");
            Statement stmt = con.createStatement();
            String querySQL = "select `_id`,applyid,userid,creditbillid,operatetime,idcard from  real_time_mongo.credit_creditlog  limit 10";

            ResultSet res = stmt.executeQuery(querySQL);

            while (res.next()) {

                System.out.println(res.getString("_id") + "," + res.getString(1) + " ," + res.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}