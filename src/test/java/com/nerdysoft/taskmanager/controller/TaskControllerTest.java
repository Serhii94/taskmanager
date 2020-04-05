//package com.nerdysoft.taskmanager.controller;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import javax.inject.Inject;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(TaskController.class)
//class TaskControllerTest {
//
//    @Inject
//    private MockMvc mockMvc;
//
////    @Inject
////    private TaskController taskController;
////
////    @MockBean
////    private TaskRepository taskRepository;
//
//    @BeforeAll
//    void setUp() {
//
//    }
//
//    @Test
//    void createTask() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.get("/tasks");
//        MvcResult result = mockMvc.perform(request).andReturn();
//        assertEquals("/tasks/1",result.getResponse().getHeader("location"));
//        assertEquals(, result.getResponse().getContentAsString());
//    }
//
//    @Test
//    void updateTask() {
//    }
//
//    @Test
//    void shareTask() {
//    }
//
//    @Test
//    void deleteTask() {
//    }
//}