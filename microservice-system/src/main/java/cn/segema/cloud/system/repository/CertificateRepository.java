package cn.segema.cloud.system.repository;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.Certificate;

@Repository
public interface CertificateRepository extends PagingAndSortingRepository<Certificate, BigInteger>,JpaRepository<Certificate, BigInteger>,JpaSpecificationExecutor<Certificate>{
	 
	 @Query("select c from Certificate c  where c.businessId = ?1 and certificateType =?2 ") 
	 public Certificate findByBusinessAndType(BigInteger businessId,String type); 
	 
	 @Query(value = "SELECT * FROM sys_certificate  ",
			    countQuery = "SELECT count(*) FROM sys_certificate ",
			    nativeQuery = true)
	 public	Page<Certificate> findByPage(@Param("certificate") Certificate certificate, Pageable pageable);
}
