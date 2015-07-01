package com.ksi.bcolony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksi.bcolony.model.GroupMstr;
import com.ksi.bcolony.service.GroupMstrService;
import com.ksi.bcolony.util.SessionData;

@RestController
@RequestMapping("/groupmstr")
public class GroupMstrController {

	@Autowired
	GroupMstrService service;

	@Autowired
	SessionData session;

	@RequestMapping(value = "/{speciesId}/{name}", method = RequestMethod.GET)
	public GroupMstr get(@PathVariable("name") String name,
			@PathVariable("speciesId") int speciesId) {
		return service.get(session.getCustomerId(), name, speciesId);
	}

	@RequestMapping(value = "/{speciesId}", method = RequestMethod.POST)
	public String save(@PathVariable("speciesId") int speciesId,
			@RequestBody GroupMstr mdata) {
		mdata.setSpeciesId(speciesId);
		mdata.setCustomerId(session.getCustomerId());
		mdata.setCreatedBy(session.getLoginId());
		mdata.setModifiedBy(session.getLoginId());
		return service.save(mdata);
	}

	@RequestMapping(value = "/{speciesId}/{name}", method = RequestMethod.PUT)
	public void update(@PathVariable("name") String name,
			@PathVariable("speciesId") int speciesId,
			@RequestBody GroupMstr mdata) {
		mdata.setSpeciesId(speciesId);
		mdata.setName(name);
		mdata.setCustomerId(session.getCustomerId());
		mdata.setModifiedBy(session.getLoginId());
		service.update(mdata);
	}

	@RequestMapping(value = "/{speciesId}/{name}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("name") String name,
			@PathVariable("speciesId") int speciesId) {
		service.delete(session.getCustomerId(), name, speciesId,
				session.getLoginId());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<GroupMstr> list(@PathVariable("type") String type) {
		return service.list(session.getCustomerId());
	}

	@RequestMapping(value = "/{speciesId}", method = RequestMethod.GET)
	public List<GroupMstr> list(@PathVariable int speciesId) {
		return service.list(session.getCustomerId(), speciesId);
	}

}
