package cn.segema.cloud.sso.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SsoAuthorizationServerConfig.class);

//	@Autowired
//	private DataSource dataSource;
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//clients.withClientDetails(clientDetails());
		
		clients.inMemory()
			.withClient("segema1")
			.secret("segemasecret1")
			.authorizedGrantTypes("authorization_code","refresh_token")
			.scopes("all")
			.and()
			.withClient("segema2")
			.secret("segemasecret2")
			.authorizedGrantTypes("authorization_code","refresh_token")
			.scopes("all");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(jwtTokenStore())
		.accessTokenConverter(jwtAccessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAuthenticated()");
	}

//	@Bean
//	public ClientDetailsService clientDetails() {
//		return new JdbcClientDetailsService(dataSource);
//	}


	
	
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		 JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		 accessTokenConverter.setSigningKey("segema");
		 return accessTokenConverter;
	}

}
