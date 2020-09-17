package com.lambdaschool.shoppingcart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.shoppingcart.models.Roles;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.models.UserRoles;
import com.lambdaschool.shoppingcart.services.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // replaces @WebMvcTest(value="")
@AutoConfigureMockMvc // replaces @WebMvcTest(value="")
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class UserControllerTest
{
    @Autowired
    private WebApplicationContext webApplicationContext; // setup webContext. needs to handle security

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> userList;

    @Before
    public void setUp() throws Exception
    {
        userList = new ArrayList<>();

        Roles r1 = new Roles("admin");
        r1.setRoleid(1);
        Roles r2 = new Roles("user");
        r2.setRoleid(2);
        Roles r3 = new Roles("data");
        r3.setRoleid(3);

        // admin, data, user
        User u1 = new User("admin", "ILuvM4th!", "admin@lambdaschool.local");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));
        u1.getRoles().add(new UserRoles(u1, r3));

        u1.setUserid(101);
        userList.add(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("cinnamon", "1234567", "cinnamon@lambdaschool.local");
        u1.getRoles().add(new UserRoles(u2, r2));
        u1.getRoles().add(new UserRoles(u2, r3));


        u2.setUserid(102);
        userList.add(u2);

        // user
        User u3 = new User("testingbarn", "ILuvM4th!", "testingbarn@school.lambda");
        u3.getRoles().add(new UserRoles(u3, r1));

        u3.setUserid(103);
        userList.add(u3);

        User u4 = new User("testingcat", "password", "testingcat@school.lambda");
        u4.getRoles().add(new UserRoles(u4, r2));

        u4.setUserid(104);
        userList.add(u4);

        User u5 = new User("testingdog", "password", "testingdog@school.lambda");
        u4.getRoles().add(new UserRoles(u5, r2));

        u5.setUserid(105);
        userList.add(u5);

        System.out.println("\n*** Seed Data ***");
        for (User u : userList)
        {
            System.out.println(u);
        }
        System.out.println("*** Seed Data ***\n");

        // setup security in a test environment
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        // setup security in a test environment
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllUsers() {
    }

    @Test
    public void a_getUserById() throws Exception
    {
        String apiUrl = "/users/user/3";

        Mockito.when(userService.findUserById(3))
                .thenReturn(userList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb)
                .andReturn(); // this could throw an exception
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(1));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void addUser() {
    }

    @Test
    public void z_deleteUserById() throws Exception
    {
        String apiUrl = "/users/user/{userid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findCurrentUser() {
    }
}