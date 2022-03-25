package com.ishmamruhan.PracDay1.Controllers;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.CustomizeException;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.ResourceNotFoundException;
import com.ishmamruhan.PracDay1.Models.Student;
import com.ishmamruhan.PracDay1.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/greet")
    public ResponseEntity<Object> sayHi(){
        return ResponseEntity.ok("Greetings From Student Controller");
    }

    @PostMapping("/students/add")
    public ResponseEntity<Object> addStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(student));
    }

    @PostMapping("/students/add-all")
    public ResponseEntity<Object> addStudets(@RequestBody List<Student> students){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudentAll(students));
    }

    @PutMapping("/students/update")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) throws ResourceNotFoundException, CustomizeException {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(student));
    }

    @DeleteMapping("/students/delete")
    public ResponseEntity<Object> deleteStudent(@RequestBody Student student) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(studentService.deleteStudent(student));
    }

    @GetMapping("/students/get-all")
    public ResponseEntity<MappingJacksonValue> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/students/get/{id}")
    public ResponseEntity<MappingJacksonValue> getStudent(@PathVariable Long id) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PutMapping("/students/reactivate")
    public ResponseEntity<String> reactiveStudent(@RequestBody Student student) throws ResourceNotFoundException, CustomizeException{
        return ResponseEntity.status(HttpStatus.OK).body(studentService.reactivateStudent(student));
    }
}
