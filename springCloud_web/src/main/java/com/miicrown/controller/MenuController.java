package com.miicrown.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.miicrown.entity.User;
import com.miicrown.service.MenuService;

@RestController
@RequestMapping(value="menu/")
public class MenuController {
	
	@Autowired
	MenuService menuService;

	@RequestMapping(value="nav", method=RequestMethod.GET)
	@ResponseBody
	public Object authLogin(@ModelAttribute User obj,ModelMap modelMap, HttpServletRequest request){
		return menuService.nav();
	}
	
}
