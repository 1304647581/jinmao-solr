package com.jinmao.web.interceptor;


import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.jinmao.common.dict.AjaxDict;
import com.jinmao.common.dict.LoginDict;
import com.jinmao.web.annotation.PermissionsCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tools.common.struts.cookie.CookieUtil;
import com.tools.common.struts.result.Result;

/**
 * @描述 : web网站-权限验证拦截器
 */
public class PermissionsInterceptor extends AbstractInterceptor {
	/***************************声明区*************************************/
	private static final Log LOG = LogFactory.getLog("----------- PermissionsInterceptor -----------");
	private static final long serialVersionUID = 1L;
	private String errorPage;
	private String adminPin;
    private Map<String,String> cookieEncrypt;
    private CookieUtil cookieUtil;
    private String cookieKeyName;
    /***************************方法区*************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		ActionProxy actionProxy = invocation.getProxy();
		String methodName = actionProxy.getMethod();
		if (StringUtils.isBlank(methodName)) {
			methodName = "execute";
		}
		Class aClass = actionProxy.getAction().getClass();
		Method method = aClass.getMethod(methodName);
		PermissionsCode annotation = method.getAnnotation(PermissionsCode.class);
		String pin = (String) ActionContext.getContext().get(LoginDict.ERP_PIN);
	   /* String actionCookie=	cookieUtil.getCookieValue(cookieKeyName);
	    String actionJson= SecurityUtil.decrypt(actionCookie,cookieEncrypt.get("desPrefix"));*/
		LOG.info("登录人pin----------------"+pin);
		if (pin!=null&&!pin.equals(adminPin)) {
			/*List<Map<String,Object>> listPermissions=null;
			if(actionJson!=null){
				listPermissions= JsonUtil.fromJson(actionJson,List.class,Map.class);
			}*/
//            List<ActionResult> actionlist=actionRpc.getActionList(pin);
//            if (annotation != null && StringUtils.isNotBlank(annotation.code())) {
//                String code = annotation.code();
//                if(actionlist!=null && actionlist.size()!=0){
//                    for(ActionResult action : actionlist){
//                        if(code.equals(action.getActionCode())){
//                            //如果正确则继续执行
//                            this.setScopeContext(action.getScope());
//                            return invocation.invoke();
//                        }
//                    }
//                }
//            } else {
//                //如果没有权限码则跳过
//                return invocation.invoke();
//            }
            //跳转无权限页面
            this.redirect(request, response);
		} else {
			//如果是admin跳过权限码
			return invocation.invoke();
		}
		return null;
	}

	/**
	 * @描述 : ajax请求如未登录则返回NotLogin. ajax json 请求返回{"error":"NotLogin"}.
	 *     普通请求返回登录页面并带参数ReturnUrl 用于登录成功返回路径
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-5-4下午12:08:42
	 * @param request
	 * @param response
	 */
	private void redirect(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getHeader("X-Requested-With") != null) {
			try {
				Result ajaxResult = new Result();
				ajaxResult.addModel("message",AjaxDict.NOPERMI.getState());
				ajaxResult.addModel("data", errorPage);
				response.getWriter().write(ajaxResult.resultJson());
				return;
			} catch (Exception e) {
				LOG.error(
						"###### ajax not permiss response.getWriter error ######",
						e);
			}
		}
		try {
			response.sendRedirect(errorPage);
		} catch (Exception e) {
			LOG.error("###### redirectLogin error ######", e);
		}
	}
	private void setScopeContext(Integer scope) {
		ActionContext.getContext().put(LoginDict.ERP_SCOPE, scope);
	}
	/***************************get/set*************************************/
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public void setAdminPin(String adminPin) {
		this.adminPin = adminPin;
	}

	public Map<String, String> getCookieEncrypt() {
		return cookieEncrypt;
	}

	public void setCookieEncrypt(Map<String, String> cookieEncrypt) {
		this.cookieEncrypt = cookieEncrypt;
	}

	public CookieUtil getCookieUtil() {
		return cookieUtil;
	}

	public void setCookieUtil(CookieUtil cookieUtil) {
		this.cookieUtil = cookieUtil;
	}

	public String getCookieKeyName() {
		return cookieKeyName;
	}

    public void setCookieKeyName(String cookieKeyName) {
		this.cookieKeyName = cookieKeyName;
	}
}
