package net.wyun.dianxiao.service.secondary;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.wyun.dianxiao.BaseSpringTestRunner;
import net.wyun.dianxiao.model.CallDirection;
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

}
