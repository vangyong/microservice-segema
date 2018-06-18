package cn.segema.cloud.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.segema.cloud.demo.domain.DemoRole;

public interface DemoRoleMapper {
	@Select("select * from demo_role_mybatis where roleid = #{id}")
	DemoRole findRoleById(int id);

	@Select("select * from demo_role_mybatis")
	List<DemoRole> findAllRoles();

	@Insert("INSERT INTO DemoRole(username, name, age, balance) VALUES(#{username}, #{name}, #{age}, #{balance})")
	int insertRole(DemoRole role);

}
