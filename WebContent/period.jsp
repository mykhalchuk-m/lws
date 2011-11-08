<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="header-include.jsp">
	<jsp:param value="Costs for period" name="title"/>
</jsp:include>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp"/>
		<div class="container">
			<jsp:include page="menu.jsp"/>
			<div class="content">
				<table class="amount-data">
					<caption>Costed money for period:</caption>
					<tr>
						<th>Amount</th>
						<th>Description</th>
						<th>Created Date</th>
					</tr>
					<tr>
						<td>125</td>
						<td>beer</td>
						<td>12-10-2011</td>
					</tr>
					<tr>
						<td>25.8</td>
						<td>dinner</td>
						<td>12-10-2011</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
