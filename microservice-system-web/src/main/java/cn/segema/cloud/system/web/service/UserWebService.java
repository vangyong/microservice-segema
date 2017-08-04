package cn.segema.cloud.system.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.segema.cloud.system.web.vo.UserVO;



@Service
public class UserWebService {
  
  @Autowired
  private RestTemplate restTemplate;
  
  public Page<UserVO> listByPageable(int page,int size) {
	  	Map<String,Integer> parameter = new HashMap<String,Integer>();
	  	parameter.put("page", page);
	  	parameter.put("size", size);
	  	Page<UserVO> userPage = (Page<UserVO>) this.restTemplate.getForObject("http://microservice-system/user/listByPage", UserVO.class,parameter);
	    return userPage;
	  }
  
  public UserVO findById(String userId) {
	  	Map<String,String> parameter = new HashMap<String,String>();
	  	parameter.put("userId", userId);
	  	UserVO user = (UserVO) this.restTemplate.getForObject("http://microservice-system/user/", UserVO.class,parameter);
	    return user;
	  }

}
