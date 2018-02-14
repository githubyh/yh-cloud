mongodb
https://docs.mongodb.com/manual/reference/method/db.collection.drop/#db.collection.drop
http://www.yiibai.com/mongodb/mongodb_create_collection.html
在命令中，name 是要创建的集合的名称。 options是一个文档，用于指定集合的配置。

参数	类型	描述
name	String	要创建的集合的名称
options	Document	(可选)指定有关内存大小和索引的选项
options参数是可选的，因此只需要指定集合的名称。 以下是可以使用的选项列表：

字段	类型	描述
capped	Boolean	(可选)如果为true，则启用封闭的集合。上限集合是固定大小的集合，它在达到其最大大小时自动覆盖其最旧的条目。 如果指定true，则还需要指定size参数。
autoIndexId	Boolean	(可选)如果为true，则在_id字段上自动创建索引。默认值为false。
size	数字	(可选)指定上限集合的最大大小(以字节为单位)。 如果capped为true，那么还需要指定此字段的值。
max	数字	(可选)指定上限集合中允许的最大文档数。

mongo ip:port
show databases
use db
db.createCollection("ts_td",{capped:false,max:10000000})
db.ts_td.insert({_id:1,username:"username"})
