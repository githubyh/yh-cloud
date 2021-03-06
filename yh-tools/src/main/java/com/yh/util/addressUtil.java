public class AddressResolutionUtil {

   static Map map =new HashMap();
   static{
   	map.put("长沙市","湖南省");
   }
	
	/**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    public static List<Map<String,String>> addressResolution(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市|上海|天津|北京|重庆)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        if(address.startWith("广西") && !address.startWith("广西省")){
		 address = "广西壮族自治区" + address.substring(2);
	}else if (address.startWith("内蒙古") && !address.startWith("内蒙古自治区")){
		address = "内蒙古自治区" + address.substring(3);
	}
	
	for(Map.Entry<String,String> entry:map.entrySet()){
		if(address.startWith(entry.getKey())){
			address = entry.getValue()+address ;
		}
	}
	
	Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        return table;
    }
 
	public static void main(String[] args) {
		System.out.println(addressResolution("湖北省武汉市洪山区"));
    System.out.println(addressResolution("湖北省武汉市洪山区"));
System.out.println(addressResolution("湖北省恩施土家族苗族自治州恩施市"));
System.out.println(addressResolution("北京市市辖区朝阳区"));
System.out.println(addressResolution("内蒙古自治区兴安盟科尔沁右翼前旗"));
System.out.println(addressResolution("西藏自治区日喀则地区日喀则市"));
System.out.println(addressResolution("海南省省直辖县级行政单位中沙群岛的岛礁及其海域"));
	}
 
}
--------------------- 
作者：superSubfn 
来源：CSDN 
原文：https://blog.csdn.net/superSubfn/article/details/80290491 
版权声明：本文为博主原创文章，转载请附上博文链接！
