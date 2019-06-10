package com.miicrown.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.miicrown.entity.Menu;
import com.miicrown.entity.dto.MenuDto;
import com.miicrown.netty.service.SqlService;
import com.miicrown.repository.MenuRepository;
import com.miicrown.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class MenuServiceImpl implements MenuService{
	
	private MenuDto nav;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	SqlService sqlService;
	
	public MenuServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@PostConstruct
	@Override
	public void loadMenu(){
		Map<String, MenuDto> map_menu = new HashMap<>();
		
		MenuDto root = null;
		MenuDto tmp = null;
		
		List<Menu> menus = menuRepository.QueryAll();
		for (Menu m : menus) {
			MenuDto md = MenuDto.builder(m);
			if(root == null){
				root = md;
				map_menu.put(md.getId(), md);
			}else{
				if(map_menu.containsKey(md.getParentId())){
					tmp = map_menu.get(md.getParentId());
					tmp.setLeaf(false);
					tmp.getChildren().add(md);
				}
			}
		}
		
		
		nav = root;
		
		log.info(" MenuDto {}", map_menu);
		
	}


	public Object nav(){
		loadMenu();
		return nav;
	}
		
}
