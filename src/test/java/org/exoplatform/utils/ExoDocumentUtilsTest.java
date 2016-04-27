/**
 * 
 */
package org.exoplatform.utils;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.exoplatform.ExoBaseTest;
import org.exoplatform.model.SocialPostInfo;
import org.exoplatform.model.SocialSpaceInfo;
import org.exoplatform.shareextension.service.ShareService.UploadInfo;
import org.exoplatform.singleton.AccountSetting;
import org.exoplatform.singleton.SocialServiceHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author michael
 *
 */
public class ExoDocumentUtilsTest extends ExoBaseTest {

	SocialSpaceInfo ssi;
	SocialPostInfo postInfo;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
		super.setUp();
		ssi = SocialServiceHelper.getInstance().getSpaceByName("电话销售");
		
		postInfo = new SocialPostInfo();
	      postInfo.destinationSpace = ssi;
	      postInfo.postMessage = "fake test message";
	      postInfo.ownerAccount = AccountSetting.getInstance().getCurrentAccount();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateFolder() {
		
		  String folder = "exo-test";
	      postInfo.setDestinationFolderName("exo-test");

	      postInfo.activityType = SocialPostInfo.TYPE_DOC;
	      UploadInfo uploadInfo = new UploadInfo();
	      uploadInfo.init(postInfo);
	      String uploadUrl = uploadInfo.jcrUrl + "/" + uploadInfo.folder;
	      boolean good = ExoDocumentUtils.createFolder(uploadUrl);
	      System.out.println("create " + folder + " good: " + good);
	      

	}
	
	@Test
	public void testCreateFolderTwoLevels() {
		
		  String folder = "level1/level2";
		  
	      postInfo.activityType = SocialPostInfo.TYPE_DOC;
	      UploadInfo uploadInfo = new UploadInfo();
	      postInfo.setDestinationFolderName("level1");
	      uploadInfo.init(postInfo);
	      String uploadUrl = uploadInfo.jcrUrl + "/" + uploadInfo.folder;
	      boolean good = ExoDocumentUtils.createFolder(uploadUrl);
	      System.out.println("create " + folder + " good: " + good);
	      
	      postInfo.setDestinationFolderName(folder);
	      uploadInfo.init(postInfo);
	      uploadUrl = uploadInfo.jcrUrl + "/" + uploadInfo.folder;
	      good = ExoDocumentUtils.createFolder(uploadUrl);
	      System.out.println("create " + folder + " good: " + good);

	}

}
