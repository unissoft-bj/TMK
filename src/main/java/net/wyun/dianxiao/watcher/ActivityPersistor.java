/**
 * 
 */
package net.wyun.dianxiao.watcher;

import java.util.List;

import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.service.secondary.CallDirectionDetector;

/**
 * @author michael
 *
 */
public interface ActivityPersistor extends CallDirectionDetector{

	public void persist(CallDirection direction, List<String> list);
	
}
