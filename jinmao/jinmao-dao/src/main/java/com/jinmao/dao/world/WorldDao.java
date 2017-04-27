package com.jinmao.dao.world;

import com.jinmao.domain.world.World;

/**
 * @描述 : 练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午7:53:37
 */
public interface WorldDao {
	/**
	 * @描述 : 添加
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午7:54:50
	 * @param world	信息
	 * @return	返回添加结果
	 */
	int insert(World world);
}