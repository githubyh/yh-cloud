package yh.jdk.base;

import java.io.Serializable;

/**
 * @author
 * @create 2017-11-30 10:55
 **/
public class OverLoad {

//    public  static void sayHello(Object o){
//        System.out.println("object");
//    }

    public  static void sayHello(int o){
        System.out.println("int");
    }

    public  static void sayHello(long o){
        System.out.println("long");
    }

    public  static void sayHello(char o){
        System.out.println("char");
    }

    public  static void sayHello(Character o){
        System.out.println("Character");
    }

    public  static void sayHello(Serializable o){
        System.out.println("Serializable");
    }

    public  static void sayHello(char...  o){
        System.out.println("char...");
    }

    public  static  void  main(String[] args){
        sayHello('a');
    }
}
