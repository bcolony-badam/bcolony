package com.ksi.bcolony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksi.bcolony.dao.GroupMstrDao;
import com.ksi.bcolony.model.GroupMstr;
import com.ksi.bcolony.service.GroupMstrService;

@Service
public class GroupMstrServiceImpl implements GroupMstrService {

	@Autowired
	GroupMstrDao gMstrDao;

	@Override
	public GroupMstr get(int customerId, String name, int speciesId) {
		return gMstrDao.get(customerId, name, speciesId);
	}

	@Override
	@Transactional
	public String save(GroupMstr mdata) {
		return gMstrDao.save(mdata);
	}

	@Override
	@Transactional
	public void update(GroupMstr mdata) {
		gMstrDao.update(mdata);
	}

	@Override
	@Transactional
	public void delete(int customerId, String name, int speciesId,
			String loginId) {
		gMstrDao.delete(customerId, name, speciesId, loginId);
	}

	@Override
	public List<GroupMstr> list(int customerId) {
		return gMstrDao.list(customerId);
	}

	@Override
	public List<GroupMstr> list(int customerId, int speciesId) {
		return gMstrDao.list(customerId, speciesId);
	}

}
