package com.ishmamruhan.PracDay1.Controllers;

import com.ishmamruhan.PracDay1.ErrorManagement.Errors.CustomizeException;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.ResourceNotFoundException;
import com.ishmamruhan.PracDay1.Models.Teacher;
import com.ishmamruhan.PracDay1.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers/greet")
    public ResponseEntity<Object> sayHi(){
        return ResponseEntity.ok("Hello from Teacher Controller!");
    }

    @PostMapping("/teachers/add")
    public ResponseEntity<Object> add(@RequestBody Teacher teacher) throws CustomizeException{
        return ResponseEntity.ok(teacherService.addTeacher(teacher));
    }

    @PostMapping("/teachers/add-all")
    public ResponseEntity<Object> addAll(@RequestBody List<Teacher> teachers) throws CustomizeException{
        return ResponseEntity.ok(teacherService.addTeacherAll(teachers));
    }

    @PutMapping("/teachers/update")
    public ResponseEntity<Object> updateTeacher(@RequestBody Teacher teacher) throws ResourceNotFoundException, CustomizeException{
        return ResponseEntity.ok(teacherService.updateTeacher(teacher));
    }

    @DeleteMapping("/teachers/delete")
    public ResponseEntity<Object> deleteTeacher(@RequestBody Teacher teacher) throws ResourceNotFoundException,CustomizeException{
        return ResponseEntity.ok().body(teacherService.deleteTeacher(teacher));
    }

    @GetMapping("/teachers/get-all")
    public ResponseEntity<List<Teacher>> getAllTeacher(){
        return ResponseEntity.ok(teacherService.getAllTeacher());
    }

    @GetMapping("/teachers/get/id-{id}")
    public ResponseEntity<Teacher> getById(@PathVariable long id) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(teacherService.getTeacherById(id));
    }

    @PutMapping("/teachers/reactivate")
    public ResponseEntity<Teacher> reactivateTeacher(@PathVariable Teacher teacher) throws ResourceNotFoundException,CustomizeException{
        return ResponseEntity.ok().body(teacherService.reactivateTeacher(teacher));
    }

}
