package com.mmm.lws.calculation.period;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class Week {
	public BigDecimal getRealBalannce(Date date) {return new BigDecimal(0);}
}
