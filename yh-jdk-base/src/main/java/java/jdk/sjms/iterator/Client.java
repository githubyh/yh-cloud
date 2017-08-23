package java.jdk.sjms.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yango on 2017/3/31.
 */
public class Client {
    public static  void  main(String[] args){
        List a = new ArrayList();
        a.add("1");
        a.add("2");
        a.add("3");
        Aggregate aggregate = new ConcreAggregate();
        aggregate.setItems(a);
        String[] ar ;
        Iterator iterator = aggregate.iterator();
        while (iterator.hashNext()) {
            System.out.println(iterator.next());
        }
    }
}
