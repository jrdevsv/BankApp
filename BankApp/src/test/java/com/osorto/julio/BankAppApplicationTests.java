package com.osorto.julio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.osorto.julio.service.PersonService;

@SpringBootTest
@AutoConfigureMockMvc
class BankAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	void contextLoads() {
	}

	@Test
	void findAllPerson() throws Exception {
		/* get all person and verify that status is 200 */
		mockMvc.perform(MockMvcRequestBuilders.get("/person/findAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void findAllMovs() throws Exception {
		/* get all movements and verify that status is 200 */
		mockMvc.perform(MockMvcRequestBuilders.get("/movement/findAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
