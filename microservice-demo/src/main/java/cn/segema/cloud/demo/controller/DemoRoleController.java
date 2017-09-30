package cn.segema.cloud.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.segema.cloud.demo.domain.DemoRole;
import cn.segema.cloud.demo.repository.DemoRoleRepository;

/**
 * 角色Controller
 */
@Controller
@RequestMapping(value = "/demo/role")
public class DemoRoleController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private DemoRoleRepository roleRepository;

	/**
	   * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
	   * @RequestMapping(value = "/id", method = RequestMethod.GET)
	   * 类似的注解还有@PostMapping等等
	   * @param id
	   * @return Role信息
	   */
	@GetMapping("/{id}")
	public DemoRole findById(@PathVariable String id) {
		DemoRole findOne = this.roleRepository.findOne(id);
		return findOne;
	}

	@GetMapping("/list")
	public List<DemoRole> list(DemoRole role, Model model) {
		List<DemoRole> list = roleRepository.findAll();
		return list;
	}

	@PostMapping("/add")
	public DemoRole add(DemoRole role, Model model) {
		if (role.getRoleId() == null || "".equals(role.getRoleId())) {
			role.setRoleId(UUID.randomUUID().toString());
		}
		roleRepository.save(role);
		return role;
	}

	@RequestMapping(value = "edit")
	public DemoRole edit(DemoRole role, Model model) {
		roleRepository.save(role);
		return role;
	}

	@RequestMapping(value = "delete")
	public DemoRole delete(DemoRole role) {
		roleRepository.delete(role);
		return role;
	}

	/**
	 * 本地服务实例的信息
	 * 
	 * @return ServiceInstance
	 */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}

}
