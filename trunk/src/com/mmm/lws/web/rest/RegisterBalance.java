package com.mmm.lws.web.rest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;

@Path("/balance")
@Stateless
public class RegisterBalance {

	@EJB
	private BalanceDao balanceDao;

	@POST
	@Path("/reg")
	public Response registerBalance(@FormParam("am") BigDecimal amount,
			@FormParam("pt") PeriodType periodType, @FormParam("sd") String date) {
		BalanceEntity balance = new BalanceEntity();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date pDate = sdf.parse(date);
			balance.setStartDate(pDate);
			balance.setNumberOfPeriod(getNumberOfPeriod(pDate, periodType));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		balance.setAmount(amount);
		balance.setPeriodType(periodType);
		balanceDao.persistBalance(balance);
		return Response.status(200).entity("Saved").build();
	}
	
	private int getNumberOfPeriod(Date date, PeriodType periodType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (periodType.equals(PeriodType.DAY)) {
			return calendar.get(Calendar.DAY_OF_YEAR);
		} else if (periodType.equals(PeriodType.WEEK)) {
			return calendar.get(Calendar.WEEK_OF_YEAR);
		} else if (periodType.equals(PeriodType.MONTH)) {
			return calendar.get(Calendar.MONTH);
		} else {
			return 0;
		}
	}
}