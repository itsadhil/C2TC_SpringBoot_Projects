package com.tns.courseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    // Automatically injects the CourseRepository dependency
    @Autowired
    private CourseRepository courseRepository;

    // Retrieves all courses from the database
    public List<Course> getAllCourses() 
    {
        return courseRepository.findAll();
    }

    // Retrieves a specific course by its ID, returns null if not found
    public Course getCourseById(Long id) 
    {
    	return courseRepository.findById(id).orElse(null);
    }

    // Saves a new course or updates an existing one in the database
    public Course saveCourse(Course course) 
    {
        return courseRepository.save(course);
    }

    // Updates an existing course by ID; returns the updated course or null if not found
    public Course updateCourse(Long id, Course course) 
    {
        // Fetches the existing course by ID
        Course existingCourse = courseRepository.findById(id).orElse(null);
        
        // If the course exists, update its fields and save it
        if (existingCourse != null) {
            existingCourse.setName(course.getName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setInstructor(course.getInstructor());
            existingCourse.setPrice(course.getPrice());
            return courseRepository.save(existingCourse);
        }
        
        // Returns null if the course doesn't exist
        return null;
    }

    // Deletes a course by its ID
    public void deleteCourse(Long id) 
    {
        courseRepository.deleteById(id);
    }
}
