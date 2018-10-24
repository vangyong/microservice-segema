package cn.segema.cloud.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Sso
public class SsoClient1Application {
	public static void main(String[] args) {
		SpringApplication.run(SsoClient1Application.class, args);

	}
}
