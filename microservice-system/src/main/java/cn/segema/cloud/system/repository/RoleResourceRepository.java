package cn.segema.cloud.system.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.RoleResource;

@Repository
public interface RoleResourceRepository extends PagingAndSortingRepository<RoleResource, BigInteger>,JpaRepository<RoleResource, BigInteger> ,JpaSpecificationExecutor<RoleResource>{
	 
	 @Query("SELECT rr from RoleResource rr  where rr.role = ?1 ") 
	 public List<RoleResource> findByRoleId(BigInteger roleId); 
	 
	 @Query("SELECT rr from RoleResource rr  where rr.resource = ?1 ") 
	 public List<RoleResource> findByResourceId(BigInteger resourceId); 
	 
	 @Query(value = "DELETE FROM SYS_ROLE_RESOURCE WHERE role_id = ?1 ",nativeQuery = true) 
	 public List<RoleResource> deleteByRoleId(BigInteger roleId); 
	 
	 @Query(value = "DELETE FROM SYS_ROLE_RESOURCE WHERE resource_id = ?1 ",nativeQuery = true) 
	 public List<RoleResource> deleteByResourceId(BigInteger resourceId); 
	 
	
	
}
