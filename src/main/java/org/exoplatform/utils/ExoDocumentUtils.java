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
package org.exoplatform.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.exoplatform.model.ExoFile;
import org.exoplatform.singleton.AccountSetting;
import org.exoplatform.singleton.DocumentHelper;

import android.webkit.MimeTypeMap;

public class ExoDocumentUtils {

	private static final Logger logger = LoggerFactory.getLogger(ExoDocumentUtils.class);
  private static final String  LOG_TAG              = ExoDocumentUtils.class.getName();

  public static final String   ALL_VIDEO_TYPE       = "video/*";

  public static final String   ALL_AUDIO_TYPE       = "audio/*";

  public static final String   ALL_IMAGE_TYPE       = "image/*";

  public static final String   ALL_TEXT_TYPE        = "text/*";

  public static final String   IMAGE_TYPE           = "image";

  public static final String   TEXT_TYPE            = "text";

  public static final String   VIDEO_TYPE           = "video";

  public static final String   AUDIO_TYPE           = "audio";

  public static final String   MSWORD_TYPE          = "application/msword";

  public static final String   OPEN_WORD_TYPE       = "application/vnd.oasis.opendocument.text";

  public static final String   PDF_TYPE             = "application/pdf";

  public static final String   XLS_TYPE             = "application/xls";

  public static final String   OPEN_XLS_TYPE        = "application/vnd.oasis.opendocument.spreadsheet";

  public static final String   POWERPOINT_TYPE      = "application/vnd.ms-powerpoint";

  public static final String   OPEN_POWERPOINT_TYPE = "application/vnd.oasis.opendocument.presentation";

  public static final String[] FORBIDDEN_TYPES      = new String[] { "application/octet-stream" };


  public static void setRepositoryHomeUrl(String userName, String userHomeNodePath, String domain) {
	    String documentPath = getDocumenPath();
	    StringBuilder buffer = new StringBuilder();
	    buffer.append(domain);
	    buffer.append(documentPath);
	    buffer.append(userHomeNodePath);

	    try {
	      WebdavMethod copy = new WebdavMethod("HEAD", buffer.toString());
	      int status = ExoConnectionUtils.httpClient.execute(copy).getStatusLine().getStatusCode();

	      if (status >= 200 && status < 300) {
	        DocumentHelper.getInstance().setRepositoryHomeUrl(buffer.toString());
	      } else {
	        buffer = new StringBuilder(domain);
	        buffer.append(documentPath);
	        buffer.append("/");
	        buffer.append(userName);
	        DocumentHelper.getInstance().setRepositoryHomeUrl(buffer.toString());
	      }

	    } catch (Exception e) {
	      // XXX cannot replace because WebdavMethod, httpclient.execute can throw
	      // exception
	  //    Log.e(LOG_TAG, e.getMessage(), Log.getStackTraceString(e));
	      logger.error("error setting repo home url: ", e);
	      DocumentHelper.getInstance().setRepositoryHomeUrl(null);
	    }
	  }
 
  /*
   * Display file size by string decimal format
   */
  public static String getFileSize(long fileSize) {
    int freeUnit;
    for (freeUnit = 0; fileSize >= 100; freeUnit++) {
      fileSize /= 1024;
    }
    DecimalFormat decFormat = new DecimalFormat("0.0");
    String doubleString = decFormat.format(fileSize);
    StringBuffer buffer = new StringBuffer();
    buffer.append(doubleString);
    switch (freeUnit) {
    case 0:
      buffer.append("B");
      break;
    case 1:
      buffer.append("KB");
      break;
    case 2:
      buffer.append("MB");
      break;
    case 3:
      buffer.append("GB");
      break;
    case 4:
      buffer.append("TB");
      break;
    default:
      buffer.append("err");
      break;
    }
    return buffer.toString();
  }

 
  /**
   * Check whether the given Mime Type is forbidden. The list of forbidden types
   * is in {@link ExoDocumentUtils#FORBIDDEN_TYPES}
   * 
   * @param mimeType
   * @return true if the given Mime Type is in the list
   */
  public static boolean isForbidden(String mimeType) {
    return Arrays.asList(FORBIDDEN_TYPES).contains(mimeType);
  }


  public static String getFullFileType(String fileType) {
    String docFileType = fileType;
    if (fileType.startsWith(ExoDocumentUtils.AUDIO_TYPE)) {
      docFileType = ExoDocumentUtils.ALL_AUDIO_TYPE;
    } else if (fileType.startsWith(ExoDocumentUtils.VIDEO_TYPE)) {
      docFileType = ExoDocumentUtils.ALL_VIDEO_TYPE;
    } else if (fileType.startsWith(ExoDocumentUtils.IMAGE_TYPE)) {
      docFileType = ExoDocumentUtils.ALL_IMAGE_TYPE;
    } else if (fileType.startsWith(ExoDocumentUtils.TEXT_TYPE)) {
      docFileType = ExoDocumentUtils.ALL_TEXT_TYPE;
    }
    return docFileType;
  }

  /**
   * Returns the mimetype of the document located at the given URL.<br/>
   * Returns null if the mimetype is unknown (e.g. the URL points to a page).
   *
   * @param url the URL to check
   * @return
   */
  public static String mimeTypeFromUrl(String url) {
    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
    if (extension != null) {
      return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase(Locale.US));
    }
    return null;
  }

  

 
  /**
   * Get the content (files and folders) of the given folder.
   * 
   * @param context
   * @param file the folder to get content from
   * @return an ExoFile corresponding to the parent folder with its children
   *         ExoFile
   * @throws IOException
   */
  //public static ExoFile getPersonalDriveContent(ExoFile file) throws IOException {


  public static String fullURLofFile(String workSpaceName, String url) {
    String domain = AccountSetting.getInstance().getDomainName();
    StringBuffer buffer = new StringBuffer(domain);
    buffer.append(ExoConstants.DOCUMENT_JCR_PATH);
    buffer.append("/");
    buffer.append(DocumentHelper.getInstance().repository);
    buffer.append("/");
    buffer.append(workSpaceName);
    buffer.append(url);
    return buffer.toString();

  }

  private static String getDocumenPath() {
    StringBuilder documentPath = new StringBuilder();
    documentPath.append(ExoConstants.DOCUMENT_JCR_PATH);
    documentPath.append("/");
    documentPath.append(DocumentHelper.getInstance().repository);
    documentPath.append("/");
    documentPath.append(ExoConstants.DOCUMENT_COLLABORATION);
    return documentPath.toString();
  }

  /**
   * Get the list of folders in a drive, from the HTTP response.<br/>
   * The response's body is an XM document that is parsed to extract the
   * information of the folders. <br/>
   * This method simply calls
   * 
   * <pre>
   * ExoDocumentUtils.getDrives(response, false);
   * </pre>
   * 
   * @param response the HttpResponse from where to extract the list of folders
   * @return an ArrayList of ExoFile or an empty ArrayList if a problem happens
   * @see ExoDocumentUtils#getDrives(HttpResponse response, boolean
   *      isGroupDrive)
   */
  public static ArrayList<ExoFile> getDrives(HttpResponse response) {
    return getDrives(response, false);
  }

  
  /**
   * Get the list of folders in a drive, from the HTTP response.<br/>
   * The response's body is an XM document that is parsed to extract the
   * information of the folders. <br/>
   * If <i>isGroupDrive = true</i> , each folder's name is improved to be less
   * technical, by calling <i>ExoFile#createNaturalName()</i>
   * 
   * @param response the HttpResponse from where to extract the list of folders
   * @param isGroupDrive if <i>true</i> the file's natural name will be created
   * @return an ArrayList of ExoFile or an empty ArrayList if a problem happens
   */
  public static ArrayList<ExoFile> getDrives(HttpResponse response, boolean isGroupDrive) {
    // Initialize the blogEntries MutableArray that we declared in the
    // header
    ArrayList<ExoFile> folderArray = new ArrayList<ExoFile>();

    try {
      Document obj_doc = null;
      DocumentBuilderFactory doc_build_fact = null;
      DocumentBuilder doc_builder = null;

      doc_build_fact = DocumentBuilderFactory.newInstance();
      doc_builder = doc_build_fact.newDocumentBuilder();
      InputStream is = ExoConnectionUtils.sendRequest(response);
      if (is != null) {
        obj_doc = doc_builder.parse(is);

        NodeList obj_nod_list = null;
        if (null != obj_doc) {
          obj_nod_list = obj_doc.getElementsByTagName("Folder");

          for (int i = 0; i < obj_nod_list.getLength(); i++) {
            Node itemNode = obj_nod_list.item(i);
            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
              Element itemElement = (Element) itemNode;
              ExoFile file = new ExoFile();
              file.name = itemElement.getAttribute("name");
              // if (Config.GD_INFO_LOGS_ENABLED)
        //      Log.i(" Public file name", file.name);
              file.workspaceName = itemElement.getAttribute("workspaceName");
              file.driveName = file.name;
              file.currentFolder = itemElement.getAttribute("currentFolder");
              if (file.currentFolder == null)
                file.currentFolder = "";
              file.isFolder = true;
              // create the folder's natural name only for folders
              // in the group drive
              if (isGroupDrive)
                file.createNaturalName();
              /*
               * If file name is "Public", get path for it.
               */
              if (file.name.equals("Public")) {
                file.path = getRootDriverPath(file);
              }

              folderArray.add(file);
            }
          }
        }
      }
    } catch (ParserConfigurationException e) {
    //  Log.e(" ParserConfigurationException ", e.getMessage());
    	logger.error("parser configuration error", e);
      folderArray = null;
    } catch (SAXException e) {
      //Log.e(" SAXException ", e.getMessage());
    	logger.error("", e);
      folderArray = null;
    } catch (IOException e) {
      //Log.e(" IOException ", e.getMessage());
    	logger.error("", e);
      folderArray = null;
    }

    return folderArray;
  }

 
//get path for driver folder (ex. Public/Private)
 private static String getRootDriverPath(ExoFile file) {
   String path = null;
   String urlStr = getDriverUrl(file);
   urlStr = ExoUtils.encodeDocumentUrl(urlStr);
   Document obj_doc = null;
   DocumentBuilderFactory doc_build_fact = null;
   DocumentBuilder doc_builder = null;
   try {
     HttpResponse response = ExoConnectionUtils.getRequestResponse(urlStr);
     doc_build_fact = DocumentBuilderFactory.newInstance();
     doc_builder = doc_build_fact.newDocumentBuilder();
     InputStream is = ExoConnectionUtils.sendRequest(response);
     if (is != null) {
       obj_doc = doc_builder.parse(is);

       if (null != obj_doc) {

         // Get folders
         NodeList obj_nod_list = obj_doc.getElementsByTagName("Folder");
         Node rootNode = obj_nod_list.item(0);
         if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
           Element itemElement = (Element) rootNode;
           path = fullURLofFile(ExoConstants.DOCUMENT_COLLABORATION, itemElement.getAttribute("path"));
         }
       }
     }
     return path;
   } catch (ParserConfigurationException e) {
     return null;
   } catch (SAXException e) {
     return null;
   } catch (IOException e) {
     return null;
   }
 }

  // return the driver url
  private static String getDriverUrl(ExoFile file) {
    String domain = AccountSetting.getInstance().getDomainName();
    StringBuffer buffer = new StringBuffer(domain);
    buffer.append(ExoConstants.DOCUMENT_FILE_PATH_REST);
    buffer.append(file.driveName);
    buffer.append(ExoConstants.DOCUMENT_WORKSPACE_NAME);
    buffer.append(file.workspaceName);
    buffer.append(ExoConstants.DOCUMENT_CURRENT_FOLDER);
    buffer.append(file.currentFolder);

    return buffer.toString();
  }

  /**
   * Get a folder with its sub-files and sub-folders.<br/>
   * Parse the XML response with format:
   * 
   * <pre>
   * &lt;Folder canAddChild="bool" canRemove="bool" currentFolder="Name" driveName="DriveName" hasChild="bool" name="Name" nodeType="nt" path="..." title="Title" titlePath="Title" workspaceName="Name">
   *  &lt;Folders>
   *    &lt;Folder canAddChild="bool" canRemove="bool" currentFolder="Name" driveName="DriveName" hasChild="bool" name="Name" nodeType="nt" path="..." title="Title" titlePath="Title" workspaceName="Name"/>
   *  &lt;/Folders>
   *  &lt;Files>
   *    &lt;File canRemove="bool" creator="username" dateCreated="Date" dateModified="Date" name="doc.jpg" nodeType="nt" path="..." size="0" title="doc.jpg" workspaceName="Name"/>
   *  &lt;/Files>
   * &lt;/Folder>
   * </pre>
   * 
   * Example URL:
   * 
   * <pre>
   * https://SERVER/rest/managedocument/getFoldersAndFiles?driveName=Personal%
   * 20Documents&workspaceName=collaboration&currentFolder=Public
   * </pre>
   * 
   * @param response the response that contains the XML entity
   * @param file The folder to retrieve the content from
   * @return an ExoFile that represents the content of the given folder
   */
 

  /*
   * Get document icon from content type
   */

 
  public static String getParentUrl(String url) {

    int index = url.lastIndexOf("/");
    if (index > 0)
      return url.substring(0, index);

    return "";
  }

  /**
   * Get the last path part of the given URL.<br/>
   * Example:
   * <ul>
   * <li>URL = http://my.server.com/path/to/file.png</li>
   * <li>Returns file.png</li>
   * </ul>
   * 
   * @param url
   * @return
   */
  public static String getLastPathComponent(String url) {

    int index = url.lastIndexOf("/");
    if (index > 0)
      return url.substring(url.lastIndexOf("/") + 1, url.length());

    return url;

  }

  public static boolean isContainSpecialChar(String str, String charSet) {

    Pattern patt = Pattern.compile(charSet);
    Matcher matcher = patt.matcher(str);
    return matcher.find();
  }

  
  public static boolean createFolder(String destination) {
	    logger.info("create folder: {}", destination);
	    HttpResponse response;
	    try {

	      destination = ExoUtils.encodeDocumentUrl(destination);
	      WebdavMethod create = new WebdavMethod("HEAD", destination);
	      response = ExoConnectionUtils.httpClient.execute(create);
	      int status = response.getStatusLine().getStatusCode();
	      logger.debug("http response status: {}", status);
	      if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
	        return true;
	      } else {
	        create = new WebdavMethod("MKCOL", destination);
	        response = ExoConnectionUtils.httpClient.execute(create);
	        status = response.getStatusLine().getStatusCode();

	        return status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES;
	      }

	    } catch (Exception e) {
	      // XXX catch null of destination, WebdavMethod initial, httpclient
	      // exception
	//      Log.e(LOG_TAG, e.getMessage(), e);
	    	logger.error("", e);
	      return false;
	    }
	  }
 

  

  /**
   * Gets a DocumentInfo with info coming from the document at the given URI.
   * 
   * @param contentUri the content URI of the document (content:// ...)
   * @param context
   * @return a DocumentInfo or null if an error occurs
   */
  public static DocumentInfo documentFromContentUri(URI contentUri) {
    if (contentUri == null)
      return null;

    try {
     // ContentResolver cr = context.getContentResolver();
     // Cursor c = cr.query(contentUri, null, null, null, null);
    //  int sizeIndex = c.getColumnIndex(OpenableColumns.SIZE);
    //  int nameIndex = c.getColumnIndex(OpenableColumns.DISPLAY_NAME);
     // int orientIndex = c.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION);
     // c.moveToFirst();

      DocumentInfo document = new DocumentInfo();
    //  document.documentName = c.getString(nameIndex);
      //document.documentSizeKb = c.getLong(sizeIndex) / 1024;
      //document.documentData = cr.openInputStream(contentUri);
      //document.documentMimeType = cr.getType(contentUri);
    //  if (orientIndex != -1) { // if found orientation column
    //    document.orientationAngle = c.getInt(orientIndex);
   //   }
      //c.close();
      return document;
    } catch (Exception e) {
      //Log.e(LOG_TAG, "Cannot retrieve the content at " + contentUri);
      //Log.d(LOG_TAG, e.getMessage() + "\n" + Log.getStackTraceString(e));
    }
    return null;
  }

  /**
   * Gets a DocumentInfo with info coming from the file at the given URI.
   * 
   * @param fileUri the file URI (file:// ...)
   * @return a DocumentInfo or null if an error occurs
   */
  public static DocumentInfo documentFromFileUri(URI fileUri) {
    if (fileUri == null)
      return null;

    try {
      URI uri = new URI(fileUri.toString());
      File file = new File(uri);

      DocumentInfo document = new DocumentInfo();
      document.documentName = file.getName();
      document.documentSizeKb = file.length() / 1024;
      document.documentData = new FileInputStream(file);
      // Guess the mime type in 2 ways
      try {
        // 1) by inspecting the file's first bytes
        document.documentMimeType = URLConnection.guessContentTypeFromStream(document.documentData);
      } catch (IOException e) {
        document.documentMimeType = null;
      }
      if (document.documentMimeType == null) {
        // 2) if it fails, by stripping the extension of the filename
        // and getting the mime type from it
        String extension = "";
        int dotPos = document.documentName.lastIndexOf('.');
        if (0 <= dotPos)
          extension = document.documentName.substring(dotPos + 1);
        document.documentMimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
      }
      // Get the orientation angle from the EXIF properties
      if ("image/jpeg".equals(document.documentMimeType)){
    	//document.orientationAngle = getExifOrientationAngleFromFile(file.getAbsolutePath());
      }
        
      return document;
    } catch (URISyntaxException e) {
      //Log.e(LOG_TAG, "Cannot retrieve the file at " + fileUri);
      //Log.d(LOG_TAG, e.getMessage() + "\n" + Log.getStackTraceString(e));
    	logger.error("", e);
    } catch (FileNotFoundException e) {
      //Log.e(LOG_TAG, "Cannot retrieve the file at " + fileUri);
      //Log.d(LOG_TAG, e.getMessage() + "\n" + Log.getStackTraceString(e));
    	logger.error("", e);
    }
    return null;
  }

  /**
   * Delete the Files at the given paths
   * 
   * @param files a list of file paths
   * @return true if all files were deleted, false otherwise
   */
  public static boolean deleteLocalFiles(List<String> files) {
    boolean result = true;
    if (files != null) {
      for (String filePath : files) {
        File f = new File(filePath);
        boolean del = f.delete();
        result &= del;
      }
    }
    return result;
  }

  /**
   * On Platform 4.1-M2, the upload service renames the uploaded file. Therefore
   * the link to this file in the activity becomes incorrect. To fix this, we
   * rename the file before upload so the same name is used in the activity.
   * 
   * @param originalName the name to clean
   * @return a String without forbidden characters
   */
  public static String cleanupFilename(String originalName) {
    final String TILDE_HYPHENS_COLONS_SPACES = "[~_:\\s]";
    final String MULTIPLE_HYPHENS = "-{2,}";
    final String FORBIDDEN_CHARS = "[`!@#\\$%\\^&\\*\\|;\"'<>/\\\\\\[\\]\\{\\}\\(\\)\\?,=\\+\\.]+";
    String name = originalName;
    String ext = "";
    int lastDot = name.lastIndexOf('.');
    if (lastDot > 0 && lastDot < name.length()) {
      ext = name.substring(lastDot); // the ext with the dot
      name = name.substring(0, lastDot); // the name before the ext
    }
    // [~_:\s] Replaces ~ _ : and spaces by -
    name = Pattern.compile(TILDE_HYPHENS_COLONS_SPACES).matcher(name).replaceAll("-");
    // [`!@#\$%\^&\*\|;"'<>/\\\[\]\{\}\(\)\?,=\+\.]+ Deletes forbidden chars
    name = Pattern.compile(FORBIDDEN_CHARS).matcher(name).replaceAll("");
    // Converts accents to regular letters
    name = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    // Replaces upper case characters by lower case
    // Locale loc = new
    // Locale(SettingUtils.getPrefsLanguage(getApplicationContext()));
    name = name.toLowerCase(Locale.getDefault());
    // Remove consecutive -
    name = Pattern.compile(MULTIPLE_HYPHENS).matcher(name).replaceAll("-");
    // Save
    return (name + ext);
  }

  public static final int ROTATION_0   = 0;

  public static final int ROTATION_90  = 90;

  public static final int ROTATION_180 = 180;

  public static final int ROTATION_270 = 270;

     

  public static class DocumentInfo {

    public String      documentName;

    public long        documentSizeKb;

    public InputStream documentData;

    public String      documentMimeType;

    public int         orientationAngle = ROTATION_0;

    @Override
    public String toString() {
      return String.format(Locale.US, "File %s [%s - %s KB]", documentName, documentMimeType, documentSizeKb);
    }

    public void closeDocStream() {
      if (documentData != null)
        try {
          documentData.close();
        } catch (IOException e) {
        }
    }

    /**
     * On Platform 4.1-M2, the upload service renames the uploaded file.
     * Therefore the link to this file in the activity becomes incorrect. To fix
     * this, we rename the file before upload so the same name is used in the
     * activity.
     */
    public void cleanupFilename() {
      final String TILDE_HYPHENS_COLONS_SPACES = "[~_:\\s]";
      final String MULTIPLE_HYPHENS = "-{2,}";
      final String FORBIDDEN_CHARS = "[`!@#\\$%\\^&\\*\\|;\"'<>/\\\\\\[\\]\\{\\}\\(\\)\\?,=\\+\\.]+";
      String name = documentName;
      String ext = "";
      int lastDot = name.lastIndexOf('.');
      if (lastDot > 0 && lastDot < name.length()) {
        ext = name.substring(lastDot); // the ext with the dot
        name = name.substring(0, lastDot); // the name before the ext
      }
      // [~_:\s] Replaces ~ _ : and spaces by -
      name = Pattern.compile(TILDE_HYPHENS_COLONS_SPACES).matcher(name).replaceAll("-");
      // [`!@#\$%\^&\*\|;"'<>/\\\[\]\{\}\(\)\?,=\+\.]+ Deletes forbidden chars
      name = Pattern.compile(FORBIDDEN_CHARS).matcher(name).replaceAll("");
      // Converts accents to regular letters
      name = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
      // Replaces upper case characters by lower case
      Locale loc = Locale.US;
      name = name.toLowerCase(loc == null ? Locale.getDefault() : loc);
      // Remove consecutive -
      name = Pattern.compile(MULTIPLE_HYPHENS).matcher(name).replaceAll("-");
      // Save
      documentName = name + ext;
    }
  }
}
