package yh.jdk.jdk8;
/**
 * @Title: Student
 * @ProjectName yh-cloud-new
 * @Description: TODO
 * @author yango
 * @date 2018/8/1614:58
 */

import lombok.Data;

/**
 *
 * @author
 * @create 2018-08-16 14:58
 **/
@Data
public class Student {
    private String name ;
    private int age ;

    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }

}
