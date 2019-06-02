package org.demo.guicedemo.server.impl;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;

public class SessionManagerTest {

	@Inject SessionManager sessionManager;
	
	@Before
	public void setUp() throws Exception {
		Guice.createInjector(new ServiceModule())
			.injectMembers(this);
	}

	@Test
	public void testGetSessionId() throws InterruptedException {
		Long sessionId1 = sessionManager.getSessionId();
		// pretend we sleep enough so session expires
		Thread.sleep(1000);
		Long sessionId2 = sessionManager.getSessionId();
		assertNotEquals(
				sessionId1.longValue(), sessionId2.longValue());
	}

}
