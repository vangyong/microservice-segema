package cn.segema.cloud.broadcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
		UserVO userVO = systemFeignHystrixClient.findByIdFeign("1");
		return userVO;
	}
	
	/**
	 * 通过参数生成Pageable对象
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/listByPageable")
	public Page<Stalls> listByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		Sort sort = new Sort(Direction.DESC, "contractId");
		Pageable pageable = new PageRequest(page, size, sort);
		return stallsRepository.findAll(pageable);
	}
	
	/**
	 * 直接获取Pageable对象
	 * @param pageable
	 * @return
	 */
	@GetMapping("/listByPageableDefault")
	public Page<Stalls> listByPageableDefault(@PageableDefault(value = 15, sort = { "contractId" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return stallsRepository.findAll(pageable);
	}
	
	/**
	 * @param id
	 * @return 合同信息
	 */
	@GetMapping("/contractdetail/{id}/{userId}")
	public Stalls findContractDetailById(@PathVariable String id,@PathVariable String userId) {
		UserVO userVO = (UserVO)restTemplate.getForObject("http://microservice-system/user/{userId}", UserVO.class, userId);
		Stalls findOne = this.stallsRepository.findOne(id);
		return findOne;
	}
	
	/**
	 * 本地服务实例的信息
	 * @return
	 */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}
}
