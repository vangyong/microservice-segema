package cn.segema.cloud.wemall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.segema.cloud.wemall.domain.Organization;
import cn.segema.cloud.wemall.repository.OrganizationRepository;

@Service
public class OrganizationService {
	
	@Resource(name = "organizationRepository")
    private OrganizationRepository organizationRepository;
	
	 public List<Organization> getAll() {
	        return (List<Organization>) organizationRepository.findAll();
	    }
	 
	 public Page<Organization> findByPage(Pageable pageable,final Map<String, String> params){

	        Page<Organization> objPage = organizationRepository.findAll(new Specification<Organization>() {

	            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                List<Predicate> lstPredicates = new ArrayList<Predicate>();
	                if (params.get("organizationName")!=null && (!"".equals(params.get("organizationName")))) {
	                    lstPredicates.add(cb.like(root.get("organizationName").as(String.class), "%" + params.get("organizationName") + "%"));
	                }
	                Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
	                return cb.and(lstPredicates.toArray(arrayPredicates));
	            }
	        }, pageable);

	        return objPage;
	    }
	
}
