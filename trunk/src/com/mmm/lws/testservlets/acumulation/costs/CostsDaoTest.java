package com.mmm.lws.testservlets.acumulation.costs;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmm.lws.acumulation.costs.CostsEntity;
import com.mmm.lws.acumulation.costs.dao.CostsDao;

/**
 * Servlet implementation class CostsDaoTest
 */
@WebServlet("/cdt")
public class CostsDaoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CostsDao costsDao;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CostsEntity costs = new CostsEntity();
		costs.setAmount(new BigDecimal(request.getParameter("amount")));
		costs.setDescription(request.getParameter("description"));
		String isNow = request.getParameter("now"); 
		if ("on".equals(isNow)) {
			costs.setDate(new Date(System.currentTimeMillis()));
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy HH:mm");
			try {
				costs.setDate(sdf.parse(request.getParameter("date")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		costsDao.persistCosts(costs);
	}
}
