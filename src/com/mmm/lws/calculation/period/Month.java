package com.mmm.lws.calculation.period;

import java.util.Date;

import javax.ejb.Stateless;

import com.mmm.lws.acumulation.balance.PeriodType;

@Stateless
public class Month extends AbstractPeriod implements Period {

	public Month(Date date) {
		init(date, PeriodType.MONTH);
	}

}
