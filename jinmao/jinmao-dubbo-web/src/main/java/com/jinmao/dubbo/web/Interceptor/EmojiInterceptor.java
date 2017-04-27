package com.jinmao.dubbo.web.Interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.jinmao.common.util.EmojiUtil;
import com.tools.common.util.close.CloseUtil;
import com.tools.common.util.json.JsonUtil;
public class EmojiInterceptor implements Filter{
//	@Override
//	public void filter(ContainerRequestContext arg0) throws IOException {
//		/*String token = httpServletRequest.getParameter("token");
//		boolean flag = loginApi.validToken(token);
//		if (!flag) {
//			AppDomain app=new AppDomain();
//			app.setMessage("900"); 
//			app.setData(null);
//			print(JsonUtil.toJson(app));
//			arg0.abortWith(Response.accepted().build());
//			return ;
//		}*/
//	}
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		HttpServletResponse response = (HttpServletResponse)RpcContext.getContext().getResponse();
		String param = JsonUtil.toJson(RpcContext.getContext().getArguments());
		if (EmojiUtil.containsEmoji(param)) {
			//有表情返回
//			print(JsonUtil.toJson(app),response);
			return new RpcResult();
		}else {
			return invoker.invoke(invocation);
		}
	}
	protected void print(String str,HttpServletResponse response) {
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
}
