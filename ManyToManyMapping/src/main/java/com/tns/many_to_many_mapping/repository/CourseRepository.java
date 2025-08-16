package com.tns.many_to_many_mapping.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tns.many_to_many_mapping.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
