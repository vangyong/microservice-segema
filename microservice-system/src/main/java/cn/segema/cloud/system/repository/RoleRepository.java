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

import cn.segema.cloud.system.domain.Role;
import cn.segema.cloud.system.vo.RoleVO;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, BigInteger>,JpaRepository<Role, BigInteger>,JpaSpecificationExecutor<Role> {
	
	 @Query("select r from Role r where r.roleName = ?1") 
	 List<Role> findByRoleName(String roleName); 
	 
	 @Query(value = "SELECT * FROM sys_role WHERE if(:#{#role.roleName}!='',role_name = :#{#role.roleName},1=1) and if(:#{#role.roleCode}!='',role_code = :#{#role.roleCode},1=1) ",
			    countQuery = "SELECT count(*) FROM sys_role WHERE if(:#{#role.roleName}!='',role_name = :#{#role.roleName},1=1) and if(:#{#role.roleCode}!='',role_code = :#{#role.roleCode},1=1)",
			    nativeQuery = true)
	 public	Page<Role> findByPage(@Param("role") RoleVO role, Pageable pageable);
}
