/**
 * 
 */
package net.wyun.util;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;


/**
 * @author michaely
 *
 */
public class SysInfoUtil {
	
	public static String getSysInfo(){
		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		double loadAverage = operatingSystemMXBean.getSystemLoadAverage();
		//int    availableCPUs = operatingSystemMXBean.getAvailableProcessors();
		long   usedMemeory = operatingSystemMXBean.getTotalPhysicalMemorySize() - operatingSystemMXBean.getFreePhysicalMemorySize();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Load Average: " ).append(loadAverage).append(", used memory: ").append(usedMemeory);
		return sb.toString();
	}
	
	private final static String USER_DIR = "user.dir";
	public static String getWorkingDir(){
		return System.getProperty("user.dir");
	}
	
	

}
