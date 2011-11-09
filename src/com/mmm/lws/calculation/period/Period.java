package com.mmm.lws.calculation.period;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.CostsEntity;
import com.mmm.lws.acumulation.costs.dao.CostsDao;
import com.mmm.lws.utils.CalendarUtils;

public class Period {
	private BalanceEntity balance;
	private BalanceDao balanceDao;
	private CostsDao costsDao;
	private PeriodType periodType;

	public void setCostsDao(CostsDao costsDao) {
		this.costsDao = costsDao;
	}
	
	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}
	
	public Period(BalanceDao balanceDao, Date date, PeriodType periodType) {
		this.balanceDao = balanceDao;
		this.periodType = periodType;
		init(date);
	}
	
	private void init(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int periodNumber = CalendarUtils.getPeriodNumber(calendar, periodType);
		balance = balanceDao.getBalance(periodType, periodNumber,
				calendar.get(Calendar.YEAR));
	}
	
	public BigDecimal calculateSpendedMoney(Date date) {
		Date startDate = CalendarUtils.getStartPeriodDate(periodType, date).getTime();
		Date endDate = CalendarUtils.getEndPeriodDate(periodType, date).getTime();
		List<CostsEntity> costs = costsDao.getCostsByPeriod(startDate, endDate);
		BigDecimal res = new BigDecimal(0);
		for (CostsEntity cost : costs) {
			res = res.add(cost.getAmount());
		}
		return res;
	}
	
	public BalanceEntity getBalance() {
		return balance;
	}
	
	@Override
	public String toString() {
		return "Period [" + balance.getPeriodType() + "]";
	}

}
