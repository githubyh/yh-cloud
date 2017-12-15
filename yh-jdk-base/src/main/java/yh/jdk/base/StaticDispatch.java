package yh.jdk.base;

/**
 * @author
 * @create 2017-11-30 10:58
 **/
public class StaticDispatch {

      class  Human{
         public       void sayHello(Human guy){}
     }

     class  Man extends Human {

         public       void sayHello(Human guy){}
     }

     class  Woman extends Human {}

    public static   void sayHello(Human guy){
        System.out.println(" human");
    }

    public   void sayHello(Man guy){
        System.out.println(" man");
    }

    public   void sayHello(Woman guy){
        System.out.println(" Woman");
    }

    public static void main(String[] args){
        new StaticDispatch().show();
    }

    private   void show() {
         Human man = new Man();
        Human woman = new Woman();

//        sayHello(man);
//        sayHello(man);
        new StaticDispatch().sayHello(man);
        new StaticDispatch().sayHello(woman);
        System.out.println();
    }
}
