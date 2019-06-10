package com.miicrown.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miicrown.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User> {
	
	@Query(value="from User u where 1 = 1 and u.username like %:username%")
	public List<User> findAllByName(@Param("username") String username);
	
	@Query(value="from User u where 1 = 1 and u.username = :#{#v.username} and u.password = :#{#v.password}")
	public List<User> Query(@Param("v") User u);
	
}
