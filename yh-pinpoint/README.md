http://blog.csdn.net/xiaozhuanddapang/article/details/75554807 安装部署
  http://www.ltang.me/2016/11/29/pinpoint-installing/ 安装部署
cp aws-java-sdk-1.7.4.jar  至 hbase/lib/ 下



linux 新增agent catalina.sh
CATALINA_OPTS="$CATALINA_OPTS -javaagent:"/soft/pp/pinpoint-agent/pinpoint-bootstrap.jar""
CATALINA_OPTS="$CATALINA_OPTS -Dpinpoint.agentId=pp20171219"
CATALINA_OPTS="$CATALINA_OPTS -Dpinpoint.applicationName=MyTestPP"



window 新增agent catalina.bat
set CATALINA_OPTS=-javaagent:D:\pinpoint-agent\pinpoint-bootstrap.jar
set CATALINA_OPTS=%CATALINA_OPTS% -Dpinpoint.agentId=pp20171219-test2
set CATALINA_OPTS=%CATALINA_OPTS% -Dpinpoint.applicationName=MyTestPP2
echo Using CATALINA_OPTS:   "%CATALINA_OPTS%"


idea test
vm:-javaagent:D:\pinpoint-agent\pinpoint-bootstrap.jar -Dpinpoint.applicationName=MyTestPP-test5 -Dpinpoint.agentId=pp20171219-test5


分布式调用链监控  zipkin, pinpoint, skywalking
https://baijiahao.baidu.com/s?id=1586325087625696162&wfr=spider&for=pc
