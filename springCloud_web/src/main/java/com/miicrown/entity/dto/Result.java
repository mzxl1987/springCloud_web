package com.miicrown.entity.dto;

import java.util.List;

import com.miicrown.enums.ErrorCode;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result<T> {
	
	public int code;
	public String msg;
	public boolean success;
	public long total;
	public List<T> data;
	
	private Result(int code, String msg){
		this.code = code;
		this.msg = msg;
		this.success = true;
	}
	
	private Result(ErrorCode ec){
		this(ec.getCode(), ec.getMsg());		
	}
	
	@SuppressWarnings("rawtypes")
	public static Result getInstance(){
		return new Result(ErrorCode.OK);
	}
	
	@SuppressWarnings("rawtypes")
	public static Result getInstance(ErrorCode ec,String msg){
		return new Result(ec.getCode(),msg);
	}
	
	@SuppressWarnings("rawtypes")
	public static Result getInstance(ErrorCode ec){
		return new Result(ec);
	}
	
	@SuppressWarnings("rawtypes")
	public static Result getInstance(int code,String msg){
		return new Result(code,msg);
	}
	
}
