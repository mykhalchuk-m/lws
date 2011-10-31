package com.mmm.lws.calculation.period;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.dao.CostsDao;

@Stateful
@LocalBean
public class Day {
	@EJB
	private BalanceDao balanceDao;
	@EJB
	private CostsDao costsDao;
	private Period period;
	private static final PeriodType PERIOD_TYPE = PeriodType.DAY;

	public BigDecimal getRealBalannce(Date date) {
		init(date);
		period.setBalanceDao(balanceDao);
		if (period.getBalance() != null) {
			return period.getBalance().getAmountLest();
		} else {
			return null;
		}
	}
	
	public BigDecimal getSpendedMoney(Date date) {
		init(date);
		period.setCostsDao(costsDao);
		return period.calculateSpendedMoney(PERIOD_TYPE, date);
	}
	
	private void init(Date date) {
		period = new Period();
		period.init(date, PERIOD_TYPE);
	}
}
