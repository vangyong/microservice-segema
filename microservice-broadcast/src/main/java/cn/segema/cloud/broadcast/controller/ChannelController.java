package cn.segema.cloud.broadcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.segema.cloud.broadcast.domain.Stalls;
import cn.segema.cloud.broadcast.repository.StallsRepository;
import cn.segema.cloud.broadcast.service.SystemFeignHystrixClient;
import cn.segema.cloud.broadcast.vo.UserVO;

/**
 * 频道控制器
 */
@RestController
@RequestMapping(value = "/channel")
public class ChannelController {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private StallsRepository stallsRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private SystemFeignHystrixClient systemFeignHystrixClient;

	/**
	 * @param id
	 * @return 档位信息
	 */
	@GetMapping("/{id}")
	public Stalls findById(@PathVariable String id) {
		Stalls findOne = this.stallsRepository.findOne(id);
		return findOne;
	}

	/**
	 * @param id
	 * @return 用户信息
	 */
	@GetMapping("/user/{id}")
	public UserVO findUserById(@PathVariable String id) {
		UserVO userVO = systemFeignHystrixClient.findByIdFeign(id);
		return userVO;
	}

	/**
	 * 本地服务实例的信息
	 * 
	 * @return
	 */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}
}
