package java.jdk.sjms.singleton;

/**
 * 线程安全，内部类方式采用了classloader机制保证了初始化ls时只有一个线程，与懒汉模式区别在于，它不一定会在类转载时被初始化，而是需要显示调用getInstance方法时才会转载InnerSingletonHolder类，从而初始化ls
 * Created by yango on 2017/1/20.
 */
public class InnerSingleton
{
    private static InnerSingleton ls = null;
    private InnerSingleton(){};

    private static class InnerSingletonHolder{
        static InnerSingleton in = new InnerSingleton();
    }

    public static InnerSingleton getInstance(){

        return InnerSingletonHolder.in;
    }

    public InnerSingleton readResolve(){
        return getInstance();
    }
}
