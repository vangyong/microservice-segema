package cn.segema.cloud.demo.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.segema.cloud.common.page.Pager;
import cn.segema.cloud.demo.web.client.DemoFeignHystrixClient;
import cn.segema.cloud.demo.web.client.DemoUserFeignClient;
import cn.segema.cloud.demo.web.service.DemoWebService;
import cn.segema.cloud.demo.web.vo.DemoUserVO;

@Controller
@RequestMapping(value = "/demo/web")
public class DemoWebController {

	@Autowired
	private DemoWebService userWebService;

	@Autowired
	private DemoUserFeignClient demoUserFeignClient;
	
	@Autowired
	private DemoFeignHystrixClient demoFeignHystrixClient;

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/listByPage")
	@ResponseBody
	public Pager<DemoUserVO> listByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		// Sort sort = new Sort(Direction.DESC, "userId");
		// Pageable pageable = new PageRequest(page, size, sort);
		return userWebService.listByPage(page, size);
	}

	/**
	 * feign客户端
	 * @return
	 */
	@RequestMapping("/feignClient")
	@ResponseBody
	public DemoUserVO feignClient() {
		DemoUserVO demoUserVO = demoUserFeignClient.findUserById(1L);
		return demoUserVO;
	}

	
	/**
	 * feignHystrix客户端
	 * @return
	 */
	@RequestMapping("/feignHystrixClient")
	@ResponseBody
	public Map feignHystrixClient() {
		Map demoVO = demoFeignHystrixClient.findDemoById(1L);
		return demoVO;
	}
}
