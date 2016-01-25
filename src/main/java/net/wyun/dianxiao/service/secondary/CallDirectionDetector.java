/**
 * 
 */
package net.wyun.dianxiao.service.secondary;

import java.util.List;

import net.wyun.dianxiao.model.CallDirection;

/**
 * @author michael
 *
 */
public interface CallDirectionDetector {

	public abstract CallDirection getCallDirection(List<String> list);
}
