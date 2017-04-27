package com.jinmao.api.world;

import com.jinmao.domain.world.World;

/**
 * @描述 : 接口类,必须单独写成夹包
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午3:37:33
 */
@Deprecated
public interface WorldApi {
	/**
	 * @描述 : 根据code查询
	 * @创建者 : HeZeMin
	 * @创建时间 : 2017-3-8 下午3:50:10
	 */
	World queryWorld(String code);
}