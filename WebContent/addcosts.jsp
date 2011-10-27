<%@ page import="com.mmm.lws.acumulation.balance.PeriodType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>add cost</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/js/date.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="resources/style/datePicker.css">
<link rel="stylesheet" type="text/css" media="screen" href="resources/style/style.css">
<script type="text/javascript">
      $(function() {
          $('.date-pick').datePicker({autoFocusNextInput: true});            
      });		
</script>
</head>
<body>
	<div class="body">
	<%
		pageContext.setAttribute("pts", PeriodType.values());
	%>
	<jsp:include page="menu.jsp"/>
	<form action="/lws/rest/costs/add" method="post" class="form">
		<div class="form-fild">
			<label for="pt">Period</label> <select name="pt" id="period">
				<c:forEach items="${pts}" var="pt">
					<option value="${pt}">${pt}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-fild">
			<label>is now?</label> <input type="checkbox" name="now" id="isNow" checked="checked" />
		</div>
		<div id="date" class="form-fild date" style="display: none;">
			<label>date</label> <input name="date" class="date-pick dp-applied" />
		</div>
		<div id="message" class="form-fild" style="display: none;"></div>
		<div class="form-fild">
			<label>amount</label> <input name="am" />
		</div>
		<div class="form-fild">
			<label>description</label> <input name="des" />
		</div>
		<div class="form-fild">
			<input type="submit" value="add" />
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#isNow").click(function() {
				if ($(this).is(":checked")) {
					$("#date").css("display", "none");
				} else {
					$("#date").css("display", "block");

				};
			});
			$("#period").change(function(){
				if($("#isNow").is(":checked")) {
					loadBalance(null, $("#period option:selected").text());
				}							
			}).change();
		});
		
		var loadBalance = function(data, typePeriod) {
			$("#message").load(
					"/lws/rest/balance/grb?d=" + data + "&tp=" + typePeriod);
			$("#message").css("display", "block");
		};
	</script>
	</div>
</body>
</html>