package com.ishmamruhan.PracDay1.DTOs;

import com.ishmamruhan.PracDay1.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher findByid(long id);
}
