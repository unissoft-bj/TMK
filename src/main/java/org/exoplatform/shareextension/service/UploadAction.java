/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
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
package org.exoplatform.shareextension.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.exoplatform.controller.social.ComposeMessageController;
import org.exoplatform.model.SocialPostInfo;
import org.exoplatform.shareextension.service.ShareService.UploadInfo;
import org.exoplatform.singleton.DocumentHelper;
import org.exoplatform.utils.ExoConnectionUtils;
import org.exoplatform.utils.ExoConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by The eXo Platform SAS<br/>
 * An Action for uploading a file to Platform. Uses the upload service of ECMS.
 * 
 * @author Philippe Aristote paristote@exoplatform.com
 * @since Jun 17, 2015
 */
public class UploadAction extends Action {
  private static final Logger logger = LoggerFactory.getLogger(UploadAction.class);
  private UploadInfo uploadInfo;

  @Override
  protected void check() {
    if (uploadInfo == null)
      throw new IllegalArgumentException("Cannot pass null as the UploadInfo argument");
    super.check();
  }

  /**
   * create and execute upload action, wait for result
   * 
   * @param post
   * @param upload
   * @param listener
   * @return
   */
  public static boolean execute(SocialPostInfo post, UploadInfo upload, ActionListener listener) {
    UploadAction action = new UploadAction();
    action.postInfo = post;
    action.uploadInfo = upload;
    action.listener = listener;
    return action.execute();
  }

  @Override
  protected boolean doExecute() {
    String id = uploadInfo.uploadId;
    String boundary = "----------------------------" + id;
    String CRLF = "\r\n";
    int status = -1;
    OutputStream output = null;
    PrintWriter writer = null;
    try {
      if (postInfo == null || postInfo.ownerAccount == null)
        throw new IOException(new StringBuilder("Input parameter null info=").append(postInfo).toString());
      // Open a connection to the upload web service
      StringBuffer stringUrl = new StringBuffer(postInfo.ownerAccount.serverUrl).append("/portal")
                                                                                .append(ExoConstants.DOCUMENT_UPLOAD_PATH_REST)
                                                                                .append("?uploadId=")
                                                                                .append(id);
      URL uploadUrl = new URL(stringUrl.toString());
      // TODO need check case https connection ?
      HttpURLConnection uploadReq = (HttpURLConnection) uploadUrl.openConnection();
      uploadReq.setDoOutput(true);
      uploadReq.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
      // Pass the session cookies for authentication
      CookieStore store = ExoConnectionUtils.cookiesStore;
      if (store != null) {
        StringBuffer cookieString = new StringBuffer();
        for (Cookie cookie : store.getCookies()) {
          cookieString.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
        }
        uploadReq.addRequestProperty("Cookie", cookieString.toString());
      }
      ExoConnectionUtils.setUserAgent(uploadReq);
      // Write the form data
      output = uploadReq.getOutputStream();
      writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true);
      writer.append("--").append(boundary).append(CRLF);
      writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
            .append(uploadInfo.fileToUpload.documentName)
            .append("\"")
            .append(CRLF);
      writer.append("Content-Type: ").append(uploadInfo.fileToUpload.documentMimeType).append(CRLF);
      writer.append(CRLF).flush();
      byte[] buf = new byte[1024];
      while (uploadInfo.fileToUpload.documentData.read(buf) != -1) {
        output.write(buf);
      }
      output.flush();
      writer.append(CRLF).flush();
      writer.append("--").append(boundary).append("--").append(CRLF).flush();
      // Execute the connection and retrieve the status code
      status = uploadReq.getResponseCode();
    } catch (UnsupportedEncodingException e) {
    //  Log.e(LOG_TAG, "Error while uploading ", uploadInfo.fileToUpload, Log.getStackTraceString(e));
    	logger.error("", e);
    } catch (IOException e) {
   //   Log.e(LOG_TAG, "Error while uploading ", uploadInfo.fileToUpload, Log.getStackTraceString(e));
    	logger.error("", e);
    } finally {
      if (uploadInfo != null && uploadInfo.fileToUpload != null && uploadInfo.fileToUpload.documentData != null)
        try {
          uploadInfo.fileToUpload.documentData.close();
        } catch (IOException e1) {
    //      Log.e(LOG_TAG, "Error while closing the upload stream", e1);
        	logger.error("", e1);
        }
      if (output != null)
        try {
          output.close();
        } catch (IOException e) {
         // Log.e(LOG_TAG, "Error while closing the connection", e);
        	logger.error("", e);
        }
      if (writer != null)
        writer.close();
    }
    if (status < HttpURLConnection.HTTP_OK || status >= HttpURLConnection.HTTP_MULT_CHOICE) {
      // Exit if the upload went wrong
    	logger.error("upload file http status code {}", status);
      return listener.onError(String.format("Could not upload the file %s", uploadInfo.fileToUpload.documentName));
    }
    status = -1;
    try

    {
      if (postInfo == null || postInfo.ownerAccount == null)
        throw new IOException(new StringBuilder("Input parameter null info=").append(postInfo).toString());
      // Prepare the request to save the file in JCR
      String stringUrl = new StringBuilder(postInfo.ownerAccount.serverUrl).append("/portal")
                                                                           .append(ExoConstants.DOCUMENT_CONTROL_PATH_REST)
                                                                           .toString();
      logger.debug("upload url: {}", stringUrl);
      /*
      URI moveUri = new URI(stringUrl);
      moveUri = moveUri.buildUpon()
                       .appendQueryParameter("uploadId", id)
                       .appendQueryParameter("action", "save")
                       .appendQueryParameter("workspaceName", DocumentHelper.getInstance().workspace)
                       .appendQueryParameter("driveName", uploadInfo.drive)
                       .appendQueryParameter("currentFolder", uploadInfo.folder)
                       .appendQueryParameter("fileName", uploadInfo.fileToUpload.documentName)
                       .build();
                       */
      URIBuilder ub = new URIBuilder(stringUrl);
      ub
      .addParameter("uploadId", id)
      .addParameter("action", "save")
      .addParameter("workspaceName", DocumentHelper.getInstance().workspace)
      .addParameter("driveName", uploadInfo.drive)
      .addParameter("currentFolder", uploadInfo.folder)
      .addParameter("fileName", uploadInfo.fileToUpload.documentName);
      
      HttpGet moveReq = new HttpGet(ub.build());
      // Execute the request and retrieve the status code
      HttpResponse move = ExoConnectionUtils.httpClient.execute(moveReq);
      status = move.getStatusLine().getStatusCode();
    } catch ( ClientProtocolException e) {
 //     Log.e(LOG_TAG, "Error while saving ", uploadInfo.fileToUpload, " in JCR", Log.getStackTraceString(e));
    	logger.error("", e);
    } catch (IOException e) {
   //   Log.e(LOG_TAG, "Error while saving ", uploadInfo.fileToUpload, " in JCR", Log.getStackTraceString(e));
    	logger.error("", e);
    } catch (Exception e) {
      // XXX can not remove because Uri.parse can throw runtime exception.
   //   Log.e(LOG_TAG, "Error while saving ", uploadInfo.fileToUpload, " in JCR", Log.getStackTraceString(e));
    	logger.error("", e);
    }

    boolean ret = false;
    if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
      ret = listener.onSuccess(String.format("File %s uploaded successfully", uploadInfo.fileToUpload.documentName));
    } else {
      ret = listener.onError(String.format("Could not save the file %s", uploadInfo.fileToUpload.documentName));
    }
    return ret;
  }

}
