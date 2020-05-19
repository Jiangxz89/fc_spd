package com.thinkgem.jeesite.modules.exception;

public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 1L;  
	  
    public BusinessException() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public BusinessException(String message) {  
        super(message);  
        // TODO Auto-generated constructor stub  
    }  
  
    public BusinessException(Throwable cause) {  
        super(cause);  
        // TODO Auto-generated constructor stub  
    }  
  
    public BusinessException(String message, Throwable cause) {  
        super(message, cause);  
        // TODO Auto-generated constructor stub  
    }  
}
