package cn.segema.cloud.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActivitiApplication {
  public static void main(String[] args) {
    SpringApplication.run(ActivitiApplication.class, args);
  }
}
