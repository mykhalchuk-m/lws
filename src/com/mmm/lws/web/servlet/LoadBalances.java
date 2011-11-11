package com.mmm.lws.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.calculation.period.Day;
import com.mmm.lws.calculation.period.IPeriod;
import com.mmm.lws.calculation.period.Month;
import com.mmm.lws.calculation.period.Week;

/**
 * Servlet implementation class LoadBalances
 */
@WebServlet("/lb")
public class LoadBalances extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private Day day;
	@EJB
	private Week week;
	@EJB
	private Month month;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PeriodType pt = (request.getParameter("p") == null) ? (PeriodType.MONTH)
				: PeriodType.valueOf(request.getParameter("p"));
		
		IPeriod period = getBeanByPeriod(pt);
		List<BalanceEntity> balances = period.getBalanceByPeriod();
		request.setAttribute("balances", balances);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/balances.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	
}
