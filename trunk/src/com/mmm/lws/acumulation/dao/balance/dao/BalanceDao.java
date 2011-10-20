package com.mmm.lws.acumulation.dao.balance.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmm.lws.acumulation.dao.balance.BalanceEntity;

@Stateless
@LocalBean
public class BalanceDao {
	
	@PersistenceContext(name="lws-core")
	EntityManager entityManager;
	
	public void persistBalance(BalanceEntity balance) {
		entityManager.persist(balance);
	}
}
