package cn.segema.cloud.cms.web.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.segema.cloud.cms.web.vo.DemoUserVO;

@FeignClient("microservice-system")
public interface UserFeignClient {
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public DemoUserVO findById(@PathVariable("id") String id); //1 GetMapping 不支持，2 PathVariable 需要加参数

}
