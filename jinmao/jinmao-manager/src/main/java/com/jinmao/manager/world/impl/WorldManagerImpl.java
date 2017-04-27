package com.jinmao.manager.world.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinmao.dao.world.WorldDao;
import com.jinmao.domain.world.World;
import com.jinmao.manager.world.WorldManager;

/**
 * @描述 : 练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午7:59:22
 */
@Component("worldManager")
public class WorldManagerImpl implements WorldManager {
	/***********************声明区************************************/
	private static final Log LOG = LogFactory.getLog(WorldManagerImpl.class);
	private PlatformTransactionManager transactionManager;
	private WorldDao worldDao;
	/***********************方法区************************************/
	/**
	 * @描述 : ibatis事务
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-6-12 上午9:36:32
	 */
	public TransactionTemplate getDataSourceTransactionManager() {
		return new TransactionTemplate(transactionManager);
	}
	@Override
	public boolean insert(World world) {
		try {
			int fag = worldDao.insert(world);
			if(fag > 0){
				return true;
			}
		} catch (Exception e) {
			LOG.error("--------------insert---------------error----------------", e);
		}
		return false;
	}
	/***********************get/set*********************************/
	public WorldDao getWorldDao() {
		return worldDao;
	}
	public void setWorldDao(WorldDao worldDao) {
		this.worldDao = worldDao;
	}
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}