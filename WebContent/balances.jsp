<%@ page import="com.mmm.lws.acumulation.balance.PeriodType"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="header-include.jsp">
	<jsp:param value="Start page" name="title" />
</jsp:include>
</head>
<body>
	<div class="wrapper">
		<%
			pageContext.setAttribute("pts", PeriodType.values());
			getServletContext().setAttribute("reqPage",  "/lws/regbal.jsp");
		%>
		<jsp:include page="header.jsp" />
		<div class="container">
			<jsp:include page="menu.jsp" />
			<div class="content">
				<div class="choice-period">
					<a class="choice-period-link">MONTH</a>
					<ul class="all-periods">
						<c:forEach items="${pts}" var="pt">
						<li><a href="/lws/rest/load/balances/${pt}">${pt}</a></li>
						</c:forEach>
					</ul>
				</div>
				<div>${sessionScope["balances"]}</div>
				<table class="amount-data balaces">
					<caption>Result for the last month:</caption>
					<tr>
						<th>Amount</th>
						<th>Amount Left</th>
						<th>Created Date</th>
						<th>Period</th>
					</tr>
					<tr id="ss">
						<td>125</td>
						<td>87</td>
						<td>12-10-2011</td>
						<td>Week</td>
						<td class="hidden"><a id="a" href="period.jsp"></a></td>
					</tr>
					<tr>
						<td>55</td>
						<td>10</td>
						<td>30-10-2011</td>
						<td>Day</td>
						<td class="hidden"><a href="period.jsp"></a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
