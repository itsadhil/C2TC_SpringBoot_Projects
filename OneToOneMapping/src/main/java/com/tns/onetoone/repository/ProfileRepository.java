package com.tns.onetoone.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tns.onetoone.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
