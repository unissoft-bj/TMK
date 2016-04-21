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

import java.util.List;
import java.util.Map;

import org.exoplatform.social.client.api.model.RestActivityStream;
import org.exoplatform.social.client.api.model.RestComment;
import org.exoplatform.social.client.api.model.RestLike;

public class SocialActivityInfo {

  private String             activityId;

  private String             userID;

  private String             userName;

  private String             imageUrl;

  private String             title;

  private String             body;

  private long               postedTime;

  /** Last update time of the activity, used for PLF 4 and above */
  private long               updatedTime = 0;

  private int                nbLikes;

  private int                nbComments;

  private String             type;

  private String             attachImageUrl;

  public Map<String, String> templateParams;

  public RestActivityStream  restActivityStream;

  private List<RestComment>  commentList;

  private List<RestLike>     likeList;

  public SocialActivityInfo() {

  }

  public void setActivityId(String actId) {
    activityId = actId;
  }

  public String getActivityId() {
    return activityId;
  }

  public void setUserId(String id) {
    userID = id;
  }

  public String getUserId() {
    return userID;
  }

  public void setUserName(String name) {
    userName = name;
  }

  public String getUserName() {
    return userName;
  }

  public void setImageUrl(String url) {
    imageUrl = url;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setTitle(String tit) {
    title = tit;
  }

  public String getTitle() {
    return title;
  }

  public void setBody(String bod) {
    body = bod;
  }

  public String getBody() {
    return body;
  }

  public void setType(String t) {
    type = t;
  }

  public String getType() {
    return type;
  }

  public void setPostedTime(long time) {
    postedTime = time;
  }

  public long getPostedTime() {
    return postedTime;
  }

  public long getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(long time) {
    updatedTime = time;
  }

  public void setCommentList(List<RestComment> list) {
    commentList = list;
  }

  public List<RestComment> getCommentList() {
    return commentList;
  }

  public void setLikelist(List<RestLike> list) {
    likeList = list;
  }

  public List<RestLike> getLikeList() {
    return likeList;
  }

  public void setCommentNumber(int number) {
    nbComments = number;
  }

  public int getCommentNumber() {
    return nbComments;
  }

  public void setLikeNumber(int n) {
    nbLikes = n;
  }

  public int getLikeNumber() {
    return nbLikes;
  }

  public void setAttachedImageUrl(String url) {
    attachImageUrl = url;
  }

  public String getAttachedImageUrl() {
    return attachImageUrl;
  }

}
