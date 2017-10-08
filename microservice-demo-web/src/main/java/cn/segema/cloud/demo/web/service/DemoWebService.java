package cn.segema.cloud.demo.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.segema.cloud.common.page.Pager;
import cn.segema.cloud.demo.web.vo.DemoUserVO;


@Service
public class DemoWebService {
  
  @Autowired
  private RestTemplate restTemplate;
  
  public Pager<DemoUserVO> listByPage(int page,int size) {
	  	Map<String,Integer> parameter = new HashMap<String,Integer>();
	  	parameter.put("page", page);
	  	parameter.put("size", size);
	  	Pager<DemoUserVO> userPage =(Pager<DemoUserVO>) this.restTemplate.getForObject("http://microservice-demo/demo/listByPage", Pager.class,parameter);
	    return userPage;
	  }
  


}
