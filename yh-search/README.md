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
