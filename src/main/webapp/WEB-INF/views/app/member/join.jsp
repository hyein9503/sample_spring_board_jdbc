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
				if(isNull($("#member_id").val())){
					alert('아이디를 입력해주세요.');	
					return false;
				}
				if(isNull($("#member_pw").val())){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				if(isNull($("#member_pw2").val())){
					alert('비밀번호 재확인을 입력해주세요.');
					return false;
				}
				if(!isEqual($("#member_pw").val(), $("#member_pw2").val())){
					alert('비밀번호가 일치하지 않습니다.');
					$("#member_pw2").val("");
					return false;
				}
				if(isNull($("#member_name").val())){
					alert('이름을 입력해주세요.');
					return false;
				}
				if(isNull($("#email").val())){
					alert('이메일 입력해주세요.');
					return false;
				}
				$("#joinForm").submit();
			}
		</script>
		
		
		<div class="content">
		
			<form:form id="joinForm" action="${pageContext.request.contextPath}/member/join" method="post" commandName="memberVO" >
				아이디 : <form:input path="member_id" /><br /><br />
				비밀번호 : <form:password path="member_pw" /><br /><br />
				비밀번호 재확인 : <form:password path="member_pw2" /><br /><br />
				이름 : <form:input path="member_name" /><br /><br />
				이메일 : <form:input path="email" /><br /><br />
			</form:form>
			
		</div>
		
		<div class="buttons">
			<a href="#" onclick="f_submit();">join</a>&nbsp;&nbsp;
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>