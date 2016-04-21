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
package org.exoplatform.singleton;

import org.exoplatform.model.ExoFile;

/**
 * Created by The eXo Platform SAS Author : eXoPlatform exo@exoplatform.com Jan
 * 31, 2012
 */
public class DocumentHelper {

  private static DocumentHelper documentHelper = new DocumentHelper();

  private String                _urlrepositoryHome;

  public ExoFile                _fileCopied    = new ExoFile();

  public ExoFile                _fileMoved     = new ExoFile();

  public String                 repository     = null;

  public String                 workspace      = null;

  /**
   * The dictionary for mapping a folder with its children<br/>
   * Folder ExoFile.path (key) => List of children ExoFile (val)
   */
  //public Bundle                 folderToChildrenMap;

  /**
   * The dictionary for mapping a folder with its parent<br/>
   * Folder ExoFile.path (key) => Parent ExoFile (val)
   */

  //public Bundle                 folderToParentMap;

  private DocumentHelper() {

  }

  public static DocumentHelper getInstance() {
    return documentHelper;
  }

  public void setInstance(DocumentHelper helper) {
    documentHelper = helper;
  }

  public void setRepositoryHomeUrl(String url) {
    _urlrepositoryHome = url;
  }

  public String getRepositoryHomeUrl() {
    return _urlrepositoryHome;
  }


  
/*
  private void readFromParcel(Parcel in) {
    _urlrepositoryHome = in.readString();
    _fileCopied = in.readParcelable(_fileCopied.getClass().getClassLoader());
    _fileMoved = in.readParcelable(_fileMoved.getClass().getClassLoader());
    repository = in.readString();
  //  folderToParentMap = in.readBundle();
    folderToChildrenMap = in.readBundle();
  }
  */

    
}
