package com.mmm.lws.acumulation.balance.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BalanceDao {

	@PersistenceContext(name = "lws-core")
	private EntityManager entityManager;

	public void persistBalance(BalanceEntity balance) throws PersistenceException{
		entityManager.persist(balance);
	}

	public BalanceEntity getBalance(PeriodType periodType, int periodNumber,
			int year) {
		Query query = entityManager
				.createQuery("from BalanceEntity be where be.numberOfPeriod=:np and be.periodType=:pt and be.periodYear=:py");
		query.setParameter("np", periodNumber);
		query.setParameter("pt", periodType);
		query.setParameter("py", year);
		List<?> listBe = query.getResultList();
		if (listBe.size() > 0) {
			return (BalanceEntity)listBe.get(0);
		} else {
			return null;
		}
	}
	
	public List<BalanceEntity> getAllBalances() {
		return null;
	}

}
