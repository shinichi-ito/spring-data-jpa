package com.in28minutes.jpa.hibernate;

import com.in28minutes.jpa.hibernate.entity.*;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //studentRepository.saveStudentWithPassport();
        //courseRepository.playWithEntityManager();
        //courseRepository.addReviewForCourse();
        //List<Review> reviews = new ArrayList<>();
       // reviews.add(new Review("5", "Great Hands-on Stuff."));
       // reviews.add(new Review("5", "Hatsoff."));


       // courseRepository.addHardcodedReviewForCourse(10003L, reviews);

        //studentRepository.insertStudentAndCourse(
              //  new Student("Jack"), new Course("Mivroservices in 100 Steps"));

       // employeeRepository.insert(
              //  new PartTimeEmployee("Jill", new BigDecimal("50")));

        //employeeRepository.insert(
              //  new FullTimeEmployee("Jack", new BigDecimal("1000")));
       // logger.info("All Employee -> {}", employeeRepository.retrieveAllEmployees());
        //logger.info("Full Time Employee -> {}", employeeRepository.retrieveAllFullTimeEmployees());
       // logger.info("Part Time Employee -> {}", employeeRepository.retrieveAllPartTimeEmployees());


    }
}
