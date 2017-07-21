package cn.segema.cloud.system.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	 //@Select("SELECT CONTRACTID,CONTTRRACTNAME,TOTALMONEY  FROM spring_cloud_study.contract WHERE contractid=#{0};") 
	 //public  Map<String, Object> getTableData(int pageNum, int pageSize, String username);
	 
	 @Query("select u from User u where u.username = ?1") 
	 public List<User> findByUsername(String username); 
}
