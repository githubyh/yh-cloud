/**   
 * @Project: Test 
 * @Title: Adapter.java 
 * @Package jdk.sjms.adapter 
 * @Description: TODO 
 * @author yango 
 * @date 2017年3月24日 上午10:39:19 
 * @Copyright: 2017 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package java.jdk.sjms.adapter;

/** 
 * @ClassName Adapter  
 * @Description TODO 
 * @author yango 
 * @date 2017年3月24日  
 *   
 */
public class Adapter implements Target {

	Adaptee adaptee ;
	
	public Adapter(Adaptee a){
		adaptee = a ;
	}
	
	@Override
	public void request() {
		adaptee.specifycRequest();
	}

}
