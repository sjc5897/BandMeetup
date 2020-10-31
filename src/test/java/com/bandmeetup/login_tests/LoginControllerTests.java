package com.bandmeetup.login_tests;

import com.bandmeetup.controller.LogController;
import com.bandmeetup.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.AdditionalMatchers.not;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LogController.class)
public class LoginControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService service;

    @Test
    public void login_get_returns_login_page() throws Exception{
        ResultActions login = this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
    @Test
    public void login_post_success_returns_home_page() throws Exception{
        when(service.validateUser(any(),any())).thenReturn(true);
        this.mockMvc.perform(post("/login")
                .param("uname","fake")
                .param("pw","fake"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void login_post_failed_auth() throws Exception{
        when(service.validateUser(any(),any())).thenReturn(false);
        this.mockMvc.perform(post("/login")
                .param("uname","fake")
                .param("pw","fake"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content()
                        .string(containsString("Authentication unsuccessful")));
    }



}
