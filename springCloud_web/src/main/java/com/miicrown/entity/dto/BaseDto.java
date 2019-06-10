package com.miicrown.entity.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=50,unique=true,nullable=false)
	private String id;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date createDate;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	private String createBy;
	
	private String updateBy;
	
}
