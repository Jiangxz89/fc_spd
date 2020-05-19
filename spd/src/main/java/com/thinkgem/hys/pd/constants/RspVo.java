package com.thinkgem.hys.pd.constants;

public class RspVo {

	private String code;
	private String info;
	private String uri;
	
	public RspVo(){
		this.code = "200";
	}
	
	public RspVo(String code, String info){
		this.code = code;
		this.info = info;
	}

	public String getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
