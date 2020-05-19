package com.thinkgem.hys.pd.constants;

public enum MinaMsgCode {
	SYSTEM_EXCEPTION("系统错误！","500"),
	OPERATOR_SUCCESS("操作成功！","200"),
	OPERATOR_ERROR("操作失败！","-200"),
	NO_DATA("暂无数据！","201"),
	INFO_NOT_COMPLETED("提交信息不完整！","301");
	
	
	private String info;
	private String key;
	
	private MinaMsgCode(String info,String key) {
		this.info = info;
		this.key = key;
	}
	
	public String getInfo() {
		return this.info;
	}
	public String getKey() {
		return this.key;
	}
	public  String getValue(String key){
		return  this.getValue( key );
	}

}
