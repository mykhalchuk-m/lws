package com.mmm.lws.acumulation.costs.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mmm.lws.acumulation.costs.CostsEntity;

@Stateless
@LocalBean
public class CostsDao {
	@PersistenceContext(name = "lws-core")
	private EntityManager entityManager;

	public void persistCosts(CostsEntity costs) {
		entityManager.persist(costs);
	}

	public CostsEntity getCostByDate(Date date) {
		Query query = entityManager.createNativeQuery("Costs.byDate");
		query.setParameter("date", date);
		CostsEntity costsEntity = (CostsEntity) query.getSingleResult();
		return costsEntity;
	}

	@SuppressWarnings("unchecked")
	public List<CostsEntity> getCostsByPeriod(Date startDate, Date endDate) {
		Query query = entityManager.createNamedQuery("Costs.byPeriod");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<CostsEntity> entities = query.getResultList();
		return entities;
	}

	public List<CostsEntity> getCostsByPeriod(Date startDate) {
		Date now = new Date(System.currentTimeMillis());
		return getCostsByPeriod(startDate, now);
	}
}
