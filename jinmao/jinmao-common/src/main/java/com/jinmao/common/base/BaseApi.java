package com.jinmao.common.base;

import com.alibaba.dubbo.rpc.RpcContext;
import com.jinmao.common.dict.LoginDict;

public class BaseApi {
	/**
	 * @描述 : 获取当前用户登陆名
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-5-4上午11:44:27
	 * @return
	 * @throws Exception
	 */
	protected String getAppPin() throws Exception {
		return (String) RpcContext.getContext().get(LoginDict.APP_PIN);
	}
}
