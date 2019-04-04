package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.constants.CommonConstant;
import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.system.domain.Personal;
import cn.segema.cloud.system.domain.Role;
import cn.segema.cloud.system.domain.Tenant;
import cn.segema.cloud.system.repository.RoleRepository;
import cn.segema.cloud.system.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="角色管理")
@Controller
@RequestMapping(value = ApiConstant.API_VERSION + "/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	@ApiOperation(value = "新增角色", notes = "新增角色")
	@PostMapping("/add")
	public ResponseEntity add(@RequestBody Role role) {
		if (role.getRoleId() == null) {
			role.setRoleId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		}
		role.setCreateTime(DateTimeUtil.getCurrentUnixTime());
		roleRepository.save(role);
		return new ResponseEntity(role,HttpStatus.OK);
	}

	@ApiOperation(value = "修改角色", notes = "修改角色")
	@PutMapping
	public ResponseEntity edit(@RequestBody Role role) {
	    Role oldRole = roleRepository.findOne(role.getRoleId());
        if(oldRole!=null) {
            BeanUtils.copyProperties(role, oldRole,"createTime");
            roleRepository.save(oldRole);
            return new ResponseEntity(oldRole,HttpStatus.OK);
        }else {
            return new ResponseEntity("can't find Role",HttpStatus.BAD_REQUEST);
        }
	}

	@ApiOperation(value = "删除角色", notes = "删除角色")
	@DeleteMapping("/{roleId}")
	public ResponseEntity delete(@PathVariable BigInteger roleId) {
		Role role = roleRepository.findOne(roleId);
		if(role!=null) {
			role.setDeleteStatus(CommonConstant.MAGIC_ZERO);
			roleRepository.save(role);
		}
		return new ResponseEntity(role,HttpStatus.OK);
	}

	@ApiOperation(value = "根据id获取角色", notes = "根据id获取角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "path") })
	@GetMapping("/{roleId}")
	public ResponseEntity findById(@PathVariable BigInteger roleId) {
		Role role = this.roleRepository.findOne(roleId);
		return new ResponseEntity(role,HttpStatus.OK);
	}

	@ApiOperation(value = "分页获取角色", notes = "分页获取角色")
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
	@GetMapping("/page")
	public ResponseEntity findByPage(@RequestParam(defaultValue ="1") int page, 
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(defaultValue = "role_id") String sort,RoleVO role) {
		Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
		Pageable pageable = new PageRequest(page - 1, limit, sortOrder);
		Page<Role> rolePage =  roleRepository.findByPage(role, pageable);
		return new ResponseEntity(rolePage,HttpStatus.OK);
	}
}
