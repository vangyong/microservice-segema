package cn.segema.cloud.shrio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cn.segema.cloud.shrio.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	 
	 @Query("SELECT u from User u  where u.userName = ?1 ") 
	 public User findByUserName(String userName);
}
