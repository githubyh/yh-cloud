package java.jdk.sjms.iterator;

import java.util.List;

/**
 * Created by yango on 2017/3/31.
 */
public abstract class Aggregate {
    public abstract  Iterator iterator();
    public abstract List getItems();
    public abstract void setItems(List list);
    public abstract int getCount();
}
