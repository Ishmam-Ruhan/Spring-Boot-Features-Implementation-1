package com.ishmamruhan.PracDay1.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ishmamruhan.PracDay1.Helpers.DateGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 4, message = "Name Length Must be At least 4")
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    private Boolean isAdvisor;

    private Boolean isActive;

    @OneToMany(mappedBy = "advisor")
    @JsonIgnoreProperties({"courses","advisor"})
    private List<Student> currentAdviseeStudents = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "teacher_course",
            joinColumns = {
                    @JoinColumn(name = "teacher_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id")
            }
    )
    @JsonIgnoreProperties({"studentList","teacherList"})
    private List<Course> courses = new ArrayList<>();

    private String created_at = new DateGenerator().getDate();
    private String updated_at;

    public Teacher() {
    }

    public Teacher(String name, String email, Boolean isAdvisor, Boolean isActive, List<Student> currentAdviseeStudents, List<Course> courses, String created_at, String updated_at) {
        this.name = name;
        this.email = email;
        this.isAdvisor = isAdvisor;
        this.isActive = isActive;
        this.currentAdviseeStudents = currentAdviseeStudents;
        this.courses = courses;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Boolean getAdvisor() {
        return isAdvisor;
    }

    public void setAdvisor(Boolean advisor) {
        isAdvisor = advisor;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Student> getCurrentAdviseeStudents() {
        return currentAdviseeStudents;
    }

    public void setCurrentAdviseeStudents(List<Student> currentAdviseeStudents) {
        this.currentAdviseeStudents = currentAdviseeStudents;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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
