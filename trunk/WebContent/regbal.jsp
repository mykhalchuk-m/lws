<%@page import="com.mmm.lws.acumulation.balance.PeriodType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/js/date.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="resources/style/datePicker.css">
<script type="text/javascript">
      $(function() {
          $('.date-pick').datePicker({autoFocusNextInput: true});            
      });		
</script>
<link rel="stylesheet" type="text/css" media="screen" href="resources/style/style.css">
<title>Add balance</title>
</head>
<body>
	<%
		pageContext.setAttribute("pts", PeriodType.values());
	%>
	<form method="post" action="/lws/rest/balance/reg">
		<div class="form-fild">
			<label for="am">Amount</label><input name="am" />
		</div>
		<div class="form-fild">
			<label for="pt">Period</label> <select name="pt">
				<c:forEach items="${pts}" var="pt">
					<option value="${pt}">${pt}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-fild date">
			<label for="sd">Date</label>
			<input id="sd" name="sd" class="date-pick dp-applied" /> 
		</div>
		<div class="form-fild">
			<input name="Register" type="submit" />
		</div>
	</form>
</body>
</html>