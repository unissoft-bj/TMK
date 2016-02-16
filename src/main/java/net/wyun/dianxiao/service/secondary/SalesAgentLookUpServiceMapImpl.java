/**
 * 
 */
package net.wyun.dianxiao.service.secondary;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.wyun.dianxiao.model.CallDirection;

/**
 * @author michael
 *
 */
@Service
public class SalesAgentLookUpServiceMapImpl implements SalesAgentLookUpService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesAgentLookUpServiceMapImpl.class);
	private static HashMap<String, String> agentLookUpTable = new HashMap<String, String>();
	static {
		agentLookUpTable.put("230", "刘明明");
		agentLookUpTable.put("228", "李文博");
	}
	
	/* (non-Javadoc)
	 * @see net.wyun.dianxiao.service.secondary.SalesAgentLookUpService#getUserNameByPhoneExt(java.lang.String)
	 */
	@Override
	public String getUserNameByPhoneExt(String phoneExt){
		return agentLookUpTable.get(phoneExt);
	}
	
	/* (non-Javadoc)
	 * @see net.wyun.dianxiao.service.secondary.SalesAgentLookUpService#getCallDirection(java.util.List)
	 */
	@Override
	public CallDirection getCallDirection(List<String> list){
		String tel1 = list.get(0);
		String tel2 = list.get(1);
		
		CallDirection direction = CallDirection.NOTSET; //default
		if(agentLookUpTable.containsKey(tel1) && !agentLookUpTable.containsKey(tel2)){
		   //call out
			direction = CallDirection.OUT;
		}
		
		if(!agentLookUpTable.containsKey(tel1) && agentLookUpTable.containsKey(tel2)){
		    //call in
				direction = CallDirection.IN;
		}
		
		return direction;
	}
	
	
	@Value("${dianxiao.agent.table}")
	private String agents;
	
	@PostConstruct
    public void init() {
		logger.info("AgentLookUpService: initialising hashmap for agents");
		if(agents == null) return;
		String[] agentList = agents.split(",");
		for(String ag:agentList){
			logger.info("add agent: " + ag);
			String[] agPair = ag.split(":");
			agentLookUpTable.put(agPair[0], agPair[1]);
		}
	}

}
