package yh.jdk.sjms.obServer;

/**
 * 主题：被观察者
 * Created by yango on 2017/3/22.
 */
public interface Subject {

    public void attach(ObServer obServer);

    public void detach(ObServer obServer);

    public void notify(String message);
}
