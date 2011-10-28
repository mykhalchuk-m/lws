package com.mmm.lws.acumulation.balance.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.utils.CalendarUtils;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BalanceDao {

	@PersistenceContext(name = "lws-core")
	private EntityManager entityManager;

	public void persistBalance(BalanceEntity balance) {
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

	public BalanceEntity getParentBalance(BalanceEntity balance) {
		Calendar calendar = Calendar.getInstance();
		calendar = CalendarUtils.getStartPeriodDate(balance);
		CalendarUtils.DatePeriodStruct dps = CalendarUtils.getParentPeriod(
				calendar, balance);
		Query query = entityManager
				.createQuery("from BalanceEntity be where be.numberOfPeriod=:np and be.periodType=:pt and be.periodYear=:py");
		query.setParameter("np", dps.nuberOfPeriod);
		query.setParameter("pt", dps.periodType);
		query.setParameter("py", balance.getPeriodYear());
		BalanceEntity be = (BalanceEntity) query.getSingleResult();
		return be;
	}

	public BalanceEntity getBalanceForPeriod(PeriodType periodType) {

		return null;
	}

	public BalanceEntity getBalanceForPeriod(Date forDate, PeriodType periodType) {
		if (periodType == PeriodType.DAY) {
			// Query query = entityManager
			// .createQuery("from BalanceEntity be where be.startDate>=:startDate and be.startDate<=:endDate");
			// query.setParameter("startDate", getStartDay(forDate));
			// query.setParameter("endDate", getEndDay(forDate));
		}
		return null;
	}

}
