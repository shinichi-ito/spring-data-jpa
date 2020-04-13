package com.in28minutes.jpa.hibernate.repository;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
    return course;
    }


    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Servies in 100 Steps");
        em.persist(course1);

        Course course2 = new Course("Angular Js in 100 Steps");
        em.persist(course2);
        em.flush();


        course1.setName("Web Servies in 100 Steps - Updated");
        course2.setName("Angular Js in 100 Steps - Updated");
        em.refresh(course1);
        em.flush();

    }

    public void addReviewForCourse() {
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        Review review1 = new Review("5", "Great Hands-on Stuff.");
        Review review2 = new Review("5", "Hatsoff.");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);

    }

    public void addHardcodedReviewForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());

        for(Review review:reviews) {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }
}
