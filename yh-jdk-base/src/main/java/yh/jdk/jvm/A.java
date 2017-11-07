package yh.jdk.jvm;

class A {
    public A() {
        B.getInstance().setA(this);
    }

}

//B类采用单例模式 
class B {
    private A a;
    private static B instance = new B();

    public B() {
    }

    public static B getInstance() {
        return instance;
    }

    public void setA(A a) {
        this.a = a;
    }
//getter... 
} 