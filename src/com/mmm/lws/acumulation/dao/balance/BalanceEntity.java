package com.mmm.lws.acumulation.dao.balance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Entity
@Table(name = "balance")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BalanceEntity implements Serializable {
	private static final long serialVersionUID = 7051078210988387981L;

	@Id
	@GeneratedValue
	private long id;
	private BigDecimal amount;
	@Column(name = "updates")
	private String updatesJson;
	private Date createdDate;

	@Transient
	private static Gson gson = new Gson();

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

	public List<BigDecimal> getUpdates() {
		List<BigDecimal> updates = gson.fromJson(updatesJson,
				new TypeToken<List<BigDecimal>>() {
				}.getType());
		return updates;
	}

	public void setUpdates(List<BigDecimal> updates) {
		updatesJson = gson.toJson(updates);
	}

	@Override
	public String toString() {
		return "Balance [id=" + id + ", amount=" + amount + ", updatesJson="
				+ updatesJson + ", createdDate=" + createdDate + "]";
	}

}
