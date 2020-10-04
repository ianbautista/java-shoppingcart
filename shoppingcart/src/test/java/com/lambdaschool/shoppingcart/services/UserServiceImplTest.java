package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.ShoppingcartApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingcartApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest
{
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void a_findAll()
    {
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void b_findUserById()
    {
        assertEquals("barnbarn", userService.findUserById(1).getUsername());
    }

    @Test
    public void z_delete()
    {
        userService.delete(3);
        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void save()
    {

    }

    @Test
    public void findByName() {
    }
}