package com.ksi.bcolony.util;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class BcolonySequenceGenerator implements IdentifierGenerator {

	AtomicInteger counter = new AtomicInteger();
	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return counter.incrementAndGet()+"";
	}

}
