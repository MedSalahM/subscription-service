package com.mms.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mms.domain.MyAccount;
import com.mms.dto.SignUpRequest;
import com.mms.dto.SignUpResponse;
import com.mms.service.SignUpService;

@WebMvcTest(SignUpController.class)
public class TestSignUpController {

	
	
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private SignUpService service;
	
	
	
	@Test
	@DisplayName("Signup first step")
	public void shouldRturnCreated() throws Exception{
		
		 SignUpRequest request = SignUpRequest.builder()
				                 .email("med@live.fr")
				                 .build();
	   	
		when(service.signup(request)).thenReturn(
				SignUpResponse.builder().
				email("med@live.fr")
				.activationLink("link").build());
		
		mock.perform(post("/api/v1/signup").contentType(MediaType.APPLICATION_JSON)
				.content("{  \"email\" : \"med@live.fr\"  }")
				)
		 .andExpect(status().isCreated())
		 /*.andExpect(MockMvcResultMatchers.jsonPath("$[0].email" ).value("med@live.fr"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].activationLink" ).value("link"))*/;
		
	}
	
	@Test
	@DisplayName("Signup second step")
	public void shouldRturnAnAccount() throws Exception{
		
		
	   	
		when(service.activate("abc123")).thenReturn(
				MyAccount.builder()
				 .accountId(100L)
				 .email("a@a.dz")
				 .name("name").build()
				);
		
		mock.perform(get("/api/v1/pass/abc123")
			
				)
		 .andExpect(status().isOk())
		 ;
		
	}
	
	
}
