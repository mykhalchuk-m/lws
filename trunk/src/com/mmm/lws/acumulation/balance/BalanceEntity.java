package com.mmm.lws.acumulation.balance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mmm.lws.acumulation.costs.CostsEntity;

@Entity
@Table(name = "balance")
public class BalanceEntity implements Serializable {
	private static final long serialVersionUID = 7051078210988387981L;

	@Id
	@GeneratedValue
	private long id;
	private BigDecimal amount;
	@OneToMany(mappedBy = "balance")
	private List<CostsEntity> updates;
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Enumerated(EnumType.STRING)
	private PeriodType periodType;
	private int numberOfPeriod;
	private int periodYear;
	private BigDecimal amountLest;

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

	public List<CostsEntity> getUpdates() {
		return updates;
	}

	public void setUpdates(List<CostsEntity> updates) {
		this.updates = updates;
	}

	public PeriodType getPeriodType() {
		return periodType;
	}

	public void setPeriodType(PeriodType periodType) {
		this.periodType = periodType;
	}

	public int getNumberOfPeriod() {
		return numberOfPeriod;
	}

	public void setNumberOfPeriod(int numberOfPeriod) {
		this.numberOfPeriod = numberOfPeriod;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(int periodYear) {
		this.periodYear = periodYear;
	}

	public BigDecimal getAmountLest() {
		return amountLest;
	}

	public void setAmountLest(BigDecimal amountLest) {
		this.amountLest = amountLest;
	}
	
	@Override
	public String toString() {
		return "BalanceEntity [amount=" + amount + ", periodType=" + periodType
				+ ", numberOfPeriod=" + numberOfPeriod + ", periodYear="
				+ periodYear + "]";
	}
	
}
