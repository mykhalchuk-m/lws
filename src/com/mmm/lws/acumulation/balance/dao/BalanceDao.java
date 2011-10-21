package com.mmm.lws.acumulation.balance.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmm.lws.acumulation.balance.BalanceEntity;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BalanceDao {

	@PersistenceContext(name = "lws-core")
	private EntityManager entityManager;

	
	public void persistBalance(BalanceEntity balance) {
		entityManager.persist(balance);
	}
}
