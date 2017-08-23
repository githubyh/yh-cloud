package java.jdk.base;/*
package jdk.base;

import java.lang.reflect.Method;
  
import net.sf.cglib.proxy.Enhancer;  
import net.sf.cglib.proxy.MethodInterceptor;  
import net.sf.cglib.proxy.MethodProxy;  
  
public class CGLibTest {  
  
    public static void main(String[] args) {  
        new CGLibTest().testCGLIB();  
    }  
  
    public void testCGLIB() {  
     int i = 0;  
     while(true) {  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(EnhancerTest.class);  
        enhancer.setCallback(new MethodInterceptorImpl());  
  
        EnhancerTest demo = (EnhancerTest) enhancer.create();  
        //demo.test();  
        //System.out.println(demo);  
        System.out.println("Create " + (i++) +" instance:" + demo.getClass().getSimpleName());  
     }  
    }  
      
    static class EnhancerTest {  
           
    }  
  
    private static class MethodInterceptorImpl implements MethodInterceptor {  
        @Override  
        public Object intercept(Object obj, Method method, Object[] args,  
                MethodProxy proxy) throws Throwable {  
            //System.err.println("Before invoke " + method);  
            Object result = proxy.invokeSuper(obj, args);  
            //System.err.println("After invoke" + method);  
            return result;  
        }  
    }  
}  */
