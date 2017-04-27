package com.jinmao.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.taobao.common.tfs.TfsManager;


/***
 * 
 * @描述: io操作工具类(TFS工具类)
 * @创建者：lvxuepeng
 * @创建时间： 2014-7-21上午11:23:37
 *
 */
public class IOUtil {
	
	private static final Log LOG = LogFactory
			.getLog("----------- IOUtil Error -----------");
	
	/* 定义TFS模型 */
	private TfsManager tfsClient;
	/* 定义工具模型 */
	private ContentHtmlUtil contentHtmlUtil;

	/***
     * 
     * @描述 : 读取文件
     * @创建者：lvxuepeng
     * @创建时间： 2014-7-21上午11:23:55
     *
     * @param fileName
     * @return
     */
    public static String readFileToString(String fileName){
    	
    	BufferedReader br = null;
    	String fileContent = "";
    	try {
    		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8")); 
			String line="";
			StringBuffer  buffer = new StringBuffer();
			while((line=br.readLine())!=null){
				buffer.append(line);
			}
			fileContent = buffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
				}
			}
		}
    	return fileContent;
    }
    
    /***
     * 
     * @描述 : 删除文件
     * @创建者：lvxuepeng
     * @创建时间： 2014-7-21上午11:24:08
     *
     * @param path
     */
    public static void delFile(String path){
    	try {
			if(path!=null && !"".equals(path)){
				File file = new File(path);
				// 路径为文件且不为空则进行删除  
				if (file.isFile() && file.exists()) {  
					file.delete();  
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /***
     * 
     * @描述 : 获得真实路径
     * @创建者：lvxuepeng
     * @创建时间： 2014-7-23上午8:18:45
     *
     * @return E:\javawork\...\sdy-vip-center-erp-web\
     */
    public String getRealPath(){
    	return ServletActionContext.getServletContext().getRealPath("/");
    }
    
    /***
     * 
     * @描述 : 获得class包路径
     * @创建者：lvxuepeng
     * @创建时间： 2014-7-23上午8:20:42
     *
     * @return /E:/javawork/.../sdy-vip-center-erp-web/WEB-INF/classes/
     */
    public String getClassPath(){
    	return this.getClass().getClassLoader().getResource("").getPath();
    }
    
    /***
     * 
     * @描述 : 根据文件名获得文件扩展名
     * @创建者：lvxuepeng
     * @创建时间： 2014-7-23上午8:25:50
     *
     * @param fileName
     * @return
     */
    public String getExtension(String fileName){
    	return fileName.substring(fileName.lastIndexOf("."),fileName.length());
    }
    
    /***
     * 
     * @描述 : 生成带扩展名的文件名
     * @创建者：lvxuepeng
     * @创建时间： 2014-7-23上午8:28:19
     *
     * @param extension
     * @return
     */
    public String getFileNameByAuto(String extension){
    	return System.currentTimeMillis() + "_" + (int)(Math.random()*10000)+extension;
    }
    
  /*  *//***
	 * 
	 * @描述 : 上传文件到本地，在上传到TFS
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-11上午11:57:18
	 *
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @return TFS文件名+扩展名
	 *//*
	public String ajaxfileUpload(File file,String fileName,String fileContentType){
		
		java.io.InputStream is = null;
		java.io.OutputStream os = null;
		String returnName = "";
		String pexName  = "";
		try {
			is = new java.io.FileInputStream(file);
			//文件扩展名
			pexName = this.getExtension(fileName);
			//生成文件名
			fileName = this.getFileNameByAuto(pexName);
			// 获取项目真实路径
			String realPath = this.getRealPath();
			String uploadSavePath = realPath+UploadPathDict.ProClassPhotoPath.getState();
			//建目录
			java.io.File myFilePath = new java.io.File(uploadSavePath.toString());
			if (!myFilePath.exists()) {
				System.out.println("新建文件目录");
			    myFilePath.mkdirs();
			  }
			
			//输出
			os = new java.io.FileOutputStream(uploadSavePath + fileName);
			byte buffer[] = new byte[8192];
			int count = 0;
			while((count = is.read(buffer)) > 0){
			    os.write(buffer, 0, count);
			}
			 tfs start
			returnName = tfsClient.saveFile(uploadSavePath+fileName, null, pexName);
			System.out.println("TFS图片文件名："+returnName);
			 tfs end
			
		} catch (Exception e) {
			LOG.error("###### ajaxfileUpload error ######", e);
		} finally{
			try {
				if(os!=null){
					os.close();
				}
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				LOG.error("###### ajaxfileUpload close io error ######", e);
			}
			
		}
		return returnName+pexName;
	}*/
	
	/***
	 * 
	 * @描述 : 直接上传图片到TFS
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-23上午9:36:34
	 *
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @return TFS文件名+扩展名
	 */
	public String uploadImgToTFS(File file,String fileName,String fileContentType){
		
		String returnName = "";
		String pexName  = "";
		try {
			//文件扩展名
			pexName = this.getExtension(fileName);
			/* tfs start*/
			returnName = tfsClient.saveFile(file, null, pexName);
			//替换.-->!
			returnName = returnName.replaceAll("\\.", "!");
			System.out.println("TFS图片文件名："+returnName+pexName);
			/* tfs end*/
			
		} catch (Exception e) {
			LOG.error("###### uploadImgToTFS error ######", e);
		} finally{
			
		}
		return returnName+pexName;
	}
	
	/***
	 * 
	 * @描述 : 直接上传文件到TFS
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-23上午9:36:34
	 *
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @return TFS文件名+扩展名
	 */
	public String uploadFileToTFS(String content){
		
		String returnName = "";
		try {
			/* tfs start*/
			returnName = tfsClient.saveFile(content.getBytes("UTF-8"), null, ".html");
			//替换.-->!
			returnName = returnName.replaceAll("\\.", "!");
			System.out.println("TFS文件名："+returnName+".html");
			/* tfs end*/
			
		} catch (Exception e) {
			LOG.error("###### uploadFileToTFS error ######", e);
		} finally{
			
		}
		return returnName+".html";
	}
	
	/***
	 * 
	 * @描述 : 根据文件名+扩展名删除文件在TFS
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-23上午10:54:47
	 *
	 * @param fileName
	 * @return
	 */
	public boolean deleteFileOnTFS(String fileName){
		try {
			/* tfs start*/
			boolean b = tfsClient.unlinkFile(fileName, this.getExtension(fileName));
			System.out.println("成功删除文件："+fileName);
			/* tfs end*/
			return b;
		} catch (Exception e) {
			LOG.error("###### deleteFileOnTFS error ######", e);
			return false;
		} finally{
			
		}
		
	}
	
	/***
	 * 
	 * @描述 : 根据文件名+扩展名(Set)删除文件在TFS
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-24下午12:51:19
	 *
	 * @param fileName
	 * @return
	 */
	public boolean deleteFileForSetOnTFS(Set<String> set){
		try {
			if(set!=null && set.size()!=0){
				for(String str:set){
					/* tfs start*/
					tfsClient.unlinkFile(str, this.getExtension(str));
					/* tfs end*/
				}
			}
			
			return true;
		} catch (Exception e) {
			LOG.error("###### deleteFileForSetOnTFS error ######", e);
			return false;
		} finally{
			
		}
		
	}
	
	/***
	 * 
	 * @描述 : 根据文件名+扩展名删除文件在TFS同时删除所有图片
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-7-23上午11:36:29
	 *
	 * @param fileName
	 * @return
	 */
	public boolean deleteFileAndImgsOnTFS(String fileName){
		try {
			//访问路径
			String path = contentHtmlUtil.getShowHtml()+fileName;
			//获得Html代码
			String content = HttpUtil.getHtml(path);
			//System.out.println(content);
			//获得所有图片名
			List<String> list = HttpUtil.getImgListForDelete(content);
			System.out.println("需要删除的图片："+list);
			/* tfs start*/
			//删除Html
			tfsClient.unlinkFile(fileName, this.getExtension(fileName));
			//删除所有图片
			if(list!=null && list.size()!=0){
				for(String str : list){
					tfsClient.unlinkFile(str, this.getExtension(str));
				}
			}
			/* tfs end*/
			return true;
		} catch (Exception e) {
			LOG.error("###### deleteFileAndImgsOnTFS error ######", e);
			return false;
		} finally{
			
		}
		
	}
	
	public ContentHtmlUtil getContentHtmlUtil() {
		return contentHtmlUtil;
	}

	public void setContentHtmlUtil(ContentHtmlUtil contentHtmlUtil) {
		this.contentHtmlUtil = contentHtmlUtil;
	}

	public TfsManager getTfsClient() {
		return tfsClient;
	}

	public void setTfsClient(TfsManager tfsClient) {
		this.tfsClient = tfsClient;
	}

	

	
   
}
