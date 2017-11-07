/**   
 * @Project: Test 
 * @Title: General.java 
 * @Package jdk.sjms.adapter 
 * @Description: TODO 
 * @author yango 
 * @date 2017年3月24日 上午10:38:19 
 * @Copyright: 2017 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package yh.jdk.sjms.adapter;

/** 
 * @ClassName General  
 * @Description TODO 
 * @author yango 
 * @date 2017年3月24日  
 *   
 */
public class General implements Target {

	@Override
	public void request() {
		System.out.println("普通功能");
	}

}
