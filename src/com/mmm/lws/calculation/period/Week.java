package com.mmm.lws.calculation.period;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;

@Stateless
@LocalBean
public class Week {
	@EJB
	private BalanceDao balanceDao;
	private Period period;
	
	public BigDecimal getRealBalannce(Date date) {
		period = new Period(balanceDao);
		period.init(date, PeriodType.WEEK);
		return period.calculateRealAmound();
	}
}
