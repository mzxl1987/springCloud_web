package com.miicrown.enums;

public enum ErrorCode {

	OK(0,"OK"),
	ERROR(1,"ERROR");
	
	
	
	int code;
	String msg;
	
	ErrorCode(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
}
