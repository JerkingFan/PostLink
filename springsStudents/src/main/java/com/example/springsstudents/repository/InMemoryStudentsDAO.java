package com.example.springsstudents.repository;

import com.example.springsstudents.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryStudentsDAO {

    private final List<Student> students = new ArrayList<Student>();

    public List<Student> findAllStudents() {

        return students;

    }

    public Student saveStudent(Student student) {
        students.add(student);
        return student;
    }

    public Student findByEmail(String email) {
        return students.stream().filter(student -> student.getEmail().equals(email)).findFirst().orElse(null);
    }

    public Student updateStudent(Student student) {
        var studentIndex = IntStream.range(0, students.size())
                .filter(index -> students.get(index).getEmail().equals(student.getEmail()))
                .findFirst().orElse(-1);

        if(studentIndex != -1) {
            students.set(studentIndex, student);
            return student;
        }
        else{
            return null;
        }

    }

    public void deleteEmail(String email) {

        students.removeIf(student -> student.getEmail().equals(email));

    }

}
