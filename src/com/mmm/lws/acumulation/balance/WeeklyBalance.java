package com.mmm.lws.acumulation.balance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("weekly")
public class WeeklyBalance extends BalanceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1140823812958536134L;

}
