<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<jsp:include page="header-include.jsp">
	<jsp:param value="Start page" name="title"/>
</jsp:include>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp"/>
		<div class="container">
			<jsp:include page="menu.jsp"/>
			<div>
				<h4>LWS</h4>
			</div>
		</div>
		<div class="hFooter"></div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>
