package com.example.university.Controller;

import com.example.university.ApiResponse.ApiResponse;
import com.example.university.Model.Student;
import com.example.university.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/university-student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("student added"));
    }

    @GetMapping("/get")
    public ResponseEntity getStudent() {
        return ResponseEntity.status(200).body(studentService.getStudent());
    }

    @PutMapping("/update/{ID}")
    public ResponseEntity updateStudent(@PathVariable String ID, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isDeleted = studentService.updateStudent(ID, student);
        if (isDeleted) {return ResponseEntity.status(200).body(new ApiResponse("student updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("student not updated,id not found."));
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity deleteStudent(@PathVariable String ID) {
        boolean isDeleted = studentService.deleteStudent(ID);
        if (isDeleted) {return ResponseEntity.status(200).body(new ApiResponse("student deleted"));
        }else return ResponseEntity.status(400).body(new ApiResponse("student not deleted, id not found."));
    }

    @PutMapping("/add-course-hour/{id}/{hours}")
    public ResponseEntity addCourseHour(@PathVariable String id,@PathVariable int hours){
        boolean isAdded = studentService.addCourseHour(id, hours);
        if (isAdded) {return ResponseEntity.status(200).body(new ApiResponse("Course Hours added"));
        }else return ResponseEntity.status(400).body(new ApiResponse("Course Hours not added , id not found or reached max hours."));
    }

    @PutMapping("/delete-course-hour/{id}/{hours}")
    public ResponseEntity deleteCourseHour(@PathVariable String id,@PathVariable int hours){
        boolean isDeleted = studentService.deleteCourseHour(id, hours);
        if (isDeleted) {return ResponseEntity.status(200).body(new ApiResponse("Course Hours deleted"));
        }else return ResponseEntity.status(400).body(new ApiResponse("Course Hours not deleted, id not found or reached min hours."));
    }

    @GetMapping("/get-range-students-by-hours/{min}/{max}")
    public ResponseEntity getRangeStudentsByHours(@PathVariable int min, @PathVariable int max) {
        ArrayList<Student> students = studentService.getRangeStudentsbyHours(min, max);
        if (students == null) {return ResponseEntity.status(400).body(new ApiResponse("Student range couldn't found"));
        }else return ResponseEntity.status(200).body(students);
    }

}
