package yh.jdk.tool;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.file.Path;

public class JavaPath {
       public static void main(String[] args) throws MalformedURLException,URISyntaxException {

           String tomcatPath = System.getProperty("user.dir");
           System.out.print("tomcatPath:" + tomcatPath + " ");
           String filepath = "";
           if (tomcatPath.contains("//")) {
               filepath = tomcatPath.replace("//bin", "//webapps//CDNManageSystem//") + "speed.txt";
           } else {
               filepath = tomcatPath.replace("/bin", "/webapps/CDNManageSystem/") + "speed.txt";
           }
           URL url = JavaPath.class.getClassLoader().getResource("/a.properties");
           String path = null;
           try {
               path = URLDecoder.decode(url.getPath(), "utf-8");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
           System.out.print("path:" + path + "   ");
                   System.out.print("filepath:" + filepath + "   ");

//           SystemShow();
       }

    private static void SystemShow() throws URISyntaxException {
        System.out.println("java.home :"+System.getProperty("java.home"));
        System.out.println("java.class.version :"+System.getProperty("java.class.version"));
        System.out.println("java.class.path: "+System.getProperty("java.class.path"));
        System.out.println("java.library.path :"+System.getProperty("java.library.path"));
        System.out.println("java.io.tmpdir :"+System.getProperty("java.io.tmpdir"));
        System.out.println("java.compiler :"+System.getProperty("java.compiler"));
        System.out.println("java.ext.dirs :"+System.getProperty("java.ext.dirs"));
        System.out.println("user.name : "+System.getProperty("user.name"));
        System.out.println("user.home :"+System.getProperty("user.home"));
        System.out.println("user.dir :"+System.getProperty("user.dir"));
        System.out.println("===================");
        System.out.println("package:"+JavaPath.class.getPackage().getName());
        System.out.println("package:"+JavaPath.class.getPackage().toString());
        System.out.println("=========================");
        String packName =JavaPath.class.getPackage().getName();

        URI packuri = new URI(packName);
        System.out.println(packuri.getPath());
        //System.out.println(packuri.toURL().getPath());
        System.out.println(packName.replaceAll("\\.", "/"));
        System.out.println(System.getProperty("user.dir")+"/"+(JavaPath.class.getPackage().getName()).replaceAll("\\.","/")+"/");
        //(Test.class.getPackage().getName()).replaceAll("//.","/")   当前包路径。
    }
}
