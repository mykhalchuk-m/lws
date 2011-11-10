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

	public void persistBalance(BalanceEntity balance)
			throws PersistenceException {
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
			return (BalanceEntity) listBe.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<BalanceEntity> getAllBalanceByPeriod(PeriodType periodType) {
		Query query = entityManager
				.createQuery("from BalanceEntity be where be.periodType=:pt order by be.periodYear desc");
		query.setParameter("pt", periodType);
		return (List<BalanceEntity>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BalanceEntity> getAllBalanceByScope(PeriodType periodType, int startPeriod, int endPeriod, int year) {
		Query query = entityManager
				.createQuery("from BalanceEntity be where be.periodType=:pt and be.periodYear=:py and be.numberOfPeriod>:sp and be.numberOfPeriod<:ep");
		query.setParameter("pt", periodType);
		query.setParameter("py", year);
		query.setParameter("sp", startPeriod);
		query.setParameter("ep", endPeriod);
		return (List<BalanceEntity>)query.getResultList();
	}

}
