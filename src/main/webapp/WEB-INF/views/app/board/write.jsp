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
				$("#writeForm").submit();
			}
		
			function f_reset(){
				$("form").each(function() {  
					this.reset();  
				});  
			}
			
		</script>
		
		
		<div class="content">
			<div class="board-view">
				<form:form id="writeForm"  action="${pageContext.request.contextPath}/board/write" method="post" commandName="boardVO">
				<table>
					<colgroup>
						<col width="15%" />
						<col width="85%" />
					</colgroup>
				
					<tbody>
						<tr>
							<th>제목</th>
							<td>
								<form:input path="title" style="width: 99%;" />
							</td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td><form:textarea path="contents" style="width: 99%; height: 300px;" /></td>
						</tr>
					</tbody>
				</table>
				
				</form:form>
			</div><!-- board-view end -->
		</div><!-- content end -->
		
		<div class="buttons">
			<c:if test="${! empty sessionScope.SESSION_MEMBER_ID}">
				<a href="#" onclick="f_submit();">write</a>&nbsp;&nbsp;
				<a href="#" onclick="f_reset();">reset</a>&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/board/list" onclick="history.back();">list</a>&nbsp;&nbsp;
			</c:if>
		</div><!-- buttons end -->

	</tiles:putAttribute>
</tiles:insertDefinition>