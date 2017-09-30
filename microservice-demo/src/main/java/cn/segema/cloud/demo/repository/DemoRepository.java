package cn.segema.cloud.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.demo.domain.DemoUser;
import cn.segema.cloud.demo.vo.DemoUserPersonalVO;

@Repository
public interface DemoRepository extends JpaRepository<DemoUser, String> {
	
	
	 @Query("select new cn.segema.cloud.system.vo.DemoUserPersonalVO(u.userId,u.userName,u.nickName,p.personalId,p.personalName)"
	 		+ " from DemoUser u,DemoUserPersonal up,Personal p where u.userName = ?1 and u.userId=up.user and up.personal = p.personalId ") 
	 public List<DemoUserPersonalVO> findUserPersonalByUserName(String userName); 
	 
	 
}
