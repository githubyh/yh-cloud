package java.jdk.sjms.obServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yango on 2017/3/22.
 */
public class SubScriptionSubject implements  Subject{

    //储存订阅公众号的微信用户
    private List<ObServer> weixinUserlist = new ArrayList<ObServer>();

    @Override
    public void attach(ObServer obServer) {
        weixinUserlist.add(obServer);
    }

    @Override
    public void detach(ObServer obServer) {
        weixinUserlist.remove(obServer);
    }

    @Override
    public void notify(String message) {
        for (ObServer  a:weixinUserlist) {
            a.update(message);
        }
        System.out.println("通知完成所有=");
    }
}
