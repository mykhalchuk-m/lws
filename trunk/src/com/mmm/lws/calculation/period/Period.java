package com.mmm.lws.calculation.period;

import java.math.BigDecimal;

import javax.ejb.EJB;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.CostsEntity;

public abstract class Period {
	@EJB
	protected BalanceDao balanceDao;
	protected BalanceEntity balance;

	public void registerBalance(BigDecimal amount) {
		balance.setAmount(amount);
		balanceDao.persistBalance(balance);
	}

	public void addCost(CostsEntity cost) {
		balance.getUpdates().add(cost);
	}
}
