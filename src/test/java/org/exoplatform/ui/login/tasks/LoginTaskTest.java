package org.exoplatform.ui.login.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTaskTest {

	@Test
	public void testExecute() {
		
		String user = "root";
		String pw = "gtn";
		String tenant = "http://localhost:8080";
		LoginTask lt = new LoginTask();
		lt.execute(user, pw, tenant);
		//fail("Not yet implemented");
	}

}
