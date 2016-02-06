package com.ibm.timetrackerweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.timetrackerweb.model.ComputerConfig;

public interface ComputerConfigRepository extends JpaRepository<ComputerConfig, Integer> {
	
	List<ComputerConfig> findByComputerNameAndCurrentDate(String computerName, String currentDate);
	
	List<ComputerConfig> findByCurrentDate(String currentDate);
	
	List<ComputerConfig> findByComputerName(String computerName);

}
