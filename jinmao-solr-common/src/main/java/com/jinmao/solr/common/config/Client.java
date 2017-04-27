package com.jinmao.solr.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Client {
	
	public String sendGet(String url) throws ClientProtocolException, IOException{
		String result = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		InputStream in = null;
		try {
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);
				in = entity.getContent();
				byte[] read = new byte[1024];
				byte[] all = new byte[0];
				int num;
				while ((num = in.read(read)) > 0) {
					byte[] temp = new byte[all.length + num];
					System.arraycopy(all, 0, temp, 0, all.length);
					System.arraycopy(read, 0, temp, all.length, num);
					all = temp;
				}
				result = new String(all, "UTF-8");
			}
		} finally {
			if (in != null) in.close();
			get.abort();
		}
		
		return result;
	}
	
	public String sendPost(String url, Map<String, String> params) throws ClientProtocolException, IOException{
		String result = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost get = new HttpPost(url);
		
		// 创建表单参数列表  
		List<NameValuePair> qparams = new ArrayList<NameValuePair>(); 
		Set<String> keys = params.keySet();
		for (String key : keys) {
			qparams.add(new BasicNameValuePair(key, params.get(key)));
		}
		// 填充表单  
		get.setEntity(new UrlEncodedFormEntity(qparams,"UTF-8"));
		
		HttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			entity = new BufferedHttpEntity(entity);
			
			InputStream in = entity.getContent();
			byte[] read = new byte[1024];
			byte[] all = new byte[0];
			int num;
			while ((num = in.read(read)) > 0) {
				byte[] temp = new byte[all.length + num];
				System.arraycopy(all, 0, temp, 0, all.length);
				System.arraycopy(read, 0, temp, all.length, num);
				all = temp;
			}
			result = new String(all,"UTF-8");
			if (null != in) {
				in.close();
			}
		}
		get.abort();
		
		return result;
	}
	
	public String sendGet(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		Set<String> keys = params.keySet();
		StringBuilder urlBuilder = new StringBuilder(url + "?");
		for (String key : keys) {
			urlBuilder.append(key).append("=").append(params.get(key)).append("&");
		}
		urlBuilder.delete(urlBuilder.length() - 1, urlBuilder.length());
		System.out.println(urlBuilder.toString());
		return this.sendGet(urlBuilder.toString());
	}
	
	public void doPost(String urlParam){
		OutputStreamWriter out = null;
		InputStream in = null;
		try{
			URL url = new URL(urlParam);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			//发送域信息
			out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
			out.write("");//这里组织域信息
			out.flush();
			//获取返回数据
			in = connection.getInputStream();
			//.......
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
