package java.jdk.sjms.command;

/**
 * Created by yango on 2017/3/30.
 */
public class Client {
    public static void main(String[] args){
        Invoker invoker = new Invoker();
        invoker.setIcommand(new CommandImpl(new Receiver()));
        invoker.excuteCommand();
    }
}
