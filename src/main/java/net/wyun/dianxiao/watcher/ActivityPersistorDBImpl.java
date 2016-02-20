/**
 * 
 */
package net.wyun.dianxiao.watcher;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.model.primary.ATC1;
import net.wyun.dianxiao.model.primary.OATC;
import net.wyun.dianxiao.model.primary.OCLG;
import net.wyun.dianxiao.model.primary.OSLP;
import net.wyun.dianxiao.model.primary.OUSR;
import net.wyun.dianxiao.repository.primary.ATC1Repository;
import net.wyun.dianxiao.repository.primary.OATCRepository;
import net.wyun.dianxiao.repository.primary.OCLGRepository;
import net.wyun.dianxiao.repository.primary.OSLPRepository;
import net.wyun.dianxiao.repository.primary.OUSRRepository;
import net.wyun.dianxiao.service.secondary.SalesAgentLookUpService;
import net.wyun.dianxiao.service.secondary.SalesAgentLookUpServiceDBImpl;

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
	ATC1Repository atc1Repo;
	
	@Autowired
	OATCRepository oatcRepo;
	
	@Autowired
	@Qualifier("lookupServiceDBImpl")
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
	@Transactional
	public void persist(CallDirection direction, List<String> list) {
		int absEntry = this.nextAtcEntry();
		this.oatcRepo.save(new OATC(absEntry));
		
		OCLG oclg = this.generateOCLG(direction, list);	
		oclg.setAtcEntry(absEntry);
		oclg = oclgRepo.save(oclg);
		ATC1 atc1 = this.generateATC1(direction, list);
		atc1.setDateTime(oclg.getCntctDate());
		atc1.setAbsEntry(absEntry);
		Short attendUser = oclg.getAttendUser();
		if(attendUser != null){
			atc1.setUserId(new Integer(attendUser));
		}
		
		this.atc1Repo.save(atc1);
	}
	
	public ATC1 generateATC1(CallDirection direction, List<String> list){
		ATC1 atc1 = new ATC1();
		
		atc1.setLine(1);
		String path = list.get(6);
		logger.debug("path: " + path);
		
		File file = new File(path);
		String parentPath = file.getAbsoluteFile().getParent();
		atc1.setSrcPath(parentPath);
		atc1.setTrgtPath(parentPath);
		
		String fileName = FileProcessUtil.extractFileName(path);
		atc1.setFileName(fileName);
		atc1.setFileExt("mp3");
		
		return atc1;
	}
	
	private final static String CARD_CODE = "E000009";
	private OCLG generateOCLG(CallDirection direction, List<String> list){
		OCLG oclg = new OCLG();
		oclg.setCardCode(CARD_CODE);
		int clgCode = this.nextClgCode();
		oclg.setClgCode(clgCode);
	//	oclg.setDetails(list.get(6)); //mp3 file path; notes for speech recognition data; details varchar 60, too short
		
		String date = list.get(2);
		Date callDate = this.getActDate(date);
		oclg.setCntctDate(callDate);
		oclg.setRecontact(callDate);
		oclg.setEndDate(callDate);  //for sap requirement
		
		int cntctTime = this.getCntctTime(list.get(3));
		oclg.setCntctTime(cntctTime);
		
		oclg.setClosed("N");  //means not closed NO
		oclg.setAttachment(list.get(6));
		//set tel, userid, and slpcode
		if(direction == CallDirection.NOTSET){
			this.handleNotSetPhoneExt(oclg, list);
		}else{
			this.normalFlow(oclg, direction, list);
		}
		
		oclg.setAction("C");
		oclg.setCntctType(direction); //呼入/呼出
		
		Date end = getRecordEndTime(list.get(2) + "-" + list.get(3));
		
		//BigDecimal duration = this.calcDuration(list.get(2) + "-" + list.get(3), now);
		int durationInSec = Integer.parseInt(list.get(7));
		double minute = (double) (durationInSec / 60.0);
		oclg.setDuration(new BigDecimal(minute));
		
		Date begin = new Date();
		begin.setTime(end.getTime() - durationInSec * 1000); // 5 seconds ago
		int endTime = this.getTimeInInt(end);
		oclg.seteNDTime(endTime);
		
	    int beginTime = this.getTimeInInt(begin);
	    oclg.setBeginTime(beginTime);
		oclg.setPersonal("N");
		
		return oclg;
	}
	
	/**
	 * direction is NOTSET
	 * @param oclg
	 * @param list
	 */
	private void handleNotSetPhoneExt(OCLG oclg, List<String> list){
		//direction is NOTSET
		String tel1 = list.get(0);
		String tel2 = list.get(1);
		String tel = tel1.length() > tel2.length()?tel1:tel2;
		oclg.setTel(tel);
		oclg.setSlpCode((short) -1);
	}
	
	/**
	 * direction is IN or OUT
	 * @param oclg
	 * @param direction
	 * @param list
	 */
	private void normalFlow(OCLG oclg, CallDirection direction, List<String> list){
		String tel = list.get(0);
		if(direction == CallDirection.OUT){
			tel = list.get(1);
		}
		oclg.setTel(tel);
		
		String phoneExt = list.get(0);
		if(direction == CallDirection.IN){
			phoneExt = list.get(1);
		}
		//the phoneExt has been checked before, so userName should be available
		String userName = this.agentLookUpService.getUserNameByPhoneExt(phoneExt);
		logger.debug("user name: " + userName);
		OUSR ousr = this.ousrRepo.findByUName(userName);
		short userId = ousr.getUSERID();
		oclg.setAttendUser(userId);
		
		
		OSLP oslp = oslpRepo.findBySlpName(userName);
		if(null == oslp){
			oclg.setSlpCode((short)-1);
		}else{
			oclg.setSlpCode(oslp.getSlpCode());
		}
	}
	
	private Date getActDate(String str){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date callDate = null;
		try {
			callDate = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return callDate;
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
	@Deprecated
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
	
	public Date getRecordEndTime(String dateTime){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	    Date date = null;
		try {
			date = dateFormat.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
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
	
	/**
	 * need synchronize this call
	 * @return
	 */
	public synchronized int nextAtcEntry(){
		int key = oatcRepo.findMaxAbsEntry();  //atcEntry = absEntry?
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
