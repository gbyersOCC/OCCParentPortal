package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by dnova_000 on 11/22/2016.
 */
public class ParentTest {

    Parent parent = new Parent();

    @Before
    public void setUp() throws Exception {

        parent.setUsername("user");
        parent.setNameFirst("firstName");
        parent.setNameLast("lastName");
        parent.setEmailAddress("email@email.com");
        parent.setPhoneNumber(5551112222l);
        parent.setLastLogin(0l);
        parent.addChild(new Child());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(parent.getId(), -1);
    }

    @Test
    public void getUsername() throws Exception {
        assertEquals(parent.getUsername(), "user");
    }

    @Test
    public void getNameFirst() throws Exception {
        assertEquals(parent.getNameFirst(), "firstName");
    }

    @Test
    public void getNameLast() throws Exception {
        assertEquals(parent.getNameLast(), "lastName");

    }

    @Test
    public void getEmailAddress() throws Exception {
        assertEquals(parent.getEmailAddress(), "email@email.com");

    }

    @Test
    public void getPhoneNumber() throws Exception {
        assertEquals(parent.getPhoneNumber(), 5551112222l);
    }

    @Test
    public void getImagePath() throws Exception {
        assertNull(parent.getImagePath());

    }

    @Test
    public void getLastLogin() throws Exception {
        assertEquals(parent.getLastLogin(), 0l);
    }

    @Test
    public void getChildren() throws Exception {
        ArrayList<Child> children = parent.getChildren();
        assertEquals(children.size(), 1);
    }

}