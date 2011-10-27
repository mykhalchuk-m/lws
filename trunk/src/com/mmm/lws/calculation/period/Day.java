package com.mmm.lws.calculation.period;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mmm.lws.acumulation.balance.PeriodType;

@Stateless
public class Day extends AbstractPeriod implements Period {

	public Day(Date date) {
		System.out.println("Day 1");
		init(date, PeriodType.DAY);
		System.out.println("Day 2");
	}
}
