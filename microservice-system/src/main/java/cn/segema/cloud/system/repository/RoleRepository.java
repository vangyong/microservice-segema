package cn.segema.cloud.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
	 
	 @Query("select r from Role r where r.roleName = ?1") 
	 List<Role> getByRoleName(String roleName); 
	 
	 @Query("select r from Role r where r.roleName = ?1") 
	 List<Role> listByRoleName(String roleName); 
	 
	 @Query("select count(r) from Role r where r.roleName = ?1") 
	 List<Role> countByRoleName(String roleName); 
	 
	 @Query("select r from Role r where r.roleName = ?1") 
	 List<Role> saveRole(String roleName); 
	 
	 @Query("select r from Role r where r.roleName = ?1") 
	 List<Role> updateRole(String roleName); 
	 
	 @Query("select r from Role r where r.roleName = ?1") 
	 List<Role> deleteRole(String roleName); 
	 
	 
}
