package yh.jdk.sjms.decoration;

/**
 * 鸡
 * Created by yango on 2017/3/27.
 */
public class Chicken extends Food {

    public Chicken(){
        this.desc = "鸡";
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
