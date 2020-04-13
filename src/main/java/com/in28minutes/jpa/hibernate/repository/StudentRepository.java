package com.in28minutes.jpa.hibernate.repository;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
    return student;
    }


    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z12345");
        em.persist(passport);
        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }
    public void someOperationToUnderstandPersistenceContext() {
        Student student = em.find(Student.class, 20001L);
        Passport passport = student.getPassport();
        passport.setNumber("E123457");
        student.setName("Ranga - updated");
    }
    public void insertHardcodeStudentAndCourse(){
        Student student = new Student("Jack");
        Course course = new Course("Mivroservices in 100 Steps");
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course){
        //Student student = new Student("Jack");
        //Course course = new Course("Mivroservices in 100 Steps");
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
        em.persist(course);

    }
}
