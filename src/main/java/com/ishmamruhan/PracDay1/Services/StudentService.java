package com.ishmamruhan.PracDay1.Services;

import com.ishmamruhan.PracDay1.DTOs.StudentRepo;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.CustomizeException;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.ResourceNotFoundException;
import com.ishmamruhan.PracDay1.Helpers.DateGenerator;
import com.ishmamruhan.PracDay1.Helpers.DynamicPropertyFiltering;
import com.ishmamruhan.PracDay1.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public String addStudent(Student student){
        student.setActive(true);
        studentRepo.save(student);
        return "Student Added with Name: "+student.getName();
    }

    public String addStudentAll(List<Student> students){
        students.forEach(student -> {
            student.setActive(true);
        });
        studentRepo.saveAll(students);
        return "All Students Added";
    }

    public Student updateStudent(Student student) throws ResourceNotFoundException, CustomizeException {
        Student student1 = null;

        try {
            student1= Optional.ofNullable(studentRepo.findByid(student.getId())).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }

        if(!student1.getActive())throw new CustomizeException(400,"Bad Request","Student property is not active.");

        student1.setName(student.getName() == null ? student1.getName():student.getName());
        student1.setEmail(student.getEmail() == null ? student1.getEmail():student.getEmail());
        student1.setAdvisor(student.getAdvisor() == null ? student1.getAdvisor():student.getAdvisor());
        student1.setCourses(student.getCourses());
        student1.setUpdated_at(new DateGenerator().getDate());

        try{
            studentRepo.save(student1).getId();
        }catch (Exception ex){
            throw new CustomizeException(409,"Conflict",ex.getMessage());
        }

        return student1;
    }

    public String deleteStudent(Student student) throws ResourceNotFoundException{
        try{
            Student student1=Optional.ofNullable(studentRepo.findByid(student.getId())).orElseThrow();
            student1.setActive(false);
            studentRepo.save(student1);
        }catch (Exception ex){
            throw new ResourceNotFoundException("No student found!");
        }

        return "Successfully Deleted Student with Name: "+ student.getName();
    }

    public MappingJacksonValue getAllStudents(){
        List<Student> students=studentRepo.findAll();

        DynamicPropertyFiltering filtering = new DynamicPropertyFiltering(students,"name");
        return filtering.applyFilter("StudentFilter");
    }

    public MappingJacksonValue getStudentById(Long id){
        Student student = null;

        try {
            student = Optional.ofNullable(studentRepo.findByid(id)).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException("No student found with ID: "+id);
        }
        DynamicPropertyFiltering filtering = new DynamicPropertyFiltering(student,"email");

        return filtering.applyFilter("StudentFilter");
    }

    public String reactivateStudent(Student student) throws CustomizeException, ResourceNotFoundException{
        Student student1=null;

        try{
            student1 = Optional.ofNullable(studentRepo.findByid(student.getId())).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException("No student found with ID: "+student.getId());
        }

        student1.setActive(true);
        student1.setUpdated_at(new DateGenerator().getDate());

        try{
            studentRepo.save(student1).getId();
        }catch (Exception ex){
            throw new CustomizeException(409,"Conflict",ex.getMessage());
        }

        return "Successfully Reactivated Student.";
    }

}
