/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.controller.social;

import org.exoplatform.model.SocialSpaceInfo;
import org.exoplatform.utils.ExoConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComposeMessageController {

	private static final Logger logger = LoggerFactory.getLogger(ComposeMessageController.class);
	private int composeType;

	private PostStatusTask mPostTask;

	private String sdcard_temp_dir = null;

	private String inputTextWarning;

	private String okString;

	private String titleString;

	private String contentString;

	private String sendingData;

	/**
	 * Either null (public) or the space name
	 */
	private SocialSpaceInfo postDestination;

	public ComposeMessageController(int type) {
		composeType = type;
		postDestination = null;
	}

	/**
	 * Set this post's destination.
	 * 
	 * @param destination
	 *            null (public) or the SocialSpaceInfo object
	 */
	public void setPostDestination(SocialSpaceInfo destination) {
		this.postDestination = destination;
	}

	/**
	 * @return This post's destination:<br/>
	 *         - null: public<br/>
	 *         - SocialSpaceInfo: the destination space
	 */
	public SocialSpaceInfo getPostDestination() {
		return this.postDestination;
	}

	/*
	 * Take a photo and store it into /sdcard/eXo/DocumentCache
	 */
	public void initCamera() {
		// sdcard_temp_dir = PhotoUtils.startImageCapture(mComposeActivity);
	}

	/**
	 * 
	 * @param composeMessage
	 * @param sdcard
	 * @param position
	 * @param destinationFolder, for example, the test folder at 
	 *        http://localhost:8080/rest/private/jcr/repository/collaboration/Groups/spaces/dian_hua_xiao_shou/Documents/test
	 */
	public void onSendMessage(String composeMessage, String sdcard, int position, String destinationFolder) {
		if ((composeMessage != null) && (composeMessage.length() > 0)) {

			if (composeType == ExoConstants.COMPOSE_POST_TYPE) {
				logger.info("post to activity stream.");
				onPostTask(composeMessage, sdcard, destinationFolder);
			}else{
				logger.error("Post comments is not supported for now.");
			}
		}

	}

	private void onPostTask(String composeMessage, String sdcard, String destinationFolder) {
		if (mPostTask == null) {
			mPostTask = new PostStatusTask(sdcard, composeMessage, destinationFolder, this);
			mPostTask.execute();
		}
	}

	public String getSdCardTempDir() {
		return sdcard_temp_dir;
	}

	public void onSendMessage(String composeMessage, String sdcard, int position) {
		onSendMessage(composeMessage, sdcard, position, null);
	}

}
