package java.jdk.sjms.decoration;

/**
 * Created by yango on 2017/3/27.
 */
public class RoastFood extends  FoodDecoration {

    Food food;

    public RoastFood(Food food){
        this.food = food;
    }

    @Override
    public String getDesc() {
        return getFoodDec() + food.getDesc();
    }

    @Override
    public String getFoodDec() {
        return "烤";
    }

}
