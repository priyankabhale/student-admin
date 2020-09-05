package com.administration.student.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administration.student.entity.Student;
import com.administration.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

  private static final Logger logger = LogManager.getLogger(StudentController.class);

  @Autowired
  StudentService studentService;

  @GetMapping("/id/{id}")
  public Student getStudentById(@PathVariable("id") long id) {
    logger.info("Searching student information for id: " + id);
    return studentService.getStudentById(id);
  }

  @DeleteMapping("/id/{id}")
  public void deleteStudentInformation(@PathVariable("id") long id) {
    logger.info("Deleting student information for id: " + id);
    Student studentObj = studentService.getStudentById(id);
    studentService.deleteStudent(studentObj, id);
  }

  @PostMapping
  public Student addStudent(@RequestBody Student student) {
    logger.info("Adding new student information for email: " + student.getEmail());
    return studentService.saveStudent(student);
  }

  @PutMapping
  public Student updateStudent(@RequestBody Student student) {
    logger.info("Upating student information for id: " + student.getId());
    Student studentObj = studentService.getStudentById(student.getId());
    if (studentObj != null) {
      return studentService.saveStudent(studentObj);
    } else {
      logger.info("No record found for updating for student: " + student.getId());
      return null;
    }
  }

  @GetMapping("/all/{classNumber}")
  public List<Student> getStudentInformation(@PathVariable("classNumber") Integer classNumber) {
    logger.info("Retrieving Student information for class : " + classNumber);
    return studentService.getStudentByClass(classNumber);
  }

  @GetMapping("/all")
  public List<Student> getAllStudentInformation() {
    logger.info("Retrieving Student information");
    return studentService.getAllStudents();
  }
}
