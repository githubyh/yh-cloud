package java.jdk.sjms.singleton;

/**
 * 类转载时初始化，多线程下线程安全
 * Created by yango on 2017/1/20.
 */
public class ESingleton
{
    private static ESingleton ls = new ESingleton();
    private ESingleton(){};

    public static  ESingleton getInstance(){

        return ls;
    }
}
