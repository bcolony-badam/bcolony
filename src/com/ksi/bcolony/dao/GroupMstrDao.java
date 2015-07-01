package com.ksi.bcolony.dao;

import java.util.List;

import com.ksi.bcolony.model.GroupMstr;

public interface GroupMstrDao {

	GroupMstr get(int customerId, String name, int speciesId);

	String save(GroupMstr mdata);

	void update(GroupMstr mdata);

	void delete(int customerId, String name, int speciesId, String loginId);

	List<GroupMstr> list(int customerId);

	List<GroupMstr> list(int customerId, int speciesId);
}
