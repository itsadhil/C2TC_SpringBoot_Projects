package com.tns.onetoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tns.onetoone.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
