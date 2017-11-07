package yh.jdk.sjms.iterator;

/**
 * Created by yango on 2017/3/31.
 */
public abstract class Iterator {
    public abstract boolean hashNext();
    public abstract Object next();
    public abstract Object remove();
    public abstract Object currentItem();

}
