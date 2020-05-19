package com.thinkgem.hys.pd.response;

import java.io.Serializable;


/**
 * 功能: 
 */
public class CommonRspVo implements Serializable {
	
	public CommonRspVo() {
		this.setCode("200");
	}
	
	public CommonRspVo(String code) {
		this.setCode(code);
	}
	
	public CommonRspVo(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 结果编码
	 * 0 - 成功
	 */
	private String code;
	/**
	 * 返回结果描述
	 */
	private String message; 
	
	/**
	 * 返回的具体数据
	 */
	private Object data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static final String RESULT_CODE_SUCCESS = "0";//成功
	public static final String RESULT_CODE_FAILURE = "1";//失败
	public static CommonRspVo success(String msg) {
		return new CommonRspVo(RESULT_CODE_SUCCESS, msg);
	}
	
	public static CommonRspVo failure(String msg) {
		return new CommonRspVo(RESULT_CODE_FAILURE, msg);
	}
	
	
}
