package cn.segema.cloud.activiti.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/activiti/web")
public class ActivitiWebController {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	
	@RequestMapping(value="/listUI", method = RequestMethod.GET)
	public ModelAndView listUI() {
		ModelAndView ModelAndView = new ModelAndView("activiti/user/listUI");
		return ModelAndView;
	}
	
	@RequestMapping(value="/uploadfile", method = RequestMethod.GET)
    public String uploadFile() {
        return "activiti/user/listUI";
    }
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
	
	@RequestMapping(value="/modeler", method = RequestMethod.GET)
    public String modeler() {
        return "modeler";
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
