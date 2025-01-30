package com.example.springsstudents.repository;

import com.example.springsstudents.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    void deleteByEmail(String email);
    Student findByEmail(String email);

}
