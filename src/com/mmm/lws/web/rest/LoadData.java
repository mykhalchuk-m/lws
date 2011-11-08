package com.mmm.lws.web.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/load")
public class LoadData {
	@GET
	@Path("/balances")
	public Response loadBalances(@Context HttpServletResponse response,
			@Context HttpServletRequest request) {
		
		try {
			response.sendRedirect("/lws/balances.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
