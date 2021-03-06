package com.ksi.bcolony.service;

import java.util.List;

import com.ksi.bcolony.model.MasterData;

public interface MasterDataService {

	MasterData get(int customerId, String id);

	String save(MasterData mdata);

	void update(MasterData mdata);

	void delete(int customerId, String id, String loginId);

	List<MasterData> list(String type, int customerId);

	List<MasterData> list(String type, int customerId, int speciesId);
}
