package com.ishmamruhan.PracDay1.Models;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ishmamruhan.PracDay1.Helpers.DateGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonFilter("CourseFilter")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String courseName;

    @NotNull
    private String courseCode;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties({"courses","advisor"})
    private List<Student> studentList = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties({"courses","currentAdviseeStudents"})
    private List<Teacher> teacherList = new ArrayList<>();

    private String added_at = new DateGenerator().getDate();

    private String updated_at;

    public Course() {
    }

    public Course(long id, String courseName, String courseCode, List<Student> studentList, List<Teacher> teacherList, String added_at, String updated_at) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.studentList = studentList;
        this.teacherList = teacherList;
        this.added_at = added_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public String getAdded_at() {
        return added_at;
    }

    public void setAdded_at(String added_at) {
        this.added_at = added_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
