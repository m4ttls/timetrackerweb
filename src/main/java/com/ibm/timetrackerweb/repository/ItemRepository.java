package com.ibm.timetrackerweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.timetrackerweb.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
