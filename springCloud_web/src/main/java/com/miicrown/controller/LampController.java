package com.miicrown.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miicrown.entity.Lamp;
import com.miicrown.entity.dto.PageDto;
import com.miicrown.service.LampService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping(path="/lamp")
@Api(value="路灯 Controller")
public class LampController {

	@Autowired
	LampService lampService;
	
	@ResponseBody
	@RequestMapping(path="/add", method = RequestMethod.POST)
	public Object add(@ModelAttribute Lamp obj,ModelMap modelMap, HttpServletRequest request) throws Exception{
		return lampService.save(obj); 
	}
	
	@ResponseBody
	@RequestMapping(path="/edit", method = RequestMethod.POST)
	public Object edit(@ModelAttribute Lamp obj,ModelMap modelMap, HttpServletRequest request) throws Exception{
		return lampService.edit(obj); 
	}
	
	@ResponseBody
	@RequestMapping(path="/delete", method = RequestMethod.POST)
	public Object delete(@ModelAttribute Lamp obj,ModelMap modelMap, HttpServletRequest request) throws Exception{
		return lampService.delete(obj); 
	}
	
	@ResponseBody
	@RequestMapping(path="/queryByPage", method = RequestMethod.GET)
	public Object queryByPage(@ModelAttribute Lamp obj,@ModelAttribute PageDto page ,ModelMap modelMap, HttpServletRequest request) throws Exception{
		return lampService.QueryPage(obj,page); 
	}
	
}
