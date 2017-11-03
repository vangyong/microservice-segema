package cn.segema.cloud.auth.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.auth.server.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	 
	 @Query("SELECT u from User u  where u.userName = ?1 ") 
	 public List<User> findByUserName(String userName); 
}
