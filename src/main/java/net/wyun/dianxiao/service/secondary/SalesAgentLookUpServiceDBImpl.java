/**
 * 
 */
package net.wyun.dianxiao.service.secondary;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.model.primary.OUSR;
import net.wyun.dianxiao.repository.primary.OUSRRepository;

/**
 * @author michael
 *
 */
@Service("lookupServiceDBImpl")
public class SalesAgentLookUpServiceDBImpl implements SalesAgentLookUpService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesAgentLookUpServiceDBImpl.class);
	private static Set<String> phoneExtSet = new HashSet<String>();
	private static HashMap<String, OUSR> userLookupTable = new HashMap<String, OUSR>();
	
	@Autowired
	OUSRRepository ousrRepo;
	
	public final static String UNKNOWN = "NOT_SET";
	
	@Override
	public String getUserNameByPhoneExt(String phoneExt){
		String un = UNKNOWN;
		if(userLookupTable.containsKey(phoneExt)){
			un = userLookupTable.get(phoneExt).getUName();
		}
		return un;
	}
	
	@Override
	public CallDirection getCallDirection(List<String> list){
		String tel1 = list.get(0);
		String tel2 = list.get(1);
		logger.info("tel1: " + tel1 + ", tel2: " + tel2);
		CallDirection direction = CallDirection.NOTSET; //default
		List<OUSR> ousrList = ousrRepo.findByFax(tel1);
		
		if(!ousrList.isEmpty()){
			direction = CallDirection.OUT;
			OUSR ou = this.select(ousrList);
			if(ou != null) {
				userLookupTable.put(tel1, ou);
				return direction;
			}
			
		}
		
		ousrList = ousrRepo.findByFax(tel2);
		if(!ousrList.isEmpty()){
			direction = CallDirection.IN;
			OUSR ou = this.select(ousrList);
			if(ou != null){
				userLookupTable.put(tel2, ou);
				return direction;
			}
			
		}
		
		return direction;
	}
	
	/**
	 * precondition l is not empty
	 * @param l
	 * @return
	 */
	public OUSR select(List<OUSR> l){
		List<OUSR> filtered = l.stream().filter(ousr -> ousr.getLocked().equals("N"))
				              .sorted(Comparator.comparingInt(OUSR::getUSERID).reversed())
				              .collect(Collectors.toList());
		OUSR top = null;
		if(!filtered.isEmpty()){
			top = filtered.get(0);
		}
		return top;
		
	}
	@PostConstruct
    public void init() {
		
		logger.info("AgentLookUpService: initialising set<ext> for agents, loading from DB:");
		Iterable<OUSR> users = ousrRepo.findAll();
		for(OUSR user:users){
			String fax = user.getFax();
			logger.info("user: " + user.getUName() + ", fax: " + fax + ", locked: " + user.getLocked());
			if(user.getFax() != null && "N".equals(user.getLocked())){
				phoneExtSet.add(user.getFax());
				userLookupTable.put(user.getFax(), user);
			}
		}
	}

}
