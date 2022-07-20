package com.example.springcache.service;

import com.example.springcache.domain.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Cacheable("student")
    public Student getStudentByID(String id) {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Student(id, "Huy", "java");
    }
}
