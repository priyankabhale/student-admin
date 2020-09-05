package com.administration.student.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class StudClass {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Integer classNumber;

//  @JsonIgnore
//  @ManyToMany(mappedBy = "classes", fetch = FetchType.LAZY)
//  private List<Student> students = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy="studclass")
  private List<Student> students;
  
  public StudClass() {
  }

  public StudClass(Integer classNumber) {
    super();
    this.classNumber = classNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getClassNumber() {
    return classNumber;
  }

  public void setClassNumber(Integer classNumber) {
    this.classNumber = classNumber;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

}
