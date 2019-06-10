package com.miicrown.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=false)
@Entity
@Table(name="t_cmd")
public class Cmd extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442472005915630644L;

	@Column
	private String lampId;  //单灯编号
	
	@Column
	private String cmdType;   //命令类型
	
	@Column
	private String cmdContentHex;    //命令内容,HEX文本
	
	
}
