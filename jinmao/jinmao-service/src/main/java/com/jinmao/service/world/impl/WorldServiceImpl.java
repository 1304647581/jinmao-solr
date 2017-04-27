package com.jinmao.service.world.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.jinmao.common.dict.AjaxDict;
import com.jinmao.domain.world.World;
import com.jinmao.manager.world.WorldManager;
import com.jinmao.service.world.WorldService;
import com.tools.common.struts.result.Result;

/**
 * @描述 : 练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午7:57:18
 */
@Service("worldService")
public class WorldServiceImpl implements WorldService {
	/***********************声明区************************************/
	private static final Log LOG = LogFactory.getLog(WorldServiceImpl.class);
	private WorldManager worldManager;
	/***********************方法区************************************/
	@Override
	public Result insert(World world) {
		Result result = new Result();
		boolean bool = worldManager.insert(world);
		if(bool){//添加成功
			result.addModel("message", AjaxDict.REQUEST_SUCCESS.getState());
		}else{//添加失败
			LOG.info("-----------insert-------------------------");
			result.addModel("message", AjaxDict.REQUEST_FAILURE.getState());
		}
		return result;
	}
	/***********************get/set*********************************/
	public WorldManager getWorldManager() {
		return worldManager;
	}
	public void setWorldManager(WorldManager worldManager) {
		this.worldManager = worldManager;
	}
}