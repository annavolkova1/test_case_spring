package com.testTaskSpring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  List<Student> students = new ArrayList<Student>();
  {
    students.add(new Student("Sajal", "IV", "India"));
    students.add(new Student("Lokesh", "V", "India"));
    students.add(new Student("Kajal", "III", "USA"));
    students.add(new Student("Sukesh", "VI", "USA"));
  }

  @RequestMapping(value = "/getStudents")
  public List<Student> getStudents() {
    return students;
  }

  @RequestMapping(value = "/getStudent/{name}")
  public Student getStudent(@PathVariable(value = "name") String name) {
    return students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
  }

  @RequestMapping(value = "/getStudentByCountry/{country}")
  public List<Student> getStudentByCountry(@PathVariable(value = "country") String country) {
    System.out.println("Searching Student in country : " + country);
    List<Student> studentsByCountry = students.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
        .collect(Collectors.toList());
    System.out.println(studentsByCountry);
    return studentsByCountry;
  }

  @RequestMapping(value = "/getStudentByClass/{cls}")
  public List<Student> getStudentByClass(@PathVariable(value = "cls") String cls) {
    return students.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
  }

  private class Student {

    private String name;
    private String cls;
    private String country;

    public Student(String name, String cls, String country) {
      super();
      this.name = name;
      this.cls = cls;
      this.country = country;
    }

    public String getName() {
      return name;
    }

    public String getCls() {
      return cls;
    }

    public String getCountry() {
      return country;
    }

    @Override
    public String toString() {
      return "Student [name=" + name + ", cls=" + cls + ", country=" + country + "]";
    }
  }
}
