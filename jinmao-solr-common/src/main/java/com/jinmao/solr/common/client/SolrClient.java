package com.jinmao.solr.common.client;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.jinmao.solr.common.config.Client;
import com.jinmao.solr.common.config.SolrBean;
import com.jinmao.solr.common.config.SolrConfig;
import com.jinmao.solr.common.config.SolrReturnBean;
import com.jinmao.solr.common.config.SortBean;
import com.jinmao.solr.common.exception.SolrInitializerException;
import com.jinmao.solr.common.exception.SolrParamException;
import com.jinmao.solr.common.util.SolrUrls;


/***
 * 
 * @描述: 操作索引客户端
 * @创建者：yuanwei
 * @创建时间： 2014-6-25下午4:16:45
 *
 */
public class SolrClient {
	
	private static final Log LOG = LogFactory.getLog(SolrClient.class);
	/*solr连接配置对象 */
	private SolrConfig solrConfig;
	/*solr写连接 */
	private HttpSolrServer writer_master_Pool;
	/*solr读连接 */
	private HttpSolrServer reader_slave_Pool;
	/*solr写连接(集合)*/
	private Map<String,HttpSolrServer> map_writer_master_Pool = new HashMap<String,HttpSolrServer>();
	/*solr读连接(集合) */
	private Map<String,HttpSolrServer> map_reader_slave_Pool = new HashMap<String,HttpSolrServer>();
	
	private String dbName = "";//索引库名称(insert参数)
	
	private String workDBName = "";//work注入
	
	private String url = "";//处理URL
	
	
	
	/***
	 * 
	 * @描述 : 加载配置构造器
	 * @创建者：yuanwei
	 * @创建时间： 2014-6-30上午9:06:38
	 *
	 * @param solrConfig
	 */
	public SolrClient(SolrConfig solrConfig){
		this.solrConfig = solrConfig;
		try {
			init();
		} catch (SolrInitializerException e) {
			LOG.error("connection error", e);
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @描述 : 初始化solr连接
	 * @创建者：yuanwei
	 * @创建时间： 2014-6-30上午8:22:30
	 *
	 */
	private void init() throws SolrInitializerException{
		LOG.info("solr client init start~");
		String dbNames = solrConfig.getDbNames();
		String[] dbNameList = dbNames.split(",");
		if(!"".equals(dbNames)){
			for(int i=0;i<dbNameList.length;i++){
				try {
					URL url = new URL(solrConfig.getURLWriter());  
					HttpURLConnection con = (HttpURLConnection) url.openConnection();  
					int state = con.getResponseCode();
					if(state!=200){
						continue;
					}
				} catch (MalformedURLException e) {
					continue;
				} catch (IOException e) {
					continue;
				} 
				writer_master_Pool = new HttpSolrServer(solrConfig.getURLWriter()+"/"+dbNameList[i]);
				writer_master_Pool.setSoTimeout(solrConfig.getSoTimeOut());  // socket 读取超时时间
				writer_master_Pool.setConnectionTimeout(solrConfig.getConnectionTimeOut());//连接超时时间
				writer_master_Pool.setDefaultMaxConnectionsPerHost(solrConfig.getDefaultMaxConnectionsPerHost());//每个主机默认最大连接数
				writer_master_Pool.setMaxTotalConnections(solrConfig.getMaxTotalConnections());//最大连接数
				writer_master_Pool.setFollowRedirects(solrConfig.getFollowRedirects());  // 是否重定向，默认false
				writer_master_Pool.setAllowCompression(solrConfig.getAllowCompression());//是否允许压缩
				writer_master_Pool.setMaxRetries(solrConfig.getMaxRetries()); // 最大重试次数，默认0 推荐1
				
				map_writer_master_Pool.put(dbNameList[i]+"_writer", writer_master_Pool);				
			}
			for(int i=0;i<dbNameList.length;i++){
				try {
					URL url = new URL(solrConfig.getURLReader());  
					HttpURLConnection con = (HttpURLConnection) url.openConnection();  
					int state = con.getResponseCode();
					if(state!=200){
						continue;
					}
				} catch (MalformedURLException e) {
					continue;
				} catch (IOException e) {
					continue;
				}				
				reader_slave_Pool = new HttpSolrServer(solrConfig.getURLReader()+"/"+dbNameList[i]);
				reader_slave_Pool.setSoTimeout(solrConfig.getSoTimeOut());  // socket 读取超时时间
				reader_slave_Pool.setConnectionTimeout(solrConfig.getConnectionTimeOut());//连接超时时间
				reader_slave_Pool.setDefaultMaxConnectionsPerHost(solrConfig.getDefaultMaxConnectionsPerHost());//每个主机默认最大连接数
				reader_slave_Pool.setMaxTotalConnections(solrConfig.getMaxTotalConnections());//最大连接数
				reader_slave_Pool.setFollowRedirects(solrConfig.getFollowRedirects());  // 是否重定向，默认false
				reader_slave_Pool.setAllowCompression(solrConfig.getAllowCompression());//是否允许压缩
				reader_slave_Pool.setMaxRetries(solrConfig.getMaxRetries()); // 最大重试次数，默认0 推荐1
				
				map_reader_slave_Pool.put(dbNameList[i]+"_reader", reader_slave_Pool);
			}
		}else{
			throw new SolrInitializerException(
					"SolrClient init parameter dbNames is empty,please check spring config file!");
		}
		LOG.info("solr client init end~ create writer:"+map_writer_master_Pool
				+",create reader:"+map_reader_slave_Pool);
		
	}
	
	/***
	 * 
	 * @描述 : 获得连接
	 * @创建者：yuanwei
	 * @创建时间： 2014-6-30上午9:05:31
	 *
	 * @param dbName 索引库名称
	 * @param type writer or reader
	 * @return
	 */
	private HttpSolrServer getConnection(String dbName,String type){
		HttpSolrServer httpSolrServer = null;
		if("writer".equals(type)){
			httpSolrServer = map_writer_master_Pool.get(dbName+"_writer");
			try {
				URL url = new URL(solrConfig.getURLWriter());  
				HttpURLConnection con = (HttpURLConnection) url.openConnection();  
				int state = con.getResponseCode();
				if(state!=200){
					httpSolrServer = null;
				}
			} catch (MalformedURLException e) {
				httpSolrServer = null;
			} catch (IOException e) {
				httpSolrServer = null;
			}
		}else if("reader".equals(type)){
			httpSolrServer = map_reader_slave_Pool.get(dbName+"_reader");
			try {
				URL url = new URL(solrConfig.getURLReader());  
				HttpURLConnection con = (HttpURLConnection) url.openConnection();  
				int state = con.getResponseCode();
				if(state!=200){
					httpSolrServer = null;
				}
			} catch (MalformedURLException e) {
				httpSolrServer = null;
			} catch (IOException e) {
				httpSolrServer = null;
			}
		}
		return httpSolrServer;
	}
	
	/***
	 * 
	 * @描述 : 查询索引
	 * @创建者：yuanwei
	 * @创建时间： 2014-6-25下午2:08:01
	 *
	 * @param solrSelectBean 查询对象
	 *
	 * @return
	 */
	public SolrReturnBean readIndex(SolrBean solrSelectBean){
		
		SolrReturnBean solrReturnBean = new SolrReturnBean();
		
		HttpSolrServer solrServer = null;
		QueryResponse response = null;
		
		if(solrSelectBean!=null){
			try {
				solrServer = this.getConnection(solrSelectBean.getDbName(), "reader");
				if(solrServer == null){
					solrServer = this.getConnection(solrSelectBean.getDbName(), "writer");
				}
				SolrQuery query = new SolrQuery();
				query.setQuery(SolrUrls.mergeParam(solrSelectBean.getSelectParams()));
				//查询条件
				//ModifiableSolrParams params = new ModifiableSolrParams(); 
				/*if(solrSelectBean.getSelectParams()!=null && solrSelectBean.getSelectParams().size()!=0){
					for(String s : solrSelectBean.getSelectParams()){
						query.addFilterQuery(s);
					}
				}*/
				//params.add(query);
				//高亮
				if(solrSelectBean.isHight() == true){
					query.setHighlight(true); // 开启高亮组件
					query.addHighlightField(solrSelectBean.getHightKey());
					/*if(solrSelectBean.getHightKey()!=null && !"".equals(solrSelectBean.getHightKey())){
						String []hkeys = solrSelectBean.getHightKey().split("@");
						for(String s : hkeys){
							query.addHighlightField(s);// 高亮字段
						}
					}*/
			        query.setHighlightSimplePre("<font color=\"red\">");// 标记
			        query.setHighlightSimplePost("</font>");
			        //query.setHighlightSnippets(1);//结果分片数，默认为1
			        //query.setHighlightFragsize(100);//每个分片的最大长度，默认为100
				}
				//分页
				if(solrSelectBean.getPageSize()!=0){
					query.setStart((solrSelectBean.getPage()-1)*solrSelectBean.getPageSize());//开始
			    	query.setRows(solrSelectBean.getPageSize());//几条
				}
				//排序
				if(solrSelectBean.getSortList()!=null && solrSelectBean.getSortList().size()!=0){
					for(SortBean sortBean : solrSelectBean.getSortList()){
						if(!"".equals(sortBean.getSortKey()) && !"".equals(sortBean.getSortBy())
								&& sortBean.getSortKey()!=null && sortBean.getSortBy()!=null){
							if("ASC".equals(sortBean.getSortBy())){
								query.addSortField(sortBean.getSortKey(),SolrQuery.ORDER.asc);
							}else{
								query.addSortField(sortBean.getSortKey(),SolrQuery.ORDER.desc);
							}
						}
					}
				}
				/*if(!"".equals(solrSelectBean.getSortKey()) && !"".equals(solrSelectBean.getSortBy())
						&& solrSelectBean.getSortKey()!=null && solrSelectBean.getSortBy()!=null){
					if("ASC".equals(solrSelectBean.getSortBy())){
						query.addSortField(solrSelectBean.getSortKey(),SolrQuery.ORDER.asc);
					}else{
						query.addSortField(solrSelectBean.getSortKey(),SolrQuery.ORDER.desc);
					}
				}*/
				//统计
				if(solrSelectBean.isFacet()==true){
					query.setFacet(true);
					if(solrSelectBean.getFacetFields()!=null && solrSelectBean.getFacetFields().size()!=0){
						for(String str : solrSelectBean.getFacetFields()){
							query.addFacetField(str);
						}
					}
				}
//				if(solrSelectBean.getFlValue()!=""&&!solrSelectBean.getFlValue().equals("")){
//					query.set("fl", solrSelectBean.getFlValue());
//				}
				response = solrServer.query(query); //文档方式读取，实际项目中如果业务比较复杂，采用这种方式显得比较灵活
				solrReturnBean.setQueryResponse(response);
			} catch (SolrServerException  e) {
				LOG.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		return solrReturnBean;
	}
	/**
	 * 
	 * @描述 :  初始化全部索引(DIM模式)
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-6-25下午4:14:52
	 *
	 * @param dbName 索引库名
	 */
	public void initIndex(SolrBean solrBean){
		if(solrBean!=null){
			this.dbName = solrBean.getDbName();
		}
		try {
			LOG.info("*-*-*-*-*-开始导入索引:" + dbName + "*-*-*-*-*-");
			Map<String,String> map = new HashMap<String,String>();
			map.put("command", "full-import");
			map.put("commit", "true");
	        map.put("wt", "json");
	        map.put("indent", "true");
	        map.put("verbose", "false");
	        map.put("clean", "true");
	        map.put("optimize", "false");
	        map.put("debug", "false");
	        String url = "";
	        if(solrConfig!=null){
	        	url = solrConfig.getURLWriter();
	        }
	        String urlParm = SolrUrls.mergeUrl(url+"/"+dbName+"/dataimport", map);
	        LOG.info("执行："+urlParm);
	        Client http = new Client();
            try {
            	http.doPost(urlParm);
			} catch (Exception e) {
				e.printStackTrace();
			}
            LOG.info("*-*-*-*-*-导入索引结束:"+dbName+"*-*-*-*-*-");
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
	}
	
	/***
	 * 导入索引线程
	 */
	private Thread indexThread = new Thread() {
		public void run() {
        }
    };
    
    /***
     * 
     * @描述: 定时更新索引Spring自动执行(DIM模式)
     * @创建者：yuanwei
     * @创建时间： 2014-6-25下午4:17:36
     *
     */
    public void work(){
    	LOG.info("*-*-*-*-*-"+new Date()+"定时更新索引开始*-*-*-*-*-");
    	String[] workIndexs = workDBName.split(",");
    	if(!"".equals(workDBName)){
    		for(int i=0;i<workIndexs.length;i++){
    			Map<String,String> map = new HashMap<String,String>();
    	        
    			map.put("command", "delta-import");
    			map.put("commit", "true");
    			map.put("wt", "json");
    			map.put("indent", "true");
    			map.put("verbose", "false");
    			map.put("clean", "false");
    			map.put("optimize", "true");
    			map.put("debug", "false");
    			String url = "";
    	        if(solrConfig!=null){
    	        	url = solrConfig.getURLWriter();
    	        }
    			String urlParm = SolrUrls.mergeUrl(url+"/"+workIndexs[i]+"/dataimport", map);
    			Client http = new Client();
    			LOG.info("执行："+urlParm);
    			try {
    				http.doPost(urlParm);
    			} catch (Exception e) {
    				e.printStackTrace();
    			} 
    		}
    	}
    	LOG.info("*-*-*-*-*-"+new Date()+"定时更新索引结束*-*-*-*-*-");
    }
    
    public synchronized  void workSolr(String workname){
    	LOG.info("*-*-*-*-*-" + workname + "更新索引开始*-*-*-*-*-");
    	if(!"".equals(workname)){
    			Map<String,String> map = new HashMap<String,String>();
    			map.put("command", "delta-import");
    			map.put("commit", "true");
    			map.put("wt", "json");
    			map.put("indent", "false");
    			map.put("verbose", "false");
    			map.put("clean", "false");
    			map.put("optimize", "false");
    			map.put("debug", "false");
    			String url = "";
    	        if(solrConfig!=null){
    	        	url = solrConfig.getURLWriter();
    	        }
    			String urlParm = SolrUrls.mergeUrl(url+"/"+workname+"/dataimport", map);
    			Client http = new Client();
    			LOG.info("执行："+urlParm);
    			try {
    				http.doPost(urlParm);
    			} catch (Exception e) {
    				e.printStackTrace();
    			} 
    	}
    	LOG.info("*-*-*-*-*-" + workname + " 更新索引结束*-*-*-*-*-");
    }
    /***
     * 
     * @描述 : 更新索引
     * @创建者：yuanwei
     * @创建时间： 2014-6-30下午1:56:45
     *
     * @param solrSelectBean
     */
    public void updateIndex(SolrBean solrBean,List listObject){
    	
    	SolrReturnBean deleteSolrReturnBean = null;
    	SolrReturnBean insertSolrReturnBean = null;
    	
    	try {
    		deleteSolrReturnBean = deleteIndexByQuery(solrBean);
    		insertSolrReturnBean = insertIndexAuto(solrBean,listObject);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			SolrRollBack(deleteSolrReturnBean.getHttpSolrServer());
			SolrRollBack(insertSolrReturnBean.getHttpSolrServer());
			e.printStackTrace();
		} finally{
			SolrCommit(deleteSolrReturnBean.getHttpSolrServer());
			SolrCommit(insertSolrReturnBean.getHttpSolrServer());
		}
    }
    
    /***
     * 
     * @描述 : 定时更新索引方法(SorlJ模式)
     * @创建者：yuanwei
     * @创建时间： 2014-6-30下午2:40:41
     *
     */
    public void updateIndexWork() throws SolrParamException{
    	LOG.info("*-*-*-*-*-定时更新索引开始*-*-*-*-*-");
    	
    	HttpSolrServer solrServer = null;
		UpdateResponse response = null;
    	String[] workIndexs = workDBName.split(",");
    	//如果注入的workDBName不为空
    	if(!"".equals(workDBName)){
    		for(int i=0;i<workIndexs.length;i++){
    			//获得增量开始......
    			//查询出最后更新时间，检索出数据库大于最后更新时间的数据
    	    	List listObject = new ArrayList();
    	    	/*Company c1 = new Company("702125","aaa",new Date(),10.00,"11");
    			Company c2 = new Company("702126","aaa",new Date(),10.00,"11");
    			Company c3 = new Company("702127","aaa",new Date(),10.00,"11");
    			Company c4 = new Company("702128","aaa",new Date(),10.00,"11");
    			listObject.add(c1);
    			listObject.add(c2);
    			listObject.add(c3);
    			listObject.add(c4);*/
    			//获得增量结束......
    			//执行集合
    			List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>(); 
    			try {
    				//参数实体对象List不能为空
    				if(listObject!=null && listObject.size()!=0){
    					//循环参数List
    					for(int j=0;j<listObject.size();j++){
    						//创建执行对象
    						SolrInputDocument doc = new SolrInputDocument(); 
    						//拿到参数实体对象类 
    						Class<?> clz = listObject.get(j).getClass();
    						//获取实体类的所有属性，返回Field数组  
    			            Field[] fields = clz.getDeclaredFields(); 
    			            //循环Field数组
    			            for (Field field : fields) {
    			            	//如果字段不是serialVersionUID
    			            	if(!"serialVersionUID".equals(field.getName())){
    			            		//获得该字段的getter(注意boolean类型要写成get,不要写成is)
    				            	Method m = (Method) listObject.get(j).getClass().getMethod(  
    			                            "get" + getMethodName(field.getName()));  
    				            	//判断该字段字头如果有@Field注解
    			                    if(field.isAnnotationPresent(org.apache.solr.client.solrj.beans.Field.class)){
    			                    	//把字段名称,和执行getter获得的属性值,放入执行对象
    			                    	doc.addField(field.getName(),m.invoke(listObject.get(j)));
    			                    }
    			            	}
    			            }
    			            //把执行对象放入集合
    			            docs.add(doc);
    					}
    				}else{
    					throw new SolrParamException(
    							"updateIndexAuto() parameter *listObject* is empty!");
    				}
    				solrServer = this.getConnection(workIndexs[i], "writer");
    				//执行
    				response = solrServer.add(docs);
    			} catch (Exception e) {
    				LOG.error(e.getMessage(), e);
    				try {
						solrServer.rollback();
					} catch (SolrServerException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
    				e.printStackTrace();
    			}finally {
    				try {
						solrServer.optimize();
						solrServer.commit();
					} catch (SolrServerException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
    			}
    		}
    	}
    	LOG.info("*-*-*-*-*-定时更新索引结束*-*-*-*-*-");
    }
    
    /***
     * 
     * @描述 : 删除索引
     * @创建者：yuanwei
     * @创建时间： 2014-6-26上午9:26:04
     *
     * @param dbName 索引库名称
     * @param params 参数，删除Byid-->id:n 删除所有*:*
     * @return
     */
    public SolrReturnBean deleteIndexByQuery(SolrBean solrBean){
    	
    	SolrReturnBean solrReturnBean = new SolrReturnBean();
		HttpSolrServer solrServer = null;
		UpdateResponse response = null;
		try {
			solrServer = this.getConnection(solrBean.getDbName(), "writer");
			response = solrServer.deleteByQuery(solrBean.getDeleteParams());
			solrReturnBean.setHttpSolrServer(solrServer);
			solrReturnBean.setUpdateResponse(response);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			solrServer.rollback();
			e.printStackTrace();
		}finally {
			return solrReturnBean;
		}
	}
    
    /***
     * 
     * @描述 : 插入索引(SorlJ模式)
     * @创建者：yuanwei
     * @创建时间： 2014-6-30上午10:32:55
     *
     * @param solrBean 
     * @param docs SolrInputDocument 对象List
     * @return
     */
    public SolrReturnBean insertIndex(SolrBean solrBean,List<SolrInputDocument> docs){
    	
    	SolrReturnBean solrReturnBean = new SolrReturnBean();
    	HttpSolrServer solrServer = null;
		UpdateResponse response = null;
		try {
			solrServer = this.getConnection(solrBean.getDbName(), "writer");
			response = solrServer.add(docs);
			solrReturnBean.setHttpSolrServer(solrServer);
			solrReturnBean.setUpdateResponse(response);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			solrServer.rollback();
			e.printStackTrace();
		}finally {
			return solrReturnBean;
		}
	}
    
    
    /***
     * 
     * @描述 : 插入索引自动匹配对象(SorlJ模式)
     * @创建者：yuanwei
     * @创建时间： 2014-6-30上午10:32:55
     *
     * @param solrBean 
     * @param docs 业务实体List
     * @return
     */
    public SolrReturnBean insertIndexAuto(SolrBean solrBean,List listObject) throws SolrParamException{
    	
    	SolrReturnBean solrReturnBean = new SolrReturnBean();
    	HttpSolrServer solrServer = null;
		UpdateResponse response = null;
		//执行集合
		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>(); 
		try {
			//参数实体对象List不能为空
			if(listObject!=null && listObject.size()!=0){
				//循环参数List
				for(int i=0;i<listObject.size();i++){
					//创建执行对象
					SolrInputDocument doc = new SolrInputDocument(); 
					//拿到参数实体对象类 
					Class<?> clz = listObject.get(i).getClass();
					//获取实体类的所有属性，返回Field数组  
		            Field[] fields = clz.getDeclaredFields(); 
		            //循环Field数组
		            for (Field field : fields) {
		            	//如果字段不是serialVersionUID
		            	if(!"serialVersionUID".equals(field.getName())){
		            		//获得该字段的getter(注意boolean类型要写成get,不要写成is)
			            	Method m = (Method) listObject.get(i).getClass().getMethod(  
		                            "get" + getMethodName(field.getName()));  
			            	//判断该字段字头如果有@Field注解
		                    if(field.isAnnotationPresent(org.apache.solr.client.solrj.beans.Field.class)){
		                    	//把字段名称,和执行getter获得的属性值,放入执行对象
		                    	doc.addField(field.getName(),m.invoke(listObject.get(i)));
		                    }
		            	}
		            }
		            //把执行对象放入集合
		            docs.add(doc);
				}
			}else{
				throw new SolrParamException("insertIndexAuto() parameter *listObject* is empty!");
			}
			solrServer = this.getConnection(solrBean.getDbName(), "writer");
			//执行
			response = solrServer.add(docs);
			solrReturnBean.setHttpSolrServer(solrServer);
			solrReturnBean.setUpdateResponse(response);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			solrServer.rollback();
			e.printStackTrace();
		}finally {
			return solrReturnBean;
		}
	}
    
    /**
     * 
     * @描述 : UpdateResponse提交
     * @创建者：yuanwei
     * @创建时间： 2014-6-27上午9:11:37
     *
     * @param solrServer css连接对象
     */
    public void SolrCommit(HttpSolrServer solrServer){
    	try {
    		solrServer.optimize();//优化，有提交效果
			solrServer.commit();
		} catch (SolrServerException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 
     * @描述 : UpdateResponse回滚
     * @创建者：yuanwei
     * @创建时间： 2014-6-27上午9:11:37
     *
     * @param solrServer css连接对象
     */
    public void SolrRollBack(HttpSolrServer solrServer){
    	try {
			solrServer.rollback();
		} catch (SolrServerException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}
    	
    }
    
    /***
	 * 
	 * @描述 : 把一个字符串的第一个字母大写、效率是最高的
	 * @创建者：yuanwei
	 * @创建时间： 2014-6-30上午11:06:21
	 *
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
    private static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }
    
	  
    public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getWorkDBName() {
		return workDBName;
	}
	public void setWorkDBName(String workDBName) {
		this.workDBName = workDBName;
	}
    

}
