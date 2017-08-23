package java.jdk.sjms.decoration;

/**
 * 鸭
 * Created by yango on 2017/3/27.
 */
public class Duck extends Food {
    public Duck(){
        this.desc = "鸭";
    }
    @Override
    public String getDesc() {
        return desc;
    }
}
