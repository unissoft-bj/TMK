/**
 * 
 */
package net.wyun.dianxiao.watcher;

import static org.junit.Assert.*;

import java.util.Date;

import net.wyun.dianxiao.BaseSpringTestRunner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author michael
 *
 */
public class ActivityPersistorDBImplTest extends BaseSpringTestRunner {
	
	@Autowired
	ActivityPersistorDBImpl activityPersistor;

	@Test
	public void testGetEndTime() {
		int endTime = activityPersistor.getTimeInInt(new Date());
		System.out.println("current time (hour minute: " + endTime);
	}

}
