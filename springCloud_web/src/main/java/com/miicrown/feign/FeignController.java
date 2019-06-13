package com.miicrown.feign;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miicrown.entity.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping("feign/")
public class FeignController {

	@RequestMapping("/hello")
	public String hello(){
		log.info("访问feign.hello");
		return "hello";
	}
	
	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(@RequestParam String name){
		log.info("访问feign.hello1");
		return "hello";
	}
	
	@RequestMapping(value = "/hello2", method= RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
		log.info("访问feign.hello2");
        return new User();
    }
	
	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello(@RequestBody User user) {
		log.info("访问feign.hello3");
        return "Hello "+ user.getUsername() + ", " + user.getPassword();
    }
	
}
