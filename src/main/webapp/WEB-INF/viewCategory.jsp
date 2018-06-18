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
	<h1><c:out value="${category.name}" /></h1>
	
	<h2>Products:</h2>
	<c:forEach items="${category.getProducts()}" var="product">
		<p><c:out value="${product.name}" /></p>
	</c:forEach>
	<hr>
	<h3>Add Product:</h3>
	<form action="/addProduct" method="post">
		<input type="hidden" value="${category.id}" name="categoryId">
		<select name="newProduct">
			<option value="-" label="Select New Product" />
			<c:forEach items="${products}" var="product">
				<option value="${product.id}" label="${product.name}" />
			</c:forEach>
		</select>
		<input type="submit" value="Add Product">
	</form>
	
</body>
</html>