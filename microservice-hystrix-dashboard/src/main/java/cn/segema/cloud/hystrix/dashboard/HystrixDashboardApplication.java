package cn.segema.cloud.hystrix.dashboard;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 通过@EnableHystrixDashboard接口，激活对HystrixDashboard的支持。
 * @author eacdy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HystrixDashboardApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(HystrixDashboardApplication.class).web(true).run(args);
  }
}
