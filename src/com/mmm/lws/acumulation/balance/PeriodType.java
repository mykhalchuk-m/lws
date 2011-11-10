package com.mmm.lws.acumulation.balance;

import java.util.Calendar;

public enum PeriodType {
	DAY {
		@Override
		public PeriodType getChildPeriod() {
			return null;
		}

		@Override
		public PeriodType getParentPeriod() {
			return WEEK;
		}

		@Override
		public int getPeriodNumber(Calendar calendar) {
			return calendar.get(Calendar.DAY_OF_YEAR);
		}
	},
	WEEK {
		@Override
		public PeriodType getChildPeriod() {
			return DAY;
		}

		@Override
		public PeriodType getParentPeriod() {
			return MONTH;
		}

		@Override
		public int getPeriodNumber(Calendar calendar) {
			return calendar.get(Calendar.WEEK_OF_YEAR);
		}
	},
	MONTH {
		@Override
		public PeriodType getChildPeriod() {
			return WEEK;
		}

		@Override
		public PeriodType getParentPeriod() {
			return null;
		}

		@Override
		public int getPeriodNumber(Calendar calendar) {
			return calendar.get(Calendar.MONTH);
		}
	};
	public abstract PeriodType getChildPeriod();

	public abstract PeriodType getParentPeriod();
	public abstract int getPeriodNumber(Calendar calendar);
}
