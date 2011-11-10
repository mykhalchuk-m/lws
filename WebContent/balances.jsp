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
				<table class="amount-data balaces">
					<caption>Result for the last month:</caption>
					<tr>
						<th>Amount</th>
						<th>Amount Left</th>
						<th>Created Date</th>
						<th>Period</th>
					</tr>
					<c:forEach items="${sessionScope['balances']}" var="balance">
						<tr>
							<td>${balance.amount}</td>
							<td>${balance.amountLest}</td>
							<td>${balance.createdDate}</td>
							<td>${balance.numberOfPeriod}</td>
							<c:if test="${balance.periodType.childPeriod != null}">
								<td class="hidden"><a id="a"
									href="/lws/rest/load/balances/${balance.periodYear}/${balance.periodType}/${balance.numberOfPeriod}"></a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
