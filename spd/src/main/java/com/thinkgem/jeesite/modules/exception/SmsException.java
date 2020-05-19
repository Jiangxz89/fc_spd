package com.thinkgem.jeesite.modules.exception;

public class SmsException extends RuntimeException{
	private static final long serialVersionUID = 1L;  
	  
    public SmsException() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public SmsException(String message) {  
        super(message);  
        // TODO Auto-generated constructor stub  
    }  
  
    public SmsException(Throwable cause) {  
        super(cause);  
        // TODO Auto-generated constructor stub  
    }  
  
    public SmsException(String message, Throwable cause) {  
        super(message, cause);  
        // TODO Auto-generated constructor stub  
    }  
}
