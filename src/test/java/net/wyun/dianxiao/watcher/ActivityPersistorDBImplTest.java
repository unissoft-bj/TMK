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
	ActivityPersistor activityPersistor;

	@Test
	public void testGetEndTime() {
		ActivityPersistorDBImpl impl = new ActivityPersistorDBImpl();
		int endTime = impl.getTimeInInt(new Date());
		System.out.println("current time (hour minute: " + endTime);
	}

}
