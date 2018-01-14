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
			${member_name} 님 변경이 완료되었습니다.<br/>
		</div>
		
		<div class="buttons">
			<a href="${pageContext.request.contextPath}/home">home</a>&nbsp;&nbsp;
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>