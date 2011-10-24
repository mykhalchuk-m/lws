package com.mmm.lws.testservlets.acumulation.balance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmm.lws.acumulation.balance.BalanceEntity;
import com.mmm.lws.acumulation.balance.PeriodType;
import com.mmm.lws.acumulation.balance.dao.BalanceDao;
import com.mmm.lws.acumulation.costs.CostsEntity;

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
		BalanceEntity balance = new BalanceEntity();
		balance.setAmount(BigDecimal.valueOf(1000));
		balance.setStartDate(new Date(System.currentTimeMillis()));
		List<CostsEntity> list = new ArrayList<CostsEntity>();
		list.add(createCost(BigDecimal.valueOf(100), "dayly costs"));
		balance.setPeriodType(PeriodType.DAY);
		balance.setUpdates(list);
		return balance;
	}
	
	private BalanceEntity getWeeklyBalance() {
		BalanceEntity balance = new BalanceEntity();
		balance.setAmount(BigDecimal.valueOf(1000));
		balance.setStartDate(new Date(System.currentTimeMillis()));
		List<CostsEntity> list = new ArrayList<CostsEntity>();
		list.add(createCost(BigDecimal.valueOf(250), "weekly costs"));
		balance.setPeriodType(PeriodType.WEEK);
		balance.setUpdates(list);
		return balance;
	}
	
	private BalanceEntity getMonthlyBalance() {
		BalanceEntity balance = new BalanceEntity();
		balance.setAmount(BigDecimal.valueOf(1000));
		balance.setStartDate(new Date(System.currentTimeMillis()));
		List<CostsEntity> list = new ArrayList<CostsEntity>();
		list.add(createCost(BigDecimal.valueOf(1000), "monthly costs"));
		balance.setPeriodType(PeriodType.MONTH);
		balance.setUpdates(list);
		return balance;
	}
	
	private CostsEntity createCost(BigDecimal amount, String description) {
		CostsEntity entity = new CostsEntity();
		entity.setAmount(amount);
		entity.setDate(new Date(System.currentTimeMillis()));
		entity.setDescription(description);
		return entity;
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
