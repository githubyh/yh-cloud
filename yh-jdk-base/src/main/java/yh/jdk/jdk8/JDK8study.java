package yh.jdk.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Title: JDK8study
 * @ProjectName yh-cloud-new
 * @Description: TODO
 * @author yango
 * @date 2018/8/1614:51
 *
 * https://juejin.im/post/5b6d801af265da0f926bb2a2
 *
 */

public class JDK8study {


    @Test
    public void teststream(){
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三",10));
        list.add(new Student("李四",20));
        list.add(new Student("李四",20));
        list.add(new Student("王八",30));
        list.add(new Student("老李",50));
        list.add(new Student("老李",50));

        System.out.println(list.stream().findFirst());
        System.out.println(list.stream().filter(e->e.getAge()==30).collect(toList()));
        System.out.println(list.stream().distinct().collect(toList()));
        System.out.println(list.stream().map(Student::getName).collect(toList()));


        List<String> list2 = new ArrayList<>();
        list2.add("aaa bbb ccc");
        list2.add("ddd eee fff");
        list2.add("ggg hhh iii");
        System.out.println(list2.stream().map(e->e.split(" ")).flatMap(Arrays::stream).collect(toList()));
    }
}
