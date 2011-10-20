package com.mmm.lws.testservlets.acumulation.dao.balance;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmm.lws.acumulation.dao.balance.BalanceEntity;
import com.mmm.lws.acumulation.dao.balance.DaylyBalance;
import com.mmm.lws.acumulation.dao.balance.MonthlyBalance;
import com.mmm.lws.acumulation.dao.balance.WeeklyBalance;
import com.mmm.lws.acumulation.dao.balance.dao.BalanceDao;

/**
 * Servlet implementation class BalanceDaoTest
 */
@WebServlet("/bdt")
public class BalanceDaoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BalanceDao balanceDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BalanceEntity dbalance = getDaylyBalance();
		BalanceEntity wbalance = getWeeklyBalance();
		BalanceEntity mbalance = getMonthlyBalance();
		balanceDao.persistBalance(dbalance);
		balanceDao.persistBalance(mbalance);
		balanceDao.persistBalance(wbalance);
	}

	private BalanceEntity getDaylyBalance() {
		BalanceEntity balance = new DaylyBalance();
		balance.setAmount(BigDecimal.valueOf(1000));
		balance.setCreatedDate(new Date(System.currentTimeMillis()));
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		list.add(BigDecimal.valueOf(3));
		list.add(BigDecimal.valueOf(6));
		list.add(BigDecimal.valueOf(78));
		list.add(BigDecimal.valueOf(0.2));
		balance.setUpdates(list);
		return balance;
	}
	
	private BalanceEntity getWeeklyBalance() {
		BalanceEntity balance = new WeeklyBalance();
		balance.setAmount(BigDecimal.valueOf(1000));
		balance.setCreatedDate(new Date(System.currentTimeMillis()));
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		list.add(BigDecimal.valueOf(3));
		list.add(BigDecimal.valueOf(6));
		list.add(BigDecimal.valueOf(78));
		list.add(BigDecimal.valueOf(0.2));
		balance.setUpdates(list);
		return balance;
	}
	
	private BalanceEntity getMonthlyBalance() {
		BalanceEntity balance = new MonthlyBalance();
		balance.setAmount(BigDecimal.valueOf(1000));
		balance.setCreatedDate(new Date(System.currentTimeMillis()));
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		list.add(BigDecimal.valueOf(3));
		list.add(BigDecimal.valueOf(6));
		list.add(BigDecimal.valueOf(78));
		list.add(BigDecimal.valueOf(0.2));
		balance.setUpdates(list);
		return balance;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
