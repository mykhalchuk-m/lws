package com.mmm.lws.acumulation.balance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dayly")
public class DaylyBalance extends BalanceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4892255326978574822L;

}
