package cn.segema.cloud.system.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.Region;

@Repository
public interface RegionRepository extends PagingAndSortingRepository<Region, BigInteger>,JpaRepository<Region, BigInteger>,JpaSpecificationExecutor<Region>{
	 
	 @Query(value ="SELECT A.* FROM SYS_REGION A WHERE SUBSTR(A.REGION_CODE, 3, 7)='0000000' AND A.COUNTRY_CODE = ?1",nativeQuery = true) 
	 public List<Region> findProvinceList(String countryCode); 
	 
	 @Query(value ="SELECT A.* FROM SYS_REGION A WHERE SUBSTR(A.REGION_CODE, 5, 5)='00000' AND SUBSTR(A.REGION_CODE, 3, 2)!='00' AND SUBSTR(A.REGION_CODE, 1, 2)= SUBSTR(?1,1,2)",nativeQuery = true) 
	 public List<Region> findCityList(String regionCode); 
	 
	 @Query(value ="SELECT A.* FROM SYS_REGION A WHERE SUBSTR(A.REGION_CODE, 7, 3)='000' AND SUBSTR(A.REGION_CODE, 5, 2)!='00' AND SUBSTR(A.REGION_CODE, 1, 4)= SUBSTR(?1,1,4)",nativeQuery = true) 
	 public List<Region> findCountyList(String regionCode); 
	
	 @Query(value ="SELECT A.* FROM SYS_REGION A WHERE SUBSTR(A.REGION_CODE, 10, 3)!='000' AND SUBSTR(A.REGION_CODE, 1, 6)= SUBSTR(?1, 1,6)",nativeQuery = true) 
	 public List<Region> findTownsList(String regionCode); 
	 
	 @Query(value ="SELECT A.* FROM SYS_REGION A WHERE A.REGION_CODE= ?1",nativeQuery = true) 
	 public Region findRegionByCode(String regionCode);
	 
	 @Query(value ="SELECT A.* FROM SYS_REGION A WHERE A.PARENT_CODE= ?1",nativeQuery = true) 
	 public List<Region> findRegionByParentCode(String parentCode);
	 
	
}
