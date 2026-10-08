package com.spring.CrudSpringBoot.service;


import com.spring.CrudSpringBoot.entity.Student;
import com.spring.CrudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    // 1 - End point listen (/api/student  POST)
    // 2 - Business logic
    // 3 - Interest with DB to store
    //4 - Response back to client (Postman)

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        studentReq.setDeleted(false);
               Student studentResp =  studentRepository.save(studentReq);
               return studentResp;
    }

    public Student getStudent(Long id){
       Optional<Student> studentResp =  studentRepository.findByIdAndDeletedIsFalse(id);
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudent(){
      List<Student> studentList =  studentRepository.findByDeletedIsFalse();
      return studentList;
    }

    public Student updateStudent(Long id, Student studentReq){
      Optional<Student> updateResp = studentRepository.findByIdAndDeletedIsFalse(id);
      if(updateResp.isEmpty()){
          return null;
      }
      Student studenToSave =  updateResp.get();

      studenToSave.setName(studentReq.getName());
      studenToSave.setAge(studentReq.getAge());
      studenToSave.setRollNo(studentReq.getRollNo());
      studenToSave.setSubject(studentReq.getSubject());
      studenToSave.setEmail(studentReq.getEmail());
      studenToSave.setDeleted(false);

      return studentRepository.save(studenToSave);
    }

public Boolean deleteStudent(Long id){
  Boolean isStudent =  studentRepository.existsById(id);

  if(!isStudent){
      return false;
  }
  studentRepository.deleteById(id);
  return true;
}

//Soft Delete
public Boolean deleteStudentSoft(Long id){
      Optional<Student>  existingStudent =  studentRepository.findByIdAndDeletedIsFalse(id);
      if(existingStudent.isEmpty()){
          return false;
      }

      Student studentToSave =  existingStudent.get();

      studentToSave.setDeleted(true);
      studentRepository.save(studentToSave);
      return true;
}
}
