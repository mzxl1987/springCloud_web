package com.miicrown.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miicrown.entity.Lamp;

@Repository
public interface LampRepository extends BaseRepository<Lamp> {
	
	@Modifying
	@Query(value="from Lamp")
	public List<Lamp> QueryAll();
	
	@Modifying
	@Query(value="from Lamp t where t.id = :id")
	public Lamp QueryById(@Param("id") String id);
	
	@Modifying
	@Query(value="update Lamp t "
			+ "set t.equipName = :#{#v.equipName}, "
			+ "t.equipNumber = :#{#v.equipNumber}, "
			+ "t.equipType = :#{#v.equipType}, "
			+ "t.manufacturer = :#{#v.manufacturer}, "
			+ "t.lampCount = :#{#v.lampCount}, "
			+ "t.dimType = :#{#v.dimType}, "
			+ "t.wiringType = :#{#v.wiringType}, "
			+ "t.equipTagNumber = :#{#v.equipTagNumber}, "
			+ "t.simNumber = :#{#v.simNumber}, "
			+ "t.mobileNumber = :#{#v.mobileNumber}, "
			+ "t.lampType = :#{#v.lampType}, "
			+ "t.serviceLife = :#{#v.serviceLife}, "
			+ "t.produceDate = :#{#v.produceDate}, "
			+ "t.accessWay = :#{#v.accessWay}, "
			+ "t.remark = :#{#v.remark}, "
			+ "t.updateDate = :#{#v.updateDate} ,"
			+ "t.updateBy = :#{#v.updateBy} "
			+ "where t.id = :#{#v.id}")
	public void update(@Param("v") Lamp lamp);
	
	@Modifying
	@Query(value="delete from Lamp t where t.id in :ids")
	public void delete(@Param("ids") List<String> ids);
	
	@Query(value="select * from t_lamp t "
			+ "where 1=1 "
			+ "and if(:#{#v.equipName} != '', t.equip_name like %:#{#v.equipName}% , 1=1 ) "
			+ "and if(:#{#v.equipNumber} != '', t.equip_number like %:#{#v.equipNumber}% , 1=1 ) "
			+ "and if(:#{#v.equipType} != '', t.equip_type like %:#{#v.equipType}% , 1=1 ) "
			
			+ "and if(:#{#v.manufacturer} != '', t.manufacturer like %:#{#v.manufacturer}% , 1=1 ) "
			+ "and if(:#{#v.accessWay} != '', t.access_way like %:#{#v.accessWay}% , 1=1 ) "
			+ "order by t.create_date desc, t.update_date desc",nativeQuery=true)
	public Page<Lamp> QueryPage(@Param("v") Lamp lamp, Pageable pageable);
	
}
