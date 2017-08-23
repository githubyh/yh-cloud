package java.jdk.sjms.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yango on 2017/3/22.
 */
public class MyInvocationHandler implements InvocationHandler {

    Object object ;
    public   MyInvocationHandler(Object object){
        this.object = object;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args != null)
        for(int i=0;i<args.length;i++){
                       System.out.println(args[i]);
                    }
                 Object ret=null;
               try{
                       /*原对象方法调用前处理日志信息*/
                        System.out.println("satrt-->>");

                       //调用目标方法
                       ret=method.invoke(object, args);
                        /*原对象方法调用后处理日志信息*/
                      System.out.println("success-->>");
                  }catch(Exception e){
                  e.printStackTrace();
                       System.out.println("error-->>");
                   throw e;
                  }
              return ret;
    }
}
