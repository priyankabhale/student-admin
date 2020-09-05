package com.administration.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.administration.student.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("select s from Student s where s.studclass.classNumber=?1")
  Optional<List<Student>> findByClass(Integer class_id);

}
