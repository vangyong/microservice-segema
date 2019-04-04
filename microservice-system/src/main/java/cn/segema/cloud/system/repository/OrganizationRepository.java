package cn.segema.cloud.system.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.Organization;
import cn.segema.cloud.system.vo.OrganizationPersonalVO;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, BigInteger>,JpaRepository<Organization, BigInteger>,JpaSpecificationExecutor<Organization>{
	
	 @Query("select new cn.segema.cloud.system.vo.OrganizationPersonalVO(o.organizationId,o.organizationName,o.organizationCode,p.personalId,p.personalName,op.type)"
	 		+ " from Organization o,OrganizationPersonal op,Personal p where o.organizationName = ?1 and o.organizationId=op.organization and op.personal = p.personalId ") 
	 public List<OrganizationPersonalVO> findOrganizationPersonalByName(String organizationName); 
	 
	 @Query("SELECT o from Organization o  where o.organizationName = ?1 ") 
	 public List<Organization> findByOrganizationName(String organizationName); 
	 
	 @Query("SELECT max(o.organizationCode) as organizationCode from Organization o  where o.parent.organizationId = ?1 ") 
	 public BigInteger findMaxOrganization(BigInteger parentId); 
	 
	 @Query("SELECT o from Organization o  where o.parent.organizationId = ?1 ") 
	 public List<Organization> findTreeList(String parentId); 
}
