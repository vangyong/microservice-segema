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

import cn.segema.cloud.system.domain.Personal;
import cn.segema.cloud.system.vo.PersonalVO;

@Repository
public interface PersonalRepository extends PagingAndSortingRepository<Personal, BigInteger>,JpaRepository<Personal, BigInteger> ,JpaSpecificationExecutor<Personal>{
	
	 @Query("SELECT p from Personal p  where p.personalName = ?1 ") 
	 public List<Personal> findByPersonalName(String personalName); 
	 
	 
	 @Query(value = "SELECT * FROM sys_personal WHERE if(:#{#personal.personalName}!='',personal_name = :#{#personal.personalName},1=1) and if(:#{#personal.gender}!='',gender = :#{#personal.gender},1=1) ",
			    countQuery = "SELECT count(*) FROM sys_personal WHERE if(:#{#personal.personalName}!='',personal_name = :#{#personal.personalName},1=1) and if(:#{#personal.gender}!='',gender = :#{#personal.gender},1=1)",
			    nativeQuery = true)
	 public	Page<Personal> findByPage(@Param("personal") PersonalVO personal, Pageable pageable);
	 
}
