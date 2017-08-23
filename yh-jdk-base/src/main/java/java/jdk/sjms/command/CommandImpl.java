package java.jdk.sjms.command;

/**
 * Created by yango on 2017/3/30.
 */
public class CommandImpl implements Icommand {

    private Receiver  receiver;

    public CommandImpl(Receiver r){
        this.receiver  = r;
    }

    @Override
    public void excute() {
        System.out.println("CommandImpl命令执行");
        receiver.action();
    }
}
