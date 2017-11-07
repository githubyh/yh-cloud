package yh.jdk.sjms.singleton;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * Created by yango on 2017/3/6.
 */
public class test {
	
	private int i = 0 ;

    public static  void main(String[] args){
    	String consumer = URLDecoder.decode("[consumer%3A%2F%2F172.16.104.22%2Fcom.alibaba.dubbo.demo.DemoService%3Fapplication%3Ddemo-consumer%26category%3Dconsumers%26check%3Dfalse%26dubbo%3D2.0.0%26interface%3Dcom.alibaba.dubbo.demo.DemoService%26methods%3DsayHello%26pid%3D37308%26side%3Dconsumer%26timestamp%3D1495780948024]");
    	String dubbo = URLDecoder.decode("[dubbo%3A%2F%2F172.16.104.22%3A20881%2Fcom.alibaba.dubbo.demo.DemoService%3Fanyhost%3Dtrue%26application%3Ddemo-provider%26dubbo%3D2.0.0%26generic%3Dfalse%26interface%3Dcom.alibaba.dubbo.demo.DemoService%26loadbalance%3Droundrobin%26methods%3DsayHello2%2CsayHello%26owner%3Dyh%26pid%3D11864%26side%3Dprovider%26timestamp%3D1495782364906, dubbo%3A%2F%2F172.16.104.22%3A20880%2Fcom.alibaba.dubbo.demo.DemoService%3Fanyhost%3Dtrue%26application%3Ddemo-provider%26dubbo%3D2.0.0%26generic%3Dfalse%26interface%3Dcom.alibaba.dubbo.demo.DemoService%26loadbalance%3Droundrobin%26methods%3DsayHello2%2CsayHello%26owner%3Dyh%26pid%3D45196%26side%3Dprovider%26timestamp%3D1495781812400]");
    	
        System.out.println(consumer);
        System.out.println(dubbo);
        test t =new test();
        new test().s(t);
        String s = "2017-06-01 10:28:36 029";
        if(s.length() > 19)
        	s = s.substring(0, 19);
        System.out.println(s);
        
        
        Date date = new Date();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Date date2 = new Date();
        System.out.println(date2.after(date));
        BigDecimal d = new BigDecimal(10);

        BigDecimal d1 = new BigDecimal(10);
        d.add(d1);
        System.out.println(d);
        
        String ss = "NNNNNNNNNNNNNNNNNNNNN1NN";
        String ss1 = "NNNNNNNNNNNNNNNNNNNNN22N";
        String ss2 = "NNNNNNNNNNNNNNNNNNNNNNN3";
        int i=0 ;
        i += addInt(ss);
        i += addInt(ss1);
        i += addInt(ss2);
        System.out.println(i);
        
    }
    
    public void s(test t){
    	test t1 = t ;
    	t1.i = 1;
    	System.out.println(t.i);
    }
    public static int addInt(String str) {
    	Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        
		 
		int cnt = 0 ;
		int end1 = 0;
		int end2 = 0;
		int end3 = 0;
		if (str.length() >= 1){
			try{
//			end1 = Integer.valueOf(str.substring(str.length() - 1));
				if(pattern.matcher(str.substring(str.length() - 1)).matches()){
					cnt++;
				}
			}catch(Throwable t){}
		}
		if (str.length() >= 2){
			try{
//			end2 = Integer.valueOf(str.substring(str.length() - 2, str.length() - 1));
				if(pattern.matcher(str.substring(str.length() - 2, str.length() - 1)).matches()){
					cnt++;
				}
			}catch(Throwable t){}
		}
		if (str.length() >= 3){
			try{
//			end3 = Integer.valueOf(str.substring(str.length() - 3, str.length() - 2));
				if(pattern.matcher(str.substring(str.length() - 3, str.length() - 2)).matches()){
					cnt++;
				}
			}catch(Throwable t){}
		}
		return cnt;
	}
}
