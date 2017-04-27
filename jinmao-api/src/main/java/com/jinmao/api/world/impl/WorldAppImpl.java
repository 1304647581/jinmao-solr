package com.jinmao.api.world.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.jinmao.api.world.WorldApi;
import com.jinmao.common.base.BaseApi;
import com.jinmao.domain.world.World;
/**
 * @描述 : 实现夹包接口[手机app接口]
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午3:37:51
 */
@Path("/world")
@Service("worldApp")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class WorldAppImpl extends BaseApi implements WorldApi {
	/***********************声明区***********************************/
	private static final Log LOG = LogFactory.getLog(WorldAppImpl.class);
	/***********************方法区***********************************/
	@Override
	@GET
	@Path("/queryWorld")
	public World queryWorld(String code) {
		//实现功能
		
		return null;
	}
	
	
	
	
	
	/***********************get/set***********************************/
	
	
	
	
}