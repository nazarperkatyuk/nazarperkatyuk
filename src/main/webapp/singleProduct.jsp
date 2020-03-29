<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cabinet</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<p>Product name:    <%= request.getAttribute("productName")%>
</p>
<p>Product description: <%= request.getAttribute("productD")%>
</p>
<p>Product price: <%= request.getAttribute("productP")%> грн</p>

<button type="button" class="btn btn-primary add-to-bucket" product-id="<%=request.getAttribute("productId")%>">
    Add to bucket
</button>

<jsp:include page="footer.jsp"></jsp:include>

<script src="js/header.js"></script>
<script src="js/singleProduct.js"></script>
</body>
</html>