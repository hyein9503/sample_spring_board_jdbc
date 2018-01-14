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
			
			function f_search(){
				if(isNull($("#member_name").val())){
					alert('이름을 입력해주세요.');	
					return false;
				}
				if(isNull($("#email").val())){
					alert('이메일을 입력해주세요.');
					return false;
				}
				$("#searchForm").submit();
			}
		
		</script>
			
			
		<div class="content">
			<form:form id="searchForm" action="${pageContext.request.contextPath}/member/search" method="post" commandName="memberVO">
				이름 : <form:input path="member_name" /><br /><br />
				이메일 : <form:input path="email" /><br /><br />
			</form:form>
		</div>
		
		<div class="buttons">
			<a href="#" onclick="f_search()">search</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/main/gate">main</a>&nbsp;&nbsp;
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>