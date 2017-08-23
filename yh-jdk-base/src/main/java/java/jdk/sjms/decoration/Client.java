package java.jdk.sjms.decoration;

/**
 * Created by yango on 2017/3/27.
 */
public class Client {

    public static void  main(String[] args){
        Food chicken = new Chicken();
        Food duck = new Duck();

        Food roast = new RoastFood(chicken);
        Food steamed = new SteamedFood(roast);

        System.out.println(roast.getDesc());
        System.out.println(steamed.getDesc());

    }

}
