package net.wyun.dianxiao.repository.primary;

import static org.assertj.core.api.Assertions.assertThat;

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

}
