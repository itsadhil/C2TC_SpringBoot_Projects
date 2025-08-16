//This program to define Course REST controller for managing Course entities.
package com.tns.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tns.course.entity.Course;
import com.tns.course.exception.ServiceException;
import com.tns.course.service.CourseService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private final CourseService courseService;

	//Constructor injection for CourseService dependency.
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //End point to retrieve all courses.
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
        	// Internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //End point to retrieve a course by ID.
    @GetMapping("/courseid/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
       
    	try {
            Optional<Course> course = courseService.getCourseById(id);
            return course.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
        	// Internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // End point to retrieves a course by its title
    @GetMapping("/course/{title}")
    public ResponseEntity<List<Course>> getCourse(@PathVariable String title) {
        try {
        	List<Course> course = courseService.getCourse(title);
            if (course != null) {
                return ResponseEntity.ok().body(course);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ServiceException e) {
            // If an exception occurs, return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //End point to retrieves a course by its ID
    @GetMapping("/lessthanid/{id}")
    public ResponseEntity<List<Course>> getCoursesByIdLessThan(
            @PathVariable long id) {
        
        try {
            List<Course> courses = courseService.getCoursesByIdLessThan(id);
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	
	//End point to retrieves a course by instructor
    @GetMapping("/instructor/{instructor}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(
            @PathVariable String instructor) {
        
        try {
            List<Course> courses = courseService.getCoursesByInstructorIgnoreCase(instructor);
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //End point to retrieves a course by its description
    @GetMapping("/description/{description}")
    public ResponseEntity<List<Course>> getCourseByDescription(@PathVariable String description) {
        try {
        	List<Course> course = courseService.getCourseByDescription(description);
            return ResponseEntity.ok().body(course);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //End point to retrieves a course by its startDate
    @GetMapping("/startdate/{startDate}")
    public ResponseEntity<List<Course>> getCourseByStartDate(@PathVariable Date startDate) {
        try {
        	
        	List<Course> course = courseService.getCourseByStartDate(startDate);
            return ResponseEntity.ok().body(course);
            
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //End point to retrieves a course by its startDate with range
    @GetMapping("/startdate/between/")
    public ResponseEntity<List<Course>> getCoursesByStartDateBetween(
            @RequestParam("start") Date startDateStartOfDay,
            @RequestParam("end") Date startDateEndOfDay) {
        
        List<Course> courses = courseService.getCoursesByStartDateBetween(startDateStartOfDay, startDateEndOfDay);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    // End point to retrieves a course by its endDate
    @GetMapping("/enddate/{endDate}")
    public ResponseEntity<List<Course>> getCourseByEndDate(@PathVariable Date endDate) {
        try {
        	List<Course> course = courseService.getCourseByEndDate(endDate);
            return ResponseEntity.ok().body(course);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //End point to retrieves a course by its title containing a keyword
    @GetMapping("/searchByTitle")
    public ResponseEntity<List<Course>> findByTitleContaining(@RequestParam String keyword) {
        try {
            List<Course> courses = courseService.findByTitleContaining(keyword);
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //End point to retrieve all courses with pagination.
    @GetMapping("/all")
    public ResponseEntity<Page<Course>> findAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            PageRequest pageable = PageRequest.of(page, size);
            Page<Course> coursesPage = courseService.findAll(pageable);
            return ResponseEntity.ok(coursesPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

   //End point to retrieve all courses by limit in descending order.
    @GetMapping("/topEndDateCourses")
    public ResponseEntity<List<Course>> findTopNCoursesByEndDate(@RequestParam int n) {
        try {
            List<Course> topCourses = courseService.findTopNCoursesByEndDate(n);
            return ResponseEntity.ok(topCourses);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
  //End point to retrieve all courses by limit in ascending order.
    @GetMapping("/allOrderByStartDate")
    public ResponseEntity<List<Course>> findAllCoursesOrderByStartDateAsc() {
        try {
            List<Course> courses = courseService.findAllCoursesOrderByStartDateAsc();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // End point to find courses by instructor and title containing a keyword
    @GetMapping("/searchInstructorTitle")
    public ResponseEntity<List<Course>> findByInstructorAndTitleContaining(
            @RequestParam String instructor,
            @RequestParam String keyword) {
        try {
            List<Course> courses = courseService.findByInstructorAndTitleContaining(instructor, keyword);
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // End point to find courses with pagination and sorting by title
    @GetMapping("/paginatedWithKeyword")
    public ResponseEntity<Page<Course>> findByTitleContaining(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Course> coursesPage = courseService.findByTitleContaining(keyword, pageable);
            return ResponseEntity.ok(coursesPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // End point to count courses by instructor
    @GetMapping("/countByInstructor")
    public ResponseEntity<Long> countByInstructor(@RequestParam String instructor) {
        try {
            long count = courseService.countByInstructor(instructor);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //End point to create a new course.
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (Exception e) {
        	// Internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //End point to delete a course by ID.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
        	// Internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Endpoint to update a course by ID.
    @PutMapping("update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        try {
            Optional<Course> optionalUpdatedCourse = courseService.updateCourse(id, updatedCourse);
            return optionalUpdatedCourse.map(ResponseEntity::ok)
                                        .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
        	// Internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
