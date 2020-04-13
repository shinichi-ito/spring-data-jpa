package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.Application;
import com.in28minutes.jpa.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void native_queries_basic() {
     Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
     List resultList = query.getResultList();
     logger.info("SELECT * FROM COURSE -> {}", resultList);
    }

    @Test
    public void native_queries_with_parameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE where id = ? -> {}", resultList);
    }

    @Test
    public void native_queries_with_named_parameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE where id = ? -> {}", resultList);
    }

    @Test
    @Transactional
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("Update COURSE set last_updated_date=sysdate()", Course.class);
        int noOfRowUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated -> {}", noOfRowUpdated);
    }
}