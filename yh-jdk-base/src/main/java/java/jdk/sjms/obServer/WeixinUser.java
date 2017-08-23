package java.jdk.sjms.obServer;

/**
 * 具体观察者
 * Created by yango on 2017/3/22.
 */
public class WeixinUser implements ObServer {

    private  String name ;
    public WeixinUser(String name){
        this.name = name;
    }

    @Override
    public void update(String s){
        System.out.println(Thread.currentThread().getName() + " name= " + name + " = " + s);
    }
}
