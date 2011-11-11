<%@ page import="com.mmm.lws.acumulation.balance.PeriodType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="header-include.jsp">
	<jsp:param value="Add balance" name="title"/>
</jsp:include>
<script type="text/javascript">
      $(function() {
          $('.date-pick').datePicker({autoFocusNextInput: true});            
      });		
</script>
</head>
<body>
	<div class="wrapper">
	<%
		pageContext.setAttribute("pts", PeriodType.values());
		getServletContext().setAttribute("reqPage",  "/lws/regbal.jsp");
	%>
	<jsp:include page="header.jsp"/>
		<div class="container">
		<jsp:include page="menu.jsp"/>
		<form id="" method="post" action="/lws/rb" class="form">
			<div class="form-fild">
				<label for="pt">Period</label> <select name="pt">
					<c:forEach items="${pts}" var="pt">
						<option value="${pt}">${pt}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-fild date">
				<label for="sd">Chose a date from destination period</label>
				<input id="sd" name="sd" class="date-pick dp-applied" /> 
			</div>
			<div class="form-fild">
				<label for="am">Amount</label><input name="am" />
			</div>
			<div class="form-fild">
				<input name="Register" type="submit" value="Register"/>
			</div>
		</form>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>