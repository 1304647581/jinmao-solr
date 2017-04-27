package com.jinmao.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @描述: Html代码工具类
 * @创建者：yuanwei
 * @创建时间： 2014-7-22下午5:09:42
 *
 */
public class HttpUtil {
	
	public static void main(String[] args) throws Exception {
		//System.out.println(getHtml("http://192.168.1.155/v1/tfs/T10yWTByKT1RXx1p6K.html"));
		System.out.println("http://192.168.1.155/v1/tfs/T10yWTByKT1RXx1p6K.html"
				.replaceAll("http://192.168.1.155/v1/tfs/", ""));
	}
	/***
	 * 
	 * @描述 : 根据url获得Html代码
	 * @创建者：yuanwei
	 * @创建时间： 2014-7-22下午5:09:23
	 *
	 * @param urlParam
	 * @return
	 */
	public static String getHtml(String urlParam){
		InputStreamReader  is = null;
		StringBuffer sb = new StringBuffer();
		try {
			is = new InputStreamReader(new URL(urlParam).openStream(),"UTF-8");
			int ch;
			while((ch = is.read())!=-1){
				sb.append((char)ch);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/***
	 * 
	 * @描述 : 获得文本中所有图片名称用于删除
	 * @创建者：yuanwei
	 * @创建时间： 2014-7-23上午11:27:53
	 *
	 * @param content
	 * @return
	 */
	public static List<String> getImgListForDelete(String content){
		
		String str = content;
		List<String> list = new ArrayList<String>();
		while(str.indexOf("<img src=\"")!=-1){
			int i = str.indexOf("<img src=\"");
			str = str.substring(i+10, str.length());
			int j = str.indexOf("\"");
			String temp = str.substring(0,j);
			list.add(temp);
			str = str.substring(temp.length(), str.length());
		}
		return list;
	}
} 