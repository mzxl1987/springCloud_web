package com.miicrown.service;

import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;

import com.miicrown.entity.Lamp;
import com.miicrown.entity.dto.Result;

public interface LampService {
	
	public Result<?> save(Lamp obj) throws Exception;
	public Result<?> edit(Lamp obj) throws Exception;	
	public Result<?> delete(Lamp obj) throws Exception;
	
	public Result<?> QueryAll(Lamp obj) throws Exception;
	public PageDto QueryPage(Lamp obj,PageDto page) throws Exception;
}
