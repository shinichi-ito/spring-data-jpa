package com.in28minutes.jpa.hibernate.repository;

import com.in28minutes.jpa.hibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
}
