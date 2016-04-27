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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.exoplatform.model.SocialPostInfo;
import org.exoplatform.shareextension.service.Action.ActionListener;
import org.exoplatform.shareextension.service.PostAction;
import org.exoplatform.shareextension.service.PostAction.PostActionListener;
import org.exoplatform.shareextension.service.ShareService;
import org.exoplatform.shareextension.service.ShareService.UploadInfo;
import org.exoplatform.shareextension.service.UploadAction;
import org.exoplatform.singleton.AccountSetting;
import org.exoplatform.utils.ExoConstants;
import org.exoplatform.utils.ExoDocumentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A background task that publishes a status with optional attachment. Uses
 * {@link UploadAction} and {@link PostAction} to not have duplicate activities
 * (MOB-1384). <br/>
 * TODO An even better solution would be to use the {@link ShareService}
 * directly.
 */
public class PostStatusTask{
	
	private static final Logger logger = LoggerFactory.getLogger(PostStatusTask.class);
	
  private String                   sdcard_temp_dir;

  private String                   composeMessage;

  private String                   sendingData;

  private String                   okString;

  private String                   errorString;

  private String                   warningTitle;

  private ComposeMessageController messageController;
  private String destinationFolder; //could be null

  public PostStatusTask(String dir, String content, ComposeMessageController controller) {
    messageController = controller;
    sdcard_temp_dir = dir;
    composeMessage = content;
  }

 

  public PostStatusTask(String sdcard, String composeMessage,
		String destinationFolder,
		ComposeMessageController composeMessageController) {
	  this(sdcard, composeMessage, composeMessageController);
	  this.destinationFolder = destinationFolder;
}



public Integer execute() {

    try {
      SocialPostInfo postInfo = new SocialPostInfo();
      postInfo.destinationSpace = messageController.getPostDestination();
      postInfo.postMessage = composeMessage;
      postInfo.ownerAccount = AccountSetting.getInstance().getCurrentAccount();
      postInfo.setDestinationFolderName(destinationFolder);

      final AtomicBoolean uploaded = new AtomicBoolean(false);
      // If the post contains an attached image
      if (sdcard_temp_dir != null) {
        postInfo.activityType = SocialPostInfo.TYPE_DOC;
        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.init(postInfo);
        String uploadUrl = uploadInfo.jcrUrl + "/" + uploadInfo.folder;

        // Create destination folder
        if (ExoDocumentUtils.createFolder(uploadUrl)) {

          // Upload file
          File file = new File(sdcard_temp_dir);
          if (file != null) {
            String uploadedFileName = file.getName();
              uploadInfo.fileToUpload = ExoDocumentUtils.documentFromFileUri(file.toURI());
              uploadInfo.uploadId = Long.toHexString(System.currentTimeMillis());
              if (uploadInfo.fileToUpload != null) {
                uploadInfo.fileToUpload.documentName = uploadedFileName;
               // uploadInfo.fileToUpload.cleanupFilename(mContext);
                // UploadAction.execute is synchronous
                UploadAction.execute(postInfo, uploadInfo, new ActionListener() {

                  @Override
                  public boolean onSuccess(String message) {
                    uploaded.set(true);
                    return true;
                  }

                  @Override
                  public boolean onError(String error) {
                    uploaded.set(false);
                    logger.error("upload error: {}", error);
                    return false;
                  }
                });
                if (uploadInfo.fileToUpload.documentData != null) {
                  try {
                    uploadInfo.fileToUpload.documentData.close();
                  } catch (IOException e) {
                  //  Log.d(getClass().getSimpleName(), Log.getStackTraceString(e));
                	  logger.error("close file stream error", e);
                  }
                }
               // tempFile.delete();
                if (uploaded.get()) {
                  uploadedFileName = uploadInfo.fileToUpload.documentName;
                }
              }
            
            // build post param
            postInfo.buildTemplateParams(uploadInfo);
          }
        }
      } //sending file to server

      final AtomicBoolean posted = new AtomicBoolean(false);
      if ((sdcard_temp_dir == null) || (sdcard_temp_dir != null && uploaded.get())) {
        // PostAction.execute is synchronous
        PostAction.execute(postInfo, new PostActionListener() {

          @Override
          public boolean onSuccess(String message) {
            posted.set(true);
            return true;
          }

          @Override
          public boolean onError(String error) {
        	logger.error("Post Action error: {}", error);
            posted.set(false);
            return false;
          }
        });
      }
      if (posted.get())
        return 1;
      else
        return 0;
    } catch (RuntimeException e) {
      // Cannot replace because SocialClientLib can throw exceptions like
      // ServerException, UnsupportMethod, etc
    	logger.error("runtime execption catched: ", e);
      return -2;
    }

  }

  public void onPostExecute(Integer result) {
    if (result == 1) {

    } else {
    }

  }

}
