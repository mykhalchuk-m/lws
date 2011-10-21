package com.mmm.lws.acumulation.balance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.mmm.lws.acumulation.costs.CostsEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BalanceEntity implements Serializable {
	private static final long serialVersionUID = 7051078210988387981L;

	@Id
	@GeneratedValue
	private long id;
	private BigDecimal amount;
	@OneToMany(mappedBy = "balance")
	private List<CostsEntity> updates;
	private Date createdDate;

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
				+ updates.size() + ", createdDate=" + createdDate + "]";
	}

}
