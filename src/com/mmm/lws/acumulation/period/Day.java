package com.mmm.lws.acumulation.period;

import java.math.BigDecimal;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.mmm.lws.acumulation.balance.DaylyBalance;
import com.mmm.lws.acumulation.costs.CostsEntity;

/**
 * Session Bean implementation class Day
 */
@Stateful
@LocalBean
public class Day {

	private DaylyBalance balance;
	
    public void registerBalance(BigDecimal amount) {
    	balance = new DaylyBalance();
    	balance.setAmount(amount);
    }
    
    public void addCost(CostsEntity cost) {
    	balance.getUpdates().add(cost);
    }
}
