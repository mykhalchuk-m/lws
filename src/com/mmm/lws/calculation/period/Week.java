package com.mmm.lws.calculation.period;

import java.util.Date;

import javax.ejb.Stateless;

import com.mmm.lws.acumulation.balance.PeriodType;

@Stateless
public class Week extends AbstractPeriod implements Period {

	public Week(Date date) {
		init(date, PeriodType.WEEK);
	}

}
