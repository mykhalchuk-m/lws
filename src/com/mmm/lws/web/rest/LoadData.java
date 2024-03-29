package com.mmm.lws.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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
	public Response loadBalanceByPeriod(
			@DefaultValue("MONTH") @QueryParam("p") String periodType,
			@Context HttpServletResponse response,
			@Context HttpServletRequest request) throws URISyntaxException {
		PeriodType pt = PeriodType.valueOf(periodType);
		IPeriod period = getBeanByPeriod(pt);
		List<BalanceEntity> balances = period.getBalanceByPeriod();
		request.getSession().setAttribute("balances", balances);
		request.setAttribute("t", "test");
		goToRedirect("/lws/balances.jsp", response, request);
		return Response.ok().build();
	}
	
	@GET
	@Path("/balances/{year}/{period}/{perionNum}")
	public Response loadBalanceByPeriod(@PathParam("period") String periodType,
			@PathParam("perionNum") Integer perionNum,
			@PathParam("year") Integer year,
			@Context HttpServletResponse response,
			@Context HttpServletRequest request) throws URISyntaxException {
		PeriodType pt = PeriodType.valueOf(periodType);
		IPeriod period = getBeanByPeriod(pt);
		List<BalanceEntity> balances = period.getSubBalancesByScope(perionNum, year);
		request.getSession().setAttribute("balances", balances);
		try {
			response.sendRedirect("/lws/balances.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	private IPeriod getBeanByPeriod(PeriodType periodType) {
		if (PeriodType.DAY.equals(periodType))
			return day;
		else if (PeriodType.WEEK.equals(periodType))
			return week;
		else if (PeriodType.MONTH.equals(periodType))
			return month;
		else
			return day;
	}
	private void goToRedirect(String uri, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			response.sendRedirect(uri);
		} catch (IOException e) {
//			redirectToError(e, response, request);
			e.printStackTrace();
		}
	}
}
