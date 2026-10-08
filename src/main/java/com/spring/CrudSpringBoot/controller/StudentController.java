package com.spring.CrudSpringBoot.controller;

import com.spring.CrudSpringBoot.entity.Student;
import com.spring.CrudSpringBoot.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

@PostMapping
public ResponseEntity<Student> createStudent(@RequestBody Student student){
        student.setDeleted(false);
      Student createdStudent = studentService.createStudent(student);
      return ResponseEntity.ok().body(createdStudent);
}

@GetMapping()
public ResponseEntity<Student>  getStudent(@RequestParam Long id){
       Student studentResp =  studentService.getStudent(id);

       if(studentResp == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok().body(studentResp);
}

@GetMapping("/getAll")
public ResponseEntity<List<Student>> getAllStudents(){
     List<Student> studentResp =  studentService.getAllStudent();

     if(studentResp == null){
         return ResponseEntity.notFound().build();
     }
     return ResponseEntity.ok(studentResp);
}

@PutMapping()
public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student student){
       Student updateResp = studentService.updateStudent(id, student);

       if(updateResp == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok().body(updateResp);
}

@DeleteMapping()
 public ResponseEntity<String> deleteStudent(@RequestParam Long id){
      Boolean isDeleted =  studentService.deleteStudent(id);

      if(!isDeleted){
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok().body("Deleted Successfully");
}

// Soft Delete
@PatchMapping()
    public  ResponseEntity<String>  deleteStudentSoft(@RequestParam Long id){
        Boolean isDeleted = studentService.deleteStudentSoft(id);

        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Deleted Successfully");
}
}
