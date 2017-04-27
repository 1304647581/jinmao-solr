package com.jinmao.api.world.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.jinmao.api.world.WorldApi;
import com.jinmao.domain.world.World;

/**
 * @描述 : 实现夹包接口[其他系统调用接口]
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午3:37:51
 */
@Service("worldApi")
public class WorldApiImpl implements WorldApi {
	/***********************声明区***********************************/
	private static final Log LOG = LogFactory.getLog(WorldApiImpl.class);
	//WorldManager worldManager;
	/***********************方法区***********************************/

	@Override
	public World queryWorld(String code) {
		//实现功能
		return null;
	}
	
	
	
	
	
	/***********************get/set***********************************/
	
	
	
	
}