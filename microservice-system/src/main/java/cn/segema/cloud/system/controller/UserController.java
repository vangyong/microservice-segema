package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import cn.segema.cloud.system.domain.User;
import cn.segema.cloud.system.repository.UserRepository;
import cn.segema.cloud.system.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="用户管理")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "新增用户", notes = "新增用户")
	@PostMapping
	public ResponseEntity add(@RequestBody User user) {
		if (user.getUserId() == null) {
			user.setUserId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		}
		User checkUser = userRepository.findByUserName(user.getUserName());
		if(checkUser!=null) {
			if(!user.getUserId().equals(checkUser.getUserId())) {
				return new ResponseEntity("user name has exist",HttpStatus.BAD_REQUEST);
			}
		}
		User checkUser2 = userRepository.findByMobileNumber(user.getMobileNumber());
		if(checkUser2!=null) {
			if(!user.getUserId().equals(checkUser2.getUserId())) {
				return new ResponseEntity("user mobileNumber has exist",HttpStatus.BAD_REQUEST);
			}
		}
		String pass = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(pass);
		user.setCreateTime(DateTimeUtil.getCurrentUnixTime());
		userRepository.save(user);
		
		return new ResponseEntity(user,HttpStatus.OK);
	}

	@ApiOperation(value = "修改用户", notes = "修改用户")
	@PutMapping
	public ResponseEntity edit(@RequestBody User user) {
		User checkUser = userRepository.findByUserName(user.getUserName());
		if(checkUser!=null) {
			if(!user.getUserId().equals(checkUser.getUserId())) {
				return new ResponseEntity("user name has exist",HttpStatus.BAD_REQUEST);
			}
		}
		User checkUser2 = userRepository.findByMobileNumber(user.getMobileNumber());
		if(checkUser2!=null) {
			if(!user.getUserId().equals(checkUser2.getUserId())) {
				return new ResponseEntity("user mobileNumber has exist",HttpStatus.BAD_REQUEST);
			}
		}
		User oldUser = userRepository.findOne(user.getUserId());
		if(oldUser!=null) {
		    BeanUtils.copyProperties(user, oldUser,"createTime");
	        userRepository.save(oldUser);
	        return new ResponseEntity(oldUser,HttpStatus.OK);
		}else {
		    return new ResponseEntity("can't find User",HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "删除用户", notes = "删除用户")
	@DeleteMapping("/{userId}")
	public ResponseEntity delete(@PathVariable BigInteger userId) {
		User user = userRepository.findOne(userId);
		if(user!=null) {
			user.setDeleteStatus(CommonConstant.MAGIC_ONE);
			userRepository.save(user);
		}
		return new ResponseEntity(user,HttpStatus.OK);
	}

	@ApiOperation(value = "根据id获取用户", notes = "根据id获取用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path") })
	@GetMapping("/{userId}")
	public ResponseEntity findById(@PathVariable BigInteger userId) throws Exception {
		User user = userRepository.findOne(userId);
		return new ResponseEntity(user,HttpStatus.OK);
	}

	@ApiOperation(value = "根据用户名获取用户", notes = "根据用户名获取用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "path") })
	@GetMapping("/user-name/{userName}")
	public ResponseEntity findByUserName(@PathVariable String userName) {
		User user = userRepository.findByUserName(userName);
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据昵称获取用户", notes = "根据昵称获取用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "nickName", value = "昵称", required = true, paramType = "path") })
	@GetMapping("/nick-name/{nickName}")
	public ResponseEntity findByNickName(@PathVariable String nickName) {
		List<User> userList = userRepository.findByNickName(nickName);
		return new ResponseEntity(userList,HttpStatus.OK);
	}

	@ApiOperation(value = "分页获取用户", notes = "分页获取用户")
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
	@GetMapping("/page")
	public ResponseEntity findByPage(@RequestParam(defaultValue ="1") int page, 
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(defaultValue = "create_time") String sort,UserVO user) {
		Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
		Pageable pageable = new PageRequest(page - 1, limit, sortOrder);
		Page<User> userPage = userRepository.findByPage(user,pageable);
		return new ResponseEntity(userPage,HttpStatus.OK);
	}

}
