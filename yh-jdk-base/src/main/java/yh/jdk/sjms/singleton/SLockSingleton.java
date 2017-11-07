package yh.jdk.sjms.singleton;

/**
 * 线程安全，双重检查锁机制，效率较好
 * Created by yango on 2017/1/20.
 */
public class SLockSingleton
{
    private static SLockSingleton ls = null;
    private SLockSingleton(){};

    public static SLockSingleton getInstance(){
        if (ls == null){
            synchronized (SLockSingleton.class){
                if(ls == null)
                    ls = new SLockSingleton();
            }
        }
        return ls;
    }
}
