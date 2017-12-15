package com.yh.popoint;

/**
 * @author
 * @create 2017-12-15 10:06
 *
 *
 * http://www.ltang.me/2016/11/29/pinpoint-installing/ 安装部署
 *
 * http://blog.csdn.net/kangkanglou/article/details/30748139 安装部署
 http://m.blog.csdn.net/heyeqingquan/article/details/74456591
Pinpoint是什么
简单的说，Pinpoint是一款对Java编写的大规模分布式系统的APM工具，有些人也喜欢称呼这类工具为调用链系统、分布式跟踪系统。我们知道，前端向后台发起一个查询请求，后台服务可能要调用多个服务，每个服务可能又会调用其它服务，最终将结果返回，汇总到页面上。如果某个环节发生异常，工程师很难准确定位这个问题到底是由哪个服务调用造成的，Pinpoint等相关工具的作用就是追踪每个请求的完整调用链路，收集调用链路上每个服务的性能数据，方便工程师能够快速定位问题。

同类工具
google的Dapper 中文翻译
twitter的Zipkin
淘宝的鹰眼（EgleEye）
大众点评的CAT CAT的安装和配置
为什么要用Pinpoint
最重要的原因，对代码的零侵入，运用JavaAgent字节码增强技术，只需要加启动参数即可。

 **/
public class Test {



}
