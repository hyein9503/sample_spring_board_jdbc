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
		
			function f_submit(){
				if(isNull($("#member_name").val())){
					alert('이름을 입력해주세요.');	
					return false;
				}
				if(isNull($("#email").val())){
					alert('이메일을 입력해주세요.');
					return false;
				}
				$("#editForm").submit();
			}
		</script>
			
			
		<div class="content">
		
			<form:form id="editForm" action="${pageContext.request.contextPath}/member/edit" method="post" commandName="memberVO" >
				아이디 : ${memberVO.member_id}<br /><br />
				이름 : <form:input path="member_name" /><br /><br />
				이메일 : <form:input path="email" /><br /><br />
			</form:form>
			
		</div>
		
		<div class="buttons">
			<a href="#" onclick="f_submit()">edit</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/member/view">back</a>&nbsp;&nbsp;
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>