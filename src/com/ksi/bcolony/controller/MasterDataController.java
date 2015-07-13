package com.ksi.bcolony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MasterData get(@PathVariable("id") String id) {
		return service.get(session.getCustomerId(), id);
	}

	@RequestMapping(value = "/{type}", method = RequestMethod.POST)
	public String save(@PathVariable("type") String type,
			@RequestBody MasterData mdata) {
		mdata.setType(type);
		mdata.setCustomerId(session.getCustomerId());
		mdata.setCreatedBy(session.getLoginId());
		mdata.setModifiedBy(session.getLoginId());
		return service.save(mdata);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") String id,
			@RequestBody MasterData mdata) {
		mdata.setId(id);
		mdata.setCustomerId(session.getCustomerId());
		mdata.setModifiedBy(session.getLoginId());
		service.update(mdata);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		service.delete(session.getCustomerId(), id, session.getLoginId());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<MasterData> list(@RequestParam("type") String type,
			@RequestParam("speciesId") int speciesId) {
		if (speciesId != 0) {
			return service.list(type, session.getCustomerId(), speciesId);
		} else {
			return service.list(type, session.getCustomerId());
		}
	}

}
