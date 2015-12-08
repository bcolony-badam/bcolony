package com.ksi.bcolony.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksi.bcolony.dao.MasterDataDao;
import com.ksi.bcolony.model.MasterData;

@Repository
public class MasterDataDaoImpl implements MasterDataDao {

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
	public MasterData get(int customerId, String id) {
		Query q = session().createQuery(
				"from MasterData where id=? and customerId=?");
		q.setString(0, id);
		q.setInteger(1, customerId);
		return (MasterData) q.uniqueResult();
	}

	@Override
	public String save(MasterData mdata) {
		mdata.setCreatedOn(new Date().getTime());
		mdata.setModifiedOn(new Date().getTime());
		return (String) session().save(mdata);
	}

	@Override
	public void update(MasterData mdata) {
		Query q = session().createQuery(
				"update MasterData set description=?,modifiedBy=?,modifiedOn=?"
						+ " where id=? and customerId=? and deleted=0");
		q.setString(0, mdata.getDescription());
		q.setString(1, mdata.getModifiedBy());
		q.setLong(2, new Date().getTime());
		q.setString(3, mdata.getId());
		q.setInteger(4, mdata.getCustomerId());
		q.executeUpdate();
	}

	@Override
	public void delete(int customerId, String id, String usrId) {
		Query q = session()
				.createQuery(
						"update MasterData set deleted=1,modifiedBy=?,modifiedOn=? where id=? and customerId=?");
		q.setString(0, usrId);
		q.setLong(1, new Date().getTime());
		q.setString(2, id);
		q.setInteger(3, customerId);
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasterData> list(String type, int customerId) {
		Query q = session().createQuery(
				"from MasterData where type=? and customerId=? ");
		q.setString(0, type);
		q.setInteger(1, customerId);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasterData> list(String type, int customerId, int speciesId) {
		Query q = session()
				.createQuery(
						"from MasterData where type=? and speciesId=? and customerId=? and deleted=0");
		q.setString(0, type);
		q.setInteger(1, speciesId);
		q.setInteger(2, customerId);
		return q.list();
	}

}
