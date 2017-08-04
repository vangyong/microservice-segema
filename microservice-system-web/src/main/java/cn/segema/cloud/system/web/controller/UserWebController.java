package cn.segema.cloud.system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.segema.cloud.system.web.service.UserWebService;
import cn.segema.cloud.system.web.vo.UserVO;

@Controller
@RequestMapping(value = "/user/web")
public class UserWebController {
	
	@Autowired
	private UserWebService userWebService;

 
    
    @GetMapping("/getTableData") 
    public Page<UserVO> getTableData() {
  	  try { 
  		  int pageNum=1;
  		  int pageSize=20;
  		  String username="A";
  		  Page<UserVO> userList = userWebService.listByPageable(pageNum,pageSize);
  		  System.out.println("this is userList:");
  		  return userList;
  	  } catch (Exception e) { 
  		  e.printStackTrace(); 
  	  } return null; 
  	}
    
    @GetMapping("/listByPageable")
	public Page<UserVO> listByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		Sort sort = new Sort(Direction.DESC, "userId");
		Pageable pageable = new PageRequest(page, size, sort);
		return userWebService.listByPageable(page,size);
	}
    
    @GetMapping("/findById/{userId}")
    public UserVO findById(@PathVariable String userId) {
    	UserVO findOne = this.userWebService.findById(userId);
      return findOne;
    }
}
