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

import java.util.ArrayList;

import org.exoplatform.model.ExoAccount;
import org.exoplatform.utils.ExoConstants;

/**
 * Used for storing the list of server url and the index of the selected which
 * is used for adding/repairing/deleting function in setting
 */
public class ServerSettingHelper {

  // The application version number
  private String                     applicationVersion;

  // the server version number
  private String                     serverVersion;

  private String                     serverEdition;

  /** List of server url */
  private ArrayList<ExoAccount>      serverInfoList;

  private static ServerSettingHelper helper = new ServerSettingHelper();

  private ServerSettingHelper() {

  }

  public static ServerSettingHelper getInstance() {
    return helper;
  }

  public void setInstance(ServerSettingHelper instance) {
    helper = instance;
  }

  public void setApplicationVersion(String version) {
    applicationVersion = version;
  }

  public String getApplicationVersion() {
    return applicationVersion;
  }

  public void setServerVersion(String ver) {
    serverVersion = ver;
  }

  public String getServerVersion() {
    return serverVersion;
  }

  public void setServerEdition(String ver) {
    serverEdition = ver;
  }

  public String getServerEdition() {
    return serverEdition;
  }

  public void setServerInfoList(ArrayList<ExoAccount> list) {
    serverInfoList = list;
  }

  /**
   * Returns the list of server objects configured in the app.<br/>
   * If the property has not yet been set, the list is retrieved from storage
   * lazily.
   * 
   * @param context
   * @return The list of server objects
   */
  
  /**
   * Checks whether two or more accounts are configured on the app
   * 
   * @param ctx
   * @return true if two or more accounts exist, false otherwise
   */
  

  
  
 


}
