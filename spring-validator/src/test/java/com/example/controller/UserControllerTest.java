package com.example.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.repository.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenPostRequestForCreateUserIsValid_ThenCorrectResponse() throws Exception {

		/**
		 * Use this when response is String
		 */
//		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));

		String userRequest = "{\"username\":\"akshay\",\"password\":\"123\",\"email\":\"akshay@gmail.com\",\"firstName\":\"Akshay\",\"lastName\":\"H\"}";
		String userResponse = "{\"id\":1,\"username\":\"akshay\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(userRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.content().json(userResponse));

	}
}
