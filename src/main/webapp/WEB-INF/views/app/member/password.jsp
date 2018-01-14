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
			
			function f_change(){
				if(isNull($("#member_pw_org").val())){
					alert('기존의 비밀번호를 입력해주세요.');
					return false;
				}
				if(isNull($("#member_pw").val())){
					alert('변경할 비밀번호를 입력해주세요.');
					return false;
				}
				if(isNull($("#member_pw2").val())){
					alert('변경할 비밀번호 재확인을 입력해주세요.');
					return false;
				}
				if(!isEqual($("#member_pw").val(), $("#member_pw2").val())){
					alert('변경할 비밀번호가 일치하지 않습니다.');
					$("#member_pw2").val("");
					return false;
				}
				$("#passwordForm").submit();
			}
		
		</script>
			
			
		<div class="content">
			<form:form id="passwordForm" action="${pageContext.request.contextPath}/member/password" method="post" commandName="memberVO" >
				<form:hidden path="member_id" />
				
				아이디 : ${memberVO.member_id}<br /><br />
				기존 비밀번호 : <form:password path="member_pw_org" /><br /><br />
				변경할 비밀번호 : <form:password path="member_pw" /><br /><br />
				변경할 비밀번호 재확인  : <form:password path="member_pw2" /><br /><br />
			</form:form>
		</div>
		
		<div class="buttons">
			<a href="#" onclick="f_change()">password change</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/member/view">back</a>&nbsp;&nbsp;
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>