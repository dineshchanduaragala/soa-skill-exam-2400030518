package com.klef.soa.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.exam.entity.*;

@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, Long>
{
	List<ServiceCenter> findByName(String name);
	List<ServiceCenter> findByType(String type);
	
}
