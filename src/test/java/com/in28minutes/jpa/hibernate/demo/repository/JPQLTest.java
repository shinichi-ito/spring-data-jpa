package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.Application;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Student;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
     Query query = em.createNamedQuery("query_get_all_courses");
     List resultList = query.getResultList();
     logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    public void jpql_basic2() {
        Query query = em.createQuery("select c from Course c");
        List resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }
    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    public void jpql_typed2() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_100_Step_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c where name like '%100 Steps' -> {}", resultList);
    }

    @Test
    public void jpql_where2() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.name like '%100 Steps'", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c where name like '%100 Steps' -> {}", resultList);
    }

    @Test
    public void jpql_where3() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.id = :id", Course.class);
        query.setParameter("id", 10001L);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c where c.id = 10001L  -> {}", resultList);
    }

    @Test
    public void jpql_where_like() {
        String data = "100 Steps";
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.name like :data", Course.class);
        query.setParameter("data", "%"+data);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c where name like '%100 Steps' -> {}", resultList);
    }


    @Test
    @Transactional
    public void jpql_to_update() {
        Query query = em.createQuery("Update Course c set c.lastUpdatedDate=sysdate()");
        int noOfRowUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated -> {}", noOfRowUpdated);
    }
    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty ", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }
    @Test
    public void jpql_courses_with_atleast_2_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2 ", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }
    @Test
    public void jpql_courses_ordered_by_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by c.students.size desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }
    @Test
    public void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }
    @Test
    public void join() {
        Query query = em.createQuery("select c,s from Course c join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList) {
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }
    @Test
    public void left_join() {
        Query query = em.createQuery("select c,s from Course c left join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList) {
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }
    @Test
    public void cross_join() {
        Query query = em.createQuery("select c,s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList) {
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }
}