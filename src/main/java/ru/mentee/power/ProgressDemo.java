package ru.mentee.power;

import ru.mentee.power.devtools.student.Student;
import ru.mentee.power.devtools.student.StudentList;

import java.util.ArrayList;

public class ProgressDemo {

  public static void main(String[] args) {

    StudentList studentList = new StudentList(new ArrayList<>());

    Student student = new Student("Laura", "Saratov");

    studentList.addStudent(student);

    System.out.println(studentList.getStudentList());
  }
}