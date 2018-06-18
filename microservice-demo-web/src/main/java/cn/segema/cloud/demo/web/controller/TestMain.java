
package cn.segema.cloud.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangyong
 */
public class TestMain {

	@Autowired
	  private RestTemplate restTemplate;
	
	
	public static void main(String[] args) {
		//AbstractLoadBalancerRule;
		final String name ="a";
	     Integer age =18;
		Object[] arr = new Object[] {name,age};
	}
	
	public void test1() {
		final String name ="a";
		//org.hibernate.dialect.MySQL5Dialect;
	
		//restTemplate.postForLocation(url, request, uriVariables)
	}

}
