package com.ibm.timetrackerweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.timetrackerweb.model.MacAddress;

public interface MacAddressRepository extends JpaRepository<MacAddress, Integer> {

}
