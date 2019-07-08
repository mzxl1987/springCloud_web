package com.miicrown.entity.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class OilDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442472005915630644L;
	
	private String equipNumber;  //设备编号
	private int percent;  //百分比
	private int rawValue;  //MCU测得的实际值
	

	private String headtitle;  //标题
	
	private String locationstr; // 地址描述
	
	private int volume;      //容量
	
	private int height;      // 桶高
	
	private String lon;         // 经度
	
	private String lat;         // 纬度
	
	private int emptyvalue;     // 空桶传感器的值
	
	private int cheight;        //标定高度
	
	private int cvalue;         //标定值
	
	public OilDto(){};

	/**
	 * 将Object[] 转换成 Dto
	 * @param id
	 * @param equipNumber
	 * @param rawValue
	 * @param createDate
	 * @param updateDate
	 * @param headtitle
	 * @param locationstr
	 * @param volume
	 * @param height
	 * @param lon
	 * @param lat
	 * @param emptyvalue
	 * @param cheight
	 * @param cvalue
	 */
	public OilDto(String id,
			String equipNumber,
			Integer rawValue,
			Timestamp createDate,
			Timestamp updateDate,
			String headtitle,
			String locationstr,
			Integer volume,
			Integer height,
			String lon,
			String lat,
			Integer emptyvalue,
			Integer cheight,
			Integer cvalue){
		
		this.setId(id);
		this.setEquipNumber(equipNumber);
		this.setRawValue(isEmptyOrNull(rawValue) ? 0 : rawValue);
		this.setHeadtitle(headtitle);
		this.setLocationstr(locationstr);
		this.setVolume(isEmptyOrNull(volume) ? 0 : volume);
		this.setHeight(isEmptyOrNull(height) ? 0 : height);
		this.setLon(lon);
		this.setLat(lat);
		this.setEmptyvalue(isEmptyOrNull(emptyvalue) ? 0 : emptyvalue);
		this.setCheight(isEmptyOrNull(cheight) ? 0 : cheight);
		this.setCvalue(isEmptyOrNull(cvalue) ? 0 : cvalue);
		this.setCreateDate(createDate);
		this.setUpdateDate(updateDate);
	}
	
}
