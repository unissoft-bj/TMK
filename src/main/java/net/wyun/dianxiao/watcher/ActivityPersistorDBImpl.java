/**
 * 
 */
package net.wyun.dianxiao.watcher;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.model.primary.OCLG;
import net.wyun.dianxiao.model.primary.OSLP;
import net.wyun.dianxiao.model.primary.OUSR;
import net.wyun.dianxiao.repository.primary.OCLGRepository;
import net.wyun.dianxiao.repository.primary.OSLPRepository;
import net.wyun.dianxiao.repository.primary.OUSRRepository;
import net.wyun.dianxiao.service.secondary.SalesAgentLookUpService;

/**
 * @author michael
 *
 */
@Component
public class ActivityPersistorDBImpl implements ActivityPersistor{
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityPersistorDBImpl.class);
	private final static int ACTIVITY_START_INDEX = 100000000;
	
	@Autowired
	OCLGRepository oclgRepo;
	
	@Autowired
	OSLPRepository oslpRepo;
	
	@Autowired
	OUSRRepository ousrRepo;
	
	@Autowired
	SalesAgentLookUpService agentLookUpService;

	/**
	 * ex. of List<String> list
	   230
       18833500052
       20151218
       102537
       24578
       cg
       /opt/tmk/record/dysoft/20151218/230_18833500052_20151218-102537_24578_cg.mp3
       duration for sweeper
	 */
	@Override
	public void persist(CallDirection direction, List<String> list) {
		OCLG oclg = this.generateOCLG(direction, list);	
		oclgRepo.save(oclg);
	}
	
	private final static String CARD_CODE = "E000009";
	private OCLG generateOCLG(CallDirection direction, List<String> list){
		OCLG oclg = new OCLG();
		oclg.setCardCode(CARD_CODE);
		int clgCode = this.nextClgCode();
		oclg.setClgCode(clgCode);
	//	oclg.setDetails(list.get(6)); //mp3 file path; notes for speech recognition data; details varchar 60, too short
		
		String date = list.get(2);
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date callDate = null;
		try {
			callDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		oclg.setCntctDate(callDate);
		oclg.setRecontact(callDate);
		oclg.setCloseDate(callDate);  //for sap requirement
		
		int cntctTime = this.getCntctTime(list.get(3));
		oclg.setCntctTime(cntctTime);
		
		oclg.setClosed("N");  //means not closed NO
		
		String tel = list.get(0);
		if(direction == CallDirection.OUT){
			tel = list.get(1);
		}
		oclg.setTel(tel);
		
		String phoneExt = list.get(0);
		if(direction == CallDirection.IN){
			phoneExt = list.get(1);
		}
		String userName = this.agentLookUpService.getUserNameByPhoneExt(phoneExt);
		logger.debug("user name: " + userName);
		OUSR ousr = ousrRepo.findByUName(userName);
		short userId = ousr.getUSERID();
		oclg.setAttendUser(userId);
		
		OSLP oslp = oslpRepo.findBySlpName(userName);
		oclg.setSlpCode(oslp.getSlpCode());
		
		oclg.setAction("C");
		oclg.setCntctType(direction); //呼入/呼出
		
		Date end = new Date();
		Date begin = new Date();
		//BigDecimal duration = this.calcDuration(list.get(2) + "-" + list.get(3), now);
		int sec = Integer.parseInt(list.get(7));
		double minute = (double) (sec / 60.0);
		oclg.setDuration(new BigDecimal(minute));
		
		end.setTime(end.getTime() - 5000); // 5 seconds ago
		int endTime = this.getTimeInInt(end);
		oclg.seteNDTime(endTime);
		
	    begin.setTime(begin.getTime() - 5000 - sec * 1000);
	    int beginTime = this.getTimeInInt(begin);
	    oclg.setBeginTime(beginTime);
	
		
		oclg.setPersonal("N");
		return oclg;
	}
	/**
	 * for example 1015 ==> 10:15am
	 * @param date
	 * @return
	 */
	public int getTimeInInt(Date date){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
		String dt = dateFormat.format(date);
		String[] time = dt.split("-");
		return Integer.parseInt(time[1]);
	}
	
	/**
	 * duration in minutes
	 * @param dateTime
	 * @return
	 */
	public BigDecimal calcDuration(String dateTime /* yyyyMMdd-HHmmss */, Date now ){
		
		BigDecimal duration;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	    Date date = null;
	    try {
			date = dateFormat.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    long begin = date.getTime(); //milliseconds
	    long end   = now.getTime();
	    double elapsed = (end - begin)/60.0/1000.0;
	    
	    duration = new BigDecimal(elapsed);
	    
	    return duration;
		
	}
	/**
	 * time ex., 102537 == > 1025
	 * @param time
	 * @return
	 */
	public int getCntctTime(String time){
		int length = time.length();
		System.out.println("time field length: " + length);
		String hm = time.substring(0,4);
		return Integer.parseInt(hm);
	}

	/**
	 * need synchronize this call
	 * @return
	 */
	public synchronized int nextClgCode(){
		int key = oclgRepo.findMaxClgCode();
		if(key < ACTIVITY_START_INDEX){
			return ACTIVITY_START_INDEX + 1;
		}
		return (key + 1);
	}
	
	@Override
	public CallDirection getCallDirection(List<String> list) {
		return agentLookUpService.getCallDirection(list);
	}
}
