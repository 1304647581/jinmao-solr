package com.jinmao.common.base;

import com.jinmao.common.dict.LoginDict;
import com.opensymphony.xwork2.ActionContext;

public class BaseAction extends com.tools.common.struts.base.BaseAction {
	private static final long serialVersionUID = 1L;
	/** 用户pin 在context中的key **/
	public static final String PIN = "_pin";

	/**
	 * @描述 : 获取当前用户登陆名
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-5-4上午11:44:27
	 * @return
	 * @throws Exception
	 */
	protected String getErpPin() throws Exception {
		String pin = (String) ActionContext.getContext().get(LoginDict.ERP_PIN);
		return pin;
	}

	/**
	 * @描述 : 获取当前用户登陆名
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-5-4上午11:44:27
	 * @return
	 * @throws Exception
	 */
	protected String getErpUserName() throws Exception {
		String pin = (String) ActionContext.getContext()
				.get(LoginDict.ERP_NAME);
		return pin;
	}

	/**
	 * @描述 : 获取当前用户登陆名
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-5-4上午11:44:27
	 * @return
	 * @throws Exception
	 */
	protected String getErpDept() throws Exception {
		String dept = (String) ActionContext.getContext().get(
				LoginDict.ERP_DEPT);
		return dept;
	}

	protected Integer getErpDeptId() throws Exception {
		Integer deptId = (Integer) ActionContext.getContext().get(
				LoginDict.ERP_DEPTID);
		return deptId;
	}

	protected Integer getErpUserScope() throws Exception {
		if (ActionContext.getContext().get(LoginDict.ERP_SCOPE) == null) {
			return null;
		} else {
			String scopeStr = String.valueOf(ActionContext.getContext().get(
					LoginDict.ERP_SCOPE));
			Integer scope = Integer.valueOf(scopeStr);
			return scope;
		}
	}
	protected String getRole() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_ROLE) == null) {
			return null;
		} else {
			String value = String.valueOf(ActionContext.getContext().get(
					LoginDict.CHANNELS_ROLE));
			return value;
		}
	}
	protected String getChannelsCode() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_CODE) == null) {
			return null;
		} else {
			String value = String.valueOf(ActionContext.getContext().get(
					LoginDict.CHANNELS_CODE));
			return value;
		}
	}
	protected String getChannels() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_CHANNELSNAME) == null) {
			return null;
		} else {
			String value = String.valueOf(ActionContext.getContext().get(
					LoginDict.CHANNELS_CHANNELSNAME));
			return value;
		}
	}
	protected String getStore() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_STORENAME) == null) {
			return null;
		} else {
			String value = String.valueOf(ActionContext.getContext().get(
					LoginDict.CHANNELS_STORENAME));
			return value;
		}
	}
	protected String getStoreCode() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_STORECODE) == null) {
			return null;
		} else {
			String value = String.valueOf(ActionContext.getContext().get(
					LoginDict.CHANNELS_STORECODE));
			return value;
		}
	}
	protected String getTel() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_TEL) == null) {
			return null;
		} else {
			String value = String.valueOf(ActionContext.getContext().get(
					LoginDict.CHANNELS_TEL));
			return value;
		}
	}
	protected String getAccount() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_ACCOUNT) == null) {
			return null;
		} else {
			String value = String.valueOf(ActionContext.getContext().get(
					LoginDict.CHANNELS_ACCOUNT));
			return value;
		}
	}
	protected Integer getAccountType() throws Exception {
		if (ActionContext.getContext().get(LoginDict.CHANNELS_TYPE) == null) {
			return null;
		} else {
			Integer value = (Integer) ActionContext.getContext().get(LoginDict.CHANNELS_TYPE);
			return value;
		}
	}
}
