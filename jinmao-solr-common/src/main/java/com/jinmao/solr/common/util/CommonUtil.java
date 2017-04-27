package com.jinmao.solr.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.jinmao.solr.dubbo.room.param.RoomSolrParam;

public class CommonUtil {
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(String str, Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String[] s = str.split(",");
		Arrays.sort(s);
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				int index = Arrays.binarySearch(s, key);
				if (index > -1) {
					// 过滤class属性
					if (!key.equals("class")) {
						// 得到property对应的getter方法
						Method getter = property.getReadMethod();
						Object value = getter.invoke(obj);

						map.put(key, value);
					}

				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}
	
	public static void main(String[] args) {
		RoomSolrParam p=new RoomSolrParam();
		p.setCounty("天津");
		Map<String,Object> map=transBean2Map(p);
		System.out.println(map.toString());
		
	}
}
