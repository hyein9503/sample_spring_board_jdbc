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
		</script>
			
			
		<div class="content">
			아이디 : ${memberVO.member_id}<br /><br />
			이름 : ${memberVO.member_name}<br /><br />
			이메일 : ${memberVO.email}<br /><br />
			가입일 : ${memberVO.reg_date}<br /><br />
			최종 수정일 : ${memberVO.mod_date}<br /><br />
		</div>
		
		<div class="buttons">
			<a href="${pageContext.request.contextPath}/member/edit">edit</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/member/password">password</a>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/member/withdraw">withdraw</a>&nbsp;&nbsp;
		</div>
	
	</tiles:putAttribute>
</tiles:insertDefinition>