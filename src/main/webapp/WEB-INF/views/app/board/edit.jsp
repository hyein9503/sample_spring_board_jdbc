<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="default_layout">
    <tiles:putAttribute name="body">
    
    	<script type="text/javascript" src="${pageContext.request.contextPath}/tinymce/tinymce.min.js"></script>
		<script type="text/javascript">
			tinymce.init({
		    	selector: '#contents'
		  	});
		</script>
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_submit(){
				if(isNull($("#title").val())){
					alert('제목을 입력해주세요.');	
					return false;
				}
				tinyMCE.triggerSave(); // tinymce editor
				if(isNull($("#contents").val())){
					alert('내용을 입력해주세요.');	
					return false;
				}
				$("#editForm").submit();
			}
			
			function f_reset(){
				$("form").each(function() {  
					this.reset();  
				}); 
			}
			
		</script>
		
		<div class="content">
		
			<div class="board-view">
				<form:form id="editForm"  action="${pageContext.request.contextPath}/board/edit" method="post" commandName="boardVO">
					<form:hidden path="seq" />
					<form:hidden path="reg_id" />
				
					<table>
						<colgroup>
							<col width="100%" />
						</colgroup>
					
						<tbody>
							<tr>
								<td><form:input path="title" style="width: 99%;" /></td>
							</tr>
							
							<tr>
								<td><form:textarea path="contents" style="width: 99%; height: 300px;" /></td>
							</tr>
							
							<tr>
								<td>${boardVO.reg_date}</td>
							</tr>
						</tbody>
					</table>
				
				</form:form>
			</div><!-- board-view end -->
			
		</div><!-- content end -->
		
		<div class="buttons">
			<c:if test="${! empty sessionScope.SESSION_MEMBER_ID}">
				<a href="#" onclick="f_submit();">edit</a>&nbsp;&nbsp;
				<a href="#" onclick="f_reset();">reset</a>&nbsp;&nbsp;
			</c:if>
			<a href="${pageContext.request.contextPath}/board/list">list</a>&nbsp;&nbsp;
		</div><!-- buttons end -->
			
	</tiles:putAttribute>
</tiles:insertDefinition>