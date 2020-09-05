package com.administration.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.administration.student.entity.Address;
import com.administration.student.entity.StudClass;
import com.administration.student.entity.Student;
import com.administration.student.repository.AddressRepository;
import com.administration.student.repository.StudClassRepository;
import com.administration.student.repository.StudentRepository;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {

  private static final Logger logger = LogManager.getLogger(StudentApplication.class);

  @Autowired
  StudentRepository studentrepo;

  @Autowired
  StudClassRepository classrepo;

  @Autowired
  AddressRepository addressrepo;

  public static void main(String[] args) {
    logger.info("Starting application");
    SpringApplication.run(StudentApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Student stud1 = new Student("Tom", "tom@gmail.com");
    Student stud2 = new Student("Harry", "harry@gmail.com");
    Student stud3 = new Student("Ron", "ron@gmail.com");
    Student stud4 = new Student("Giny", "ginny@gmail.com");

    Address address_stud1 = new Address("Germany");
    Address address_stud2 = new Address("UK");
    Address address_stud3 = new Address("India");
    Address address_stud4 = new Address("London");

    StudClass class8 = new StudClass(8);
    StudClass class9 = new StudClass(9);
    StudClass class10 = new StudClass(10);

    stud1.getAddress().add(address_stud1);
    stud1.getAddress().add(address_stud2);
    stud2.getAddress().add(address_stud2);
    stud3.getAddress().add(address_stud3);
    stud4.getAddress().add(address_stud4);

    stud1.setStudclass(class8);
    stud2.setStudclass(class8);
    stud3.setStudclass(class9);
    stud4.setStudclass(class10);

    classrepo.save(class8);
    classrepo.save(class9);
    classrepo.save(class10);

    addressrepo.save(address_stud1);
    addressrepo.save(address_stud2);
    addressrepo.save(address_stud3);
    addressrepo.save(address_stud4);

    studentrepo.save(stud1);
    studentrepo.save(stud2);
    studentrepo.save(stud3);
    studentrepo.save(stud4);

  }

}
