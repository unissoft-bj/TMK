package net.wyun.dianxiao.service.secondary;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.wyun.dianxiao.BaseSpringTestRunner;
import net.wyun.dianxiao.model.CallDirection;
import net.wyun.dianxiao.model.primary.OUSR;
import net.wyun.dianxiao.repository.primary.OUSRRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SalesAgentLookUpServiceDBImplTest extends BaseSpringTestRunner{
	@Autowired
	SalesAgentLookUpServiceDBImpl lookupSvc;
	

	@Test
	public void testGetCallDirection1() {
		List<String> list = new ArrayList<String>();;
		list.add("200");
		list.add("18333531611");
		CallDirection direction = lookupSvc.getCallDirection(list);
		assertTrue(direction == CallDirection.OUT);
	}
	
	@Test
	public void testGetCallDirection2() {
		List<String> list = new ArrayList<String>();;
		list.add("18333531611");
		list.add("200");
		CallDirection direction = lookupSvc.getCallDirection(list);
		assertTrue(direction == CallDirection.IN);
	}
	
	@Test
	public void testGetCallDirection3() {
		List<String> list = new ArrayList<String>();;
		list.add("229");
		list.add("200");
		CallDirection direction = lookupSvc.getCallDirection(list);
		assertTrue(direction == CallDirection.OUT);
	}
	
	@Test
	public void testGetCallDirection4() {
		List<String> list = new ArrayList<String>();;
		list.add("18333531611");
		list.add("900");
		CallDirection direction = lookupSvc.getCallDirection(list);
		assertTrue(direction == CallDirection.NOTSET);
	}
	
	@Test
	public void testSelect(){
		List<OUSR> list = new ArrayList<OUSR>();
		OUSR ou1 = new OUSR();
		ou1.setLocked("N");
		ou1.setUSERID((short) 77);
		
		OUSR ou2 = new OUSR();
		ou2.setLocked("N");
		ou2.setUSERID((short) 88);
		
		OUSR ou3 = new OUSR();
		ou3.setLocked("N");
		ou3.setUSERID((short) 66);
		
		OUSR ou4 = new OUSR();
		ou4.setLocked("Y");
		ou4.setUSERID((short) 99);
		
		list.add(ou1); list.add(ou2); list.add(ou3); list.add(ou4);
		
		OUSR topOUSR = lookupSvc.select(list);
		System.out.println("USERID: " + topOUSR.getUSERID());
		short expected = 88;
		assertEquals(expected, topOUSR.getUSERID());
	}

}
