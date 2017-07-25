package cn.segema.cloud.elasticsearch.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.elasticsearch.domain.Elasticsearch;
import cn.segema.cloud.elasticsearch.repository.ElasticsearchRepository;
import cn.segema.cloud.elasticsearch.vo.UserPersonalVO;

@RestController
public class ElasticsearchController {
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private ElasticsearchRepository elasticsearchRepository;

  /**
   * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
   * @RequestMapping(value = "/id", method = RequestMethod.GET)
   * 类似的注解还有@PostMapping等等
   * @param id
   * @return user信息
   */
  @GetMapping("/{userId}")
  public Elasticsearch findById(@PathVariable String userId) {
    Elasticsearch findOne = this.elasticsearchRepository.findOne(userId);
    return findOne;
  }
  
  @GetMapping("/list")
	public List<Elasticsearch> list(Elasticsearch user, Model model) {
		List<Elasticsearch> userList = elasticsearchRepository.findAll();
		return userList;
	}

	@PostMapping("/add")
	public Elasticsearch add(Elasticsearch user, Model model) {
		if (user.getUserId() == null || "".equals(user.getUserId())) {
			user.setUserId(UUID.randomUUID().toString());
		}
		elasticsearchRepository.save(user);
		return user;
	}

	@RequestMapping(value = "edit")
	public Elasticsearch edit(Elasticsearch user, Model model) {
		// Role oldRole = roleRepository.getOne(role.getRoleId());
		// BeanUtils.copyProperties(role, oldRole);
		elasticsearchRepository.save(user);
		return user;
	}

	@RequestMapping(value = "delete")
	public Elasticsearch delete(Elasticsearch user) {
		elasticsearchRepository.delete(user);
		return user;
	}
  
  
  @GetMapping("/listByUserName/{userName}") 
  public List<UserPersonalVO> listByUserName(@PathVariable String userName) {
	  List<UserPersonalVO> userList = elasticsearchRepository.findByUserName(userName);
	  return userList;
	}
  
  
  @GetMapping("/listByPage/{page}/{size}")
	public Page<Elasticsearch> listByPage(@PathVariable Integer page,@PathVariable Integer size) {
		Sort sort = new Sort(Direction.DESC, "contractId");
		Pageable pageable = new PageRequest(page, size, sort);
		return elasticsearchRepository.findAll(pageable);
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
