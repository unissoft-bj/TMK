/**
 * 
 */
package net.wyun.dianxiao.service.secondary;

import java.util.HashMap;

/**
 * @author michael
 *
 */
public class SalesAgentLookUpService {
	private static HashMap<String, String> agentLookUpTable = new HashMap<String, String>();
	static {
		agentLookUpTable.put("230", "刘明明");
		agentLookUpTable.put("228", " 李文博");
	}
	
	public static String getUserNameByPhoneExt(String phoneExt){
		return agentLookUpTable.get(phoneExt);
	}

}
