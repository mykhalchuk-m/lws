package com.mmm.lws.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;

public class CalendarUtils {
	public static Calendar getStartPeriodDate(PeriodType periodType, int year,
			int numOfPeriod) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		if (periodType.equals(PeriodType.DAY)) {
			calendar.set(Calendar.DAY_OF_YEAR, numOfPeriod);
		} else if (periodType.equals(PeriodType.WEEK)) {
			calendar.set(Calendar.WEEK_OF_YEAR, numOfPeriod);
			calendar.set(Calendar.DAY_OF_WEEK, 1);
		} else if (periodType.equals(PeriodType.MONTH)) {
			calendar.set(Calendar.MONTH, numOfPeriod);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar;
	}

	public static Calendar getStartPeriodDate(BalanceEntity balance) {
		Calendar calendar = getStartPeriodDate(balance.getPeriodType(),
				balance.getPeriodYear(), balance.getNumberOfPeriod());
		return calendar;
	}
	
	public static DatePeriodStruct getParentPeriod(Calendar calendar,
			BalanceEntity balance) {
		PeriodType periodType = balance.getPeriodType();
		DatePeriodStruct dps = new DatePeriodStruct();
		if (periodType.equals(PeriodType.DAY)) {
			dps.periodType = PeriodType.WEEK;
			dps.nuberOfPeriod = calendar.get(Calendar.WEEK_OF_YEAR);
		} else if (periodType.equals(PeriodType.WEEK)) {
			dps.periodType = PeriodType.MONTH;
			dps.nuberOfPeriod = calendar.get(Calendar.MONTH);
		}
		return dps;
	}
	
	public static Date convertStringToDate(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(strDate);
	}
	
	public static int getPeriodNumber(Calendar calendar, PeriodType periodType) {
		if (periodType.equals(PeriodType.DAY)) {
			return calendar.get(Calendar.DAY_OF_YEAR);
		} else if (periodType.equals(PeriodType.WEEK)) {
			return calendar.get(Calendar.WEEK_OF_YEAR);
		} else if (periodType.equals(PeriodType.MONTH)) {
			return calendar.get(Calendar.MONTH);
		}
		return 0;
	}	
	
	public static class DatePeriodStruct {
		public int nuberOfPeriod;
		public PeriodType periodType;
	}
	
	
}
