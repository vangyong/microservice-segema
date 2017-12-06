package cn.segema.cloud.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.segema.cloud.common.page.Pager;
import cn.segema.cloud.demo.web.service.DemoWebService;
import cn.segema.cloud.demo.web.vo.DemoUserVO;

@Controller
@RequestMapping(value = "/hystrix/web")
public class HystrixWebController {
	
	@Autowired
	private RestTemplate restTemplate;
	
    @HystrixCommand(fallbackMethod="findByIdFallback")
    @GetMapping("/{userId}")
	public DemoUserVO findById(@PathVariable String userId) {
    		return restTemplate.getForObject("http://microservice-demo/demo/"+userId, DemoUserVO.class);
	}
    
    public DemoUserVO findByIdFallback(Long id) {
    		DemoUserVO demoUserVO = new DemoUserVO();
    		demoUserVO.setUserId("1L");
    		demoUserVO.setNickName("测试超时");
    		return demoUserVO;
    }
    

}
