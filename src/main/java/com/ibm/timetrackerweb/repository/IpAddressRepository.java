package com.ibm.timetrackerweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.timetrackerweb.model.IpAddress;

public interface IpAddressRepository extends JpaRepository<IpAddress, Integer> {

}
