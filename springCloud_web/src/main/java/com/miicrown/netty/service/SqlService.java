package com.miicrown.netty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.miicrown.entity.Lamp;
import com.miicrown.repository.LampRepository;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SqlService {
	
	public static ConcurrentMap<String, String> map_lamps = PlatformDependent.newConcurrentHashMap();
	public static LinkedBlockingQueue<String> queue_insertSQL = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<String> queue_updateSQL = new LinkedBlockingQueue<>();
	private List<String> listUpdateBuffer = new ArrayList<>();
	private List<String> listInsertBuffer = new ArrayList<>();
	private final int BUFFERMAXSIZE = 30; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	LampRepository lampRepository;
	
	public boolean existMapLamps(String key){
		return map_lamps.containsKey(key);
	}
	
	/**
	 * 添加路灯信息
	 * @param key
	 * @param value
	 */
	public void addMapLamps(String key, String value){
		map_lamps.putIfAbsent(key, value);
		log.info("ADD 路灯, 总计 {} 盏", map_lamps.size());
	}
	
	/**
	 * 删除路灯信息
	 * @param value
	 */
	public void removeMapLamps(String value){
		for (Map.Entry<String, String> entry : map_lamps.entrySet()) {
			if(value.equals(entry.getValue())){
				map_lamps.remove(entry.getKey(), entry.getValue());
			}
		}
		log.info("DELETE 路灯, 总计 {} 盏", map_lamps.size());
	}
	
	@PostConstruct
	public void loadLampsFromDB(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				List<Lamp> list = lampRepository.QueryAll();
				list.forEach(lamp -> {
					if(!StringUtil.isNullOrEmpty(lamp.getEquipNumber())){
						map_lamps.putIfAbsent(lamp.getEquipNumber(), lamp.getId());
					}
				});
				
				log.info("路灯加载完毕, 总计 {} 盏", map_lamps.size());
				
			}
		}).start();
		
	}
	
	@PostConstruct
	public void batchUpdate(){
		
		new Thread(new Runnable() {
					
			@Override
			public void run() {
				while(true){
					try {
						listUpdateBuffer.add(queue_updateSQL.take());
						
						if(listUpdateBuffer.size() > BUFFERMAXSIZE){
							String[] sqls = new String[listUpdateBuffer.size()];
							listUpdateBuffer.toArray(sqls);
							jdbcTemplate.batchUpdate(sqls);
							listUpdateBuffer.clear();
							log.info("execute UPDATE SQL");
						}else{
							log.info("Update Buffer List 剩余长度 = {}",BUFFERMAXSIZE - listUpdateBuffer.size());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	
	
	@PostConstruct
	public void batchInsert(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						listInsertBuffer.add(queue_insertSQL.take());
						
						if(listInsertBuffer.size() > BUFFERMAXSIZE){
							String[] sqls = new String[listInsertBuffer.size()];
							listInsertBuffer.toArray(sqls);
							jdbcTemplate.batchUpdate(sqls);
							listInsertBuffer.clear();
							log.info("execute INSERT SQL");
						}else{
							log.info("Insert Buffer List 剩余长度 = {}",BUFFERMAXSIZE - listInsertBuffer.size());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}
