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

import cn.segema.cloud.system.domain.Option;
import cn.segema.cloud.system.vo.OptionVO;

@Repository
public interface OptionRepository extends PagingAndSortingRepository<Option, BigInteger>,JpaRepository<Option, BigInteger>,JpaSpecificationExecutor<Option>{
	 
	 @Query("SELECT o from Option o  where o.optionType = ?1 ") 
	 public List<Option> findValueByOptionType(String optionType); 
	 
	 @Query("SELECT o from Option o  where o.optionType = ?1 and o.optionKey = ?2 ") 
	 public List<Option> findValueByTypeKey(String optionType,String optionKey); 
	 
	 @Query(value ="SELECT * FROM SYS_OPTION ",nativeQuery = true) 
	 public List<Option> findOptionList(); 
	 
	 @Query(value = "SELECT * FROM sys_option WHERE if(:#{#option.optionKey}!='',option_key = :#{#option.optionKey},1=1)  ",
			    countQuery = "SELECT count(*) FROM sys_option WHERE if(:#{#option.optionKey}!='',option_key = :#{#option.optionKey},1=1)",
			    nativeQuery = true)
	 public	Page<Option> findByPage(@Param("option") OptionVO option, Pageable pageable);
}
