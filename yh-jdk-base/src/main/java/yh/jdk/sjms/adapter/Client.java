/**   
 * @Project: Test 
 * @Title: Client.java 
 * @Package jdk.sjms.adapter 
 * @Description: TODO 
 * @author yango 
 * @date 2017年3月24日 上午10:40:20 
 * @Copyright: 2017 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package yh.jdk.sjms.adapter;

/** 
 * @ClassName Client  
 * @Description TODO 
 * @author yango 
 * @date 2017年3月24日  
 *   
 */
public class Client {
	
	public static void main(String[] args){
		Target t= new General();
		t.request();
		
		Target t2=  new Adapter(new Adaptee());
		t2.request();
	}

}
