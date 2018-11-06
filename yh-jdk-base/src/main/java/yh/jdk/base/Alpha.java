package yh.jdk.base;

public class Alpha {
    public static void foo() {
        System.out.print("Afoo ");
    }


    public  static void main(String[] args) {
        Alpha a = new Beta();
        Beta b = (Beta) a;
        a.foo();
        b.foo();

        String str1 = "banalan";
        String str2 = ("bana" + new String("lan")).intern();
        System.err.println(str1 == str2);
    }
}
  class Beta extends Alpha {
    public static void foo1() {
        System.out.print("Bfoo");
    }


}