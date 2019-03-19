package com.miicrown.controller.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/rest")
@Api(value="Swagger API接口测试")
public class RestfulController {


	@RequestMapping(value="/add/{a}/{b}", method = RequestMethod.POST, produces="application/json")
	@ApiOperation(nickname="a+b",httpMethod="POST",notes="求a+b的值", value = "求和")
	@ApiImplicitParams({
		@ApiImplicitParam(name="a", value="A 第1个数", defaultValue="10"),
		@ApiImplicitParam(name="b", value="B 第2个数", defaultValue="20"),
	})
	public int add(@PathVariable(value="a") int a,@PathVariable(value="b") int b){
		return a + b;
	}	
}
