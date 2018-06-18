<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>       
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><c:out value="${product.name}" /></h1>
	
	<h2>Categories:</h2>
	<c:forEach items="${product.getCategories()}" var="category">
		<p><c:out value="${category.name}" /></p>
	</c:forEach>
	<hr>
	<h3>Add Category:</h3>
	<form action="/addCategory" method="post">
		<input type="hidden" value="${product.id}" name="productId">
		<select name="newCategory">
			<option value="-" label="Select New Category" />
			<c:forEach items="${categories}" var="category">
				<option value="${category.id}" label="${category.name}" />
			</c:forEach>
		</select>
		<input type="submit" value="Add Category">
	</form>
	
</body>
</html>