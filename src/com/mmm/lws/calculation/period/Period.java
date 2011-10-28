package com.mmm.lws.calculation.period;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.CostsEntity;
import com.mmm.lws.utils.CalendarUtils;

public class Period {
	private BalanceEntity planedBalance;
	private BalanceDao balanceDao;
	
	public Period(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
		Date date = new Date(System.currentTimeMillis());
		init(date, PeriodType.DAY);
	}
	
	public Period(BalanceDao balanceDao, Date date, PeriodType periodType) {
		this.balanceDao = balanceDao;
		init(date, periodType);
	}
	
	public BigDecimal calculateRealAmound() {
		if (planedBalance == null) {
			return null;
		}
		BigDecimal realAmound = planedBalance.getAmount();
		for (CostsEntity ce : planedBalance.getUpdates()) {
			realAmound = realAmound.subtract(ce.getAmount());
		}
		return realAmound;
	}

	public void init(Date date, PeriodType periodType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int periodNumber = CalendarUtils.getPeriodNumber(calendar, periodType);
		planedBalance = balanceDao.getBalance(periodType, periodNumber,
				calendar.get(Calendar.YEAR));
	}

	@Override
	public String toString() {
		return "Period [" + planedBalance.getPeriodType() + "]";
	}

}
