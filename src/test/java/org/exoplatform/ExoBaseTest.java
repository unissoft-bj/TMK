package org.exoplatform;

import java.net.URL;

import org.exoplatform.model.ExoAccount;
import org.exoplatform.singleton.AccountSetting;
import org.exoplatform.singleton.SocialServiceHelper;
import org.exoplatform.social.client.api.ClientServiceFactory;
import org.exoplatform.social.client.api.SocialClientContext;
import org.exoplatform.social.client.api.service.VersionService;
import org.exoplatform.social.client.core.ClientServiceFactoryHelper;
import org.exoplatform.ui.login.tasks.LoginTask;
import org.exoplatform.utils.ExoConnectionUtils;
import org.exoplatform.utils.ExoConstants;
import org.exoplatform.utils.ExoUtils;
import org.junit.After;
import org.junit.Before;

public class ExoBaseTest {

	public ExoBaseTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("set up test.");
		String user = "michaelyin";
		String pw = "andy0127";
		//String tenant = "http://121.22.36.226:5015";
		String tenant = "http://localhost:8080";
		ExoAccount newAccountObj = new ExoAccount();
		String name = ExoUtils.getAccountNameFromURL(tenant, "My Intranet");
		System.out.println("name: " + name);
	    //newAccountObj.accountName = ExoUtils.capitalize(name);
	    newAccountObj.serverUrl = tenant;
	    newAccountObj.username = user;
	    newAccountObj.password = pw;
	    
	   AccountSetting.getInstance().setCurrentAccount(newAccountObj);
		
		LoginTask lt = new LoginTask();
		int result = lt.execute(user, pw, tenant);
		
		 if (ExoConnectionUtils.LOGIN_SUCCESS == result) {
	          URL u = new URL(tenant);
	          SocialClientContext.setProtocol(u.getProtocol());
	          SocialClientContext.setHost(u.getHost());
	          SocialClientContext.setPort(u.getPort());
	          SocialClientContext.setPortalContainerName(ExoConstants.ACTIVITY_PORTAL_CONTAINER);
	          SocialClientContext.setRestContextName(ExoConstants.ACTIVITY_REST_CONTEXT);
	          SocialClientContext.setUsername(user);
	          SocialClientContext.setPassword(pw);
	          ClientServiceFactory clientServiceFactory = ClientServiceFactoryHelper.getClientServiceFactory();
	          VersionService versionService = clientServiceFactory.createVersionService();
	          SocialClientContext.setRestVersion(versionService.getLatest());
	          SocialServiceHelper.getInstance().activityService = clientServiceFactory.createActivityService();
	          SocialServiceHelper.getInstance().spaceService = clientServiceFactory.createSpaceService();
	          SocialServiceHelper.getInstance().identityService = clientServiceFactory.createIdentityService();
	        }
	}

	@After
	public void tearDown() throws Exception {
		
		
	}

}