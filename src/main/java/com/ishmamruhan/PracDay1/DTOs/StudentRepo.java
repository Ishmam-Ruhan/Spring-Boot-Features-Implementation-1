package com.ishmamruhan.PracDay1.DTOs;

import com.ishmamruhan.PracDay1.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    Student findByid(Long id);
}
