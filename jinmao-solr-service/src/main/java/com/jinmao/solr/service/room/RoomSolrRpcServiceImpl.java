package com.jinmao.solr.service.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.jinmao.solr.common.client.SolrClient;
import com.jinmao.solr.common.config.LogicParam;
import com.jinmao.solr.common.config.SolrBean;
import com.jinmao.solr.common.config.SolrReturnBean;
import com.jinmao.solr.common.config.SortBean;
import com.jinmao.solr.common.util.CommonUtil;
import com.jinmao.solr.domain.RoomBase;
import com.jinmao.solr.dubbo.room.param.RoomSolrParam;
import com.jinmao.solr.dubbo.room.service.RoomSolrRpcService;
@Service("roomSolrRpcService")
public class RoomSolrRpcServiceImpl implements RoomSolrRpcService {
	private static final Log LOG = LogFactory.getLog("----------- RoomSolrRpcServiceImpl Error -----------");
	// 注入Solr模型
	private SolrClient solrClient;

	@Override
	public List<Map<String, Object>> getRoomListByParam(RoomSolrParam param) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			List<RoomBase> roomlist = new ArrayList<RoomBase>();
			SolrReturnBean solrReturnBean = new SolrReturnBean();
			List<LogicParam> list = new ArrayList<LogicParam>();
			/** 区域 **/
			if (StringUtils.isNotBlank(param.getCounty())) {
				list.add(new LogicParam("county:" + param.getCounty(), "AND"));
			}
			/** 地铁 **/
			if (StringUtils.isNotBlank(param.getMetro())) {
				list.add(new LogicParam("metro:*" + param.getMetro()+"*", "AND"));
			}
			/** 朝向 **/
			if (StringUtils.isNotBlank(param.getFace())) {
				list.add(new LogicParam("face:" + param.getFace(), "AND"));
			}
			/** 特色 **/
			if (StringUtils.isNotBlank(param.getFeature())) {
				list.add(new LogicParam("feature:*" + param.getFeature()+"*", "AND"));
			}
			/** 房间状态【3已租、0未租、1预定、2预租】 **/
			if (param.getState() != null&&!param.getState().equals("")) {
				String[] str=param.getState().split(",");
				for(int i=0;i<str.length;i++){
					if(str.length>1){
						if(i==0){
							list.add(new LogicParam("(state:"+str[i],"AND"));
						}else if(i==str.length-1){
							list.add(new LogicParam("state:"+str[i]+")","OR"));
						}else{
							list.add(new LogicParam("state:"+str[i],"OR"));
						}
					}else{
						list.add(new LogicParam("state:" + str[i], "AND"));
					}
				}
			}
			/** 租金**/
			if (param.getRentPrice_start() != null&&param.getRentPrice_end() != null) {
				list.add(new LogicParam("rentPrice:["
						+  param.getRentPrice_start()+" TO "+param.getRentPrice_end()+" ]", "AND"));
			}
			/** 搜索 **/
			if (StringUtils.isNotBlank(param.getKeywords())) {
				list.add(new LogicParam("name-keyword1:*" + param.getKeywords()	+ "*", "AND"));
			}
			/**房屋編號**/
			if(param.getShsList()!=null&&param.getShsList().size()>0){
				for(int i=0;i<param.getShsList().size();i++){
					if(param.getShsList().size()>1){
						if(i==0){
							list.add(new LogicParam("(shsNo:"+param.getShsList().get(i),"AND"));
						}else if(i==param.getShsList().size()-1){
							list.add(new LogicParam("shsNo:"+param.getShsList().get(i)+")","OR"));
						}else{
							list.add(new LogicParam("shsNo:"+param.getShsList().get(i),"OR"));
						}
					}else{
						list.add(new LogicParam("shsNo:"+param.getShsList().get(i),"AND"));
					}
				}
			}
			SolrBean sb = new SolrBean();
			sb.setDbName("young_room");
			sb.setPage(param.getPage());
			sb.setPageSize(param.getPageSize());
			if (param.getSortKey() != null && !param.getSortKey().equals("")) {
				SortBean s0 = new SortBean();
				s0.setSortKey(param.getSortKey());
				s0.setSortBy(param.getSortBy());
				sb.getSortList().add(s0);
			} else {
				SortBean s1 = new SortBean();
				s1.setSortKey("createTime");
				s1.setSortBy("DESC");
				sb.getSortList().add(s1);
			}
			sb.setFacet(false);
			sb.setSelectParams(list);
			
			solrReturnBean = solrClient.readIndex(sb);
			roomlist = solrReturnBean.getQueryResponse().getBeans(RoomBase.class);
			if (roomlist.size() > 0) {
				for (RoomBase room : roomlist) {
					Map<String, Object> map = CommonUtil.transBean2Map(room);
					listmap.add(map);
				}
			}
		} catch (Exception e) {
			LOG.error("###### getRoomListByParam error #######", e);
		}
		return listmap;
	}

	@Override
	public int getRoomListCount(RoomSolrParam param) {
		int count = 0;
		try {
			SolrReturnBean solrReturnBean = new SolrReturnBean();
			List<LogicParam> list = new ArrayList<LogicParam>();
			/** 区域 **/
			if (StringUtils.isNotBlank(param.getCounty())) {
				list.add(new LogicParam("county:" + param.getCounty(), "AND"));
			}
			/** 地铁 **/
			if (StringUtils.isNotBlank(param.getMetro())) {
				list.add(new LogicParam("metro:*" + param.getMetro()+"*", "AND"));
			}
			/** 朝向 **/
			if (StringUtils.isNotBlank(param.getFace())) {
				list.add(new LogicParam("face:" + param.getFace(), "AND"));
			}
			/** 特色 **/
			if (StringUtils.isNotBlank(param.getFeature())) {
				list.add(new LogicParam("feature:*" + param.getFeature()+"*", "AND"));
			}
			/** 房间状态【3已租、0未租、1预定、2预租】 **/
			if (param.getState() != null) {
				String[] str=param.getState().split(",");
				for(int i=0;i<str.length;i++){
					if(str.length>1){
						if(i==0){
							list.add(new LogicParam("(state:"+str[i],"AND"));
						}else if(i==str.length-1){
							list.add(new LogicParam("state:"+str[i]+")","OR"));
						}else{
							list.add(new LogicParam("state:"+str[i],"OR"));
						}
					}else{
						list.add(new LogicParam("state:" + str[i], "AND"));
					}
				}
			}
			/** 租金**/
			if (param.getRentPrice_start() != null&&param.getRentPrice_end() != null) {
				list.add(new LogicParam("rentPrice:[" +  param.getRentPrice_start()+" TO "+param.getRentPrice_end()+" ]", "AND"));
			}
		
			/** 搜索 **/
			if (StringUtils.isNotBlank(param.getKeywords())) {
				list.add(new LogicParam("name-keyword1:*" + param.getKeywords()	+ "*", "AND"));
			}
			SolrBean sb = new SolrBean();
			sb.setDbName("young_room");
			sb.setPage(1);
			sb.setPageSize(10);
			sb.setHight(false);
			sb.setFacet(false);
			sb.setSelectParams(list);
			solrReturnBean = solrClient.readIndex(sb);
			String strJson = solrReturnBean.getQueryResponse().getResponse().get("response").toString();
			count = Integer.parseInt(strJson.substring(10, strJson.indexOf(",")));
		} catch (Exception e) {
			LOG.error("###### getRoomListCount error #######", e);
		}
		return count;
	}
	@Override
	public void updateSolrByName(String dbname) {
		try {
			solrClient.workSolr(dbname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @描述 : 测试方法
	 */
	public static void main(String[] args) {
		String[] str="1,2".split(",");
		System.out.println(str.length);
		System.out.println(str[0]);
	}
	/**************************get/set***************************************/
	public SolrClient getSolrClient() {
		return solrClient;
	}
	public void setSolrClient(SolrClient solrClient) {
		this.solrClient = solrClient;
	}
}
