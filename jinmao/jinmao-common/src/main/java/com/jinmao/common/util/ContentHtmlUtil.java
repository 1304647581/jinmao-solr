package com.jinmao.common.util;


/***
 * 
 * @描述: TFS上传地址工具类
 * @创建者：lvxuepeng
 * @创建时间： 2014-7-22下午3:26:58
 *
 */
public class ContentHtmlUtil {

	private static String htmlTag = "<img src=\"";
	
	public static String charCode = "<!DOCTYPE html>"
			+"<head>"
			+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
			+"<script type=\"text/javascript\">document.domain = \"loxiaw.com\";</script>"
			+"</head>";
	/* TFS 上传路径 */
	private String url;
	/* TFS 浏览图片路径 */
	private String showImg;
	/* TFS 浏览Html路径 */
	private String showHtml;
	/* 用户中心会员首页域名 */
	private String userHost;

	/***
	 * 
	 * @描述 : 读取Html时，加上图片域名读取
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-22下午3:30:00
	 *
	 * @param content
	 * @return
	 */
	public String loadReplaceImgPath(String content){
		content = content.replaceAll(htmlTag, htmlTag+showImg);
		return content.replaceAll(showImg+"http://", "http://");
	}
	/***
	 * 
	 * @描述 : 上传html时，把<img>下的src地址域名去掉
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-22下午3:27:49
	 *
	 * @param content
	 * @return
	 */
	public String uploadHtmlreplaceImgPathToZero(String content){
		return content.replaceAll(htmlTag+showImg, htmlTag);
	}
	
	/***
	 * 
	 * @描述 : 给Img去掉域名
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-23上午11:26:21
	 *
	 * @param urlParam
	 * @return
	 */
	public String replaceProClassImgUrl(String urlParam){
		return urlParam.replaceAll(url, "");
	}
	
	/***
	 * 
	 * @描述 : 给Img去掉域名（读取路径）
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-23上午11:26:21
	 *
	 * @param urlParam
	 * @return
	 */
	public String replaceProClassImgShowUrl(String urlParam){
		return urlParam.replaceAll(showImg, "");
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public String getShowHtml() {
		return showHtml;
	}

	public void setShowHtml(String showHtml) {
		this.showHtml = showHtml;
	}
	
	public String getUserHost() {
		return userHost;
	}
	
	public void setUserHost(String userHost) {
		this.userHost = userHost;
	}
	
}
