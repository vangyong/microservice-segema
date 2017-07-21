package cn.segema.cloud.system.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.segema.cloud.system.web.domain.User;



@Service
public class WebUserService {
  
  @Autowired
  private RestTemplate restTemplate;
  
  public List<User> listByPageable(int page,int size) {
	  	Map<String,Integer> parameter = new HashMap<String,Integer>();
	  	parameter.put("page", page);
	  	parameter.put("size", size);
	    return (List<User>) this.restTemplate.getForObject("http://microservice-contract/listByPageable", User.class,parameter);
	  }
  
}
