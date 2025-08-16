//This program to define Course Repository Interface for Courses entities
package com.tns.course.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tns.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	//Automated Custom Queries
	//Retrieve a course by its title
	List<Course> findByTitle(String title);
    
    // Retrieve a course by its description
	List<Course> findByDescription(String description);
    
    // Retrieve a course by its start date
	List<Course> findByStartDate(Date startDate);
    
    // Retrieve a course by its end date
	List<Course> findByEndDate(Date endDate);
	
	// Retrieve courses taught by a specific instructor (case-insensitive)
    List<Course> findByInstructorIgnoreCase(String instructor);
    
    // Retrieve courses by title containing a keyword
    List<Course> findByTitleContaining(String keyword);
    
    // Retrieve all courses with pagination
    Page<Course> findAll(Pageable pageable);

    // Custom query to find courses sorted by start date
    List<Course> findAllByOrderByStartDateAsc();
    
    // Find courses by instructor and title containing a keyword
    List<Course> findByInstructorAndTitleContaining(String instructor, String keyword);

    // Find courses with pagination and sorting by title
    Page<Course> findByTitleContaining(String keyword, Pageable pageable);

    // Count courses by instructor
    long countByInstructor(String instructor);
	
	//Manual Custom Queries
    
    // Retrieve courses with an ID less than the provided ID
    @Query("SELECT c FROM Course c WHERE c.id < :id")
    List<Course> retrieveByIdLessThan(@Param("id") long id);
     
    // Retrieve courses with start date between the provided start and end dates
    @Query("SELECT c FROM Course c WHERE c.startDate BETWEEN :startDateStartOfDay AND :endDateEndOfDay")
    List<Course> retrieveByStartDateBetween(
            @Param("startDateStartOfDay") Date startDateStartOfDay,
            @Param("endDateEndOfDay") Date endDateEndOfDay);

    //Retrieve top N courses by end date 
    @Query("SELECT c FROM Course c ORDER BY c.endDate DESC")
    List<Course> findTopNByOrderByEndDateDesc(Pageable pageable);
    
}
