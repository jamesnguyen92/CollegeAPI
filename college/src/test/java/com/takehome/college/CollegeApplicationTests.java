package com.takehome.college;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.takehome.models.CollegeInfo;


@SpringBootTest(webEnvironment = RANDOM_PORT)
class CollegeApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCollege()
    {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/college?name=Adelphi University&housing=true", CollegeInfo.class)
                        .getCollegeName().equals("Test"));
    }

    @Test
    public void testAllColleges() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/colleges", List.class).size() == 2);
    }

}
