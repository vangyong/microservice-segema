package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.constants.CommonConstant;
import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.system.domain.Personal;
import cn.segema.cloud.system.domain.User;
import cn.segema.cloud.system.domain.UserPersonal;
import cn.segema.cloud.system.repository.PersonalRepository;
import cn.segema.cloud.system.repository.UserPersonalRepository;
import cn.segema.cloud.system.repository.UserRepository;
import cn.segema.cloud.system.vo.PersonalVO;
import cn.segema.cloud.system.vo.UserPersonalVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 人员信息Controller
 */
@Api(tags="人员信息")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/personal")
public class PersonalController {

	@Autowired
	private PersonalRepository personalRepository;
	
	@Autowired
	private UserPersonalRepository userPersonalRepository;
	
	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "新增人员信息", notes = "新增人员信息")
	@PostMapping
	public ResponseEntity add(@RequestBody Personal personal) {
		if (personal.getPersonalId() == null) {
			personal.setPersonalId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		}
		personal.setCreateTime(DateTimeUtil.getCurrentUnixTime());
		personalRepository.save(personal);
		return new ResponseEntity(personal,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据userId新增人员信息", notes = "根据userId新增人员信息")
	@PostMapping("/user")
	public ResponseEntity addByUserId(@RequestBody Personal personal,BigInteger userId) {
		if(userId==null) {
			return new ResponseEntity("userId can't be null",HttpStatus.BAD_REQUEST);
		}
		User user = userRepository.findOne(userId);
		if (personal.getPersonalId() == null) {
			personal.setPersonalId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		}
		personal.setCreateTime(DateTimeUtil.getCurrentUnixTime());
		personalRepository.save(personal);
		UserPersonal userPersonal = new UserPersonal();
		userPersonal.setUserPersonalId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		userPersonal.setUser(user);
		userPersonal.setPersonal(personal);
		userPersonalRepository.save(userPersonal);
		return new ResponseEntity(personal,HttpStatus.OK);
	}

	@ApiOperation(value = "修改人员信息", notes = "修改人员信息")
	@PutMapping
	public ResponseEntity edit(Personal personal) {
		Personal oldPersonal = personalRepository.findOne(personal.getPersonalId());
		if(oldPersonal!=null) {
		    BeanUtils.copyProperties(personal, oldPersonal,"createTime");
	        personalRepository.save(oldPersonal);
	        return new ResponseEntity(oldPersonal,HttpStatus.OK);
		}else {
		    return new ResponseEntity("can't find personal",HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "删除人员信息", notes = "删除人员信息")
	@DeleteMapping("/{personalId}")
	public ResponseEntity delete(@PathVariable BigInteger personalId) {
		Personal personal = personalRepository.findOne(personalId);
//		List<UserPersonal> userPersonal = userPersonalRepository.findByPersonalId(personalId);
//		if(!CollectionUtils.isEmpty(userPersonal)) {
//			userPersonalRepository.deleteAll(userPersonal);
//		}
		if(personal!=null) {
			personal.setDeleteStatus(CommonConstant.MAGIC_ONE);
			personalRepository.delete(personal);
		}
		return new ResponseEntity(personal,HttpStatus.OK);
	}

	@ApiOperation(value = "根据id获取人员信息", notes = "根据id获取人员信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "personalId", value = "人员id", required = true, paramType = "path") })
	@GetMapping("/{personalId}")
	public ResponseEntity findById(@PathVariable BigInteger personalId) throws Exception {
		Personal personal = personalRepository.findOne(personalId);
		return new ResponseEntity(personal,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据用户名获取人员信息", notes = "根据用户名获取人员信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "path") })
	@GetMapping("/user/name/{userName}")
	public ResponseEntity findPersonalByUserName(@PathVariable String userName) {
		User user = userRepository.findByUserName(userName);
		UserPersonalVO userPersonalVO = userRepository.findUserPersonalByUserId(user.getUserId());
		Personal personal = personalRepository.findOne(userPersonalVO.getPersonalId());
		Map map = new HashMap<>();
		map.put("user", user);
		map.put("personal", personal);
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据用户id获取人员信息", notes = "根据用户id获取人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path") })
    @GetMapping("/user/{userId}")
    public ResponseEntity findByUserId(@PathVariable BigInteger userId) {
	    User user = userRepository.findOne(userId);
        UserPersonalVO userPersonalVO = userRepository.findUserPersonalByUserId(userId);
        Map map = new HashMap<>();
        map.put("user", user);
        if(userPersonalVO!=null) {
            Personal personal = personalRepository.findOne(userPersonalVO.getPersonalId());
            map.put("personal", personal);
        }
        return new ResponseEntity(map,HttpStatus.OK);
    }
	
	@ApiOperation(value = "根据真实姓名获取人员信息", notes = "根据真实姓名获取人员信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "personalName", value = "真实姓名", required = true, paramType = "path") })
	@GetMapping("/name/{personalName}")
	public ResponseEntity findByPersonalName(@PathVariable String personalName) {
		List<Personal> personalList = personalRepository.findByPersonalName(personalName);
		return new ResponseEntity(personalList,HttpStatus.OK);
	}

	@ApiOperation(value = "分页获取用户", notes = "分页获取用户")
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
	@GetMapping("/page")
	public ResponseEntity findByPage(@RequestParam(defaultValue ="1") int page, 
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(defaultValue = "user_id") String sort,PersonalVO personal) {
		Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
		Pageable pageable = new PageRequest(page - 1, limit, sortOrder);
		Page<Personal> personalPage = personalRepository.findByPage(personal,pageable);
		return new ResponseEntity(personalPage,HttpStatus.OK);
	}

}
