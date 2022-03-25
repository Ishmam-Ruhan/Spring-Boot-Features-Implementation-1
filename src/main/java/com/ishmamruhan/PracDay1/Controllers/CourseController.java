package com.ishmamruhan.PracDay1.Controllers;

import com.ishmamruhan.PracDay1.ErrorManagement.Errors.ResourceNotFoundException;
import com.ishmamruhan.PracDay1.Models.Course;
import com.ishmamruhan.PracDay1.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses/greet")
    public ResponseEntity<String> sayHi(){
        return ResponseEntity.status(HttpStatus.OK).body("Hi From Course Controller");
    }

    @PostMapping("/courses/add")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        String msg=courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PostMapping("/courses/add-all")
    public ResponseEntity<String> addCourses(@RequestBody List<Course> courses){
        String msg=courseService.addCourses(courses);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @GetMapping("/courses/get-all")
    public ResponseEntity<MappingJacksonValue> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("/courses/get/id-{id}")
    public ResponseEntity<Course> getById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(courseService.getCourseByID(id));
    }

    @GetMapping("/courses/get/code-{code}")
    public ResponseEntity<MappingJacksonValue> getByCode(@PathVariable String code) throws ResourceNotFoundException{
        return ResponseEntity.ok(courseService.getCourseByCourseCode(code));
    }

    @PutMapping("/courses/update")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.updateCourseCourseCode(course));
    }

    @DeleteMapping("/courses/delete")
    public ResponseEntity<String> deleteCourse(@RequestBody Course course) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(courseService.deleteCoure(course));
    }

}
