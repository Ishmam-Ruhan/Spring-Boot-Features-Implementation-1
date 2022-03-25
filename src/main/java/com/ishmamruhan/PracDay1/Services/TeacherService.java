package com.ishmamruhan.PracDay1.Services;

import com.ishmamruhan.PracDay1.DTOs.TeacherRepo;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.CustomizeException;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.ResourceNotFoundException;
import com.ishmamruhan.PracDay1.Helpers.DateGenerator;
import com.ishmamruhan.PracDay1.Models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    public String addTeacher(Teacher teacher) throws CustomizeException {
        try{
            teacher.setActive(true);
            teacherRepo.save(teacher).getId();
        }catch (Exception ex){
            throw new CustomizeException(409,"Conflict",ex.getMessage());
        }

        return "Teacher added successfully named: "+teacher.getName();
    }

    public String addTeacherAll(List<Teacher> teachers) throws CustomizeException {
        try{
            teacherRepo.saveAll(teachers).forEach(teacher -> {
                teacher.setActive(true);
            });
        }catch (Exception ex){
            throw new CustomizeException(409,"Conflict",ex.getMessage());
        }

        return "All Teachers added successfully";
    }

    public Teacher updateTeacher(Teacher teacher) throws ResourceNotFoundException, CustomizeException {
        Teacher teacher1 = null;

        try{
            teacher1 = Optional.ofNullable(teacherRepo.findByid(teacher.getId())).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }

        if(!teacher1.getActive()) throw new CustomizeException(400,"Bad Request","Teacher property is not Active");

        teacher1.setName(teacher.getName() == null ? teacher1.getName():teacher.getName());
        teacher1.setEmail(teacher.getEmail() == null ? teacher1.getEmail():teacher.getEmail());
        teacher1.setCourses(teacher.getCourses());
        teacher1.setUpdated_at(new DateGenerator().getDate());


        try{
            teacherRepo.save(teacher1);
        }catch (Exception ex){
            throw new CustomizeException(409,"Conflict",ex.getMessage());
        }

        return teacher1;
    }

    public String deleteTeacher(Teacher teacher) throws CustomizeException,ResourceNotFoundException{
        Teacher teacher1 = null;

        try{
            teacher1 = Optional.ofNullable(teacherRepo.findByid(teacher.getId())).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
        teacher1.setActive(false);
        teacher1.setAdvisor(false);
        teacher1.setCurrentAdviseeStudents(new ArrayList<>());

        try{
            teacherRepo.save(teacher1);
        }catch (Exception ex){
            throw new CustomizeException(409,"Conflict",ex.getMessage());
        }

        return "Successfully Deleted The Teacher!";
    }

    public List<Teacher> getAllTeacher(){
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(long id) throws ResourceNotFoundException{
        Teacher teacher1 = null;

        try{
            teacher1 = Optional.ofNullable(teacherRepo.findByid(id)).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }

        return teacher1;
    }

    public Teacher reactivateTeacher(Teacher teacher) throws ResourceNotFoundException,CustomizeException{
        Teacher teacher1 = null;
        try{
            teacher1 = Optional.ofNullable(teacherRepo.findByid(teacher.getId())).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
        teacher1.setActive(true);

        try{
            teacherRepo.save(teacher1);
        }catch (Exception ex){
            throw new CustomizeException(409,"Conflict",ex.getMessage());
        }

        return teacher1;
    }

}
