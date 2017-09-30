package cn.segema.cloud.demo.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.segema.cloud.common.page.Pager;
import cn.segema.cloud.demo.web.vo.UserVO;


@Service
public class DemoWebService {
  
  @Autowired
  private RestTemplate restTemplate;
  
  public Pager<UserVO> listByPage(int page,int size) {
	  	Map<String,Integer> parameter = new HashMap<String,Integer>();
	  	parameter.put("page", page);
	  	parameter.put("size", size);
	  	Pager<UserVO> userPage =null; //(Pager<UserVO>) this.restTemplate.getForObject("http://microservice-system/user/listByPage", UserVO.class,parameter);
	    return userPage;
	  }
  


}
