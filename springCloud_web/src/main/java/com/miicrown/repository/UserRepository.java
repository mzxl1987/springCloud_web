package com.miicrown.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miicrown.entity.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
