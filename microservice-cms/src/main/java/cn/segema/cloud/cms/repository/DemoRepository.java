package cn.segema.cloud.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cn.segema.cloud.cms.domain.DemoUser;

@Repository
public interface DemoRepository extends JpaRepository<DemoUser, String> {
	
	
//	 @Query("select new cn.segema.cloud.demo.vo.DemoUserPersonalVO(u.userId,u.userName,u.nickName,p.personalId,p.personalName)"
//	 		+ " from DemoUser u,DemoUserPersonal up,DemoPersonal p where u.userName = ?1 and u.userId=up.user and up.personal = p.personalId ") 
//	 public List<DemoUserPersonalVO> findUserPersonalByUserName(String userName); 
	 
	 
}
