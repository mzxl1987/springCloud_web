package com.miicrown.entity.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.miicrown.entity.Menu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@Table(name="t_menu")
public class MenuDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    
	private String text; 
	private String iconCls;
	
	private String routeId;
	private String viewType;
	
	private String parentId;
	
	private boolean leaf;
	
	private List<MenuDto> children;
	
	private boolean expanded;
	
	private MenuDto(){
		expanded = false;
		leaf = true;
		children = new ArrayList<>();
		routeId = null;
		viewType = null;
		iconCls = null;
	}
	
	public static MenuDto builder(Menu m){
		MenuDto md = new MenuDto();
		BeanUtils.copyProperties(m, md);
		
		return md;
	}
	
}
