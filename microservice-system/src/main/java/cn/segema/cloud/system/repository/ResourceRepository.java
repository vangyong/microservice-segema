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

import cn.segema.cloud.system.domain.Resource;
import cn.segema.cloud.system.domain.User;
import cn.segema.cloud.system.vo.ResourceVO;

@Repository
public interface ResourceRepository extends PagingAndSortingRepository<Resource, BigInteger>,JpaRepository<Resource, BigInteger> ,JpaSpecificationExecutor<Resource>{
	 
	 @Query("SELECT r from Resource r  where r.resourceName = ?1 ") 
	 public List<User> findByName(String resourceName); 
	 
	 @Query("SELECT r from Resource r") 
	 public List<User> findAllUser(); 
	 
	 @Query(value = "SELECT * FROM sys_resource WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) and if(:#{#user.gender}!='',gender = :#{#user.gender},1=1) ",
			    countQuery = "SELECT count(*) FROM sys_resource WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) and if(:#{#user.gender}!='',gender = :#{#user.gender},1=1)",
			    nativeQuery = true)
	 public	Page<Resource> findByPage(@Param("user") ResourceVO user, Pageable pageable);
	
}
