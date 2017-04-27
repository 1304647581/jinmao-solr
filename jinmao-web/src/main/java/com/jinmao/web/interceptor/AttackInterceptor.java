package com.jinmao.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * @描述 : web网站-安全拦截器
 */
public class AttackInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		ValueStack valueStack = actionContext.getValueStack();
		Map<String, Object> valueTreeMap = actionContext.getParameters();
		WafRequestWrapper.filterParamString(valueTreeMap, valueStack);
		return invocation.invoke();
	}
}
