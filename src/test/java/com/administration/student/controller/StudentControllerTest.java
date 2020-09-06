package com.administration.student.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.administration.student.entity.Address;
import com.administration.student.entity.StudClass;
import com.administration.student.entity.Student;
import com.administration.student.repository.AddressRepository;
import com.administration.student.repository.StudClassRepository;
import com.administration.student.repository.StudentRepository;
import com.administration.student.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {

  @Autowired
  private MockMvc mockmvc;

  @MockBean
  private StudentService studentService;

  @MockBean
  private StudentRepository studentRepository;

  @MockBean
  private StudClassRepository studClassRepository;

  @MockBean
  private AddressRepository addressRepository;

  @Test
  public void addStudentTest() throws Exception {
    Student student = new Student();
    student.setId(1l);
    student.setName("testStudent");
    student.setEmail("testStudent@gmail.com");

    List<Address> addressList = new ArrayList<>();
    addressList.add(new Address("TestAddress 1"));
    addressList.add(new Address("TestAddress 2"));
    student.setAddress(addressList);

    student.setStudclass(new StudClass(3));

    String inputInJson = this.mapToJson(student);

    String URI = "/student";

    Mockito.when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
        .content(inputInJson).contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockmvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertThat(outputInJson).isEqualTo(inputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void getStudentByIdTest() throws Exception {
    Student student = new Student();
    student.setId(1l);
    student.setName("testStudent");
    student.setEmail("testStudent@gmail.com");

    List<Address> addressList = new ArrayList<>();
    addressList.add(new Address("TestAddress 1"));
    addressList.add(new Address("TestAddress 2"));
    student.setAddress(addressList);

    student.setStudclass(new StudClass(3));

    Mockito.when(studentService.getStudentById(1)).thenReturn(student);

    String URI = "/student/id/1";
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockmvc.perform(requestBuilder).andReturn();
    String expectedJson = this.mapToJson(student);
    String outputInJson = result.getResponse().getContentAsString();

    assertThat(outputInJson).isEqualTo(expectedJson);
  }

  @Test
  public void getAllStudents() throws Exception {

    Student student = new Student();
    student.setId(1l);
    student.setName("testStudent");
    student.setEmail("testStudent@gmail.com");

    List<Address> addressList = new ArrayList<>();
    addressList.add(new Address("TestAddress 1"));
    addressList.add(new Address("TestAddress 2"));
    student.setAddress(addressList);

    student.setStudclass(new StudClass(3));

    Student student1 = new Student();
    student1.setId(2l);
    student1.setName("testStudent2");
    student1.setEmail("testStudent2@gmail.com");

    List<Address> addressList2 = new ArrayList<>();
    addressList2.add(new Address("TestAddress 3"));
    addressList2.add(new Address("TestAddress 4"));
    student1.setAddress(addressList2);

    student1.setStudclass(new StudClass(4));

    List<Student> studentlist = new ArrayList<>();
    studentlist.add(student);
    studentlist.add(student1);

    Mockito.when(studentService.getAllStudents()).thenReturn(studentlist);

    String URI = "/student/all";
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockmvc.perform(requestBuilder).andReturn();

    String expectedJson = this.mapToJson(studentlist);
    String outputInJson = result.getResponse().getContentAsString();
    assertThat(outputInJson).isEqualTo(expectedJson);
  }

  /**
   * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
   */
  private String mapToJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }

}
