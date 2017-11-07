/**   
 * @Project: Test 
 * @Title: Snippet.java 
 * @Package jdk.jvm 
 * @Description: TODO 
 * @author yango 
 * @date 2017年3月23日 下午2:33:06 
 * @Copyright: 2017 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package yh.jdk.jvm;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName Snippet  
 * @Description TODO 
 * @author yango 
 * @date 2017年3月23日  
 *   
 */
public class Snippet {

	final static int m = 1024 * 1024 ;
	final static byte[] b = new byte[2*m];

	private Object in ;
	private String s  ;
	public static void main(String[] args) {
		test3();
	}

	private static void test4() {
		Snippet s = new Snippet();
		Snippet s2 = new Snippet();

		s.in = s2;
		s2.in = s;

		s=null;
		s2 = null;
		System.gc();
	}

	private static void test3() {
		String s = "str";
		String str2 = new String("str")+new String("01");
		str2.intern();
		String str1 = "str01";
		System.out.println(str2==str1);//#7
		System.out.println(str2==s+"01");//#7


		String  s2 = new StringBuilder("ja").append("va").toString();
		System.out.println(s2.intern() == s2);
		String  s22 = new StringBuilder("11").append("软件").toString();
		System.out.println(s.intern() == s22);

	}
	private static void test2() {
		List list = new ArrayList();
		for (int i = 0; i < 100000000; i++) {
			list.add(String.valueOf(i).intern());
		}
	}
	private static void test1() {
		List<Snippet> list = new ArrayList<Snippet>();
		for (int i = 0; i < 100000000; i++) {
			Snippet str = new Snippet();
			str.s = i+"sdfsdffsdffsdfsdfdfsdsdffdsfddsfdsdsfdsfdsfsdfasdfasdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffasdfassafsd";
//			str = "asdfasdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffasdfassafsd";
			str.s  = str.s.intern();
			list.add(str);
		}
	}


	public static  void testPretenureSizeThreshold(){
		byte[] by = new byte[1024 * 1024 * 4];
		byte[] by2 = new byte[1024 * 1024 * 4];
		byte[] by3 = new byte[1024 * 1024 * 4];
		by = null;
		byte[] by4 = new byte[1024 * 1024 * 4];

	}
}
