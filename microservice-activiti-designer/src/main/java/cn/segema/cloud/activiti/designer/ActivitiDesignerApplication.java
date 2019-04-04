package cn.segema.cloud.activiti.designer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.segema.cloud.activiti.designer.modeler.JsonpCallbackFilter;

@SpringBootApplication
@ComponentScan({"org.activiti.rest.diagram", "cn.segema.cloud.activiti.designer"})
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		org.activiti.spring.boot.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class
})
//@EnableAsync
@EnableDiscoveryClient
public class ActivitiDesignerApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiDesignerApplication.class, args);
	}

	@Bean
	public JsonpCallbackFilter filter(){
		return new JsonpCallbackFilter();
	}
}
