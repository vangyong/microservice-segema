package cn.segema.cloud.system.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.UserResource;

@Repository
public interface UserResourceRepository extends PagingAndSortingRepository<UserResource, BigInteger>,
		JpaRepository<UserResource, BigInteger>, JpaSpecificationExecutor<UserResource> {

}
