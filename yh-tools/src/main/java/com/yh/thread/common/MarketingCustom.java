package com.yh.thread.common;


import com.yh.page.PageParam;
import com.yh.page.SqlParam;
import com.yh.service.IDALService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketingCustom extends SingleThread
{
  private static Logger logger = LoggerFactory.getLogger(MarketingCustom.class);
  private String fileName;
  private String topic = "marketingCustom-";

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public MarketingCustom(IDALService dalService, PageParam pageParam)
  {
    super(dalService, pageParam);
    this.fileName = this.fileName;
  }

  public   Map<String, Object> getData(Map<String, Object> map)
  {
    String userId = new StringBuilder().append(map.get("userId")).append("").toString();
    String createTime = new StringBuilder().append(map.get("createTime")).append("").toString();

    String idCard = " ";
    String mobile = " ";
    String userName = " ";

    String submitTime = createTime;
    String applyMoney = " ";
    String permanentCity = " ";
    String mobileCity = " ";
    String txAddress = " ";
    String hjAddress = " ";
    String dwellAddress1 = " ";
    String dwellAddress2 = " ";
    String dwellAddress3 = " ";
    String dwellAddress4 = " ";
    String dwellAddress5 = " ";
    String workAddress1 = " ";
    String workAddress2 = " ";
    String workAddress3 = " ";
    String workAddress4 = " ";
    String workAddress5 = " ";
    try
    {
      Map userInfo = getUserInfo(userId);
      if (userInfo != null) {
        userName = userInfo.get("userName") == null ? " " : userInfo.get("userName").toString();
        mobile = userInfo.get("mobile") == null ? " " : userInfo.get("mobile").toString();

        idCard = userInfo.get("idCard") == null ? " " : new StringBuilder().append("'").append(userInfo.get("idCard").toString()).toString();
      }
    } catch (Exception e) {
      logger.error("数据库 查询异常======", e);
    }

    Map resultMap = new HashMap();

    userId = userId == null ? " " : userId;
    idCard = idCard == null ? " " : idCard;
    mobile = mobile == null ? " " : mobile;
    userName = userName == null ? " " : userName;
    submitTime = submitTime == null ? " " : submitTime;

    resultMap.put("userId", userId);
    resultMap.put("userName", userName);
    resultMap.put("idCard", idCard);
    resultMap.put("mobile", mobile);
    resultMap.put("submitTime", submitTime);

    return resultMap;
  }

  public   Map<String, Object> getUserInfo(String userId)
  {
    StringBuffer sb = new StringBuffer("SELECT");
    sb.append("  u.userid,u.userName,u.mobile,u.idCard  from user_userinfo u  where   ");
    sb.append("   u.userId = ?");
    Object[] param = { userId };
    try {
      return dalService.find(new SqlParam(sb.toString(), param));
    }
    catch (Exception e) {
      logger.error("getUserInfo 查询数据异常", e);
    }
    return null;
  }

  public List<Map<String, Object>> doSometing()
  {
    List list = getList();
    List resultList = new ArrayList();
    logger.info(new StringBuilder().append("==========start==============").append(list == null ? 0 : list.size()).toString());
    if (list != null) {
      for (int i = 0; i < list.size(); i++)
      {
        Map map = getData((Map)list.get(i));

        resultList.add(map);
      }
    }
    logger.info(new StringBuilder().append("============end============").append(resultList.size()).toString());
    return resultList;
  }
}