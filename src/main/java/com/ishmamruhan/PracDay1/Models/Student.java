package com.ishmamruhan.PracDay1.Models;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ishmamruhan.PracDay1.Helpers.DateGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonFilter("StudentFilter")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 4, message = "Name Length Must be At least 4")
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    @JsonIgnoreProperties({"currentAdviseeStudents","courses"})
    private Teacher advisor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course",
            joinColumns = {
                    @JoinColumn(name = "student_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id")
            }
    )
    @JsonIgnoreProperties(value = {"studentList","teacherList"})
    private List<Course> courses = new ArrayList<>();

    private boolean isActive = false;

    private String created_at = new DateGenerator().getDate();
    private String updated_at;

    public Student() {
    }

    public Student(String name, String email, Teacher advisor, List<Course> courses, boolean isActive, String created_at, String updated_at) {
        this.name = name;
        this.email = email;
        this.advisor = advisor;
        this.courses = courses;
        this.isActive = isActive;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
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

    public Teacher getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Teacher advisor) {
        this.advisor = advisor;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
