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
			
			function f_delete(){
				$("#viewForm").attr('action', '${pageContext.request.contextPath}/board/delete');
				$("#viewForm").submit();
			}
		</script>
		
		
		<div class="content">
			<div class="board-view">
				<form:form id="viewForm" method="post" commandName="boardVO">
					<form:hidden path="seq" />
					<form:hidden path="reg_id" />
					
					<table>
						<colgroup>
							<col width="100%" />
						</colgroup>
					
						<tbody>
							<tr>
								<td>${boardVO.title}</td>
							</tr>
							
							<tr style="min-height: 200px;">
								<td style="vertical-align: top;">${boardVO.contents}</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div><!-- board-view end -->
		</div><!-- content end -->
		
		<div class="buttons">
			<c:if test="${! empty sessionScope.SESSION_MEMBER_ID}">
				<c:if test="${boardVO.reg_id == sessionScope.SESSION_MEMBER_ID}">
					<a href="${pageContext.request.contextPath}/board/edit/${boardVO.seq}">edit</a>&nbsp;&nbsp;
					<a href="#" onclick="f_delete()">delete</a>&nbsp;&nbsp;
				</c:if>
			</c:if>
			<a href="${pageContext.request.contextPath}/board/list">list</a>&nbsp;&nbsp;
		</div><!-- buttons end -->
			
	</tiles:putAttribute>
</tiles:insertDefinition>