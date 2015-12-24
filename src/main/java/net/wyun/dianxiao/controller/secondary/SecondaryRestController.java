package net.wyun.dianxiao.controller.secondary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.dianxiao.model.secondary.SecondaryModel;
import net.wyun.dianxiao.repository.secondary.SecondaryRepository;
import net.wyun.dianxiao.service.secondary.SecondaryService;

@RestController
@RequestMapping("/secondary")
public class SecondaryRestController {
	
	@Autowired
	private SecondaryRepository secondaryRepository;
	
	@Autowired
	private SecondaryService secondaryService;
	
	@RequestMapping(value = "/repo", method = RequestMethod.GET)
	public List<SecondaryModel> getSecondaryFromRepository() {
		return (List<SecondaryModel>) secondaryRepository.findAll();
	}
	
	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public List<SecondaryModel> getSecondaryFromService() {
		return secondaryService.findAll();
	}
	
}
