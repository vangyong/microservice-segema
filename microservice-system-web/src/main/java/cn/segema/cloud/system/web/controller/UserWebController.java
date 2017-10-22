package cn.segema.cloud.system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.segema.cloud.system.web.service.UserWebService;
import cn.segema.cloud.system.web.vo.UserVO;

@Controller
@RequestMapping(value = "/user/web")
public class UserWebController {

	@Autowired
	private UserWebService userWebService;

	@RequestMapping("/findById/{userId}")
	@ResponseBody
	public UserVO findById(@PathVariable String userId) {
		UserVO findOne = this.userWebService.findById(userId);
		return findOne;
	}
}
