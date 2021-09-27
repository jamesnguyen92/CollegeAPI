package com.takehome.college;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.takehome.models.CollegeInfo;
import com.takehome.repository.CollegeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CollegeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CollegeRepository collegeRepo;

    @Test
    public void testGetDepartment() throws Exception {
    	CollegeInfo college = new CollegeInfo();
    	college.setCollegeName("Adelphi University");

        List<CollegeInfo> collegeList = new ArrayList<>();
        collegeList.add(college);

        given(collegeRepo.findAll()).willReturn(collegeList);

        this.mockMvc.perform(get("/colleges")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

}
