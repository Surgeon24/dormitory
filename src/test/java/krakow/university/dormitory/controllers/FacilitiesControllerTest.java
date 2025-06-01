package krakow.university.dormitory.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(FacilitiesController.class)
public class FacilitiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void facilities_ReturnsFacilitiesView() throws Exception {
        mockMvc.perform(get("/facilities"))
                .andExpect(status().isOk())
                .andExpect(view().name("facilities"));
    }
}


