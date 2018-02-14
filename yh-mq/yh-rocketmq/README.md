资料：https://www.jianshu.com/p/11e875074a8f


rocketmq4.0


下载 http://rocketmq.apache.org/dowloading/releases/
解押：rocketmq-all-4.0.0-incubating-bin-release.zip

修改runservice.sh和runbroker.sh
JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m -XX:PermSize=128m -XX:MaxPermSize=320m"

修改broker.conf

brokerClusterName = DefaultCluster
brokerName = broker-a
namesrvAddr=192.168.156.101:9876
brokerId = 0
brokerIP1=192.168.156.101 #rocketmq 安装本机ip地址
deleteWhen = 04
fileReservedTime = 48
brokerRole = ASYNC_MASTER
flushDiskType = ASYNC_FLUSH
storePathRootDir=/soft/rocketmq/data
storePathCommitLog=/soft/rocketmq/logs




启动 namesrv
nohup sh apache-rocketmq-all/bin/mqnamesrv >logs/namesrv.log 2>&1 &


启动broker
nohup sh apache-rocketmq-all/bin/mqbroker -c apache-rocketmq-all/conf/broker.conf  > logs/broker.log 2>&1 &

[root@myyh2 conf]# jps
2930 NamesrvStartup
3508 BrokerStartup
3562 Jps

启动成功


开放端口：
/sbin/iptables -I INPUT -p tcp --dport 9876 -j ACCEPT
