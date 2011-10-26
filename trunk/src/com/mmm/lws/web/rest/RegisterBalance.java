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
import com.mmm.lws.utils.CalendarUtils;

@Path("/balance")
@Stateless
public class RegisterBalance {

	@EJB
	private BalanceDao balanceDao;

	@POST
	@Path("/reg")
	public Response registerBalance(@FormParam("am") BigDecimal amount,
			@FormParam("pt") PeriodType periodType,
			@FormParam("sd") String date, @Context HttpHeaders headers)
			throws URISyntaxException {
		BalanceEntity balance = new BalanceEntity();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date pDate;
		try {
			pDate = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pDate);
			balance.setNumberOfPeriod(CalendarUtils.getPeriodNumber(calendar, periodType));
			balance.setPeriodYear(calendar.get(Calendar.YEAR));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		balance.setCreatedDate(new Date(System.currentTimeMillis()));
		balance.setAmount(amount);
		balance.setPeriodType(periodType);
		balanceDao.persistBalance(balance);
		return Response.temporaryRedirect(
				new URI(headers.getRequestHeader("Referer").get(0))).build();
	}
}