package yh.jdk.sjms.command;

/**
 * Created by yango on 2017/3/30.
 */
public class Invoker {

    public void setIcommand(Icommand icommand) {
        this.icommand = icommand;
    }

    private Icommand icommand;

    public void excuteCommand(){
        icommand.excute();
    }

}
