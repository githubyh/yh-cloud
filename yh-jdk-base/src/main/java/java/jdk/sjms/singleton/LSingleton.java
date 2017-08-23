package java.jdk.sjms.singleton;

/**
 * 线程安全，效率较低
 * Created by yango on 2017/1/20.
 */
public class LSingleton
{
    private static LSingleton ls = null;
    private LSingleton(){};

    public static synchronized LSingleton getInstance(){
        if (ls == null){
            ls = new LSingleton();
        }
        return ls;
    }
}
