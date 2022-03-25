package com.ishmamruhan.PracDay1.Services;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ishmamruhan.PracDay1.DTOs.CourseRepo;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.ResourceNotFoundException;
import com.ishmamruhan.PracDay1.Helpers.DateGenerator;
import com.ishmamruhan.PracDay1.Helpers.DynamicPropertyFiltering;
import com.ishmamruhan.PracDay1.Models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public String addCourse(Course course){
        try{
            courseRepo.save(course);
        }catch (Exception ex){
            System.out.println("Error trigered add Course method: "+ex.getMessage());
        }
        return "Successfully Saved Course named "+course.getCourseName();
    }

    public String addCourses(List<Course> course){
        try{
            courseRepo.saveAll(course);
        }catch (Exception ex){
            System.out.println("Error trigered add Courses method: "+ex);
        }
        return "Successfully Saved All Courses";
    }

    public MappingJacksonValue getAllCourse(){
        List<Course> courses = null;
        try {
             courses = Optional.ofNullable(courseRepo.findAll()).orElseThrow();
        }catch (Exception ex){
            System.out.println("Error trigered getAllCourse method: "+ex);
        }

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("courseName");
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("CourseFilter",simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(courses);
        mappingJacksonValue.setFilters(filterProvider);


        DynamicPropertyFiltering filtering = new DynamicPropertyFiltering(courses, "courseCode");



        return filtering.applyFilter("CourseFilter");
    }

    public Course getCourseByID(Long id) throws ResourceNotFoundException {
        Course course = null;
        try {
            course = Optional.ofNullable(courseRepo.findByid(id)).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException("Course with id "+id+" not found.");
        }

        return course;
    }

    public MappingJacksonValue getCourseByCourseCode(String code) throws ResourceNotFoundException{
        Course course = null;

        try {
            course = Optional.ofNullable(courseRepo.findBycourseCode(code)).orElseThrow();
        }catch (Exception ex){
            throw new ResourceNotFoundException("Course with course-code "+code+" not found.");
        }

        DynamicPropertyFiltering filtering = new DynamicPropertyFiltering(course,"courseName");

        return filtering.applyFilter("CourseFilter");
    }

    public Course updateCourseCourseCode(Course updatedCourse){
        Course course=null;
        try {
            course= Optional.ofNullable(courseRepo.findByid(updatedCourse.getId())).orElseThrow();

            course.setCourseName(updatedCourse.getCourseName() == null ? course.getCourseName():updatedCourse.getCourseName());
            course.setCourseCode(updatedCourse.getCourseCode() == null ? course.getCourseCode():updatedCourse.getCourseCode());
            course.setStudentList(updatedCourse.getStudentList());
            course.setTeacherList(updatedCourse.getTeacherList());
            course.setUpdated_at(new DateGenerator().getDate());

            courseRepo.save(course);
        }catch (Exception ex){
            throw new ResourceNotFoundException("No Course Found");
        }

        return course;
    }

    public String deleteCoure(Course course) throws ResourceNotFoundException{
        try{
            Course course1=Optional.ofNullable(courseRepo.findByid(course.getId())).orElseThrow();
            course.setCourseName(course1.getCourseName());
            courseRepo.delete(course);
        }catch (Exception ex){
            throw new ResourceNotFoundException("No course Found");
        }
        return "Successfully Deleted Course "+course.getCourseName();
    }

}
