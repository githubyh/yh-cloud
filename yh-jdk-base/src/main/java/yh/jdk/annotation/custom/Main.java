package yh.jdk.annotation.custom;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        try {
            Person person = new Person();
            person.setId(1);
            person.setAge(50);
            person.setName("张三");
            person.setAddress("北京");
            validateParam(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证数据完整性
     * 
     * @param person
     * @throws Exception
     */
    public static void validateParam(Person person) throws Exception{
        Class<?> personClass = (Class<?>)person.getClass();
        Field[] field = personClass.getDeclaredFields();

        for (int i = 0; i < field.length; i++) {
             Field f= field[i];

            //启用和禁用访问安全检查的开关,JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
            //当变量为private时，需要设置true才能进行访问
             f.setAccessible(true);
            if (f.getAnnotation(NotEmpty.class)!= null) {
                Object attrValue=f.get(person);
                if(attrValue==null||attrValue.toString().equals("")){
                    //throw new RuntimeException(f.getName()+"属性值不能为空");
                    System.out.println(f.getName()+"属性值不能为空");
                }
                continue;
            }
        }
    }
}