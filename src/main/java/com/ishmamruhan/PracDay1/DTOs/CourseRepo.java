package com.ishmamruhan.PracDay1.DTOs;

import com.ishmamruhan.PracDay1.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    Course findByid(Long id);
    Course findBycourseCode(String code);
}
