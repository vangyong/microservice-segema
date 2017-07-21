package cn.segema.cloud.contract.controller;

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

import cn.segema.cloud.contract.domain.Contract;
import cn.segema.cloud.contract.repository.ContractRepository;

/**
 * 合同接口
 * @author Administrator
 *
 */
@RestController
public class ContractController {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private ContractRepository contractRepository;

	/**
	 * @param id
	 * @return 合同信息
	 */
	@GetMapping("/{id}")
	public Contract findById(@PathVariable Long id) {
		Contract findOne = this.contractRepository.findOne(id);
		return findOne;
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

	/**
	 * 通过参数生成Pageable对象
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/listByPageable")
	public Page<Contract> listByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {

		Sort sort = new Sort(Direction.DESC, "contractId");
		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findAll(pageable);
	}
	
	/**
	 * 直接获取Pageable对象
	 * @param pageable
	 * @return
	 */
	@GetMapping("/listByPageableDefault")
	public Page<Contract> listByPageableDefault(@PageableDefault(value = 15, sort = { "contractId" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return contractRepository.findAll(pageable);
	}

}
