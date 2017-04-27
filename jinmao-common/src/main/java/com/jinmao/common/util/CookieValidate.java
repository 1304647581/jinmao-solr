package com.jinmao.common.util;

import java.io.Serializable;
import java.util.Date;

import com.tools.common.util.date.DateUtil;
import com.tools.common.util.security.SecurityUtil;

/**
 * @描述 : 登录cookie验证类
 * @创建者：liushengsong
 * @创建时间： 2014-5-5下午5:18:12
 * 
 */
@SuppressWarnings("serial")
public class CookieValidate implements Serializable {

	/** 用户登录账户 **/
	private String pin;
	/** des规则加密串（pin+new Date()） **/
	private String test;
	/** MD5规则加密串（desPrefix+pin+new Date()） **/
	private String ceshi;
	/** des解密后value **/
	private String decryptStr;
	/** 登录时间value **/
	private String dateVal;
	/** 验证时间 **/
	private String validateDate;
	/** desPrefix+pin+new Date() MD5 **/
	private String validateStr;
	/** 加密定义的后缀 **/
	private String desPrefix;
	/** 解密后用户pin **/
	private String decryptPin;
	/**IP**/
	private String ip;
	/**浏览器版本**/
	private String brower;
	
	
	public String getBrower() {
		return brower;
	}

	public void setBrower(String brower) {
		this.brower = brower;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPin() {
		return pin;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public void setCeshi(String ceshi) {
		this.ceshi = ceshi;
	}

	/**
	 * @描述 : 初始化参数
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-4下午12:14:10
	 * @param desPrefix
	 * @param desKey
	 * @throws Exception
	 */
	public void init(String desPrefix, String desKey) throws Exception {
		this.validateStr = SecurityUtil.md5(new StringBuffer(desPrefix)
				.append(this.pin).append(DateUtil.formatDate(new Date()))
				.toString());
		this.decryptStr = SecurityUtil.decrypt(this.test, desKey);
		this.dateVal = this.decryptStr.split(",")[1].toString();
		this.validateDate = DateUtil.formatDate(new Date());
		this.desPrefix = desPrefix;
		this.decryptPin = this.decryptStr.split(",")[0].toString();
	}

	/**
	 * @描述 : 验证
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-4下午12:14:44
	 * 
	 * @return
	 */
	public boolean verify() {
		if (this.pin.equals(this.decryptPin)
				&& this.dateVal.equals(this.validateDate)
				&& this.validateStr.equals(this.ceshi)) {
			return true;
		}
		return false;
	}

	public boolean verifyWx(){
		if (this.pin.equals(this.decryptPin)) {
			return true;
		}
		return false;
	}
	public String getDecryptStr() {
		return decryptStr;
	}

	public void setDecryptStr(String decryptStr) {
		this.decryptStr = decryptStr;
	}

	public String getDateVal() {
		return dateVal;
	}

	public void setDateVal(String dateVal) {
		this.dateVal = dateVal;
	}

	public String getValidateDate() {
		return validateDate;
	}

	public void setValidateDate(String validateDate) {
		this.validateDate = validateDate;
	}

	public String getValidateStr() {
		return validateStr;
	}

	public void setValidateStr(String validateStr) {
		this.validateStr = validateStr;
	}

	public String getDesPrefix() {
		return desPrefix;
	}

	public void setDesPrefix(String desPrefix) {
		this.desPrefix = desPrefix;
	}

	public String getDecryptPin() {
		return decryptPin;
	}

	public void setDecryptPin(String decryptPin) {
		this.decryptPin = decryptPin;
	}

	public String getTest() {
		return test;
	}

	public String getCeshi() {
		return ceshi;
	}

}
