package net.wyun.dianxiao.repository.primary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.dianxiao.BaseSpringTestRunner;
import net.wyun.dianxiao.model.primary.OSLP;

public class OSLPepositoryTest extends BaseSpringTestRunner {
	
	@Autowired
	private OSLPRepository oslpRepository;
	
	@Test
	public void findAll() {
		assertThat(oslpRepository.findAll()).isNotEmpty();
		Iterable<OSLP> oslps = oslpRepository.findAll();
		for(OSLP o:oslps){
			System.out.println(o.toString());
		}
	}
	
	@Test
	public void getOSLPByName(){
		OSLP o = oslpRepository.findBySlpName("张涛");
		assertThat(o).isNotNull();
		System.out.println(o.toString());
	}

}
