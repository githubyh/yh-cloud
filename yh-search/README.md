Elasticsearch: 权威指南 https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html

head安装：https://blog.csdn.net/mjlfto/article/details/79772848

 npm install phantomjs-prebuilt@2.1.16 --ignore-scripts
npm run start


1. curl -XGET 'http://172.18.68.11:9200/_cat/nodes?pretty'

 . 172.18.68.12 18 68 0 0.07 0.06 0.05 mdi - els2
 
 . 172.18.68.13 25 67 0 0.01 0.02 0.05 mdi * els3                *号表示为当前节点为主节点的意思
 
 . 172.18.68.11  7 95 0 0.02 0.04 0.05 mdi - els1
 

如果你要想查看更多有关于集群信息、当前节点统计信息等等，可以使用一下命令来获取到所有可以查看的信息。

2. curl -XGET 'http://172.18.68.11:9200/_cat?pretty'   



集群：https://www.cnblogs.com/bixiaoyu/p/9460554.html

调优
https://blog.csdn.net/wfs1994/article/details/80836570

https://www.cnblogs.com/bixiaoyu/p/9460554.html

http://www.cnblogs.com/hseagle/p/6015245.html

https://blog.csdn.net/u010781176/article/details/79489151


jdk
vi elasticsearch

JAVA_HOME=/usr/jdk8
if [ -x "JAVA_HOME/bin/java" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  JAVA=`which java`
  

创建elsearch用户组及elsearch用户

groupadd elsearch
useradd elsearch -g elsearch -p elasticsearch
更改elasticsearch文件夹及内部文件的所属用户及组为elsearch:elsearch

cd /opt
chown -R elsearch:elsearch  elasticsearch
切换到elsearch用户再启动

su elsearch cd elasticsearch/bin
./elasticsearch



#切换到root用户
vim /etc/security/limits.conf 添加
appdeploy hard nofile 65536
appdeploy soft nofile 65536
soft nproc 5000
hard nproc 5000
root soft nproc 5000
root hard nproc 5000
soft nofile 65535
hard nofile 65535
vim /etc/sysctl.conf 添加
vm.max_map_count = 655360
保存后执行 sysctl -p
vim /etc/security/limits.d/90-nproc.conf
* soft nproc 4096
* hard nproc 4096
* root soft nproc unlimited


异常解决：https://blog.csdn.net/gebitan505/article/details/54709515
http://www.cnblogs.com/jstarseven/p/6803054.html
重启问题：https://www.jianshu.com/p/9752709bfea4





配置：

cluster.name: es-cluster-5.3.1   配置集群名称  服务器保持一致

node.name: node-1                 配置单一节点名称，每个节点唯一标识

path.data: /data/to/data # es数据存储的目录

path.logs: /data/to/logs # es日志存储的地方

network.host: 0.0.0.0              设置绑定的ip地址

network.bing_host: 0.0.0.0              设置绑定的ip地址

http.port: 9200                      端口

discovery.zen.ping.unicast.hosts: ["172.16.31.220", "172.16.31.221","172.16.31.224"]   集群节点ip或者主机

设置集群中自动发现其它节点时ping连接超时时间，默认为3秒，对于比较差的网络环境可以高点的值来防止自动发现时出错。

discovery.zen.ping.timeout: 10s



通过配置大多数节点(节点总数/ 2 + 1)来防止脑裂，设置这个参数来保证集群中的节点可以知道其它N个有master资格的节点。默认为1，对于大的集群来说，可以设置大一点的值（2-4）

discovery.zen.minimum_master_nodes: 2

在一个完整的集群重新启动到N个节点开始之前，阻止初始恢复

gateway.recover_after_nodes: 1

bootstrap.system_call_filter: false


下面两行配置为haad插件配置，三台服务器一致。
http.cors.enabled: true

http.cors.allow-origin: "*"



x-pack:
https://elasticsearch.cn/question/1609#!answer_form

https://blog.csdn.net/lvyuan1234/article/details/78185763

https://blog.csdn.net/zhou_shaowei/article/details/83995754 6.2.4

 <dependency>
			<groupId>org.elasticsearch.client</groupId>
			<artifactId>x-pack-transport</artifactId>
			<version>6.2.3</version>
		</dependency>
 
 		<dependency>
			<groupId>org.elasticsearch.client</groupId>
			<artifactId>x-pack-core</artifactId>
			<version>6.2.3</version>
		</dependency> 
 
		<dependency>
			<groupId>com.unboundid</groupId>
			<artifactId>unboundid-ldapsdk</artifactId>
			<version>3.2.0</version>
		</dependency>
--------------------- 
作者：稻草一根 
来源：CSDN 
原文：https://blog.csdn.net/zhou_shaowei/article/details/83995754 
版权声明：本文为博主原创文章，转载请附上博文链接！
