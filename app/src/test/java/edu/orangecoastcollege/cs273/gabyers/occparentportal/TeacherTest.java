package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by gbyers on 11/22/2016.
 */
public class TeacherTest {
    Teacher mTeacher;
    @Before
    public void setUp() throws Exception {
    mTeacher = new Teacher();
        mTeacher.setNameFirst("Grant");
        mTeacher.setNameLast("Byers");
        mTeacher.setEmailAddress("gabyers86@gmail.com");
        mTeacher.setImagePath(null);
        mTeacher.setLastLogin("00/00/00");
        mTeacher.setLesson("Lesson plan string");
        mTeacher.setPassword("password");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
    assertEquals("Error in getId", -1, mTeacher.getId());
    }

    @Test
    public void getNameFirst() throws Exception {
        assertEquals("Error in getNameFirst", "Grant", mTeacher.getNameFirst());
    }


    @Test
    public void getNameLast() throws Exception {
        assertEquals("Error in getNameLast", "Byers", mTeacher.getNameLast());
    }



    @Test
    public void getEmailAddress() throws Exception {
        assertEquals("Error in getEmailAddress", "gabyers86@gmail.com", mTeacher.getEmailAddress());
    }


    @Test
    public void getImagePath() throws Exception {
    assertNull("Error in getImagePath", mTeacher.getImagePath());
    }

    @Test
    public void getPassword() throws Exception {
        assertEquals("Error in getPassword", "password", mTeacher.getPassword());

    }

    @Test
    public void getLastLogin() throws Exception {
        assertEquals("Error in getlastLogin", "00/00/00", mTeacher.getLastLogin());
    }


    @Test
    public void getLesson() throws Exception {
        assertEquals("Error in getLesson", "Lesson plan string", mTeacher.getLesson());
    }




}