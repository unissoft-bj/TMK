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
package org.exoplatform.model;


/**
 * Describe an Account configuration, containing the URL to connect to the
 * server and credentials. A server object information is identified by the
 * combination: server name - server url - username
 */
public class ExoAccount {

  /** Describe the server; if nothing specified, use authority of url */
  public String  accountName;

  /** url of server, contains the protocol (http:// ) */
  public String  serverUrl;

  /** username */
  public String  username;

  /** unencrypted password */
  public String  password;

  public String  avatarUrl;

  public String  userFullName;

  public long    lastLoginDate;

  /**
   * Whether remember me is enabled on this credential by default remember me is
   * set to true
   */
  public boolean isRememberEnabled;

  /** Whether autologin is enabled */
  public boolean isAutoLoginEnabled;

  public ExoAccount() {
    accountName = "";
    serverUrl = "";
    username = "";
    password = "";
    isRememberEnabled = false;
    isAutoLoginEnabled = false;
    userFullName = "";
    avatarUrl = "";
    lastLoginDate = -1;
  }

  
  /**
   * Compares this ServerObjInfo with the one given.<br/>
   * Returns true if server name and server URL and username are identical.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ExoAccount))
      return false;
    ExoAccount _server = (ExoAccount) obj;
    return _server.accountName.equals(accountName) && _server.serverUrl.equals(serverUrl) && _server.username.equals(username);
  }

  /** clones this instance */
  public ExoAccount clone() {
    ExoAccount newAccount = new ExoAccount();
    newAccount.serverUrl = serverUrl;
    newAccount.accountName = accountName;
    newAccount.username = username;
    newAccount.password = password;
    newAccount.isAutoLoginEnabled = isAutoLoginEnabled;
    newAccount.isRememberEnabled = isRememberEnabled;
    newAccount.userFullName = userFullName;
    newAccount.avatarUrl = avatarUrl;
    newAccount.lastLoginDate = lastLoginDate;
    return newAccount;
  }

  @Override
  public int hashCode() {
    return (serverUrl + username).hashCode();
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder("Account Details:\n");
    b.append("* Name: ").append(accountName).append("\n");
    b.append("* URL: ").append(serverUrl).append("\n");
    b.append("* User: ").append(username).append("\n");
    b.append("* RM: [").append(isRememberEnabled).append("] / AL: [").append(isAutoLoginEnabled).append("]\n");
    b.append("* Full name: ").append(userFullName).append("\n");
    b.append("* Last login: ").append(lastLoginDate).append("\n");
    b.append("* Avatar: ").append(avatarUrl).append("\n");
    return b.toString();
  }
}
