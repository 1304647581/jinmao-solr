package com.jinmao.dubbo.web.Interceptor;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.tools.common.util.close.CloseUtil;
import com.tools.common.util.json.JsonUtil;
/**
 * @描述 : app登录拦截器
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午4:15:56
 */
public class LoginInterceptor implements Filter {
	/************************声明区***************************************/
	private static final Log LOG = LogFactory.getLog(LoginInterceptor.class);
    
	/************************方法区***************************************/
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    	HttpServletRequest request = (HttpServletRequest)RpcContext.getContext().getRequest();
		HttpServletResponse response = (HttpServletResponse)RpcContext.getContext().getResponse();
    	String cookie = request.getHeader("cookie");
		if (null==cookie) {
			//
			print(JsonUtil.toJson(""), response);
			return new RpcResult();
		}
		return invoker.invoke(invocation);
    }
	protected void print(String str, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			response.setContentType("text/html; charset=utf-8" );
			writer = response.getWriter();
			writer.print(str);
		} catch (Exception e) {
		} finally {
			CloseUtil.close(writer);
		}
	}
	/************************get/set***************************************/
	
}