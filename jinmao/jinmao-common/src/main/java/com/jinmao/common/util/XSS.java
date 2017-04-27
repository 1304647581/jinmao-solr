package com.jinmao.common.util;


import java.util.regex.Pattern;

/**
 * XSS脚本攻击
 * <p>
 * @author   hubin
 * @Date	 2014-5-8 	 
 */
public class XSS {

	/**
	 * @Description XSS脚本内容剥离
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public static String strip(String value) {
		if (value != null) {
			// NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
			// avoid encoded attacks.
			// value = ESAPI.encoder().canonicalize(value);

			// Avoid null characters
			value = value.replaceAll("", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
					| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid onload= expressions
			scriptPattern = Pattern.compile(">",Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
						// Avoid onload= expressions
			scriptPattern = Pattern.compile("<",Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}
	//安全注入
		public static String characterEncoding (String str){
			//& --> &amp;< --> &lt; > --> &gt; " --> &quot; ' --> &#x27;  / --> &#x2F;  
			if(str.contains("&")){
				str=str.replace("&", "&amp;");
			}
			if(str.contains("<")){
				str=str.replace("<", "&lt;");
			}
			if(str.contains(">")){
				str=str.replace(">", "&gt;");
			}
			if(str.contains("\"")){
				str=str.replace("\"", "&quot;");
			}
			if(str.contains("'")){
				str=str.replace("'", "&#x27;");
			}
			if(str.contains("/")){
				str=str.replace("/", "&#x2F;");
			}
			//end
			
			return str;
		}
}
