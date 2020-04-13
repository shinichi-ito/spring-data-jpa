package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.Application;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class CourseRepositoryTest {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;
    @Autowired
    EntityManager em;
    @Test
    public void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
        course.setName("JPA in 50 Steps - Updated");
        repository.save(course);
       Course course1 = repository.findById(10001L);
       assertEquals("JPA in 50 Steps - Updated", course1.getName());
    }
    @Test
    @DirtiesContext
    public void playWithEntityManager() {
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = repository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview() {
        Review review = em.find(Review.class,50001L);
        logger.info("{}", review.getCourse());
    }
}
