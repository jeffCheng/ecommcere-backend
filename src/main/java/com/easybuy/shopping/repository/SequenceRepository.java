package com.easybuy.shopping.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import com.easybuy.shopping.model.SequenceInfo;


public interface SequenceRepository extends CrudRepository<SequenceInfo, String>, JpaSpecificationExecutor<SequenceInfo>{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<SequenceInfo> findById(String id);

}
