package com.jinmao.domain.log;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Id;

public class OperateLog {
	/********* ID ***********/
	@Id private ObjectId id;
	/********* 登录id ***********/
	private String pin;
	/********* 登录人姓名 ***********/
	private String name;
	/********* 部门名称 ***********/
	private String deptName;
	/************* 操作类型:增删改 ****************/
	private String operateType;
	/************* 业务类型 ****************/
	private String serviceName;
	/************* 业务类型code ****************/
	private String serviceCode;
	/************* 资源code ****************/
	private String resourceCode;
	/************ 操作明细 ****************/
	private String operateDetail;
	/************ MAC地址 ****************/
	private String mac;
	/************ IP地址 ****************/
	private String ip;
	private Date createTime;
	
	public OperateLog() {
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getOperateDetail() {
		return operateDetail;
	}

	public void setOperateDetail(String operateDetail) {
		this.operateDetail = operateDetail;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
