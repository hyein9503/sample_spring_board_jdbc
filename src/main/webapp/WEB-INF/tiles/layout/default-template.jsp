<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>title</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	</head>
	<body>
		<div id="wrap">
			<div id="header">
				<tiles:insertAttribute name="header" />
			</div>
			
			<div id="container">
				<tiles:insertAttribute name="body" />
			</div>
			
			<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div><!-- wrap end -->
	</body>
</html>
