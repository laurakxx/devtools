package ru.mentee.power.devtools.student;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

  private List<Student> studentList;

  public StudentList(List<Student> students) {
    this.studentList = students;
  }

  public void addStudent(Student student) {
    if (student != null) {
      studentList.add(student);
    }


  }

  public List<Student> getStudentList() {
    return new ArrayList<>(studentList);
  }
}
