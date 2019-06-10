package com.miicrown.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=false)
public class CmdDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442472005915630644L;

	private String lampId;  //单灯编号
	
	private String cmdType;   //命令类型
	
	private String cmdContentHex;    //命令内容,HEX文本
	
	private String values;   //命令内容
	
	private String lampIds;  //单灯编号, 批量
	
	
	
}
