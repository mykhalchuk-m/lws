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
	private Date startDate;
	@Enumerated(EnumType.STRING)
	private PeriodType periodType;

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

	@Override
	public String toString() {
		return "Balance [id=" + id + ", amount=" + amount + ", updatesJson="
				+ updates.size() + ", createdDate=" + getStartDate() + "]";
	}

	public PeriodType getPeriodType() {
		return periodType;
	}

	public void setPeriodType(PeriodType periodType) {
		this.periodType = periodType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
