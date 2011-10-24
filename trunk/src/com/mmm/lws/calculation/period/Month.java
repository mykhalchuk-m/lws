package com.mmm.lws.calculation.period;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;

@Stateful
@LocalBean
public class Month extends Period {

	public Month() {
		balance = new BalanceEntity();
		balance.setPeriodType(PeriodType.MONTH);
	}
	
}
