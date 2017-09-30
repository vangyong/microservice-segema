package cn.segema.cloud.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.segema.cloud.common.page.Pager;
import cn.segema.cloud.demo.web.service.DemoWebService;
import cn.segema.cloud.demo.web.vo.UserVO;

@Controller
@RequestMapping(value = "/user/web")
public class DemoWebController {
	
	@Autowired
	private DemoWebService userWebService;
    
    @RequestMapping("/listByPageable")
    @ResponseBody
	public Pager<UserVO> listByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		//Sort sort = new Sort(Direction.DESC, "userId");
		//Pageable pageable = new PageRequest(page, size, sort);
		return userWebService.listByPage(page,size);
	}
    

}
