package com.mmm.lws.web.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.CostsEntity;
import com.mmm.lws.acumulation.costs.dao.CostsDao;
import com.mmm.lws.utils.CalendarUtils;

@Path("/costs")
@Stateless
public class AddCosts {

	@EJB
	private BalanceDao balanceDao;
	
	@EJB
	private CostsDao costsDao;

	@POST
	@Path("/add")
	public Response addCosts(@FormParam("am") BigDecimal amount,
			@FormParam("des") String description,
			@FormParam("now") String isNow, @FormParam("date") String date,
			@FormParam("pt") PeriodType periodType, @Context HttpHeaders headers)
			throws URISyntaxException {
		CostsEntity costs = new CostsEntity();
		costs.setAmount(amount);
		costs.setDescription(description);
		if ("on".equals(isNow)) {
			costs.setDate(new Date(System.currentTimeMillis()));
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				costs.setDate(sdf.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(costs.getDate());
		BalanceEntity balance = balanceDao.getBalance(periodType,
				CalendarUtils.getPeriodNumber(calendar, periodType),
				calendar.get(Calendar.YEAR));
		costs.setBalance(balance);
		costsDao.persistCosts(costs);
		return Response.temporaryRedirect(
				new URI(headers.getRequestHeader("Referer").get(0))).build();
	}

}
