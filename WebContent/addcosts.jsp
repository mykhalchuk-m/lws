<%@ page import="com.mmm.lws.acumulation.balance.PeriodType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="header-include.jsp">
	<jsp:param value="Add cost" name="title"/>
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
	%>
		<jsp:include page="header.jsp"/>
		<div class="container">
		<jsp:include page="menu.jsp"/>
		<form action="/lws/rest/costs/add" method="post" class="form">
			<div class="form-fild">
				<label for="pt">Period</label> 
				<select name="pt" id="period">
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
				<input type="submit" value="Add" />
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
					loadBalance($(".date-pick").val(), $("#period option:selected").text());
				}).change();
				$(".date-pick").change(function(){
					loadBalance($(".date-pick").val(), $("#period option:selected").text());
				});
			});
			
			var loadBalance = function(data, typePeriod) {
				$("#message").load(
						"/lws/rest/balance/grb?d=" + data + "&tp=" + typePeriod);
				$("#message").css("display", "block");
			};
		</script>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>