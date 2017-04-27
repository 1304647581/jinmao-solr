package com.jinmao.solr.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jinmao.solr.common.config.LogicParam;


public class SolrUrls {
	private static final Log LOG = LogFactory
			.getLog("----------- SolrUrls Error -----------");
	/**
     * 
     * @描述 : 合并URL
     * @创建者：lv
     * @创建时间： 2014-6-25下午4:13:56
     *
     * @param url url
     * @param params 参数
     * @return
     */
	public static String mergeUrl(String url, Map<String, String> params) {
		Set<String> keys = params.keySet();
		StringBuilder urlBuilder = new StringBuilder(url + "?");
		for (String key : keys) {
			urlBuilder.append(key).append("=").append(params.get(key)).append("&");
		}
		urlBuilder.delete(urlBuilder.length() - 1, urlBuilder.length());
		return urlBuilder.toString();
	}
	
	/***
	 * 
	 * @描述 : 拼接查询参数
	 * @创建者：lv
	 * @创建时间： 2014-8-4下午1:50:38
	 *
	 * @param params
	 * @return
	 */
	public static String mergeParam(List<LogicParam> params) {
		
		String returnParam = "";
		
		if(params!=null && params.size()!=0){
			for(int i=0;i<params.size();i++){
				if(i==0){
					returnParam += params.get(i).getParam();
				}else{
					returnParam += " "+params.get(i).getType()+" ";
					returnParam += params.get(i).getParam();
				}
			}
		}else{
			returnParam = "*:*";
		}
		LOG.info(returnParam);
		return returnParam.toString();
	}
	
	

}
