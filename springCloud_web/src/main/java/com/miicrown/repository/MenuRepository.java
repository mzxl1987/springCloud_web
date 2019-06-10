package com.miicrown.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miicrown.entity.Menu;

@Repository
public interface MenuRepository extends BaseRepository<Menu> {
	
	@Modifying
	@Query(value="from Menu t order by t.id asc")
	public List<Menu> QueryAll();
	
	
}
