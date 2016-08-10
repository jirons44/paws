package com.joetony.models;

import com.joetony.util.Mysql;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/10/16.
 */
public class ClientTest{

    @Before
    public void setUp() throws Exception {

        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery( "truncate table Clients").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void ShouldCreateNewClientAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("jennifer");
        session.save(c);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, c.getId());
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void ShouldNotCreateNewClientNameTooLong() throws Exception {

       Session session = null;
        try {
            session = Mysql.getSession();
            session.beginTransaction();
            Client c = new Client("123456789012345678901234567890123456789012345123456789012345678901234567890123456789012345");
            session.save(c);
            session.getTransaction().commit();
        } finally {
            session.close();
        }

    }

}