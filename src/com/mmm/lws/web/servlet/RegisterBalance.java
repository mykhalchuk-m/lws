package com.mmm.lws.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.utils.CalendarUtils;

/**
 * Servlet implementation class RegisterBalance
 */
@WebServlet(name = "rb", urlPatterns = { "/rb" })
public class RegisterBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BalanceDao balanceDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/regbal.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BalanceEntity balance = new BalanceEntity();
		Date pDate;
		PeriodType periodType = PeriodType.valueOf(request.getParameter("pt"));
		BigDecimal amount = new BigDecimal(request.getParameter("am"));
		try {
			pDate = CalendarUtils.convertStringToDate(request
					.getParameter("sd"));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pDate);
			balance.setNumberOfPeriod(CalendarUtils.getPeriodNumber(calendar,
					periodType));
			balance.setPeriodYear(calendar.get(Calendar.YEAR));
			balance.setCreatedDate(new Date(System.currentTimeMillis()));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		balance.setAmount(amount);
		balance.setPeriodType(periodType);
		try {
			BigDecimal result = balanceDao.persistBalance(balance);
			if (result != null) {
				request.setAttribute("result", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}
}
