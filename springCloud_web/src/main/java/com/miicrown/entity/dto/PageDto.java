package com.miicrown.entity.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageDto{
	
	private int start;
	private int limit;
	@SuppressWarnings("rawtypes")
	private List data;
	private long totalCount;
	private long totalPage;
	
}
