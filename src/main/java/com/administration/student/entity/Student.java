package com.administration.student.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String email;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "address_stud", joinColumns = @JoinColumn(name = "stud_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
  List<Address> address = new ArrayList<>();

//  @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(name = "stud_class_mapping", joinColumns = @JoinColumn(name = "stud_id"), inverseJoinColumns = @JoinColumn(name = "stud_class_id"))
//  List<StudClass> classes = new ArrayList<>();

  @ManyToOne
  private StudClass studclass;

  public Student() {
  }

  public Student(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Address> getAddress() {
    return address;
  }

  public void setAddress(List<Address> address) {
    this.address = address;
  }

  public StudClass getStudclass() {
    return studclass;
  }

  public void setStudclass(StudClass studclass) {
    this.studclass = studclass;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", email=" + email + "]";
  }

}
