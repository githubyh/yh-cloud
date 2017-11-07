package yh.jdk.jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * 内存泄露
 * Created by yango on 2017/9/6.
 */
public class Jvmleakage {
    public static void main(String[] args)
    {
        new Jvmleakage().show();
    }

    private   void show() {
        Set<Person> set = new HashSet<Person>();
        Person p1 = new Person("唐僧", 25);
        Person p2 = new Person("孙悟空", 26);
        Person p3 = new Person("猪八戒", 27);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:3 个元素!
        p3.setAge(2); //修改p3的年龄,此时p3元素对应的hashcode值发生改变

        set.remove(p3); //此时remove不掉，造成内存泄漏

        set.add(p3); //重新添加，居然添加成功
        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:4 个元素!
        for (Person person : set)
        {
            System.out.println(person);
        }
    }

    class Person{
        private int age;
        private String name ;

        public Person(String name,int age){
            this.name = name;
            this.age= age;
        }
        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
