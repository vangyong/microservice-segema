package cn.segema.cloud.system.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import cn.segema.cloud.system.domain.UserPersonal;

@Repository
public interface UserPersonalRepository extends PagingAndSortingRepository<UserPersonal, BigInteger>,
		JpaRepository<UserPersonal, BigInteger>, JpaSpecificationExecutor<UserPersonal> {
	
	 @Query("SELECT up from UserPersonal up  where up.personal.id = ?1 ") 
	 public List<UserPersonal> findByPersonalId(BigInteger personalId); 

}
