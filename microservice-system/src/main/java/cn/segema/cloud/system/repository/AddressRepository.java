package cn.segema.cloud.system.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.Address;
import cn.segema.cloud.system.domain.Region;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, BigInteger>,JpaRepository<Address, BigInteger>,JpaSpecificationExecutor<Address>{
	 
	 
	 @Query(value ="SELECT A.* FROM SYS_ADDRESS A WHERE A.PARENT_CODE= ?1",nativeQuery = true) 
	 public List<Region> findRegionByParentCode(String parentCode);
	 
	
}
