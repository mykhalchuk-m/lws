package com.mmm.lws.acumulation.balance.dao;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BalanceDao {

	@PersistenceContext(name = "lws-core")
	private EntityManager entityManager;

	public void persistBalance(BalanceEntity balance) {
		entityManager.persist(balance);
	}
	
	public BalanceEntity getParentBalance(BalanceEntity balanceEntity) {
		
		return null;
	}

	public BalanceEntity getBalanceForPeriod(PeriodType periodType) {
		
		return null;
	}

	public BalanceEntity getBalanceForPeriod(Date forDate, PeriodType periodType) {
		if (periodType == PeriodType.DAY) {
			Query query = entityManager
					.createQuery("from BalanceEntity be where be.startDate>=:startDate and be.startDate<=:endDate");
			query.setParameter("startDate", getStartDay(forDate));
			query.setParameter("endDate", getEndDay(forDate));
		}
		return null;
	}

	private Date getStartDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, -calendar.get(Calendar.HOUR_OF_DAY));
		calendar.add(Calendar.MINUTE, -calendar.get(Calendar.MINUTE));
		calendar.add(Calendar.SECOND, -calendar.get(Calendar.SECOND));
		return calendar.getTime();
	}

	private Date getEndDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY,
				-calendar.get(Calendar.HOUR_OF_DAY) + 23);
		calendar.add(Calendar.MINUTE, -calendar.get(Calendar.MINUTE) + 59);
		calendar.add(Calendar.SECOND, -calendar.get(Calendar.SECOND) + 59);
		return calendar.getTime();
	}
}
