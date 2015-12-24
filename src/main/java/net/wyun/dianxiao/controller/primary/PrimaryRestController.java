package net.wyun.dianxiao.controller.primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.dianxiao.model.primary.PrimaryModel;
import net.wyun.dianxiao.repository.primary.PrimaryRepository;
import net.wyun.dianxiao.service.primary.PrimaryService;

@RestController
@RequestMapping("/primary")
public class PrimaryRestController {
	
	@Autowired
	private PrimaryRepository primaryRepository;
	
	@Autowired
	private PrimaryService primaryService;
	
	@RequestMapping(value = "/repo", method = RequestMethod.GET)
	public List<PrimaryModel> getPrimaryFromRepository() {
		return (List<PrimaryModel>) primaryRepository.findAll();
	}
	
	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public List<PrimaryModel> getPrimaryFromService() {
		return primaryService.findAll();
	}

}
