package com.ibm.timetrackerweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.timetrackerweb.model.ComputerConfig;
import com.ibm.timetrackerweb.model.MacAndIp;
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
	
	@RequestMapping(value="/findbyname/{name}",method = RequestMethod.GET)
	public List<ComputerConfig> findItemsByName(@PathVariable String name) {
		return repo.findByComputerName(name);
	}
	
	@RequestMapping(value="/findbydate/{date}",method = RequestMethod.GET)
	public List<ComputerConfig> findItemsByDate(@PathVariable String date) {
		return repo.findByCurrentDate(date);
	}
	
	@RequestMapping(value="/find",method = RequestMethod.GET)
	public List<ComputerConfig> findItemsByDate(@RequestParam(value="name", required=false) String name,
	        @RequestParam(value="date", required=false) String date) {
		if(name == null && date == null)
		{
			return repo.findAll();
		}
		else if(name == null && date != null)
		{
			return repo.findByCurrentDate(date);
		}
		else if(name != null && date == null)
		{
			return repo.findByComputerName(name);
		}
		return repo.findByComputerNameAndCurrentDate(name,date);
		
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String addItem(@RequestBody ComputerConfig item) {
		List<ComputerConfig>list = repo.findByComputerNameAndCurrentDate(item.getComputerName(), item.getCurrentDate());
		ComputerConfig toBeSaved = null;
		if(list.size() > 0)
		{
			toBeSaved = list.get(0);
			for(MacAndIp mip:item.getListMacs())
			{
				MacAndIp existing = toBeSaved.findMacWithIp(mip.getMacAddress(), mip.getIpAddress());
				if(existing == null)
				{
					toBeSaved.addMacAddress(mip, false);
				}
				else
				{
					existing.setEndTime(mip.getEndTime());
				}
			}
		}
		else
		{
			toBeSaved = item;
			toBeSaved.setComputerId(null);
			for(MacAndIp mac:toBeSaved.getListMacs())
			{
				mac.setId(null);
				mac.setParent(toBeSaved);
			}
		}
		
		ComputerConfig savedData = repo.saveAndFlush(toBeSaved);
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
