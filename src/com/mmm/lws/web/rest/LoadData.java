package com.mmm.lws.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.calculation.period.Day;
import com.mmm.lws.calculation.period.IPeriod;
import com.mmm.lws.calculation.period.Month;
import com.mmm.lws.calculation.period.Week;

@Path("/load")
@Stateless
public class LoadData {
	@EJB
	private Day day;
	@EJB
	private Week week;
	@EJB
	private Month month;
	
	@GET
	@Path("/balances")
	public Response loadBalances(@Context HttpServletResponse response,
			@Context HttpServletRequest request) {

		try {
			response.sendRedirect("/lws/balances.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/balances/{period}")
	public Response loadBalanceByPeriod(
			@PathParam("period") String periodType,
			@Context HttpServletResponse response,
			@Context HttpServletRequest request) throws URISyntaxException {
		PeriodType pt = PeriodType.valueOf(periodType);
		IPeriod period = getBeanByPeriod(pt);
		List<BalanceEntity> balances = period.getBalanceByPeriod();
		request.getSession().setAttribute("balances", balances);
		try {
			response.sendRedirect("/lws/balances.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	
	private IPeriod getBeanByPeriod(PeriodType periodType) {
		if (periodType.equals(PeriodType.DAY)) {
			return day;
		} else if (periodType.equals(PeriodType.WEEK)){
			return week;
		} else if (periodType.equals(PeriodType.MONTH)) {
			return month;
		}
		return null;
	}
}
