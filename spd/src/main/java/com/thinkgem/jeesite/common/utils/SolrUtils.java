package com.thinkgem.jeesite.common.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;


/**
 * solr工具类
 * 注释：commit(硬提交)默认为true,使用这种提交会把文档立即持久化到磁盘，并且你立马可以查询到，
 *            但是会很耗性能，并且会阻塞到提交任务完成；不建议使用故把commit设置成false;
 *      softCommit(软提交) 不会把数据立即写到磁盘，但也可以立即查询到
 *      可以在solrhome/collection1/conf中的solrconfig.xml里面配置这两种方式自动完成
 *      
 *      <autoCommit> 
 *      	每隔30000ms就去做一次硬提交
 *      	<maxTime>${solr.autoCommit.maxTime:30000}</maxTime>
 *      	<openSearcher>false</openSearcher>  
 *       </autoCommit>
 *       <autoSoftCommit> 
 *       	每隔5000ms就去做一次软提交
 *       	<maxTime>${solr.autoSoftCommit.maxTime:5000}</maxTime> 
 *       </autoSoftCommit>
 *       
 * @author yangjinbao
 *
 */
public class SolrUtils {

	private static HttpSolrServer httpSolrServer = SpringContextHolder.getBean(HttpSolrServer.class);
	
	/**
	 * 添加单个对象到索引 
	 * @param object
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void savaBean(Object object) throws IOException, SolrServerException{
		httpSolrServer.addBean(object);
		httpSolrServer.commit(false, false);
	}
	
	/**
	 * 添加集合到索引 
	 * @param lists
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static <E> void savaBeans(List<E> lists) throws IOException, SolrServerException{
		httpSolrServer.addBeans(lists);
		httpSolrServer.commit(false, false);
	}
	
	/**
	 * 根据item_keywords搜索索引
	 * @param param
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static  <T>List<T> queryKeywords(String param, Class<T> clzz) throws SolrServerException, IOException {
		String solrSql = "item_keywords:"+param;
		SolrQuery query = new SolrQuery();
		query.setQuery(solrSql);
		QueryResponse rsp = httpSolrServer.query(query);
		SolrDocumentList solrDocumentList = rsp.getResults();
		// 获取对象  
		List<T> beans = httpSolrServer.getBinder().getBeans(clzz, solrDocumentList);
		httpSolrServer.commit(false, false);
		return beans;
	}
	
	public static <T>T queryId(String id, Class<T> clzz) throws SolrServerException, IOException {
		String solrSql = "id:"+id;
		SolrQuery query = new SolrQuery();
		query.setQuery(solrSql);
		QueryResponse rsp = httpSolrServer.query(query);
		T beans = null;
		if(rsp.getResults().size() > 0){
			SolrDocument solrDocument = rsp.getResults().get(0);
			// 获取对象  
			beans = httpSolrServer.getBinder().getBean(clzz, solrDocument);
		}
		httpSolrServer.commit(false, false);
		return beans;
	}
	
	/**
	 * 多条件查询
	 * @param keywords
	 * @param specifications
	 * @param classA
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static <T>List<T> queryMoreParam(List<String> paramList, Class<T> clzz, int pageNum, int pageSize) throws SolrServerException, IOException {
		StringBuilder paramSB = new StringBuilder();
		if(paramList != null && paramList.size() > 0){
			for(int i=0; i<paramList.size(); i++){
				if(i==0){
					paramSB.append(paramList.get(i));
				}
				if(i>0){
					paramSB.append(" AND ").append(paramList.get(i));
				}
			}
		}else{
			paramSB.append("*:*");
		}
		SolrQuery query = new SolrQuery();
		query.setQuery(paramSB.toString());
		query.setStart(pageNum);// 分页  
		query.setRows(pageSize);//
		QueryResponse rsp = httpSolrServer.query(query);
		SolrDocumentList solrDocumentList = rsp.getResults();
		// 获取对象  
		List<T> beans = httpSolrServer.getBinder().getBeans(clzz, solrDocumentList);
		httpSolrServer.commit(false, false);
		return beans;
	}
	
	/**
	 * 分页查询
	 * @param keywords
	 * @param pageNum
	 * @param pageSize
	 * @param clzz
	 * @return
	 */
	 public static <T>Page<T> getByPage(String keywords, int pageNum, int pageSize, Class<T> clzz){
		SolrQuery query = new SolrQuery();  
		String solrSql = "item_keywords:"+keywords;
		query.setQuery(solrSql);// 查询内容  
		query.setStart((pageNum - 1) * pageSize);// 分页  
		query.setRows(pageSize);//
		QueryResponse response = null;  
		try{  
		    response = httpSolrServer.query(query);  
		}catch (SolrServerException e){  
		    e.printStackTrace();  
		    return null;  
		}  
		// 查询结果集  
		SolrDocumentList results = response.getResults();  
		// 获取对象  
		List<T> beans = httpSolrServer.getBinder().getBeans(clzz, results);  
		// 总记录数  
		int total = new Long(response.getResults().getNumFound()).intValue();  
		
		return new Page<T>(pageNum, pageSize, total, beans);  
    }  
	
	 /**
	  * 高亮分页查询
	  * @param keywords
	  * @param pageNum
	  * @param pageSize
	  * @param hlFields
	  * @param preTag
	  * @param postTag
	  * @param clzz
	  * @param idName
	  * @param lang
	  * @param distinguish
	  * @return
	  */
	public static <T> Page<T> getHighterByPage(String keywords, int pageNum, int pageSize, List<String> hlFields, String preTag, String postTag, Class<T> clzz, String idName, String lang, Boolean distinguish){
		SolrQuery query = new SolrQuery(); 
		query.setQuery(keywords)// 查询内容  
			.setHighlight(true)// 设置高亮显示  
			.setHighlightSimplePre(preTag)// 渲染头标签  
			.setHighlightSimplePost(postTag)// 尾标签  
			.setStart((pageNum - 1) * pageSize)// 分页  
			.setRows(pageSize);//  
		// 设置高亮区域  
		for (String hl : hlFields){
			query.addHighlightField(hl);  
		}  
		QueryResponse response = null;  
		try {
			response = httpSolrServer.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
			 return null;  
		}  
		SolrDocumentList results = response.getResults();  
        // 总记录数  
        int total = new Long(results.getNumFound()).intValue();  
        // 查询结果集  
        ArrayList<T> list = new ArrayList<T>(); 
        try {
        	Object object = null;  
        	Method method = null;  
        	Class<?> fieldType = null;  
        	Map<String, Map<String, List<String>>> map = response.getHighlighting();  
        	for(SolrDocument solrDocument : results){
        		object = clzz.newInstance();
        		// 得到所有属性名  
                Collection<String> fieldNames = solrDocument.getFieldNames();  
                for(String fieldName : fieldNames){
                	Field[] fields = clzz.getDeclaredFields(); 
                	for(Field f : fields){
                		// 如果实体属性名和查询返回集中的字段名一致，填充对应的set方法  
                        if(f.getName().equals(fieldName)){
                        	f = clzz.getDeclaredField(fieldName);  
                            fieldType = f.getType();  
                            // 构造set方法名 setId  
                            String dynamicSetMethod = dynamicMethodName(f.getName(), "set");  
                            // 获取方法  
                            method = clzz.getMethod(dynamicSetMethod, fieldType);  
                            // 获取fieldType类型  
                            // fieldType = getFileType(fieldType);  
                            // 获取到的属性  
                            method.invoke(object, fieldType.cast(solrDocument.getFieldValue(fieldName)));  
                            for(String hl : hlFields){
                            	if(hl.equals(fieldName)){
                            		String idv = solrDocument.getFieldValue(idName).toString();  
                                    List<String> hfList = map.get(idv).get(fieldName);  
                                    if(null != hfList && hfList.size() > 0){
                                    	// 高亮添加  
                                        method.invoke(object, fieldType.cast(hfList.get(0)));  
                                    }else{
                                    	method.invoke(object, fieldType.cast(solrDocument.getFieldValue(fieldName)));  
                                    }
                            	}
                            }
                        }
                	}
                }
                list.add(clzz.cast(object));  
        	}
        } catch (Exception e) {
        	e.printStackTrace();  
            return null;  
        }
		return new Page<T>(pageNum, pageSize, total, list);  
	}
	 
	/**
	 * 根据删除单个索引
	 * @param id
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void deleteById(String id) throws SolrServerException, IOException{
		httpSolrServer.deleteById(id);
		httpSolrServer.commit(false, false);
	}
	
	/**
	 * 批量删除索引
	 * @param ids
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void deleteByIds(List<String> ids) throws SolrServerException, IOException{
		httpSolrServer.deleteById(ids);
		httpSolrServer.commit(false, false);
	}
	
	/**
	 * 删除全部
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void deleteAll() throws SolrServerException, IOException{
		httpSolrServer.deleteByQuery("*:*");
		httpSolrServer.commit(false, false);
	}
	
	
	/** 
     * @Description: 动态生成方法名 
     * @param @param name 
     * @param @param string 
     * @param @return 
     * @return String 
     * @throws 
     */  
    private static String dynamicMethodName(String name, String string){  
        if (Character.isUpperCase(name.charAt(0))){
        	return string + name; 
        }else{
        	 return (new StringBuilder()).append(string + Character.toUpperCase(name.charAt(0))).append(name.substring(1)).toString();  
        }
    }  

    /** 
     * @Description:因为反射的属性可能是一个集合,所以在利用反射转换之前,需要进行更精确地判断,这实例中实体对象中的属性为简单类型,所以这个方法可以处理 
     * @param @param fieldType 
     * @param @return 
     * @return Class<?> 
     * @throws 
     */  
    public static Class<?> getFileType(Class<?> fieldType)  
    {  
        // 如果是 int, float等基本类型，则需要转型  
        if (fieldType.equals(Integer.TYPE))  
        {  
            return Integer.class;  
        }  
        else if (fieldType.equals(Float.TYPE))  
        {  
            return Float.class;  
        }  
        else if (fieldType.equals(Double.TYPE))  
        {  
            return Double.class;  
        }  
        else if (fieldType.equals(Boolean.TYPE))  
        {  
            return Boolean.class;  
        }  
        else if (fieldType.equals(Short.TYPE))  
        {  
            return Short.class;  
        }  
        else if (fieldType.equals(Long.TYPE))  
        {  
            return Long.class;  
        }  
        else if (fieldType.equals(String.class))  
        {  
            return String.class;  
        }  
        else if (fieldType.equals(Collection.class)) { return Collection.class; }  
        return null;  
    }
}
