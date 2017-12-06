package cn.segema.cloud.wemall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WemallApplication {
  public static void main(String[] args) {
    SpringApplication.run(WemallApplication.class, args);
  }
}
