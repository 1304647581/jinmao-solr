package com.jinmao.domain.world;

import com.jinmao.common.base.BaseDomain;

/**
 * @描述 : dome
 * @创建者 : HeZeMin
 * @创建时间 : 2016-8-12 下午8:49:57
 */
public class WorldQuery extends BaseDomain {
	private static final long serialVersionUID = 1L;
	/** id **/
	private Integer id;
	/** 姓名 **/
	private String name;
	/** 年龄 **/
	private Integer age;
	/** 性别(0女,1男,2保密) **/
	private Integer sex;

	public WorldQuery() {
	}

	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-8-12 下午8:50:48
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-8-12 下午8:50:48
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午7:49:04
	 * @return the 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午7:49:04
	 * @param 姓名 the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午7:49:04
	 * @return the 年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午7:49:04
	 * @param 年龄 the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午7:49:04
	 * @return the 性别
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @描述 : 
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-9-8 下午7:49:04
	 * @param 性别 the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}