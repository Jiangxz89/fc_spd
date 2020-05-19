package com.thinkgem.hys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.thinkgem.hys.pd.entity.PdCategory;
import com.thinkgem.hys.pd.entity.PdGroup;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.hys.pd.entity.PdSupplier;
import com.thinkgem.hys.pd.entity.PdVender;
import com.thinkgem.hys.pd.service.PdCategoryService;
import com.thinkgem.hys.pd.service.PdGroupService;
import com.thinkgem.hys.pd.service.PdStoreroomAdminService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.hys.pd.service.PdSupplierService;
import com.thinkgem.hys.pd.service.PdVenderService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class SpdUtils {

	private static PdStoreroomService pdStoreroomService = SpringContextHolder.getBean(PdStoreroomService.class); 
	private static PdSupplierService pdSupplierService = SpringContextHolder.getBean(PdSupplierService.class); 
	private static PdVenderService pdVenderService= SpringContextHolder.getBean(PdVenderService.class); 
	private static PdStoreroomService storeroomService= SpringContextHolder.getBean(PdStoreroomService.class); 
	private static PdStoreroomAdminService storeroomAdminService= SpringContextHolder.getBean(PdStoreroomAdminService.class);
	private static PdGroupService pdGroupService=SpringContextHolder.getBean(PdGroupService.class);
	private static PdCategoryService pdCategoryService=SpringContextHolder.getBean(PdCategoryService.class);
	public static final String CACHE_STOREROOM_MAP = "storeroomMap";
	public static final String CACHE_SUPPLIER_MAP = "supplierMap";
	
	/**
	 * 返回库房信息列表
	 * @return
	 */
	public static List<PdStoreroom> findStoreroomList(){
		List<PdStoreroom> stlist = null;
		Map<String, PdStoreroom> srMap = (Map<String, PdStoreroom>)CacheUtils.get(CACHE_STOREROOM_MAP);
		if(srMap == null){
			srMap = Maps.newHashMap();
			stlist = pdStoreroomService.findList(new PdStoreroom());
			for (PdStoreroom storeroom:stlist){
				srMap.put(storeroom.getId(), storeroom);
			}
			CacheUtils.put(CACHE_STOREROOM_MAP, srMap);
		}else{
			stlist = new ArrayList<PdStoreroom>(srMap.values());
		}
		
		return stlist;
	}
	
	/**
	 * 返回一级库房
	 * @return
	 */
	public static String findFirstStoreroom(){
		String storeroomCode = pdStoreroomService.findFirstStoreroom();
		return storeroomCode;
	} 
	
	/**
	 * 返回供应商信息列表
	 * @return
	 */
	public static List<PdSupplier> findSupplierList(){
		List<PdSupplier> supList = null;
		Map<String, PdSupplier> supplierMap = (Map<String, PdSupplier>)CacheUtils.get(CACHE_SUPPLIER_MAP);
		if(supplierMap == null){
			supplierMap = Maps.newHashMap();
			supList = pdSupplierService.findList(new PdSupplier());
			for (PdSupplier pdSupplier:supList){
				if(StringUtils.isNotEmpty(pdSupplier.getPinyin())){
					pdSupplier.setNameAndpinyin(pdSupplier.getSupplierName()+"("+pdSupplier.getPinyin()+")");
				}else{
					pdSupplier.setNameAndpinyin(pdSupplier.getSupplierName());
				}
				supplierMap.put(pdSupplier.getId(), pdSupplier);

			}
			CacheUtils.put(CACHE_SUPPLIER_MAP, supplierMap);
		}else{
			supList = new ArrayList<PdSupplier>(supplierMap.values());
			for (PdSupplier pdSupplier:supList){
				if(StringUtils.isNotEmpty(pdSupplier.getPinyin())){
					pdSupplier.setNameAndpinyin(pdSupplier.getSupplierName()+"("+pdSupplier.getPinyin()+")");
				}else{
					pdSupplier.setNameAndpinyin(pdSupplier.getSupplierName());
				}
				supplierMap.put(pdSupplier.getId(), pdSupplier);
			}
		}

		return supList;
	}
	
	/**
	 * 返回供应商信息列表
	 * @return
	 */
	public static List<PdStoreroom> findSuperStoroomList(){
		
		List<PdStoreroom> roomList = findStoreroomList();
		
		List<PdSupplier> suppList = findSupplierList();
		if(suppList == null){
			return roomList;
		}
		PdStoreroom room = null;
		String suppId = null;
		for(PdSupplier supp:suppList){
			room = new PdStoreroom();
			suppId = supp.getId();
			room.setId("|" + suppId);
			room.setStoreroomName(supp.getSupplierName());
			
			roomList.add(room);
		}

		return roomList;
	}
	
	/**
	 * 返回供应商名称
	 * id       供应商id
	 * @return  String   供应商名称
	 */
	public static String getSupplierName(String id){
		if(StringUtils.isEmpty(id)){
			return "";
		}
		Map<String, PdSupplier> supplierMap = (Map<String, PdSupplier>)CacheUtils.get(CACHE_SUPPLIER_MAP);
		if (supplierMap == null){
			supplierMap = Maps.newHashMap();
			for (PdSupplier pdSupplier:pdSupplierService.findList(new PdSupplier())){
				supplierMap.put(pdSupplier.getId(), pdSupplier);
			}
			CacheUtils.put(CACHE_SUPPLIER_MAP, supplierMap);
		}
		PdSupplier upplier = supplierMap.get(id);
		if (upplier != null){
			return upplier.getSupplierName();
		}
		return "";
	}
	
	/**
	 * 返回库房名称
	 * id       库房ID
	 * @return  String  库房名称
	 */
	public static String getStoreroomName(String id){
		if(StringUtils.isEmpty(id)){
			return "";
		}
		
		Map<String, PdStoreroom> storeroomMap = (Map<String, PdStoreroom>)CacheUtils.get(CACHE_STOREROOM_MAP);
		if (storeroomMap == null){
			storeroomMap = Maps.newHashMap();
			for (PdStoreroom storeroom : storeroomService.findList(new PdStoreroom())){
				storeroomMap.put(storeroom.getId(), storeroom);
			}
			CacheUtils.put(CACHE_STOREROOM_MAP, storeroomMap);
		}
		
		PdStoreroom storeroom = storeroomMap.get(id);
		if (storeroom != null){
			return storeroom.getStoreroomName();
		}

		return "";
	}
	
	/**
	 * 返回库房类型
	 * @param id
	 * @return
	 */
	public static String getStoreroomType(String id){
		if(StringUtils.isEmpty(id)){
			return "";
		}
		
		Map<String, PdStoreroom> storeroomMap = (Map<String, PdStoreroom>)CacheUtils.get(CACHE_STOREROOM_MAP);
		if (storeroomMap == null){
			storeroomMap = Maps.newHashMap();
			for (PdStoreroom storeroom : storeroomService.findList(new PdStoreroom())){
				storeroomMap.put(storeroom.getId(), storeroom);
			}
			CacheUtils.put(CACHE_STOREROOM_MAP, storeroomMap);
		}
		
		PdStoreroom storeroom = storeroomMap.get(id);
		if (storeroom != null){
			return storeroom.getStoreroomType();
		}

		return "";
	}
	
	/**
	 * 返回生产厂家信息列表
	 * @return
	 */
	public static List<PdVender> findPdVenderList(){
		PdVender pdVenderS = new PdVender();
		List<PdVender> list = pdVenderService.findList(pdVenderS);
		if(list != null && list.size() > 0){
			for (PdVender pdVender : list) {
				pdVender.setNameAndpinyin(
						pdVender.getPinyin()!=null && !"".equals(pdVender.getPinyin())
								? pdVender.getName()+"("+pdVender.getPinyin()+")" : pdVender.getName());
			}
		}
		return list;
	}
	
	/**
	 * 返回产品组别列表
	 * @return
	 */
	public static List<PdGroup> findPdGroupList(){
		PdGroup pdGroup = new PdGroup();
		List<PdGroup> list = pdGroupService.findList(pdGroup);
		return list;
	}
	/**
	 * 返回产品分类列表
	 * @return
	 */
	public static List<PdCategory> findPdCategoryList(){
		PdCategory pdCategory = new PdCategory();
		List<PdCategory> list = pdCategoryService.findList(pdCategory);
		return list;
	}
	
	/**
	 * 按传入库房ID查询库管员列表
	 * @return   List<PdStoreroomAdmin>  传入库房ID查询库管员列表
	 */
	public static List<PdStoreroomAdmin> findRoomAdmins(String storeroomId){
		if(StringUtils.isEmpty(storeroomId)){
			return null;
		}
		PdStoreroomAdmin roomAdmin = new PdStoreroomAdmin();
		roomAdmin.setStoreroomId(storeroomId);
		List<PdStoreroomAdmin> list = storeroomAdminService.findAdminList(roomAdmin);
		
		return list;
	}
	
	/**
	 * 查询所有的库管员
	 * @param
	 * @return
	 */
	public static List<PdStoreroomAdmin> findRoomAdminAll(){
		PdStoreroomAdmin roomAdmin = new PdStoreroomAdmin();
		List<PdStoreroomAdmin> list = storeroomAdminService.findAdminList(roomAdmin);
		return list;
	}
	
	/**
	 * 根据用户id返回用户name
	 * */
	public static String getUserNameById(String id){
		User user = UserUtils.get(id);
		return user.getName();
	}
	
	/**
	 * 根据组别id返回组别name
	 * */
	public static String getGroupName(String id){
		PdGroup pdGroup = pdGroupService.get(id);
		if(pdGroup != null){
			return pdGroup.getName();
		}else{
			return "";
		}
	}
	
	/**
	 * 根据类别id返回类别name
	 * */
	public static String getCategoryName(String id){
		PdCategory pdCategory = pdCategoryService.get(id);
		if(pdCategory != null){
			return pdCategory.getName();
		}else{
			return "";
		}
	}
	
	/**
	 * 根绝厂家id返回厂家name
	 * */
	public static String getVenderName(String id){
		PdVender pdVender = pdVenderService.get(id);
		if(pdVender != null){
			return pdVender.getName();
		}else{
			return "";
		}
	}
	/**
	 * 更新缓存库房信息
	 */
	public static void updateStoreroomInfo(){
		List<PdStoreroom> stlist = pdStoreroomService.findList(new PdStoreroom());
		Map<String, PdStoreroom> srMap = new HashMap<String, PdStoreroom>();
		for (PdStoreroom storeroom:stlist){
			srMap.put(storeroom.getId(), storeroom);
		}
		CacheUtils.put(CACHE_STOREROOM_MAP, srMap);
	}
	
	
	/**
	 * 更新缓存供应商信息
	 */
	public static void updateSupploerInfo(){
		List<PdSupplier> suppliers = pdSupplierService.findList(new PdSupplier());
		Map<String, PdSupplier> srMap = new HashMap<String, PdSupplier>();
		for (PdSupplier pdSupplier:suppliers){
			srMap.put(pdSupplier.getId(), pdSupplier);
		}
		CacheUtils.put(CACHE_SUPPLIER_MAP, srMap);
	}
}
