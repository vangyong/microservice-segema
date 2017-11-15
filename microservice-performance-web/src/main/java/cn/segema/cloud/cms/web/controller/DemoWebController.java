package cn.segema.cloud.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.segema.cloud.cms.web.service.DemoWebService;
import cn.segema.cloud.cms.web.vo.DemoUserVO;
import cn.segema.cloud.common.page.Pager;

@Controller
@RequestMapping(value = "/demo/web")
public class DemoWebController {
	
	@Autowired
	private DemoWebService userWebService;
    
	/**
	 * 分页查询
	 * @param page
	 * @param size
	 * @return
	 */
    @RequestMapping("/listByPage")
    @ResponseBody
	public Pager<DemoUserVO> listByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		//Sort sort = new Sort(Direction.DESC, "userId");
		//Pageable pageable = new PageRequest(page, size, sort);
		return userWebService.listByPage(page,size);
	}
    

}
