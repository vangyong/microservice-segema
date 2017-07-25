package cn.segema.cloud.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.elasticsearch.domain.Account;
import cn.segema.cloud.elasticsearch.domain.Elasticsearch;
import cn.segema.cloud.elasticsearch.repository.AccountRepository;

@RestController
public class AccountController {
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private AccountRepository accountRepository;
  
  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;
  
   

  /**
   * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
   * @RequestMapping(value = "/id", method = RequestMethod.GET)
   * 类似的注解还有@PostMapping等等
   * @param id
   * @return user信息
   */
  @GetMapping("/{userId}")
  public Account findById(@PathVariable String userId) {
	  Account findOne = this.accountRepository.findOne(userId);
    return findOne;
  }
  
  @GetMapping("/list")
	public List<Account> list(Elasticsearch account, Model model) {
		List<Account> userList = accountRepository.findAll();
		return userList;
	}

	@PostMapping("/add")
	public Account add(Account account, Model model) {
		accountRepository.save(account);
		return account;
	}

	@RequestMapping(value = "edit")
	public Account edit(Account account, Model model) {
		accountRepository.save(account);
		return account;
	}

	@RequestMapping(value = "delete")
	public Account delete(Account account) {
		accountRepository.delete(account);
		return account;
	}
  
  
  @GetMapping("/listByAccountName/{accountName}") 
  public List<Account> listByUserName(@PathVariable String accountName) {
	  List<Account> userList = accountRepository.findByAccountName(accountName);
	  return userList;
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
