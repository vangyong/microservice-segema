package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import cn.segema.cloud.system.domain.Address;
import cn.segema.cloud.system.domain.Tenant;
import cn.segema.cloud.system.domain.User;
import cn.segema.cloud.system.repository.AddressRepository;
import cn.segema.cloud.system.repository.TenantRepository;
import cn.segema.cloud.system.repository.UserRepository;
import cn.segema.cloud.system.vo.TenantVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="租户管理")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/tenant")
public class TenantController {

	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private AddressRepository addressRepository;

	@ApiOperation(value = "新增租户", notes = "新增租户")
	@PostMapping
	public ResponseEntity add(@RequestBody Tenant tenant) {
		if(StringUtils.isEmpty(tenant.getMobileNumber())) {
			return new ResponseEntity("mobile number can't be null",HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isEmpty(tenant.getUserId())) {
			return new ResponseEntity("userId can't be null",HttpStatus.BAD_REQUEST);
		}
		if(tenant.getTenantAddressId()==null) {
            Address address =new Address();
            address.setAddressId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
            address.setCreateTime(DateTimeUtil.getCurrentUnixTime());
            addressRepository.save(address);
            tenant.setTenantAddressId(address.getAddressId());
        }
		tenant.setTenantCode(tenant.getMobileNumber());
		if (tenant.getTenantId() == null) {
			tenant.setTenantId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		}
		tenant.setCreateTime(DateTimeUtil.getCurrentUnixTime());
		tenantRepository.save(tenant);
		User user = userRepository.findOne(tenant.getUserId());
		user.setTenantId(tenant.getTenantId());
		userRepository.save(user);
		
		return new ResponseEntity(tenant,HttpStatus.OK);
	}

	@ApiOperation(value = "修改租户", notes = "修改租户")
	@PutMapping
	public ResponseEntity edit(@RequestBody Tenant tenant) {
		Tenant oldTenant = tenantRepository.findOne(tenant.getTenantId());
		if(oldTenant!=null) {
		    BeanUtils.copyProperties(tenant, oldTenant,"createTime");
	        tenantRepository.save(oldTenant);
	        return new ResponseEntity(oldTenant,HttpStatus.OK);
		}else {
		    return new ResponseEntity("can't find Tenant",HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "租户注销", notes = "租户注销")
	@DeleteMapping("/{tenantId}")
	public ResponseEntity delete(@PathVariable BigInteger tenantId) {
		Tenant tenant = tenantRepository.findOne(tenantId);
		if(tenant!=null) {
			tenant.setDeleteStatus(CommonConstant.MAGIC_ONE);
			tenantRepository.save(tenant);
			User user = userRepository.findOne(tenant.getUserId());
			if(user!=null) {
				user.setTenantId(null);
				userRepository.save(user);
			}
		}
		return new ResponseEntity(tenant,HttpStatus.OK);
	}

	@ApiOperation(value = "根据id获取租户", notes = "根据id获取租户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tenantId", value = "租户id", required = true, paramType = "path") })
	@GetMapping("/{tenantId}")
	public ResponseEntity findById(@PathVariable BigInteger tenantId) throws Exception {
		Tenant tenant = tenantRepository.findOne(tenantId);
		return new ResponseEntity(tenant,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据用户id获取租户", notes = "根据用户id获取租户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path") })
	@GetMapping("/user/{userId}")
	public ResponseEntity findByUserId(@PathVariable BigInteger userId) throws Exception {
	    Tenant tenant = tenantRepository.findByUserId(userId);
	    Map tenantMap = new HashMap();
	    tenantMap.put("tenant", tenant);
		if(tenant!=null&&tenant.getTenantAddressId()!=null) {
		    Address tenantAddress =  addressRepository.findOne(tenant.getTenantAddressId());
		    if(tenantAddress!=null) {
		        tenantMap.put("tenantAddress", tenantAddress);
		    }
		}
		return new ResponseEntity(tenantMap,HttpStatus.OK);
	}

	@ApiOperation(value = "分页获取租户", notes = "分页获取租户")
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
	@GetMapping("/page")
	public ResponseEntity findByPage(@RequestParam(defaultValue ="1") int page, 
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(defaultValue = "create_time") String sort,TenantVO tenant) {
		Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
		Pageable pageable = new PageRequest(page - 1, limit, sortOrder);
		Page<Tenant> optionPage = tenantRepository.findByPage(tenant,pageable);
		return new ResponseEntity(optionPage,HttpStatus.OK);
	}

}
