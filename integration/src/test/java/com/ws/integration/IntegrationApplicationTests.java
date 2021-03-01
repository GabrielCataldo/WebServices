package com.ws.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ws.integration.scheduledServices.IntegrationWSJob;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {IntegrationWSJob.class})
@AutoConfigureMockMvc
class IntegrationApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	@MockBean
	IntegrationWSJob integrationWSJob;
	
	@Test
	void trasferRequestsTeste() throws Exception{
		integrationWSJob.trasferRequests();
	}

}
