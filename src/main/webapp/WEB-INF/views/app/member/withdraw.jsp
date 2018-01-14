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
			
			function f_withdraw(){
				if(isNull($("#member_pw").val())){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				$("#withdrawForm").submit();
			}
		</script>
				
				
		<div class="content">
			회원탈퇴를 진행하실려면 아래에 비밀번호 다시 한번 더 입력해주세요.<br /><br /><br />
			<form:form id="withdrawForm" action="${pageContext.request.contextPath}/member/withdraw" method="post" commandName="memberVO">
				<form:hidden path="member_id" />
				
				아이디 : ${memberVO.member_id}<br /><br />
				비밀번호 : <form:password path="member_pw" /><br /><br />
			</form:form>
		</div>
		
		<div class="buttons">
			<a href="#" onclick="f_withdraw()">withdraw</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/member/view">back</a>&nbsp;&nbsp;
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>