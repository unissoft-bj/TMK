package net.wyun.dianxiao.service.secondary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.dianxiao.BaseSpringTestRunner;

public class SecondaryServiceTest extends BaseSpringTestRunner {
	
	@Autowired
	private SecondaryService secondaryService;
	
	@Test
	public void findAll() {
		assertThat(secondaryService.findAll()).isNotEmpty();
	}

}
