package net.wyun.dianxiao.repository.primary;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.dianxiao.BaseSpringTestRunner;
import net.wyun.dianxiao.model.primary.OCLG;

public class OCLGRepositoryTest extends BaseSpringTestRunner {
	
	@Autowired
	private OCLGRepository oclgRepository;
	
	@Test
	public void findAll() {
		assertThat(oclgRepository.findAll()).isNotEmpty();
		Iterable<OCLG> oclgs = oclgRepository.findAll();
		for(OCLG o:oclgs){
			System.out.println(o.toString());
		}
	}
	
	@Test
	public void findMaxClgCode(){
		int max = oclgRepository.findMaxClgCode();
		System.out.println("max clgCode: " + max);
	}
	
	@Test
	public void saveOCLG(){
		OCLG o = new OCLG();
		int max = oclgRepository.findMaxClgCode();
		if(max < 100000000) max = 100000000;
		o.setClgCode(max + 1);
		o.setNotes("/opt/wms/call/record/20151218/230_18833500052_20151218-102537_24578_cg.mp3"); 
		//o.setCntctTime(2000);
		o.setCntctDate(new Date());
		o.setAction("C");
		o.setEndDate(new Date());
		o.setSlpCode((short) 88);
		o.setDetails("no details yet");
		o.setClosed("Y");
		o.setTel("2064916276-001");
		
		oclgRepository.save(o);
		
	}

}
