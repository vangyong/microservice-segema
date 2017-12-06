package cn.segema.cloud.demo.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.segema.cloud.demo.web.vo.DemoUserVO;

@FeignClient(name="microservice-demo")
public interface FeignHystrixClientTest {
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public DemoUserVO findById(@PathVariable("id") Long id);

}
