package com.ksi.bcolony.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksi.bcolony.dao.GroupMstrDao;
import com.ksi.bcolony.model.GroupMstr;

@Repository
public class GroupMstrDaoImpl implements GroupMstrDao {

	@Autowired
	SessionFactory factory;

	private Session session() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (Exception e) {

		}
		if (session == null) {
			session = factory.openSession();
		}
		return session;
	}

	@Override
	public GroupMstr get(int customerId, String name, int speciesId) {
		Query q = session().createQuery(
				"from GroupMstr where name=? "
						+ "and speciesId=? and customerId=?");
		q.setString(0, name);
		q.setInteger(1, speciesId);
		q.setInteger(2, customerId);
		return (GroupMstr) q.uniqueResult();
	}

	@Override
	public String save(GroupMstr mdata) {
		mdata.setCreatedOn(new Date());
		mdata.setModifiedOn(new Date());
		return (String) session().save(mdata);
	}

	@Override
	public void update(GroupMstr mdata) {
		Query q = session().createQuery(
				"update GroupMstr set description=?,modifiedBy=?,modifiedOn=?"
						+ " where name=? and speciesId=? and customerId=?");
		q.setString(0, mdata.getDescription());
		q.setString(1, mdata.getModifiedBy());
		q.setDate(2, new Date());
		q.setString(3, mdata.getName());
		q.setInteger(4, mdata.getSpeciesId());
		q.setInteger(5, mdata.getCustomerId());
		q.executeUpdate();
	}

	@Override
	public void delete(int customerId, String name, int speciesId,
			String loginId) {
		Query q = session().createQuery(
				"update GroupMstr set deleted=1,modifiedBy=?,modifiedOn=? where name=? "
						+ "and speciesId=? and customerId=?");
		q.setString(0, loginId);
		q.setDate(1, new Date());
		q.setString(2, name);
		q.setInteger(3, speciesId);
		q.setInteger(4, customerId);
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupMstr> list(int customerId) {
		Query q = session().createQuery("from GroupMstr where customerId=?");
		q.setInteger(0, customerId);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupMstr> list(int customerId, int speciesId) {
		Query q = session().createQuery(
				"from GroupMstr where speciesId=? and customerId=?");
		q.setInteger(0, speciesId);
		q.setInteger(1, customerId);
		return q.list();
	}

}
