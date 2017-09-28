package cn.segema.cloud.activiti.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/activiti/web")
public class ActivitiWebController {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	
	
	@RequestMapping("/listUI")
	public ModelAndView listUI() {
		ModelAndView ModelAndView = new ModelAndView("/test/activiti/user/listUI");
		return ModelAndView;
	}
	
	/**
	 * 本地服务实例的信息
	 * 
	 * @return
	 */
	@RequestMapping("/instance-info")
	@ResponseBody
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}
}
