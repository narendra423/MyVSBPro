package com.stg.buyback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stg.buyback.entity.Attachment;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
	
	@Query(value = "select * from attachment where buyback_id = :id ;",nativeQuery = true)
	public abstract List<Attachment> findByBuybackId(@Param("id") int id);
	
	
}
