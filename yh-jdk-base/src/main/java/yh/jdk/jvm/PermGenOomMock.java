package yh.jdk.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PermGenOomMock{
    public static void main(String[] args) {  
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {  
            url = new File("d:/tmp").toURI().toURL();
            URL[] urls = {url};  
            while (true){  
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);  
                loader.loadClass("jdk.jvm.Snippet");
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
