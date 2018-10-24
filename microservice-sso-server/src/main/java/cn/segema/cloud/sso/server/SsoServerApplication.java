package cn.segema.cloud.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
	
/**
 * @author wangyong
 */
@SpringBootApplication
@EnableEurekaClient
public class SsoServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SsoServerApplication.class, args);
  }
  
}
