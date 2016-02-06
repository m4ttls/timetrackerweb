package com.ibm.timetrackerweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.timetrackerweb.model.ComputerConfig;
import com.ibm.timetrackerweb.model.IpAddress;
import com.ibm.timetrackerweb.model.MacAddress;
import com.ibm.timetrackerweb.repository.ComputerConfigRepository;

@RestController
@RequestMapping("/timetracker")
public class TimeTrackerController {

	@Autowired
	private ComputerConfigRepository repo;

	@RequestMapping(value="/findall",method = RequestMethod.GET)
	public List<ComputerConfig> findAllItems() {
		return repo.findAll();
	}
	
	@RequestMapping(value="/find/{param1}/{param2}",method = RequestMethod.GET)
	public List<ComputerConfig> findItems(@PathVariable String param1, @PathVariable String param2) {
		if(param2.equals("name"))
		{
			return repo.findByComputerName(param1);
		}else if(param2.equals("date"))
		{
			return repo.findByCurrentDate(param1);
		}else
		{
			return repo.findByComputerNameAndCurrentDate(param1, param2);
		}
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addItem(@RequestBody ComputerConfig item) {
		item.setComputerId(null);
		for(MacAddress mac:item.getListMacs())
		{
			mac.setId(null);
			mac.setParent(item);
			for(IpAddress ip:mac.getAllIpAddress())
			{
				ip.setId(null);
				ip.setParent(mac);
			}
		}
		ComputerConfig savedData = repo.saveAndFlush(item);
		return "success";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ComputerConfig updateItem(@RequestBody ComputerConfig updatedItem, @PathVariable Integer id) {
		updatedItem.setComputerId(id);
		return repo.saveAndFlush(updatedItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		repo.delete(id);
	}

}
