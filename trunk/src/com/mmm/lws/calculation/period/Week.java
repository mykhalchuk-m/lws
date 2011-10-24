package com.mmm.lws.calculation.period;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;

@Stateful
@LocalBean
public class Week extends Period {
	
	public Week() {
		balance = new BalanceEntity();
		balance.setPeriodType(PeriodType.WEEK);
	}
	
}
