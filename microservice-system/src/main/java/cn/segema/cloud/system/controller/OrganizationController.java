package cn.segema.cloud.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.system.domain.Organization;
import cn.segema.cloud.system.repository.OrganizationRepository;
import cn.segema.cloud.system.service.OrganizationService;
import cn.segema.cloud.system.vo.OrganizationPersonalVO;
import cn.segema.cloud.system.vo.OrganizationTreeVO;
import cn.segema.cloud.system.vo.TreeVO;

/**
 * 组织机构Controller
 */
@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private OrganizationRepository organizationRepository;

	/**
	 * @param userId
	 * @return Organization
	 */
	@GetMapping("/{organizationId}")
	public Organization findById(@PathVariable String organizationId) throws Exception {
		Organization organization = organizationRepository.findOne(organizationId);
		return organization;
	}

	/**
	 * @param organization
	 * @return List<Organization>
	 */
	@GetMapping("/list")
	public List<Organization> list(Organization organization) {
		List<Organization> organizationList = organizationRepository.findAll();
		return organizationList;
	}

	/**
	 * @param organization
	 * @return Organization
	 */
	@PostMapping("/add")
	public Organization add(Organization organization) {
		if(organization.getParent()!=null){
			Integer maxOrganizationCode = organizationRepository.findMaxOrganization(organization.getParent().getOrganizationId());
			if(maxOrganizationCode!=null) {
				if(organization.getOrganizationCode()!=null) {
					if(organization.getOrganizationCode()>maxOrganizationCode) {
						organization.setOrganizationId(String.valueOf(organization.getOrganizationCode()));
					}
				}else {
					organization.setOrganizationCode(maxOrganizationCode+1);
					organization.setOrganizationId(String.valueOf(maxOrganizationCode+1));
				}
			}else {
				String organizationCode = organization.getParent().getOrganizationId()+"001";
				organization.setOrganizationCode(Integer.valueOf(organizationCode));
				organization.setOrganizationId(String.valueOf(organizationCode));
			}
		}
		organizationRepository.save(organization);
		return organization;
	}

	/**
	 * @param organization
	 * @return Organization
	 */
	@RequestMapping(value = "/edit")
	public Organization edit(Organization organization) {
		organizationRepository.save(organization);
		return organization;
	}

	/**
	 * @param organization
	 * @return Organization
	 */
	@PostMapping(value = "/delete")
	public Organization delete(Organization organization) {
		organizationRepository.delete(organization);
		return organization;
	}

	@GetMapping("/listOrganizationPersonalByName/{organizationName}")
	public List<OrganizationPersonalVO> listOrganizationPersonalByName(@PathVariable String organizationName) {
		List<OrganizationPersonalVO> organizationList = organizationRepository
				.findOrganizationPersonalByName(organizationName);
		return organizationList;
	}

//	@GetMapping("/listByPage")
//	public Page<OrganizationVO> listByPage(PagerParamVO pagerParam) {
//		//Sort sort = new Sort(Direction.DESC, pagerParam.getOrder());
//		Sort sort = new Sort(Direction.DESC, "organizationId");
//		Pageable pageable = new PageRequest(pagerParam.getCurr() - 1, pagerParam.getNums(), sort);
//		Page<Organization> page = organizationService.findByPage(pageable, pagerParam.getParams());
//		Page<OrganizationVO> organizationVOPage = new Page<OrganizationVO>();
//		//Page<Organization> page = organizationRepository.findAll(pageable);
//		List<Organization> content = page.getContent();
//		List<OrganizationVO> data = new ArrayLis<OrganizationVO>();
//		if(content!=null&&content.size()>0) {
//			for(Organization organization:content) {
//				OrganizationVO organizationVO = new OrganizationVO();
//				BeanUtils.copyProperties(organization, organizationVO, "chidren","parent");
//				organizationVO.setParentId(organization.getParent()==null?null:organization.getParent().getOrganizationId());
//				data.add(organizationVO);
//			}
//		}
//		organizationVOPage.setData(data);
//		return page;
//	}
	
	@GetMapping("/maxOrganization/{parentId}")
	public Integer maxOrganization(@PathVariable String parentId) {
		Integer organizationCode = organizationRepository.findMaxOrganization(parentId);
		return organizationCode;
	}
	
	@GetMapping("/treeList/{parentId}")
	public List<OrganizationTreeVO> treeList(@PathVariable String parentId) {
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
		
		return treeList;
	}
	
	@GetMapping("/treeTest/{parentId}")
	public List<TreeVO> treeTest(@PathVariable String parentId) {
		
		List<TreeVO> treeList = new ArrayList<TreeVO>();
		TreeVO treeVO1 = new TreeVO();
		treeVO1.setId("1");
		treeVO1.setText("NODE1节点1");
		TreeVO treeVO2 = new TreeVO();
		treeVO2.setId("2");
		treeVO2.setText("NODE2节点2");
		TreeVO treeVO3 = new TreeVO();
		treeVO3.setId("3");
		treeVO3.setText("NODE3节点3");
		TreeVO treeVO4 = new TreeVO();
		treeVO4.setId("4");
		treeVO4.setText("NODE4节点4");
		TreeVO treeVO5 = new TreeVO();
		treeVO5.setId("5");
		treeVO5.setText("NODE5节点5");
		List<TreeVO> children4 = new ArrayList<TreeVO>();
		children4.add(treeVO5);
		treeVO4.setChildren(children4);
		
	
		treeList.add(treeVO1);
		treeList.add(treeVO2);
		treeList.add(treeVO3);
		treeList.add(treeVO4);
		
		
		return treeList;
	}
}
