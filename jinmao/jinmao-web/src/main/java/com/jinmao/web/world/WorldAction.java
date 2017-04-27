package com.jinmao.web.world;

import com.jinmao.common.base.BaseAction;
import com.jinmao.domain.world.World;
import com.jinmao.domain.world.WorldQuery;
import com.jinmao.service.world.WorldService;
import com.tools.common.struts.result.Result;

/**
 * @描述 : 练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午8:11:07
 */
public class WorldAction extends BaseAction {
	/***********************声明区************************************/
	private static final long serialVersionUID = 1L;
	private WorldService worldService;
	private World world;
	private WorldQuery worldQuery;
	/***********************方法区************************************/
	/**
	 * @描述 : 进入练习页面
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午8:12:34
	 */
	public String index(){
		return SUCCESS;
	}
	/**
	 * @描述 : 进入添加页面
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午8:24:50
	 */
	public String addIndex(){
		return "add";
	}
	/**
	 * @描述 : 添加
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午8:23:46
	 */
	public void add(){
		Result result = worldService.insert(world);
		print(result.resultJson());
	}
	
	
	
	/***********************get/set*********************************/
	public WorldService getWorldService() {
		return worldService;
	}
	public void setWorldService(WorldService worldService) {
		this.worldService = worldService;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public WorldQuery getWorldQuery() {
		return worldQuery;
	}
	public void setWorldQuery(WorldQuery worldQuery) {
		this.worldQuery = worldQuery;
	}
}