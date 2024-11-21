package com.example.university.Controller;

import com.example.university.ApiResponse.ApiResponse;
import com.example.university.Model.Course;
import com.example.university.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getCourse() {
        ArrayList<Course> courses = courseService.getCourses();
        return ResponseEntity.status(200).body(courses);
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        courseService.addCourse(course);
        return ResponseEntity.status(201).body("Course added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id,@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = courseService.updateCourse(id, course);
        if (isUpdated) {
        return ResponseEntity.status(201).body("Course updated");
        } else {return ResponseEntity.status(400).body("Course not updated, id not found");}
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id) {
        boolean idDeleted = courseService.removeCourse(id);
        if (idDeleted) { return ResponseEntity.status(200).body("Course deleted");
        }else {return ResponseEntity.status(400).body("Course not deleted, id not found");}
    }

    @PutMapping("/update-course-capacity/{id}/{capacity}")
    public ResponseEntity updateCourseCapacity(@PathVariable String id, @PathVariable int capacity) {
        boolean isUpdated = courseService.increaseCapacity(id, capacity);
        if (isUpdated) {return ResponseEntity.status(200).body("Capacity increased");
        }else {return ResponseEntity.status(400).body("Capacity not increased, id not found");}
    }

    @GetMapping("/get-range-of-capacity/{min}/{max}")
    public ResponseEntity getCourseRangeOfCapacity(@PathVariable int min, @PathVariable int max) {
        ArrayList<Course> courses = courseService.getRangeofCapacity(min, max);
        if (courses == null) {return ResponseEntity.status(400).body(new ApiResponse("not found the range capacity of input"));
        }else {return ResponseEntity.status(200).body(courses);}
    }

    @GetMapping("/get-courses-by-subject/{subject}")
    public ResponseEntity getCoursesBySubject(@PathVariable String subject) {
        ArrayList<Course> courses = courseService.getCoursesbySubject(subject);
        if (courses == null) {return ResponseEntity.status(400).body(new ApiResponse("not found the courses by subject"));
        }else {return ResponseEntity.status(200).body(courses);}
    }

}
