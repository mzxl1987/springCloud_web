package com.miicrown.entity;

import com.miicrown.enums.ErrorCode;

public class Result {
	
	public int code;
	public String msg;
	
	private Result(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	private Result(ErrorCode ec){
		this.code = ec.getCode();
		this.msg = ec.getMsg();
	}
	
	public static Result getInstance(){
		return new Result(ErrorCode.OK);
	}
	
	public static Result getInstance(ErrorCode ec,String msg){
		return new Result(ec.getCode(),msg);
	}
	
	public static Result getInstance(ErrorCode ec){
		return new Result(ec);
	}
	
	public static Result getInstance(int code,String msg){
		return new Result(code,msg);
	}
	
}
