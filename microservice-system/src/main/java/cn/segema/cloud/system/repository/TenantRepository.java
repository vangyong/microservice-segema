package cn.segema.cloud.system.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.Tenant;
import cn.segema.cloud.system.vo.TenantVO;

@Repository
public interface TenantRepository extends PagingAndSortingRepository<Tenant, BigInteger>,JpaRepository<Tenant, BigInteger>,JpaSpecificationExecutor<Tenant>{
	 
	 @Query("SELECT t from Tenant t  where t.userId = ?1 ") 
	 public Tenant findByUserId(BigInteger userId); 
	 
	 @Query(value = "SELECT * FROM sys_tenant WHERE if(:#{#tenant.userId}!='',user_id = :#{#tenant.userId},1=1) and if(:#{#tenant.mobileNumber}!='',mobile_number = :#{#tenant.mobileNumber},1=1)  ",
			    countQuery = "SELECT count(*) FROM sys_tenant WHERE if(:#{#tenant.userId}!='',user_id = :#{#tenant.userId},1=1) and if(:#{#tenant.mobileNumber}!='',mobile_number = :#{#tenant.mobileNumber},1=1) ",
			    nativeQuery = true)
	 public	Page<Tenant> findByPage(@Param("tenant") TenantVO tenant, Pageable pageable);
}
