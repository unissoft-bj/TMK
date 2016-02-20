package net.wyun.dianxiao.repository.primary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.dianxiao.BaseSpringTestRunner;
import net.wyun.dianxiao.model.primary.OUSR;

public class OUSRRepositoryTest extends BaseSpringTestRunner {
	
	@Autowired
	private OUSRRepository ousrRepository;
	
	@Test
	public void findAll() {
		assertThat(ousrRepository.findAll()).isNotEmpty();
		Iterable<OUSR> ousrs = ousrRepository.findAll();
		for(OUSR o:ousrs){
			System.out.println(o.toString());
		}
	}
	
	@Test
	public void getOUSRByName(){
		OUSR o = ousrRepository.findByUName("刘明明");
		assertThat(o).isNotNull();
		System.out.println(o.toString());
	}
	
	@Test
	public void getOUSRByFax(){
		List<OUSR> lo = ousrRepository.findByFax("200");
		assertThat(lo).isNotNull();
		for(OUSR o:lo)
			System.out.println(o.toString());
	}

}
