package com.mmm.lws.web.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
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

	@EJB private BalanceDao balanceDao;
	@EJB private Day day;
	@EJB private Week week;
	@EJB private Month month;
	
	@Context
	private ServletContext context;

	@POST
	@Path("/reg")
	public Response registerBalance(@FormParam("am") BigDecimal amount,
			@FormParam("pt") PeriodType periodType,
			@FormParam("sd") String date, @Context HttpServletResponse response)
			throws URISyntaxException {
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
			e.printStackTrace();
		}
		balance.setCreatedDate(new Date(System.currentTimeMillis()));
		balance.setAmount(amount);
		balance.setPeriodType(periodType);
		balanceDao.persistBalance(balance);
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
		BigDecimal realAmount = getRealBalanceForPeriod(periodType, date);
		return Response.status(200).entity(realAmount.toString()).build();
	}

	private BigDecimal getRealBalanceForPeriod(PeriodType periodType, Date date) {
		if (periodType == PeriodType.DAY) {
			return day.getRealBalannce(date);
		} else if (periodType == PeriodType.WEEK) {
			return week.getRealBalannce(date);
		} else {
			return month.getRealBalannce(date);
		}
	}
}