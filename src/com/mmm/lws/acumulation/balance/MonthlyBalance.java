package com.mmm.lws.acumulation.balance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("monthly")
public class MonthlyBalance extends BalanceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3144473336962209274L;

}
