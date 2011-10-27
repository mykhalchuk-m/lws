package com.mmm.lws.calculation.period;

import java.math.BigDecimal;

import javax.ejb.Local;

@Local
public interface Period {
	public BigDecimal calculateRealAmound();
}
