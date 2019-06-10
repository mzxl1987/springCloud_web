package com.miicrown.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@Table(name="t_menu")
public class Menu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    
	private String text; 
	private String iconCls;
	
	private String routeId;
	private String viewType;
	
	private String parentId;
	
}
