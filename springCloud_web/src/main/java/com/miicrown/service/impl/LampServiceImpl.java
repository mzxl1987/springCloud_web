package com.miicrown.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.miicrown.entity.Lamp;
import com.miicrown.entity.dto.PageDto;
import com.miicrown.entity.dto.Result;
import com.miicrown.netty.service.SqlService;
import com.miicrown.repository.LampRepository;
import com.miicrown.service.LampService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class LampServiceImpl implements LampService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private LampRepository lampRepository;
	
	@Autowired
	SqlService sqlService;
	
	public LampServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	/* (non-Javadoc)
	 * @see com.miicrown.service.LampService#save()
	 */
	@Override
	public Result<?> save(Lamp obj) throws Exception {
		
		log.info("SAVE LAMP");
		
		Result<?> r = Result.getInstance();
		
		obj.setId("" + System.nanoTime());
		obj.setCreateDate(DateUtil.now());
		obj.setUpdateDate(new Date());
		
		lampRepository.save(obj);
		
		String createTable = "CREATE TABLE l_%s ("+
			  "id varchar(50) NOT NULL,"+
//			  "access_way varchar(255) DEFAULT NULL,"+
			  "alarm varchar(255) DEFAULT NULL,"+
			  "alarm_date datetime DEFAULT NULL,"+
			  "control_mode varchar(255) DEFAULT NULL,"+
//			  "create_by varchar(255) DEFAULT NULL,"+
//			  "create_date datetime DEFAULT NULL,"+
			  "current1 varchar(255) DEFAULT NULL,"+
			  "current2 varchar(255) DEFAULT NULL,"+
			  "current3 varchar(255) DEFAULT NULL,"+
			  "current4 varchar(255) DEFAULT NULL,"+
//			  "dim_type varchar(255) DEFAULT NULL,"+
//			  "equip_name varchar(255) DEFAULT NULL,"+
//			  "equip_number varchar(255) DEFAULT NULL,"+
//			  "equip_tag_number varchar(255) DEFAULT NULL,"+
//			  "equip_type varchar(255) DEFAULT NULL,"+
//			  "lamp_count varchar(255) DEFAULT NULL,"+
//			  "lamp_type varchar(255) DEFAULT NULL,"+
//			  "manufacturer varchar(255) DEFAULT NULL,"+
//			  "mobile_number varchar(255) DEFAULT NULL,"+
			  "power1 varchar(255) DEFAULT NULL,"+
			  "power2 varchar(255) DEFAULT NULL,"+
			  "power3 varchar(255) DEFAULT NULL,"+
			  "power4 varchar(255) DEFAULT NULL,"+
			  "produce_date datetime DEFAULT NULL,"+
//			  "remark varchar(255) DEFAULT NULL,"+
//			  "service_life varchar(255) DEFAULT NULL,"+
//			  "sim_number varchar(255) DEFAULT NULL,"+
//			  "update_by varchar(255) DEFAULT NULL,"+
//			  "update_date datetime DEFAULT NULL,"+
			  "voltage1 varchar(255) DEFAULT NULL,"+
			  "voltage2 varchar(255) DEFAULT NULL,"+
			  "voltage3 varchar(255) DEFAULT NULL,"+
			  "voltage4 varchar(255) DEFAULT NULL,"+
//			  "wiring_type varchar(255) DEFAULT NULL,"+
//			  "heart_beat_date datetime DEFAULT NULL,"+
			  "zhaoce_date datetime DEFAULT NULL,"+
			  "power_factor1 varchar(255) DEFAULT NULL,"+
			  "power_factor2 varchar(255) DEFAULT NULL,"+
			  "power_factor3 varchar(255) DEFAULT NULL,"+
			  "power_factor4 varchar(255) DEFAULT NULL,"+
			  "current_running_time varchar(255) DEFAULT NULL,"+
			  "dim1 varchar(255) DEFAULT NULL,"+
			  "dim2 varchar(255) DEFAULT NULL,"+
			  "dim3 varchar(255) DEFAULT NULL,"+
			  "dim4 varchar(255) DEFAULT NULL,"+
			  "leakage_current varchar(255) DEFAULT NULL,"+
			  "leakage_voltage varchar(255) DEFAULT NULL,"+
			  "total_running_time varchar(255) DEFAULT NULL,"+
			  "water_log varchar(255) DEFAULT NULL,"+
			 " PRIMARY KEY (id)"+
			 ") ENGINE=MyISAM DEFAULT CHARSET=utf8;";
		
		jdbcTemplate.execute(String.format(createTable, obj.getId()));
		
		sqlService.addMapLamps(obj.getEquipNumber(), obj.getId());
		
		return r;
	}
	
	/* (non-Javadoc)
	 * @see com.miicrown.service.LampService#edit()
	 */
	@Override
	public Result<?> edit(Lamp obj) throws Exception {
		
		log.info("EDIT LAMP");
		
		Result<?> r = Result.getInstance();
		obj.setUpdateDate(DateUtil.now());
		lampRepository.update(obj);
		
		return r;
	}
	
	/* (non-Javadoc)
	 * @see com.miicrown.service.LampService#delete()
	 */
	@Override
	public Result<?> delete(Lamp obj) throws Exception {
		
		log.info("DELETE LAMP");
		
		Result<?> r = Result.getInstance();
		List<String> ids = Arrays.asList(obj.getId().split(","));
		
		lampRepository.delete(ids);
		
		String deleteSQL = "drop table l_%s";
		
		ids.forEach(id -> {
			jdbcTemplate.execute(String.format(deleteSQL, id));
			
			sqlService.removeMapLamps(id);
			
		});
		
		return r;
	}

	/* (non-Javadoc)
	 * @see com.miicrown.service.LampService#findLamps(java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result<?> QueryAll(Lamp obj) throws Exception {
		
		Result<?> r = Result.getInstance();
		
		List listData = lampRepository.findAll();
		r.setTotal(listData.size());
		r.setData(listData);
		
		return r;
	}

	/* (non-Javadoc)
	 * @see com.miicrown.service.LampService#QueryByPage(com.miicrown.entity.Lamp)
	 */
	@Override
	public PageDto QueryPage(Lamp obj,PageDto p) throws Exception {
		
		Pageable pageable = PageRequest.of(p.getStart() / p.getLimit(), p.getLimit());
		Page<Lamp> page = lampRepository.QueryPage(obj, pageable);
		
		p.setTotalCount(page.getTotalElements());
		p.setTotalPage(page.getTotalPages());
		p.setData(page.getContent());
		
		return p;
	}
	
	



		
}
