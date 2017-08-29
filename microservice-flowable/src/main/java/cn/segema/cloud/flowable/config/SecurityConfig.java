package cn.segema.cloud.flowable.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.inMemoryAuthentication().withUser("wangyong").password("123456").roles("USER")
	    .and().withUser("wangyong").password("123456").roles("ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/","/hello","/security/").permitAll()
		.anyRequest().authenticated()
		.and()
		.logout()
		.permitAll()
		.and()
		.formLogin();
		
		http.csrf().disable();
	}
	
}
