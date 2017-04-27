package com.jinmao.web.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.jinmao.common.dict.LoginDict;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tools.common.struts.cookie.CookieUtil;
/**
 * @描述 : web网站-登录拦截器
 */
public class LoginInterceptor extends AbstractInterceptor{
	/***************************声明区*************************************/
	private static final long serialVersionUID = 7581153251709746529L;
	private static final Log LOG = LogFactory.getLog(LoginInterceptor.class);
	private  String cookieKeyName;
    private CookieUtil cookieUtil;
    /** 登录页面的url连接 **/
	private String loginUrl;
	/** url回跳携带参数 **/
	private String returnUrlParameter;
	private String returnUrl;
	/***************************方法区*************************************/
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
//		LoginParam loginParam = new LoginParam();
//		loginParam.setCookieValue(cookieUtil.getCookieValue(cookieKeyName));
//        UserInfoResult res= loginRpc.checkLogin(loginParam);
//        if (res != null) {
//        	cookieUtil.createdCookie(cookieKeyName,	cookieUtil.getCookieValue(cookieKeyName), 600);
//			this.setPinContext(res.getPin());
//			invocation.invoke();
//		} else {
//			this.redirectLogin(request, response);
//		}
        return null;
	}
	/**
	 * @描述 : 设置值
	 * @创建者 : HeZeMin
	 * @创建时间 : 2017-3-8 下午4:34:16
	 */
	private void setPinContext(String pin) {
		ActionContext.getContext().put(LoginDict.ERP_PIN, pin);
	}
	/**
	 * @描述 : ajax请求如未登录则返回NotLogin. ajax json 请求返回{"error":"NotLogin"}.
	 *     普通请求返回登录页面并带参数ReturnUrl 用于登录成功返回路径
	 * @创建时间： 2014-5-4下午12:08:42
	 */
	private void redirectLogin(HttpServletRequest request, HttpServletResponse response) {
		if (request.getHeader("X-Requested-With") != null) {
			try {
				response.getWriter().write(LoginDict.AJAX_NOT_LOGIN_MESSAGE);
				return;
			} catch (Exception e) {
				LOG.error("###### ajax not login response.getWriter error ######", e);
			}
		}
		try {
			StringBuilder parameters = null;
			Enumeration<String> e = request.getParameterNames();
			if (e.hasMoreElements()) {
				String name = e.nextElement();
				parameters = new StringBuilder();
				parameters.append('?').append(name).append('=').append(request.getParameter(name));
			}
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				parameters.append('&').append(name).append('=').append(request.getParameter(name));
			}
			String url=loginUrl;
			LOG.info(url);
			//response.getWriter().write("<script>top.location.href='"+url+"';</script>");
			response.sendRedirect(loginUrl + returnUrlParameter	+ java.net.URLEncoder.encode(request.getRequestURL()
				.append(parameters != null ? parameters : "").toString(), "utf-8"));
		} catch (Exception e) {
			LOG.error("###### redirectLogin error ######", e);
		}
	}
	
	/***************************get/set*************************************/
	
	public CookieUtil getCookieUtil() {
		return cookieUtil;
	}
	public void setCookieUtil(CookieUtil cookieUtil) {
		this.cookieUtil = cookieUtil;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getReturnUrlParameter() {
		return returnUrlParameter;
	}
	public void setReturnUrlParameter(String returnUrlParameter) {
		this.returnUrlParameter = returnUrlParameter;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCookieKeyName() {
		return cookieKeyName;
	}
	public void setCookieKeyName(String cookieKeyName) {
		this.cookieKeyName = cookieKeyName;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
}