package com.jinmao.dao.world.impl;

import com.jinmao.dao.world.WorldDao;
import com.jinmao.domain.world.World;
import com.tools.common.ibatis.base.BaseDao;

/**
 * @描述 : 练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午7:53:59
 */
public class WorldDaoImpl extends BaseDao implements WorldDao {

	@Override
	public int insert(World world) {
		return (Integer) insert("World.insert", world);
	}
}