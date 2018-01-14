<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="default_layout">
    <tiles:putAttribute name="body">
		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
		
			function f_login(){
				if(isNull($("#member_id").val())){
					alert('아이디를 입력해주세요.');	
					return false;
				}
				if(isNull($("#member_pw").val())){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				$("#loginForm").submit();
			}
		</script>
			
			
		<div class="content">
			<form:form id="loginForm" commandName="memberVO" action="${pageContext.request.contextPath}/member/login" method="post">
				아이디 : <form:input path="member_id" /><br /><br />
				비밀번호 : <form:password path="member_pw" /><br /><br />
			</form:form>
		</div><!-- content end -->
		
		<div class="buttons">
			<a href="#" onclick="f_login();">login</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/member/search">search</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/member/join">join</a>&nbsp;&nbsp;
		</div><!-- buttons end -->
			
	</tiles:putAttribute>
</tiles:insertDefinition>