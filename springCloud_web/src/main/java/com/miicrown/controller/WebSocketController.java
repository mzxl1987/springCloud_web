package com.miicrown.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miicrown.service.ApiService;
import com.miicrown.websocket.WebSocketServer;

@Controller
@RequestMapping(value="ws/")
public class WebSocketController {
	
	@Autowired
	ApiService apiService;

	//推送数据接口
	@ResponseBody
	@RequestMapping("push/{cid}")
	public Object pushToWeb(@PathVariable String cid,String message) {  
		try {
			WebSocketServer.sendInfo(message,cid);
		} catch (IOException e) {
			e.printStackTrace();
			return "ERROR:" + cid+"#"+e.getMessage();
		}  
		return "SUCCESS:" + cid;
	} 

}
