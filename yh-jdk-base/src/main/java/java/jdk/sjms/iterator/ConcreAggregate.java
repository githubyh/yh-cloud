package java.jdk.sjms.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yango on 2017/3/31.
 */
public class ConcreAggregate extends Aggregate {

    List list = new ArrayList();
    Iterator iterator ;
    public ConcreAggregate(){
        this.iterator = new ConcreIterator(this);
    }
    public ConcreAggregate(Iterator iterator){
        this.iterator = iterator;
    }

    @Override
    public Iterator iterator() {
        return  iterator;
    }

    @Override
    public List getItems() {
        return list;
    }

    @Override
    public void setItems(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
