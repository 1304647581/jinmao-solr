package com.jinmao.service.world;

import com.jinmao.domain.world.World;
import com.tools.common.struts.result.Result;

/**
 * @描述 : 练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午7:57:07
 */
public interface WorldService {
	/**
	 * @描述 : 天假
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午8:06:37
	 * @param world	信息
	 * @return	返回添加的结果集
	 */
	Result insert(World world);
}