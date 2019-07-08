package com.miicrown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.miicrown.entity.BaseEntity;

@Repository
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {

}
