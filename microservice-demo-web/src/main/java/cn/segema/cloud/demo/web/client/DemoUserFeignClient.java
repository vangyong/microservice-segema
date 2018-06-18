package cn.segema.cloud.demo.web.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.segema.cloud.demo.web.vo.DemoUserVO;

@FeignClient(name="microservice-demo")
public interface DemoUserFeignClient {
	
	@RequestMapping(value="/demo/user/{id}",method=RequestMethod.GET)
	public DemoUserVO findUserById(@PathVariable("id") Long id);

}
