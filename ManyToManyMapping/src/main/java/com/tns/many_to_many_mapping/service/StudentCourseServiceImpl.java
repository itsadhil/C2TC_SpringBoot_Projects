package com.tns.many_to_many_mapping.service;

import org.springframework.stereotype.Service;
import com.tns.many_to_many_mapping.entity.Course;
import com.tns.many_to_many_mapping.entity.Student;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Override
    public void addStudentToCourse(Student student, Course course) {
        // Add the student to the course
        course.getStudents().add(student);
        student.getCourses().add(course);
    }
}
