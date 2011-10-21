package com.mmm.lws.acumulation.costs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mmm.lws.acumulation.balance.BalanceEntity;

@Entity
@Table(name = "costs")
@NamedQueries({
		@NamedQuery(name = "Costs.byDate", query = "select c from CostsEntity c where c.date = :date"),
		@NamedQuery(name = "Costs.byPeriod", query = "select c from CostsEntity c where c.date > :startDate and c.date < :endDate") })
public class CostsEntity implements Serializable {
	private static final long serialVersionUID = 7951683668230509999L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private BigDecimal amount;
	private String description;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@ManyToOne
	@JoinColumn(name="balance_fk")
	private BalanceEntity balance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BalanceEntity getBalance() {
		return balance;
	}

	public void setBalance(BalanceEntity balance) {
		this.balance = balance;
	}
}
