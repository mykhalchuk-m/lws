package com.mmm.lws.calculation.period;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.dao.CostsDao;

@Stateful
@LocalBean
public class Month implements IPeriod {
	@EJB
	private BalanceDao balanceDao;
	@EJB
	private CostsDao costsDao;
	private Period period;
	private static final PeriodType PERIOD_TYPE = PeriodType.MONTH;

	@Override
	public List<BalanceEntity> getBalanceByPeriod() {
		return balanceDao.getAllBalanceByPeriod(PERIOD_TYPE);
	}

	public BigDecimal getRealBalannce(Date date) {
		init(date);
		period.setBalanceDao(balanceDao);
		return (period.getBalance() != null) ? period.getBalance()
				.getAmountLest() : null;
	}

	public BigDecimal getSpendedMoney(Date date) {
		init(date);
		period.setCostsDao(costsDao);
		return period.calculateSpendedMoney(date);
	}

	private void init(Date date) {
		period = new Period(balanceDao, date, PERIOD_TYPE);
	}

	@Override
	public List<BalanceEntity> getSubBalancesByScope(int periodNumber, int year) {
		period = new Period(balanceDao, PERIOD_TYPE);
		return period.getSubBalancesByScope(periodNumber, year);
	}

}
