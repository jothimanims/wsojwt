package com.howtodoinjava.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.test.demo.SpringBootDemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootDemoApplication.class)
public class EmployeeControllerTest {

	 @Autowired
     private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;
    
    @Autowired
	private WebApplicationContext webApplicationContext;
    
    @Before
    public void setup() {
    	/*this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilters(springSecurityFilterChain)
                .build();
    	*/
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    			//apply(springSecurity()).build();

       // this.restUserMockMvc = MockMvcBuilders.build();
    }
    
	@Test
	public void getEmployeesTest() throws Exception {
		 // An entity with an existing ID cannot be created, so this API call must fail
		mockMvc.perform(get("/employe/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk());
            //.andExpect(jsonPath("$[0]").value("test"));

		
		MvcResult result = mockMvc.perform(get("/employe/1")
	            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("test", response.getContentAsString());
	}

}
