package com.miicrown.entity.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class LampDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442472005915630644L;
	
	private String equipName;   //设备名称 
	
	private String equipNumber;  //设备编号
	
	private String equipType;    //设备类型
	
	private String manufacturer;     //生产厂家
	
	private String lampCount;       //灯盏数量
	
	private String dimType;         //调光方式
	
	private String equipTagNumber;  //中断标签号
	
	private String simNumber;       //SIM卡号
	
	private String mobileNumber;    //手机号
	
	private String lampType;        //光源类型
	
	private String serviceLife;      //使用年限
	
	private Date produceDate;            //生产日期
	
	private String accessWay;        //接入方式: GPRS/NB-IOT/4G LTE
	
	
	/******************************  召测信息  *******************************************/
	
	private String voltage1;         //电压
	
	private String current1;         //电流
	
	private String power1;         //功率
	
	private String voltage2;         //电压
	
	private String current2;         //电流
	
	private String power2;         //功率
	
	private String voltage3;         //电压
	
	private String current3;         //电流
	
	private String power3;         //功率
	
	private String voltage4;         //电压
	
	private String current4;         //电流
	
	private String power4;         //功率
	
	private String controlMode;      //控制模式,手动/自动
	
	private String alarm;             //报警信息
	
	private Date alarmDate;            //报警时间
}
