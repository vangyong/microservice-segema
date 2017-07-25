package cn.segema.cloud.system.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.segema.cloud.system.web.domain.User;
import cn.segema.cloud.system.web.service.WebUserService;

@Controller
public class WebUserController {
	
	@Autowired
	private WebUserService webUserService;

    @RequestMapping("contract/contractlist")
    public String page(Model model){
    	model.addAttribute("message","张三");
        return "contract/contractlist";
    }
    
    @GetMapping("/getTableData") 
    public List<User> getTableData() {
  	  try { 
  		  int pageNum=1;
  		  int pageSize=20;
  		  String username="A";
  		  List<User> userList = webUserService.listByPageable(pageNum,pageSize);
  		  System.out.println("this is userList:");
  		  return userList;
  	  } catch (Exception e) { 
  		  e.printStackTrace(); 
  	  } return null; 
  	}
    
/*    @GetMapping("/listByPageable")
	public Page<User> listByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {

		Sort sort = new Sort(Direction.DESC, "contractId");
		Pageable pageable = new PageRequest(page, size, sort);
		webUserService.listByPageable(pageNum,pageSize);
		return contractRepository.findAll(pageable);
	}*/
    
}
