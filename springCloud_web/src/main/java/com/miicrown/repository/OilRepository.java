package com.miicrown.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miicrown.entity.dto.OilDto;

@Repository
public interface OilRepository extends BaseRepository<OilDto> {
	
	/**
	 * 利用native SQL查询数据
	 * 返回Object[]
	 * @param obj
	 * @param pageable
	 * @return
	 */
	@Query(value = "select "
					+ "t.id, "
					+ "t.equip_number, "
					+ "t.raw_value, "
					+ "t.create_date, "
					+ "t.update_date, "
					+ "b.headtitle, "
					+ "b.locationstr, "
					+ "b.volume, "
					+ "b.height, "
					+ "b.lon, "
					+ "b.lat, "
					+ "b.emptyvalue, "
					+ "b.cheight, "
					+ "b.cvalue "
					+ "from t_oil t "
					+ "left join t_bucket b on t.equip_number = b.equip_number "
					+ "where 1=1 "
					+ "and if(:#{#v.equipNumber} != '', t.equip_number like %:#{#v.equipNumber}% , 1=1 ) "
					+ "order by t.create_date desc, t.update_date desc",
					
			countQuery = "select "
					+ "count(*) "
					+ "from t_oil t "
					+ "left join t_bucket b on t.equip_number = b.equip_number "
					+ "where 1=1 "
					+ "and if(:#{#v.equipNumber} != '', t.equip_number like %:#{#v.equipNumber}% , 1=1 ) "
					+ "order by t.create_date desc, t.update_date desc",
					
			nativeQuery=true)
	public Page<Object[]> QueryPage(@Param("v") OilDto obj, Pageable pageable);
	
}
