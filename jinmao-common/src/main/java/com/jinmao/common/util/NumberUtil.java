package com.jinmao.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/***
 * 
 * @描述: 数据格式转换工具类
 * @创建者：yuanwei
 * @创建时间： 2014-9-5上午11:27:38
 *
 */
public class NumberUtil {

	public static void main(String[] args) {
		System.out.println(NumberUtil.doubleStringFormat("16.263"));
    } 
	
	/***
	 * 
	 * @描述 : 把页面String类型的科学计数法转换成普通计数法
	 * @创建者：yuanwei
	 * @创建时间： 2014-9-5上午11:32:10
	 *
	 * @param doubleString
	 * @return
	 */
	public static String doubleStringFormat(String doubleString){
		Double d = Double.parseDouble(doubleString);  
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");//格式化设置  
        String str = decimalFormat.format(d);
        return str;
	}
	
	/***
	 * 
	 * @描述 : 把页面double类型的科学计数法转换成普通计数法
	 * @创建者：yuanwei
	 * @创建时间： 2014-9-5上午11:38:08
	 *
	 * @param doub
	 * @return
	 */
	public static String doubleFormat(double doub){
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");//格式化设置  
        String str = decimalFormat.format(doub);
        return str;
	}
	public static Double div(Double v1,Double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	public static long mathFormat(Double douBum){
		if (douBum==null) {
			return 0L;
		}
		return Math.round(douBum);
    }
	
	public static String subTel(String tel){
		if(tel!=null&&!tel.equals("")&&tel.length()>8){
			int between =tel.length()/2;
		    // 拼接最终值
		    String mobile = tel.substring(0, between-2) + 
		                                "****" + 
		                                tel.substring(between+2, tel.length());
		    return mobile;
		}else{
			return "";
		}
	}
}
