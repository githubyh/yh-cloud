jdk8：http://www.importnew.com/10360.html
https://www.evget.com/article/2014/6/18/21196.html


NIO相关基础篇一 https://mp.weixin.qq.com/s/jeuISFEh49aUheFsX_aHMg
死磕java http://cmsblogs.com/

JVM菜鸟进阶高手之路（共14篇） https://mp.weixin.qq.com/s/3jfH2B5_EUAdp-JPe7b3Ng
占小狼 https://www.jianshu.com/u/90ab66c248e6

juc源码:返回主页 五月的仓颉 http://www.cnblogs.com/xrq730/
juc源码 活在夢裡：http://www.cnblogs.com/micrari/p/6937995.html
juc源码：劳夫子 http://www.cnblogs.com/liuyun1995/p/8462088.html
threadlocal 源码分析：http://www.cnblogs.com/micrari/p/6790229.html

ConcurrentHashMap能完全替代HashTable吗？ https://my.oschina.net/hosee/blog/675423
HashTable虽然性能上不如ConcurrentHashMap，但并不能完全被取代，两者的迭代器的一致性不同的，
HashTable的迭代器是强一致性的，而ConcurrentHashMap是弱一致的。 ConcurrentHashMap的get，clear，iterator
都是弱一致性的。 Doug Lea 也将这个判断留给用户自己决定是否使用ConcurrentHashMap。
那么什么是强一致性和弱一致性呢？
get方法是弱一致的，是什么含义？可能你期望往ConcurrentHashMap底层数据结构中加入一个元素后，立马能对get可见
，但ConcurrentHashMap并不能如你所愿。换句话说，put操作将一个元素加入到底层数据结构后，get可能在某段时间内
还看不到这个元素，若不考虑内存模型，单从代码逻辑上来看，却是应该可以看得到的。


深入理解同步异步，阻塞非阻塞 https://www.jianshu.com/p/2116fff869b6
聊聊同步、异步、阻塞与非阻塞：https://my.oschina.net/xianggao/blog/661085
书海陶然同步异步讲解：https://www.jianshu.com/p/aed6067eeac9
https://gitee.com/taomk

Linux 五种IO模型：https://www.jianshu.com/p/486b0965c296
同步异步
同步/异步是“下载完成消息”通知的方式（机制）
阻塞非阻塞
而阻塞/非阻塞则是在等待“下载完成消息”通知过程中的状态（能不能干其他任务）
总结：
同步和异步仅仅是关注的消息如何通知的机制，而阻塞与非阻塞关注的是等待消息通知时的状态。
也就是说，同步的情况下，是由处理消息者自己去等待消息是否被触发，而异步的情况下是由触发机制来通知处理消息者
，所以在异步机制中，处理消息者和触发机制之间就需要一个连接的桥梁：
在银行的例子中，这个桥梁就是小纸条上面的号码。
在小明的例子中，这个桥梁就是软件“叮”的声音。
