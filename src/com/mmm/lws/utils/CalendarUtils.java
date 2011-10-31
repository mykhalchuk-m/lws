package com.mmm.lws.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mmm.lws.acumulation.balance.PeriodType;

public class CalendarUtils {
	public static Calendar getStartPeriodDate(PeriodType periodType, Date date) {
		Calendar currDate = Calendar.getInstance();
		currDate.setTime(date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, currDate.get(Calendar.YEAR));
		if (periodType.equals(PeriodType.DAY)) {
			calendar.set(Calendar.DAY_OF_YEAR, currDate.get(Calendar.DAY_OF_YEAR));
		} else if (periodType.equals(PeriodType.WEEK)) {
			calendar.set(Calendar.WEEK_OF_YEAR, currDate.get(Calendar.WEEK_OF_YEAR));
			calendar.set(Calendar.DAY_OF_WEEK, 1);
		} else if (periodType.equals(PeriodType.MONTH)) {
			calendar.set(Calendar.MONTH, currDate.get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		}
		return calendar;
	}

	public static Calendar getEndPeriodDate(PeriodType periodType, Date date) {
		Calendar startDate = getStartPeriodDate(periodType, date);
		Calendar endDare = startDate;
		if (periodType.equals(PeriodType.WEEK)) {
			endDare.set(Calendar.WEEK_OF_YEAR, startDate.get(Calendar.WEEK_OF_YEAR) + 1);
		} else if (periodType.equals(PeriodType.MONTH)) {
			endDare.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
		}
		return endDare;
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
}
