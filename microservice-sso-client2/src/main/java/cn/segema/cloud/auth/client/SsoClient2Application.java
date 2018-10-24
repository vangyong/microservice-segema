package cn.segema.cloud.auth.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Sso
public class SsoClient2Application {
	public static void main(String[] args) {
		SpringApplication.run(SsoClient2Application.class, args);

	}
}
