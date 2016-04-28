/**
 * 
 */
package net.wyun.dianxiao.watcher;

import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.exoplatform.controller.social.ComposeMessageController;
import org.exoplatform.model.ExoAccount;
import org.exoplatform.model.SocialPostInfo;
import org.exoplatform.model.SocialSpaceInfo;
import org.exoplatform.shareextension.service.ShareService.UploadInfo;
import org.exoplatform.singleton.AccountSetting;
import org.exoplatform.singleton.SocialServiceHelper;
import org.exoplatform.social.client.api.ClientServiceFactory;
import org.exoplatform.social.client.api.SocialClientContext;
import org.exoplatform.social.client.api.model.RestActivity;
import org.exoplatform.social.client.api.service.ActivityService;
import org.exoplatform.social.client.api.service.VersionService;
import org.exoplatform.social.client.core.ClientServiceFactoryHelper;
import org.exoplatform.ui.login.tasks.LoginTask;
import org.exoplatform.utils.ExoConnectionUtils;
import org.exoplatform.utils.ExoConstants;
import org.exoplatform.utils.ExoDocumentUtils;
import org.exoplatform.utils.ExoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.wyun.dianxiao.model.CallDirection;

/**
 * @author michael
 *
 */
@Component
public class ActivityPersistorExoImpl {

	private static final Logger logger = LoggerFactory.getLogger(ActivityPersistorDBImpl.class);

	@Value("${exo.tmk.user}")
	private String exoUser;

	@Value("${exo.tmk.password}")
	private String exoPassword;

	@Value("${exo.server.url}")
	private String exoServerUrl; // http://localhost:8080
	
	@Value("${exo.tmk.folder}")
	private String exoTmkFolder;
	
	@Value("${exo.tmk.space}")
	private String exoTmkSpace;

	public CallDirection getCallDirection(List<String> list) {
		throw new RuntimeException("not supported!!!");
	}

	/**
	 * 
	 * @see net.wyun.dianxiao.watcher.ActivityPersistor#persist(net.wyun.dianxiao.model.CallDirection,
	 *      java.util.List)
	 * 
	 *      ex. of List<String> list<br>
	 *      230<br>
	 *      18833500052<br>
	 *      20151218<br>
	 *      102537<br>
	 *      24578<br>
	 *      cg<br>
	 *      /opt/tmk/record/dysoft/20151218/230_18833500052_20151218-
	 *      102537_24578_cg.mp3<br>
	 *      duration for sweeper
	 */
	public void persist(CallDirection direction, List<String> list) {
        SocialSpaceInfo ssi = SocialServiceHelper.getInstance().getSpaceByName("电话销售");
		
		ComposeMessageController controller = new ComposeMessageController(0);  //0 -- post
		controller.setPostDestination(ssi);
		logger.info("file path: {}", list.get(6));
		controller.onSendMessage("new call record", list.get(6), -1, this.exoTmkFolder+ "/" + list.get(2));

	}

	@PostConstruct
	public void init() throws Exception {
		logger.info("init exo tmk account");
		
		// String tenant = "http://121.22.36.226:5015";
		//String tenant = "http://localhost:8080";
		ExoAccount newAccountObj = new ExoAccount();
		String name = ExoUtils.getAccountNameFromURL(this.exoServerUrl, "My Intranet");
		logger.info("account name from URL: " + name);
		// newAccountObj.accountName = ExoUtils.capitalize(name);
		newAccountObj.serverUrl = this.exoServerUrl;
		newAccountObj.username = this.exoUser;
		newAccountObj.password = this.exoPassword;

		AccountSetting.getInstance().setCurrentAccount(newAccountObj);

		LoginTask lt = new LoginTask();
		int result = lt.execute(exoUser, exoPassword, exoServerUrl);

		if (ExoConnectionUtils.LOGIN_SUCCESS == result) {
			logger.info("login successful. init other services now.");
			URL u = new URL(exoServerUrl);
			SocialClientContext.setProtocol(u.getProtocol());
			SocialClientContext.setHost(u.getHost());
			SocialClientContext.setPort(u.getPort());
			SocialClientContext
					.setPortalContainerName(ExoConstants.ACTIVITY_PORTAL_CONTAINER);
			SocialClientContext
					.setRestContextName(ExoConstants.ACTIVITY_REST_CONTEXT);
			SocialClientContext.setUsername(exoUser);
			SocialClientContext.setPassword(exoPassword);
			ClientServiceFactory clientServiceFactory = ClientServiceFactoryHelper
					.getClientServiceFactory();
			VersionService versionService = clientServiceFactory
					.createVersionService();
			SocialClientContext.setRestVersion(versionService.getLatest());
			SocialServiceHelper.getInstance().activityService = clientServiceFactory.createActivityService();
			SocialServiceHelper.getInstance().spaceService = clientServiceFactory.createSpaceService();
			SocialServiceHelper.getInstance().identityService = clientServiceFactory.createIdentityService();
		}else{
			throw new RuntimeException("cannot login to exo. check account!!!");
		}
		
		//create folder at space 电话销售
		createBaseFolder();
	}
	
	private void createBaseFolder(){
        SocialSpaceInfo ssi = SocialServiceHelper.getInstance().getSpaceByName("电话销售");
		
		SocialPostInfo postInfo = new SocialPostInfo();
	    postInfo.destinationSpace = ssi;
	    postInfo.postMessage = "fake test message";
	    postInfo.ownerAccount = AccountSetting.getInstance().getCurrentAccount();
	    
		postInfo.setDestinationFolderName(exoTmkFolder);
		postInfo.activityType = SocialPostInfo.TYPE_DOC;
		UploadInfo uploadInfo = new UploadInfo();
		uploadInfo.init(postInfo);
		String uploadUrl = uploadInfo.jcrUrl + "/" + uploadInfo.folder;
		boolean good = ExoDocumentUtils.createFolder(uploadUrl);
		logger.info("creating {} successfully.", exoTmkFolder);
	}

}
