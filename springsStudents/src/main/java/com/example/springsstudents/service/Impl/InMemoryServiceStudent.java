package com.example.springsstudents.service.Impl;

import com.example.springsstudents.model.Student;
import com.example.springsstudents.repository.InMemoryStudentsDAO;
import com.example.springsstudents.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryServiceStudent implements StudentService {

    private final InMemoryStudentsDAO repository;

    @Override
    public List<Student> findAllStudents() {

        return repository.findAllStudents();

    }

    @Override
    public Student saveStudent(Student student) {
        return repository.saveStudent(student);
    }

    @Override
    public Student findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Student updateStudent(Student student) {
        return repository.updateStudent(student);
    }

    @Override
    public void deleteEmail(String email) {
        repository.deleteEmail(email);
    }


}
