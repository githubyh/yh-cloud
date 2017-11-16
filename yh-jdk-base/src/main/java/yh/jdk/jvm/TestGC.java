package yh.jdk.jvm;

/**
 *
 * vm： -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:permSize=50m
 * -XX:NewRatio=:2  新生代：老年代  = 1：2
 *   -XXSurvivorRatio=3，即Eden：FromSurvivor：ToSurvivor=3:1:1;
 * @author
 * @create 2017-11-16 11:09
 **/
public class TestGC {

    private static final int _1MB = 1024 * 1024 ;

    @org.junit.Test
    public   void testallocation(){
        byte[] a1,a2,a3,a4,a5;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
//        a3 = new byte[1 * _1MB];
//        a5 = new byte[1 * _1MB];
        a4 = new byte[7 * _1MB]; //出现一次Minor GC


    }
}
