package com.joetony.models;

import com.joetony.util.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/10/16.
 */
public class ShelterTest {
    @Before
    public void setUp() throws Exception {

        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery( "truncate table Shelters").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void CanCreateNewShelter() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter s = new Shelter("Dogs-R-Us", new Date());
        session.save(s);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, s.getId());

    }


    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void CanNotCreateNewShelterWithSameName() throws Exception {
        Session session = Mysql.getSession();

        try {
            session.beginTransaction();
            Shelter s1 = new Shelter("Dogs-R-Us", new Date());
            session.save(s1);
            assertEquals(1, s1.getId());

            Shelter s2 = new Shelter("Dogs-R-Us", new Date());
            session.save(s2);
            session.getTransaction().commit();

        }finally {
            session.close();
        }

    }


    @Test(expected = java.lang.Exception.class)
    public void CanNotCreateNewShelterWithNoName() throws Exception {
        Session session = Mysql.getSession();

        try {
            session.beginTransaction();
            Shelter s1 = new Shelter(null, new Date());
            session.save(s1);
            assertEquals(1, s1.getId());

        }finally {
            session.close();
        }

    }

}