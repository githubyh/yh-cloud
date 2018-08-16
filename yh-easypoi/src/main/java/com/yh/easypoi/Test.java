package com.yh.easypoi;
/**
 * @Title: Test
 * @ProjectName yh-cloud-new
 * @Description: TODO
 * @author yango
 * @date 2018/7/2419:46
 */

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author
 * @create 2018-07-24 19:46
 **/
public class Test {
    public static void main(String[] args) {
        export();
        importExcel();
    }

    public static void export(){

        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList();
        Person person1 = new Person("路飞","1",new Date());
        Person person2 = new Person("娜美","2", DateUtils.addDays(new Date(),3));
        Person person3 = new Person("索隆","1", DateUtils.addDays(new Date(),10));
        Person person4 = new Person("小狸猫","1", DateUtils.addDays(new Date(),-10));
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        FileUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"e://海贼王.xls");
//        FileUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);
    }

    public static void importExcel(){
        String filePath = "e:\\海贼王.xls";
        //解析excel，
        List<Person> personList = FileUtil.importExcel(filePath,1,1,Person.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");
        System.out.println(personList.toString());
        //TODO 保存数据库
    }

}
