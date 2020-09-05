package com.administration.student.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administration.student.entity.Student;
import com.administration.student.repository.StudentRepository;

@Service
public class StudentService {
  private static final Logger logger = LogManager.getLogger(StudentService.class);

  @Autowired
  StudentRepository studentrepo;

  public Student getStudentById(long id) {
    Student student = studentrepo.findById(id).orElse(null);
    logger.info("Found " + student + " for id: " + id);
    return student;
  }

  public void deleteStudent(Student student, long id) {
    if (student != null) {
      studentrepo.delete(student);
      logger.info("Deleting successfull for id: " + id);
    } else
      logger.info("No record found while deleting data : " + id);
  }

  public Student saveStudent(Student student) {
    return studentrepo.save(student);
  }

  public List<Student> getStudentByClass(Integer classNumber) {
    return studentrepo.findByClass(classNumber).orElse(null);
  }

  public List<Student> getAllStudents() {
    return studentrepo.findAll();
  }

}
