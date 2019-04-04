package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.CommonConstant;
import cn.segema.cloud.common.page.Pager;
import cn.segema.cloud.system.domain.Certificate;
import cn.segema.cloud.system.domain.Organization;
import cn.segema.cloud.system.domain.User;
import cn.segema.cloud.system.repository.OrganizationRepository;
import cn.segema.cloud.system.vo.OrganizationPersonalVO;
import cn.segema.cloud.system.vo.OrganizationTreeVO;
import cn.segema.cloud.system.vo.OrganizationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="组织机构")
@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationRepository organizationRepository;


	@ApiOperation(value = "新增组织机构", notes = "新增组织机构")
	@PostMapping
	public ResponseEntity add(@RequestBody Organization organization) {
		if(organization.getParent()!=null){
			BigInteger maxOrganizationCode = organizationRepository.findMaxOrganization(organization.getParent().getOrganizationId());
			if(maxOrganizationCode!=null) {
				if(organization.getOrganizationCode()!=null) {
					if(organization.getOrganizationCode().compareTo(maxOrganizationCode)>0) {
						
						organization.setOrganizationId(organization.getOrganizationCode());
					}
				}else {
					organization.setOrganizationCode(maxOrganizationCode.add(BigInteger.ONE));
					organization.setOrganizationId(maxOrganizationCode.add(BigInteger.ONE));
				}
			}else {
				String organizationCode = organization.getParent().getOrganizationId()+"001";
				organization.setOrganizationCode(BigInteger.valueOf(Long.valueOf(organizationCode)));
				organization.setOrganizationId(BigInteger.valueOf(Long.valueOf(organizationCode)));
			}
		}
		organizationRepository.save(organization);
		return new ResponseEntity(organization,HttpStatus.OK);
	}

	@ApiOperation(value = "修改组织机构", notes = "修改组织机构")
	@PutMapping
	public ResponseEntity edit(@RequestBody Organization organization) {
	    Organization oldOrganization = organizationRepository.findOne(organization.getOrganizationId());
        if(oldOrganization!=null) {
            BeanUtils.copyProperties(organization, oldOrganization,"createTime");
            organizationRepository.save(oldOrganization);
            return new ResponseEntity(oldOrganization,HttpStatus.OK);
        }else {
            return new ResponseEntity("can't find Organization",HttpStatus.BAD_REQUEST);
        }
	}

	@ApiOperation(value = "删除组织机构", notes = "删除组织机构")
	@DeleteMapping("/{organizationId}")
	public ResponseEntity delete(@PathVariable BigInteger organizationId) {
		Organization organization = organizationRepository.findOne(organizationId);
		if(organization!=null) {
			organization.setDeleteStatus(CommonConstant.MAGIC_ONE);
			organizationRepository.save(organization);
		}
		return new ResponseEntity(organization,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据id获取组织机构", notes = "根据id获取组织机构")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "组织机构id", required = true, paramType = "path") })
	@GetMapping("/{organizationId}")
	public ResponseEntity findById(@PathVariable BigInteger organizationId) throws Exception {
		Organization organization = organizationRepository.findOne(organizationId);
		return new ResponseEntity(organization,HttpStatus.OK);
	}

	@ApiOperation(value = "根据名称获取组织机构", notes = "根据名称获取组织机构")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "组织机构名称", required = true, paramType = "path") })
	@GetMapping("/personal/{organizationName}")
	public ResponseEntity findPersonalByName(@PathVariable String organizationName) {
		List<OrganizationPersonalVO> organizationList = organizationRepository.findOrganizationPersonalByName(organizationName);
		return new ResponseEntity(organizationList,HttpStatus.OK);
	}

	@ApiOperation(value = "分页获取用户", notes = "分页获取用户")
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
	@GetMapping("/page")
	public ResponseEntity findByPage(@RequestParam(defaultValue ="1") int page, 
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(defaultValue = "organizationId") String sort) {
		Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
		Pageable pageable = new PageRequest(page - 1, limit, sortOrder);
		Page<Organization> organizationPage = organizationRepository.findAll(pageable);
		List<Organization> content = organizationPage.getContent();
		List<OrganizationVO> data = new ArrayList<OrganizationVO>();
		if(content!=null&&content.size()>0) {
			for(Organization organization:content) {
				OrganizationVO organizationVO = new OrganizationVO();
				BeanUtils.copyProperties(organization, organizationVO, "chidren","parent");
				organizationVO.setParentId(organization.getParent()==null?null:organization.getParent().getOrganizationId());
				data.add(organizationVO);
			}
		}
		return new ResponseEntity(organizationPage,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据节点id获取最大编码", notes = "根据节点id获取最大编码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parentId", value = "父级节点id", required = true, paramType = "path") })
	@GetMapping("/max-code/{parentId}")
	public ResponseEntity findMaxCode(@PathVariable BigInteger parentId) {
		BigInteger organizationCode = organizationRepository.findMaxOrganization(parentId);
		return new ResponseEntity(organizationCode,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据节点id获取组织机构树", notes = "根据节点id获取组织机构树")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parentId", value = "父级节点id", required = true, paramType = "path") })
	@GetMapping("/tree-list/{parentId}")
	public ResponseEntity findTreeList(@PathVariable String parentId) {
		List<Organization> organizationList = organizationRepository.findTreeList(parentId);
		List<OrganizationTreeVO> treeList = new ArrayList<OrganizationTreeVO>();
		if(organizationList!=null&&organizationList.size()>0) {
			for(Organization organization:organizationList) {
				OrganizationTreeVO organizationTreeVO = new OrganizationTreeVO();
				organizationTreeVO.setOrganizationId(organization.getOrganizationId());
				organizationTreeVO.setOrganizationName(organization.getOrganizationName());
				organizationTreeVO.setChildren(null);
				treeList.add(organizationTreeVO);
			}
		}
		return new ResponseEntity(treeList,HttpStatus.OK);
	}

}
