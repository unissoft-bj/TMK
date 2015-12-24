package net.wyun.dianxiao.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import net.wyun.dianxiao.BaseSpringTestRunner;

public class DianxiaoControllerTest extends BaseSpringTestRunner {
	
	@Test
	public void getHome() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("home"));
	}

}
