dubbo 源码解析：

http://www.iocoder.cn/
http://blog.csdn.net/qq418517226/article/list/1

dubbo官方文档 https://dubbo.gitbooks.io/dubbo-dev-book/content/design.html





```sequence
title: 服务提供者启动(xml解析部分)序列图
DubboNamespaceHandler -> DubboBeanDefinitionParser :  init()
DubboBeanDefinitionParser -> ParserContext : parse:registerBeanDefinition(...)

```



```sequence
title: 服务提供者启动(类加载)序列图 
ServiceConfig -> ExtensionLoader : load Protocol.class
ExtensionLoader -> ExtensionLoader : getExtensionLoader(Protocol.class) 首次进来
ExtensionLoader -> ExtensionLoader : ExtensionLoader(Protocol.class) 构造方法准备创建ExtensionLoader存入：EXTENSION_LOADERS中（注意EXTENSION_LOADERS 为类变量）  
ExtensionLoader -> ExtensionLoader : getExtensionLoader(ExtensionFactory.class)  首先加载ExtensionFactory
ExtensionLoader -> ExtensionLoader :  ExtensionLoader(ExtensionFactory.class)
ExtensionLoader -> ExtensionLoader（ExtensionFactory）:getAdaptiveExtension()
ExtensionLoader（ExtensionFactory） -> ExtensionLoader（ExtensionFactory）:createAdaptiveExtension()
ExtensionLoader（ExtensionFactory） -> ExtensionLoader（ExtensionFactory）:getAdaptiveExtensionClass()
ExtensionLoader（ExtensionFactory） -> ExtensionLoader（ExtensionFactory）:getExtensionClasses()
ExtensionLoader（ExtensionFactory） -> ExtensionLoader（ExtensionFactory）:loadExtensionClasses() 读取当前class（ExtensionFacotry.class） 因添加SPI注解，所以读取SPI对应文件获取class，创建对象进行存储，存储分三部分： 对于一个接口的实现者，ExtensionLoader分三种情况来分别存储对应的实现者，属性分别如下：Class<?> cachedAdaptiveClass； Set<Class<?>> cachedWrapperClasses；Reference<Map<String, Class<?>>> cachedClasses；情况1：如果这个class含有Adaptive注解，则将这个class设置为 cachedAdaptiveClass。情况2：尝试获取带对应接口参数的构造器，如果能够获取到，则说明这个class是一个装饰类，需要存到cachedWrapperClasses中  情况3：如果没有上述构造器。则将文件中的key作为当前key，存至cachedClasses结构中
ExtensionLoader（ExtensionFactory） --> ExtensionLoader（ExtensionFactory） : 回到getExtensionLoader方法，ExtensionFactory.class 对应的ExtensionLoader对象存入EXTENSION_LOADERS中

ExtensionLoader（ExtensionFactory） -> ExtensionLoader（ExtensionFactory） :构造方法执行getAdaptiveExtension() 

ExtensionLoader（ExtensionFactory） --> ExtensionLoader  : 回到ExtensionLoader(Protocol.class) 方法，ExtensionFactory 对象赋值给objectFactory

ExtensionLoader -> ExtensionLoader  : Protocol.getAdaptiveExtension() 


ExtensionLoader -> ExtensionLoader  :createAdaptiveExtension()
ExtensionLoader -> ExtensionLoader  :getAdaptiveExtensionClass()
ExtensionLoader -> ExtensionLoader  :getExtensionClasses() 读取spi配置文件读取所有class
ExtensionLoader -> ExtensionLoader  :createAdaptiveExtensionClass()
ExtensionLoader -> ExtensionLoader  :createAdaptiveExtensionClassCode() 生成Protocol$Adaptive 类并通过compiler编译class， Compiler也是通过spi机制加载，走的流程与生成Protocol类似，通过加载AdaptiveCompiler对象，再执行具体编译器的编译操作（jdk，javassist：默认）
ExtensionLoader --> ExtensionLoader  : 最终回到getAdaptiveExtension()方法， 赋值给objectFactory
ExtensionLoader --> ServiceConfig  :  到此Protocol对象加载完毕！


```

```sequence
title: 服务提供者启动(类加载)序列图 
ServiceConfig -> ExtensionLoader : load Protocol.class
ExtensionLoader -> ExtensionLoader : getExtensionLoader(Protocol.class) 首次进来
ExtensionLoader -> ExtensionLoader : ExtensionLoader(Protocol.class)  
ExtensionLoader -> ExtensionLoader : getExtensionLoader(ExtensionFactory.class)  
ExtensionLoader -> ExtensionLoader :  ExtensionLoader(ExtensionFactory.class)
ExtensionLoader -> ExtensionLoader（ExtensionFactory）:getAdaptiveExtension()
ExtensionLoader（ExtensionFactory） -> ExtensionLoader（ExtensionFactory）:createAdaptiveExtension()
ExtensionLoader（ExtensionFactory） -> ExtensionLoader（ExtensionFactory）:getAdaptiveExtensionClass()
ExtensionLoader（ExtensionFactory）-> ExtensionLoader（ExtensionFactory）:getExtensionClasses()
ExtensionLoader（ExtensionFactory）-> ExtensionLoader（ExtensionFactory）:loadExtensionClasses() 
ExtensionLoader（ExtensionFactory）--> ExtensionLoader（ExtensionFactory）: 回到getExtensionLoader方法 
ExtensionLoader（ExtensionFactory）-> ExtensionLoader（ExtensionFactory）:构造方法执行getAdaptiveExtension() 

ExtensionLoader（ExtensionFactory）  --> ExtensionLoader  : 回到ExtensionLoader(Protocol.class) 方法 

ExtensionLoader -> ExtensionLoader  : Protocol.getAdaptiveExtension() 


ExtensionLoader -> ExtensionLoader  :createAdaptiveExtension()
ExtensionLoader -> ExtensionLoader  :getAdaptiveExtensionClass()
ExtensionLoader -> ExtensionLoader  :getExtensionClasses()  
ExtensionLoader -> ExtensionLoader  :createAdaptiveExtensionClass()
ExtensionLoader -> ExtensionLoader  : 生成Protocol$Adaptive  
ExtensionLoader --> ExtensionLoader  : 最终回到getAdaptiveExtension()方法 
ExtensionLoader --> ServiceConfig  :  到此Protocol对象加载完毕！
```

