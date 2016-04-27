package org.exoplatform.controller.social;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.exoplatform.model.SocialSpaceInfo;

import org.exoplatform.ExoBaseTest;
import org.exoplatform.singleton.SocialServiceHelper;
import org.exoplatform.social.client.api.model.RestSpace;
import org.junit.Test;

public class ComposeMessageControllerTest extends ExoBaseTest {

	@Test
	public void testOnSendMessage() {
		ComposeMessageController controller = new ComposeMessageController(0);  //0 -- post
		controller.onSendMessage("new post from android", null, -1);
		//fail("Not yet implemented");
	}
	
	//send file to default folder
	@Test
	public void testOnSendMessageWithFile() {
		SocialSpaceInfo ssi = SocialServiceHelper.getInstance().getSpaceByName("电话销售");
		
		ComposeMessageController controller = new ComposeMessageController(0);  //0 -- post
		controller.setPostDestination(ssi);
		//controller.onSendMessage("new post from android", "/home/michael/Downloads/Frozen-5098.mp3", -1);
		controller.onSendMessage("new post from android", "/home/michael/Downloads/goodbye.mp3", -1);
		//fail("Not yet implemented");
	}
	
	/**
	 * need space "电话销售" and there is a folder named dysoft in the space
	 */
	@Test
	public void testOnSendMessageWithFileAndFolder() {
		SocialSpaceInfo ssi = SocialServiceHelper.getInstance().getSpaceByName("电话销售");
		
		ComposeMessageController controller = new ComposeMessageController(0);  //0 -- post
		controller.setPostDestination(ssi);
		//controller.onSendMessage("new post from android", "/home/michael/Downloads/Frozen-5098.mp3", -1);
		controller.onSendMessage("new post from android", "/home/michael/Downloads/goodbye.mp3", -1, "dysoft/2016-04-28");
		//fail("Not yet implemented");
	}
	
	/**
	 * test to get info of a space in exo
	 */
	@Test
	public void testGetSpace(){
		 SocialSpaceInfo ssi = SocialServiceHelper.getInstance().getSpaceByName("电话销售");
	        if(ssi != null){
	        	System.out.println(ssi.displayName);
		    	 System.out.println(ssi.groupId);
		    	 System.out.println(ssi.id);
	        }else{
	        	System.out.println("no space found!");
	        }
	    	 
	    	 
	     
	}

}
