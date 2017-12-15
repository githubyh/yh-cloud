package yh.jdk.classload;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class Main {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;

        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

    };

    public static void main(String args[]) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        Class clazz = classLoader.loadClass("yh.jdk.classload.Test");
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
        System.out.println(obj.getClass()  == clazz );
        System.out.println(obj.getClass().getName()+"=="+clazz.getName() );

    }
}