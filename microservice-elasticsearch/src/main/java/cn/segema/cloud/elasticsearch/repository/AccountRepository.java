package cn.segema.cloud.elasticsearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.elasticsearch.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	
	 List<Account> findByAccountName(String accountName);
	 
	
}
