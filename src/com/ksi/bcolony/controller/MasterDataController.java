package com.ksi.bcolony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksi.bcolony.model.MasterData;
import com.ksi.bcolony.service.MasterDataService;
import com.ksi.bcolony.util.SessionData;

@RestController
@RequestMapping("/masterdata")
public class MasterDataController {

	@Autowired
	MasterDataService service;

	@Autowired
	SessionData session;

	@RequestMapping(value = "/{type}/{speciesId}/{name}", method = RequestMethod.GET)
	public MasterData get(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("speciesId") int speciesId) {
		return service.get(session.getCustomerId(), type, name, speciesId);
	}

	@RequestMapping(value = "/{type}/{speciesId}", method = RequestMethod.POST)
	public String save(@PathVariable("type") String type,
			@PathVariable int speciesId, @RequestBody MasterData mdata) {
		mdata.setType(type);
		mdata.setSpeciesId(speciesId);
		mdata.setCustomerId(session.getCustomerId());
		mdata.setCreatedBy(session.getLoginId());
		mdata.setModifiedBy(session.getLoginId());
		return service.save(mdata);
	}

	@RequestMapping(value = "/{type}/{speciesId}/{name}", method = RequestMethod.PUT)
	public void update(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("speciesId") int speciesId,
			@RequestBody MasterData mdata) {
		mdata.setType(type);
		mdata.setSpeciesId(speciesId);
		mdata.setName(name);
		mdata.setCustomerId(session.getCustomerId());
		mdata.setModifiedBy(session.getLoginId());
		service.update(mdata);
	}

	@RequestMapping(value = "/{type}/{speciesId}/{name}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("speciesId") int speciesId) {
		service.delete(session.getCustomerId(), type, name, speciesId,
				session.getLoginId());
	}

	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	public List<MasterData> list(@PathVariable("type") String type) {
		return service.list(type, session.getCustomerId());
	}

	@RequestMapping(value = "/{type}/{speciesId}", method = RequestMethod.GET)
	public List<MasterData> list(@PathVariable("type") String type,
			@PathVariable int speciesId) {
		return service.list(type, session.getCustomerId(), speciesId);
	}

}
