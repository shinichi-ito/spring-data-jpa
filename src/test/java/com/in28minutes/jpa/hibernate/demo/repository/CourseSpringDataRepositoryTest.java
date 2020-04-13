package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.Application;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.repository.CourseSpringDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent() {
       Optional<Course> courseOptional = repository.findById(10001L);
       assertTrue(courseOptional.isPresent());
       //logger.info("{}", courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
        //logger.info("{}", courseOptional.isPresent());
    }
    @Test
    public void playingAroundWithSpringDataRepository(){
//        Course course = new Course("Microservices in 100 Steps");
//        repository.save(course);
//
//        course.setName("Microservices in 100 Steps - Updated");
//        repository.save(course);
        logger.info("Courses -> {} ", repository.findAll());
        logger.info("Count -> {} ", repository.count());

    }

    @Test
    public void sort(){

        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        logger.info("Sorted Courses -> {} ", repository.findAll(sort));
        //Courses -> [Course{JPA in 50 Steps'}, Course{Spring in 150 Steps'}, Course{Spring boot in 250 Steps'}]
        //Sorted Courses -> [Course{Spring in 150 Steps'}, Course{Spring boot in 250 Steps'}, Course{JPA in 50 Steps'}]
        logger.info("Count -> {} ", repository.count());

    }

    @Test
    public void pagination(){

        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage.getContent());
        //First Page -> [Course{JPA in 50 Steps'}, Course{Spring in 150 Steps'}, Course{Spring boot in 250 Steps'}]

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page -> {}", secondPage.getContent());
        //Second Page -> [Course{Dummy1'}, Course{Dummy2'}, Course{Dummy3'}]
    }
}
