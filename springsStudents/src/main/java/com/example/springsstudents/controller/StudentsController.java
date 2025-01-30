package com.example.springsstudents.controller;

import com.example.springsstudents.model.Student;
import com.example.springsstudents.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentsController {

    @NonNull
    private final StudentService studentService;

    @GetMapping
    public List<Student> findAllStudents() {

        return studentService.findAllStudents();

    }

    @PostMapping("save_student")
    public String saveStudent(@RequestBody @NonNull Student student) {
        studentService.saveStudent(student);
        return "FISTING";
    }

    @GetMapping("/{email}")
    public Student findByEmail(@PathVariable @NonNull String email) {
        return studentService.findByEmail(email);
    }

    @PutMapping("update_student")
    public Student updateStudent(@NonNull Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("delete-student/{email}")
    public void deleteEmail(@PathVariable @NonNull String email) {
        studentService.deleteEmail(email);
    }


}
