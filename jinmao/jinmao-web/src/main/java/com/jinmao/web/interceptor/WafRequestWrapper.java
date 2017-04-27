package com.jinmao.web.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.jinmao.common.util.WafHelper;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @描述 : 
 */
public class  WafRequestWrapper {
	private static boolean filterXSS = true;
	private static boolean filterSQL = true;
	@SuppressWarnings("rawtypes")
	public static  void filterParamString( Map valueTreeMap,ValueStack valueStack){
		  Iterator iterator = valueTreeMap.entrySet().iterator();
		  while(iterator.hasNext()){
			   //获得迭代的键值对
			   Entry entry = (Entry) iterator.next();
			   //获得键值对中的键值
			   String key = (String) entry.getKey();
			   //原请求参数，因为有可能一键对多值所以这里用的String[]
			   String[] oldValues = null;
			   //对参数值转换成String类型的
			   if(entry.getValue() instanceof String){
			    oldValues = new String[]{entry.getValue().toString()};
			   }else{
			    oldValues = (String[])entry.getValue();
			   }
			   //处理后的请求参数
			   String newValueStr = null;
			   //对请求参数过滤处理
			   if(oldValues.length>1){
			    newValueStr = "{" ;
			    for(int i=0 ;i<oldValues.length; i++){
			     //替换掉非法参数，这里只替换掉了'，如有其他需求，可以专门写一个处理字符的类
			     newValueStr+=filterParamString(oldValues[i].toString());
			     if(i!=oldValues.length-1){
			      newValueStr+=",";
			     }
			    }
			    newValueStr+="}";
			   }else if(oldValues.length==1){
			    //替换掉非法参数，这里只替换掉了'，如有其他需求，可以专门写一个处理字符的类
			    newValueStr = filterParamString(oldValues[0].toString());

			   }else{
			    newValueStr = null;
			   }
			   //处理后的请求参数加入值栈中
			   valueStack.setValue(key, newValueStr);
			  }
	
 }
	



	/**
	 * @Description 过滤字符串内容
	 * @param rawValue
	 * 				待处理内容
	 * @return
	 */
	protected static String filterParamString(String rawValue) {
		if (rawValue == null) {
			return null;
		}
		String tmpStr = "";
		if (filterXSS) {
			tmpStr = WafHelper.stripXSS(rawValue);
		}
		if (filterSQL) {
			tmpStr = WafHelper.stripSqlInjection(tmpStr);
		}
		return tmpStr;
	}
	
}
