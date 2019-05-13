package com.miicrown.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.miicrown.serializer.DateToYYYYMMDDHHmmssSerializer;
import com.miicrown.serializer.DateToYYYYMMDDSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
@Entity
@Table(name="t_lamp")
public class Lamp extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442472005915630644L;
	
	@Column(unique=false)
	private String equipName;   //设备名称 
	
	@Column(unique=false)
	private String equipNumber;  //设备编号
	
	private String equipType;    //设备类型
	
	private String manufacturer;     //生产厂家
	
	private String lampCount;       //灯盏数量
	
	private String dimType;         //调光方式

	private String wiringType;      //接线方式
	
	private String equipTagNumber;  //设备标签号
	
	private String simNumber;       //SIM卡号
	
	private String mobileNumber;    //手机号
	
	private String lampType;        //光源类型
	
	private String serviceLife;      //使用年限
	
	@JsonSerialize(using=DateToYYYYMMDDSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date produceDate;            //生产日期
	
	private String accessWay;        //接入方式: GPRS/NB-IOT/4G LTE
	
	private String remark;           //备注
	
	/******************************  召测信息  *******************************************/
	
	private String voltage1;         //电压
	
	private String current1;         //电流
	
	private String dim1;            //调光
	
	private String power1;         //功率
	
	private String powerFactor1;         //功率因素
	
	private String voltage2;         //电压
	
	private String current2;         //电流
	
	private String dim2;            //调光
	
	private String power2;         //功率
	
	private String powerFactor2;         //功率因素
	
	private String voltage3;         //电压
	
	private String current3;         //电流
	
	private String dim3;            //调光
	
	private String power3;         //功率
	
	private String powerFactor3;         //功率因素
	
	private String voltage4;         //电压
	
	private String current4;         //电流
	
	private String dim4;            //调光
	
	private String power4;         //功率
	
	private String powerFactor4;         //功率因素
	
	private String controlMode;      //控制模式,手动/自动
	
	private String alarm;             //报警信息
	
	private String leakageVoltage;    //漏电电压
	
	private String leakageCurrent;    //漏电电流
	
	private String waterLog;           //水浸
	
	private String totalRunningTime;   //总运行时间
	
	private String currentRunningTime;  //当前运行时间
	
	@JsonSerialize(using=DateToYYYYMMDDHHmmssSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date alarmDate;            //报警时间
	
	@JsonSerialize(using=DateToYYYYMMDDHHmmssSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date heartBeatDate;            //心跳时间
	
	@JsonSerialize(using=DateToYYYYMMDDHHmmssSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date zhaoceDate;            //召测时间
}
