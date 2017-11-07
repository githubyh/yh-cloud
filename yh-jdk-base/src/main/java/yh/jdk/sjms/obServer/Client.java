package yh.jdk.sjms.obServer;

/**
 * Created by yango on 2017/3/22.
 */
public class Client {
    public static void main(String[] args){
        WeixinUser weixinUser1= new WeixinUser("省道");
        WeixinUser weixinUser2= new WeixinUser("省道2");
        WeixinUser weixinUser3= new WeixinUser("省道3");
        Subject subject = new SubScriptionSubject();
        subject.attach(weixinUser1);
        subject.attach(weixinUser2);
        subject.attach(weixinUser3);
        subject.notify("发通知了。。。。");
    }
}
