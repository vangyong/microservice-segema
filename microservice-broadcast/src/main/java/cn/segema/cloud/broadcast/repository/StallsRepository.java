package cn.segema.cloud.broadcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.broadcast.domain.Stalls;

@Repository
public interface StallsRepository extends JpaRepository<Stalls, String> {
	
}
