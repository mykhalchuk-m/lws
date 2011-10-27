package com.mmm.lws.calculation.period;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.CostsEntity;
import com.mmm.lws.utils.CalendarUtils;

public class AbstractPeriod {
	@EJB
	protected BalanceDao balanceDao;
	protected BalanceEntity planedBalance;

	public BigDecimal calculateRealAmound() {
		BigDecimal realAmound = planedBalance.getAmount();
		for (CostsEntity ce : planedBalance.getUpdates()) {
			realAmound = realAmound.subtract(ce.getAmount());
		}
		return realAmound;
	}

	protected void init(Date date, PeriodType periodType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		System.out.println(date + "        " + periodType);
		int periodNumber = CalendarUtils.getPeriodNumber(calendar,
				periodType);
		System.out.println(">>>>>>>>>>>>>>>>>" + periodNumber);
		planedBalance = balanceDao.getBalance(periodType, periodNumber,
				calendar.get(Calendar.YEAR));
		System.out.println(">>>>>>>>>>>>>>>>>" + planedBalance);
	}

	@Override
	public String toString() {
		return "Period [" + planedBalance.getPeriodType() + "]";
	}
	
}
