<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/lws/cdt" method="post">
		<div>
			<label>amount</label> <input name="amount" />
		</div>
		<div>
			<label>description</label> <input name="description" />
		</div>
		<div>
			<label>is now?</label> <input type="checkbox" name="now" id="isNow" />
		</div>
		<div id="date">
			<label>date</label> <input name="date" />
		</div>
		<div>
			<input type="submit" value="add" />
		</div>
	</form>
	<script type="text/javascript">
		document.getElementById("isNow").onclick = function() {
			var date = document.getElementById("date");
			if (this.checked) {
				date.style.display = "none";
			} else {
				date.style.display = "block";
			}
		}
	</script>
</body>
</html>