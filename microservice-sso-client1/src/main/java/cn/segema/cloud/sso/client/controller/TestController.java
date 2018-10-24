package cn.segema.cloud.sso.client.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	@GetMapping("/user")
	public Authentication user(Authentication user) {
		return user;
	}
	
	@GetMapping("/demo")
    public String getDemo(){
        return "good";
    }
	
	@GetMapping("/test2")
    public String test2(){
        return "test2";
    }
}
