package com.mmm.lws.calculation.period;

import java.util.List;

import javax.ejb.Local;

import com.mmm.lws.acumulation.balance.BalanceEntity;

@Local
public interface IPeriod {
	public List<BalanceEntity> getBalanceByPeriod();
}
