package yh.jdk.sjms.Proxy;

/**
 * Created by yango on 2017/3/22.
 */
public class Client {

    public static void main(String[] args){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject subject = (Subject)new MyInvocationHandler(new SubjectImpl()).getProxy();
        subject.print();



    }
}
