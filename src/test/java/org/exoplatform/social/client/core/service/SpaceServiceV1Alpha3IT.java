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
package org.exoplatform.social.client.core.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;

import java.util.List;

import org.exoplatform.social.client.api.SocialClientLibException;
import org.exoplatform.social.client.api.model.RestSpace;
import org.exoplatform.social.client.core.AbstractClientTestV1Alpha3;
import org.testng.annotations.Test;

/**
 * Created by The eXo Platform SAS Author : Philippe Aristote
 * paristote@exoplatform.com May 4, 2015
 */
public class SpaceServiceV1Alpha3IT extends AbstractClientTestV1Alpha3 {

  @Override
  public void setUp() {
    super.setUp();
  }

  @Override
  public void tearDown() {
    super.tearDown();
  }

  @Test
  public void shouldGetNoSpace() {
    if (!canRunTest()) {
      return;
    }
    startSessionAs("root", "gtn");
    if (spaceService != null) {
      List<RestSpace> spaces = spaceService.getMySocialSpaces();
      assertThat("List of Spaces should be empty.", spaces.size(), equalTo(0));
    }
  }

  @Test
  public void shouldBeUnsupported() {
    if (!canRunTest()) {
      return;
    }
    startSessionAs("root", "gtn");
    if (spaceService != null) {
      // Create Space
      try {
        RestSpace theSpace = new RestSpace();
        theSpace.setName("thespace");
        spaceService.create(theSpace);
        fail("Expecting SocialClientLibException from SpaceService#create(RestSpace)");
      } catch (SocialClientLibException e) {
      }
      // Get Space
      try {
        RestSpace theSpace = spaceService.get("123456");
        fail("Expecting SocialClientLibException from SpaceService#get(String)");
      } catch (SocialClientLibException e) {
      }
      // Update Space
      try {
        RestSpace theSpace = new RestSpace();
        theSpace.setName("thespace");
        spaceService.update(theSpace);
        fail("Expecting SocialClientLibException from SpaceService#update(RestSpace)");
      } catch (SocialClientLibException e) {
      }
      // Delete Space
      try {
        RestSpace theSpace = new RestSpace();
        theSpace.setName("thespace");
        spaceService.delete(theSpace);
        fail("Expecting SocialClientLibException from SpaceService#delete(RestSpace)");
      } catch (SocialClientLibException e) {
      }
    }
  }
}
