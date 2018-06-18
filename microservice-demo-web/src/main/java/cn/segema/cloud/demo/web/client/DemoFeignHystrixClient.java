package cn.segema.cloud.demo.web.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.segema.cloud.demo.web.vo.DemoUserVO;

@FeignClient(name="microservice-demo")
public interface DemoFeignHystrixClient {
	
	@RequestMapping(value="/demo/{id}",method=RequestMethod.GET)
	public Map findDemoById(@PathVariable("id") Long id);

}
