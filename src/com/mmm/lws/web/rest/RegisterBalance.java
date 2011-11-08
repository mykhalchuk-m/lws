package com.mmm.lws.web.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.calculation.period.Day;
import com.mmm.lws.calculation.period.Month;
import com.mmm.lws.calculation.period.Week;
import com.mmm.lws.utils.CalendarUtils;

@Path("/balance")
@Stateless
public class RegisterBalance {

	@EJB
	private BalanceDao balanceDao;
	@EJB
	private Day day;
	@EJB
	private Week week;
	@EJB
	private Month month;

	@Context
	private ServletContext context;

	@POST
	@Path("/reg")
	public Response registerBalance(@FormParam("am") BigDecimal amount,
			@FormParam("pt") PeriodType periodType,
			@FormParam("sd") String date,
			@Context HttpServletResponse response,
			@Context HttpServletRequest request) {
		BalanceEntity balance = new BalanceEntity();
		Date pDate;
		try {
			pDate = CalendarUtils.convertStringToDate(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pDate);
			balance.setNumberOfPeriod(CalendarUtils.getPeriodNumber(calendar,
					periodType));
			balance.setPeriodYear(calendar.get(Calendar.YEAR));
		} catch (ParseException e) {
			redirectToError(e, response, request);
			e.printStackTrace();
		}
		balance.setCreatedDate(new Date(System.currentTimeMillis()));
		balance.setAmount(amount);
		balance.setPeriodType(periodType);
		try {
			balanceDao.persistBalance(balance);
//			throw new PersistenceException("test exception");
		} catch (PersistenceException e) {
			redirectToError(e, response, request);
		}
		String reqPage = context.getAttribute("reqPage").toString();
		if (reqPage == null || reqPage == "") {
			reqPage = "/lws/index.jsp";
		}
		try {
			response.sendRedirect(reqPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/grb")
	public Response getRealBalance(@QueryParam("d") String destDate,
			@QueryParam("tp") PeriodType periodType) {
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		try {
			date = CalendarUtils.convertStringToDate(destDate);
		} catch (ParseException e) {
			System.out.println("Retriving balance for current period");
		}
		String resp = buildResponce(periodType, date);
		return Response.status(200).entity(resp).build();
	}

	private void redirectToError(Exception e, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			request.setAttribute("exception", e.getMessage());
			response.sendRedirect("/lws/error.jsp");
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	private String buildResponce(PeriodType periodType, Date date) {
		StringBuilder resp = new StringBuilder();
		resp.append("<span>Your current balance for this </span>");
		resp.append(periodType.toString().toLowerCase());
		BigDecimal bd1 = day.getRealBalannce(date);
		BigDecimal bd2 = week.getRealBalannce(date);
		BigDecimal bd3 = month.getRealBalannce(date);
		resp.append("<ul>");
		if (periodType == PeriodType.DAY) {
			resp.append("<li>");
			resp.append("<span>for day: </span>");
			resp.append((bd1 == null) ? "-" : bd1);
			resp.append("</li>");
			resp.append("<li>");
			resp.append("<span>for week: </span>");
			resp.append((bd2 == null) ? "-" : bd2);
			resp.append("</li>");
			resp.append("<li>");
			resp.append("<span>for month: </span>");
			resp.append((bd3 == null) ? "-" : bd3);
			resp.append("</li>");
		} else if (periodType == PeriodType.WEEK) {
			resp.append("<li>");
			resp.append("<span>for week: </span>");
			resp.append((bd2 == null) ? "-" : bd2);
			resp.append("</li>");
			resp.append("<li>");
			resp.append("<span>for month: </span>");
			resp.append((bd3 == null) ? "-" : bd3);
			resp.append("</li>");
		} else {
			resp.append("<li>");
			resp.append("<span>for month: </span>");
			resp.append((bd3 == null) ? "-" : bd3);
			resp.append("</li>");
		}
		resp.append("</ul>");
		return resp.toString();
	}
}
