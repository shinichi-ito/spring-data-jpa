package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.Application;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Student;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;
    @Autowired
    EntityManager em;
    @Test
    public void someTest() {

        repository.someOperationToUnderstandPersistenceContext();
    }


    @Test
    @Transactional
    public void retrievesStudentAndPassportDetails() {
       Student student = em.find(Student.class, 20001L);
       logger.info("student -> {}", student);
       logger.info("passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrievesStudentAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("passport -> {}", passport);
        logger.info("student -> {}", passport.getStudent());
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());

    }
    @Test
    @Transactional
    public void retrievesStudentAndCourses() {
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("courses -> {}", student.getCourses());
    }
}


