package com.jinmao.manager.world;

import com.jinmao.domain.world.World;

/**
 * @描述 : 练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午7:59:06
 */
public interface WorldManager {
	/**
	 * @描述 : 添加
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午8:03:41
	 * @param world	信息
	 * @return	返回添加结果
	 */
	boolean insert(World world);
}