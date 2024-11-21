package com.example.university.Controller;

import com.example.university.ApiResponse.ApiResponse;
import com.example.university.Model.Teacher;
import com.example.university.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

private final TeacherService teacherService;

@GetMapping("/get")
    public ResponseEntity getTeacher(){
    return ResponseEntity.status(200).body(teacherService.getTeachers());
}

@PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
    if(errors.hasErrors()){
        return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
    }
    teacherService.addTeacher(teacher);
    return ResponseEntity.status(201).body(new ApiResponse("Teacher added"));
}
@PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @RequestBody @Valid Teacher teacher, Errors errors){
    if(errors.hasErrors()){
        return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
    }
    boolean isUpdated = teacherService.updateTeacher(id, teacher);
    if(isUpdated){return ResponseEntity.status(201).body(new ApiResponse("Teacher updated"));
    }else {return ResponseEntity.status(400).body(new ApiResponse("Teacher not updated"));}
}
@DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id){

    boolean isDeleted = teacherService.deleteTeacher(id);

    if(isDeleted){return ResponseEntity.status(201).body(new ApiResponse("Teacher deleted"));
    }else {return ResponseEntity.status(400).body(new ApiResponse("Teacher not deleted"));}
}
@PutMapping("/take-day-off/{id}/{days}")
public ResponseEntity takeDayOff(@PathVariable String id,@PathVariable int days){
    boolean isTaken = teacherService.takeDayOff(id, days);

        if (isTaken){return ResponseEntity.status(201).body(new ApiResponse("Teacher taken day off , and now not available"));
        }else {return ResponseEntity.status(400).body(new ApiResponse("Teacher not taken, id not found or not enough days off remaining"));}
    }

    @GetMapping("/get-available-teacher")
    public ResponseEntity getAvailableTeacher(){
        ArrayList<Teacher> teachers = teacherService.getAvailableTeacher();
        if(teachers == null){return ResponseEntity.status(400).body(new ApiResponse("There isn't any Teachers available"));
        }else {return ResponseEntity.status(200).body(teachers);}
    }

    @GetMapping("/get-range-by-teacher-age/{min}/{max}")
    public ResponseEntity getRangeTeachersbyAge(@PathVariable int min,@PathVariable int max){

    ArrayList<Teacher> teachers = teacherService.getRangeTeachersbyAge(min, max);
    if (teachers == null){return ResponseEntity.status(400).body(new ApiResponse("There isn't any Teachers with the range age input"));
    }else {return ResponseEntity.status(200).body(teachers);}
    }
}
