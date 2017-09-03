package eu.recred.fidouafsvc.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import eu.recred.fidouafsvc.Application;
import eu.recred.fidouafsvc.storage.StorageInterface;

//@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProcessAuxRequestServiceTest {

	Gson gson = new Gson();

	@Autowired
	@Qualifier("storageDao")
	StorageInterface storageDao;

	@Autowired
	ProcessAuxRequestsService processAuxRequests;

	@Test
	public void testAuthenticationIdResponse() {
		String testId = "123456";
		String testUsername = "username";

		// save test data.
		storageDao.saveAuthenticationId(testId, testUsername);

		// verify response data.
		String response = processAuxRequests.getAuthenticated(testId);
		Response responseObj = gson.fromJson(response, Response.class);

		assertTrue(responseObj.authenticated);
		assertEquals(testUsername, responseObj.username);
	}

	@Test
	public void badTestAuthenticationidResponse() {
		String response = processAuxRequests.getAuthenticated("noidhere");
		Response responseObj = gson.fromJson(response, Response.class);

		assertFalse(responseObj.authenticated);
	}

	private class Response {
		public boolean authenticated;
		public String username;
	}
}