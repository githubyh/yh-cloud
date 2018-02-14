package yh.jdk.concurrent;

/**
 * @author
 * @create 2018-02-14 11:52
 **/
public class MyThreadLocal {

     static  ThreadLocal threadLocal = new ThreadLocal();

    public static void  main(String[] args){
        threadLocal.set("a");
    }
}
