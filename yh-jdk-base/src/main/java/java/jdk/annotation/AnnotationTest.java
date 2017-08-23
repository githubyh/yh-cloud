package java.jdk.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//调用注解并赋值
@MyAnnotation(metaAnnotation = @MetaAnnotation(birthday = "我的出身日期为1988-2-18"), color = "red", array = {23, 26 })
public class AnnotationTest {

    @MyAnnotation(color = "ll",value = "了斯柯达积分",metaAnnotation = @MetaAnnotation(birthday = "lksd"))
    public static void s(){
        System.out.println("s.........");
    }

    public static void main(String[] args) {
        // 检查类AnnotationTest是否含有@MyAnnotation注解
        /*if (AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
            // 若存在就获取注解
            MyAnnotation annotation = (MyAnnotation) AnnotationTest.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation);
            // 获取注解属性
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            // 数组
            int[] arrs = annotation.array();
            for (int arr : arrs) {
                System.out.println(arr);
            }
            // 枚举
            Gender gender = annotation.gender();
            System.out.println("性别为：" + gender);
            // 获取注解属性
            MetaAnnotation meta = annotation.metaAnnotation();
            System.out.println(meta.birthday());
        }*/

        Method[] methods = AnnotationTest.class.getMethods();

        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                // 获取注解的具体类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (MyAnnotation.class == annotationType) {
                    Class<?>[] interfaces = MyAnnotation.class.getInterfaces();
                    System.out.println("=="+interfaces[0].getName());
                    MyAnnotation annotation2 = (MyAnnotation)annotation;

                    System.out.println(annotation2);
                    // 获取注解属性
                    System.out.println(annotation2.color());
                    System.out.println(annotation2.value());

                    // 获取注解属性
                    MetaAnnotation meta = annotation2.metaAnnotation();
                    System.out.println(meta.birthday());
                }
            }
        }
            }
}