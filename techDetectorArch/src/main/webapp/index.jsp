<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}
</style>
</head>
<body>
	<h2>Enter the Website URL:</h2>
	<form action="/techDetectorArch/techDetect">
		<input name="url" type="text" value="enter website url" /> <input
			type="submit" value="Find Technology" />

	</form>

	<h2>

		<c:choose>
			<c:when test="${empty errorMsg}">
				<table>
					<caption>
						Technologies used by: <b> </b>
					</caption>

					<c:forEach var="attribute" items="${attributes}">
						<tr>
							<td>${attribute.key}</td>
							<td>${attribute.value}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<h1>
					<c:out value="${errorMsg}" />
				</h1>
			</c:otherwise>
		</c:choose>

	</h2>
</body>
</html>
