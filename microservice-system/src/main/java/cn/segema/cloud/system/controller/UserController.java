package cn.segema.cloud.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.contract.domain.Contract;
import cn.segema.cloud.system.domain.User;
import cn.segema.cloud.system.repository.UserRepository;

/**
 * 作用：
 * ① 测试服务实例的相关内容
 * ② 为后来的服务做提供
 * @author eacdy
 */
@RestController
public class UserController {
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private UserRepository userRepository;

  /**
   * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
   * @RequestMapping(value = "/id", method = RequestMethod.GET)
   * 类似的注解还有@PostMapping等等
   * @param id
   * @return user信息
   */
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    userRepository.findAll();
    return findOne;
  }
  
  @GetMapping("/getTableData") 
  public List<User> getTableData() {
	  try { 
		  int pageNum=1;
		  int pageSize=20;
		  String username="A";
		  List<User> userList = userRepository.findByUsername(username);
		  
		  System.out.println("this is userList:");
		  
		  return userList;//userRepository.getTableData(pageNum, pageSize, username); 
	  } catch (Exception e) { 
		  e.printStackTrace(); 
	  } return null; 
	}
  
  
  @GetMapping("/listByPageable")
	public Page<User> listByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		Sort sort = new Sort(Direction.DESC, "contractId");
		Pageable pageable = new PageRequest(page, size, sort);
		return userRepository.findAll(pageable);
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
