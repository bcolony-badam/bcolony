package com.ksi.bcolony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksi.bcolony.dao.MasterDataDao;
import com.ksi.bcolony.model.MasterData;
import com.ksi.bcolony.service.MasterDataService;

@Service
public class MasterDataServiceImpl implements MasterDataService {

	@Autowired
	MasterDataDao mDao;

	@Override
	public MasterData get(int customerId, String id) {
		return mDao.get(customerId, id);
	}

	@Override
	@Transactional
	public String save(MasterData mdata) {
		return mDao.save(mdata);
	}

	@Override
	@Transactional
	public void update(MasterData mdata) {
		mDao.update(mdata);
	}

	@Override
	@Transactional
	public void delete(int customerId, String id, String loginId) {
		mDao.delete(customerId, id, loginId);
	}

	@Override
	public List<MasterData> list(String type, int customerId) {
		return mDao.list(type, customerId);
	}

	@Override
	public List<MasterData> list(String type, int customerId, int speciesId) {
		return mDao.list(type, customerId, speciesId);
	}

}
