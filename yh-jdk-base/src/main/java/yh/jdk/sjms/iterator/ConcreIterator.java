package yh.jdk.sjms.iterator;

/**
 * Created by yango on 2017/3/31.
 */
public class ConcreIterator extends Iterator {

    Aggregate aggregate;
    private  int current = 0 ;
    public  ConcreIterator(Aggregate aggregate){
        this.aggregate = aggregate;
    }

    @Override
    public boolean hashNext() {
        return current < aggregate.getCount() ;
    }

    @Override
    public Object next() {
        return aggregate.getItems().get(current++);
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object currentItem() {
        return aggregate.getItems().get(current);
    }
}
