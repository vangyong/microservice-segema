package cn.segema.cloud.flowable.web.controller;

import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.flowable.web.service.FlowableWebService;

@RestController
@RequestMapping(value = "/flowable/web")
public class FlowableWebController {
	@Autowired
	private FlowableWebService flowableService;

	@GetMapping(value = "/startProcess/{personId}/{compId}")
	public ProcessInstance startProcess(@PathVariable Long personId,@PathVariable Long compId) {
		ProcessInstance processInstance = flowableService.startProcess(personId, compId);
		return processInstance;
	}
	
	@GetMapping(value = "/deploy/{wokflowId}")
	public Deployment deploy(@PathVariable String wokflowId) {
		Deployment deployment = flowableService.deploy(wokflowId);
		return deployment;
	}

}
