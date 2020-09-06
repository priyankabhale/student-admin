package com.administration.student.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.administration.student.repository.StudentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private StudentRepository studentRepo;

  @Test
  public void testSaveStudent() {
    Student student = getStudent();
    Student savedInDb = entityManager.persist(student);
    Student getFromDb = studentRepo.findById(savedInDb.getId()).get();

    assertThat(getFromDb).isEqualTo(savedInDb);
  }

  @Test
  public void testGetStudentById() {
    studentRepo.deleteAll();
    Student student = new Student();
    student.setName("testStudent");
    student.setEmail("testStudent@gmail.com");

    List<Address> addressList = new ArrayList<>();
    addressList.add(new Address("TestAddress 1"));
    addressList.add(new Address("TestAddress 2"));
    student.setAddress(addressList);

    student.setStudclass(new StudClass(3));
    // Save student in DB
    Student studentSavedInDb = entityManager.persist(student);

    // Get Student from DB
    Student studentFromInDb = studentRepo.findById(studentSavedInDb.getId()).get();
    assertThat(studentSavedInDb).isEqualTo(studentFromInDb);
  }

  @Test
  public void testGetAllBookedStudents() {
    studentRepo.deleteAll();

    StudClass class4 = new StudClass(4);
    entityManager.persistAndFlush(class4);

    List<Address> addressList = new ArrayList<>();
    addressList.add(new Address("TestAddress 1"));
    addressList.add(new Address("TestAddress 2"));
    for (Address add : addressList) {
      entityManager.persistAndFlush(add);
    }

    List<Address> addressList2 = new ArrayList<>();
    addressList2.add(new Address("TestAddress 3"));
    addressList2.add(new Address("TestAddress 4"));
    for (Address add : addressList2) {
      entityManager.persistAndFlush(add);
    }

    Student student1 = new Student();
    student1.setName("testStudent");
    student1.setEmail("testStudent@gmail.com");
    student1.setAddress(addressList);
    student1.setStudclass(class4);

    Student student2 = new Student();
    student2.setName("testStudent2");
    student2.setEmail("testStudent2@gmail.com");
    student2.setAddress(addressList2);
    student2.setStudclass(class4);

    // Save both students in DB
    entityManager.persistAndFlush(student1);
    entityManager.persistAndFlush(student2);

    Iterable<Student> allstudentsFromDb = studentRepo.findAll();
    List<Student> studentList = new ArrayList<>();

    for (Student student : allstudentsFromDb) {
      studentList.add(student);
    }
    assertThat(studentList.size()).isEqualTo(2);
  }

  @Test
  public void testUpdateStudent() {
    studentRepo.deleteAll();

    StudClass class4 = new StudClass(4);
    entityManager.persistAndFlush(class4);

    List<Address> addressList = new ArrayList<>();
    addressList.add(new Address("TestAddress 1"));
    addressList.add(new Address("TestAddress 2"));
    for (Address add : addressList) {
      entityManager.persistAndFlush(add);
    }

    Student student1 = new Student();
    student1.setName("testStudent");
    student1.setEmail("testStudent@gmail.com");
    student1.setAddress(addressList);
    student1.setStudclass(class4);

    // Save both students in DB
    entityManager.persistAndFlush(student1);

    Student getFromDb = studentRepo.findByEmail("testStudent@gmail.com");
    // update Email Address
    getFromDb.setEmail("testStudentupdated@gmail.com");
    entityManager.persist(getFromDb);

    assertThat(getFromDb.getEmail()).isEqualTo("testStudentupdated@gmail.com");
  }

  private Student getStudent() {
    Student student = new Student();
    student.setName("testStudent");
    student.setEmail("testStudent@gmail.com");

    List<Address> addressList = new ArrayList<>();
    addressList.add(new Address("TestAddress 1"));
    addressList.add(new Address("TestAddress 2"));
    student.setAddress(addressList);

    student.setStudclass(new StudClass(3));
    return student;
  }

}
