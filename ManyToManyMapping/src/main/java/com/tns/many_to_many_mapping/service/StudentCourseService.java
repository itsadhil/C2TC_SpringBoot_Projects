package com.tns.many_to_many_mapping.service;

import com.tns.many_to_many_mapping.entity.Course;
import com.tns.many_to_many_mapping.entity.Student;

public interface StudentCourseService {
    void addStudentToCourse(Student student, Course course);
}
