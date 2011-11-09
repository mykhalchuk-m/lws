<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="header-include.jsp">
	<jsp:param value="Error" name="title" />
</jsp:include>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp" />
		<div class="container">
			<jsp:include page="menu.jsp" />
			<div class="content">
				<p>Cause some error:</p>
				<div>
					${sessionScope["exception"]}
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
