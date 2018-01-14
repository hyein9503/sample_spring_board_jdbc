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
			<div class="board-list">
				<table>
					<colgroup>
						<col width="5%" />
						<col width="80%" />
						<col width="15%" />
					</colgroup>
					
					<thead>
						<tr>
							<th>순번</th>
							<th>제목</th>
							<th>등록일</th>
						</tr>
					</thead>
				
					<tbody>
						<c:choose>
							<c:when test="${! empty boardList}">
								<c:forEach var="boardList" items="${boardList}">
									<tr>
							 			<td align="center">${boardList.seq}</td>
							 			<td align="center"><a href="${pageContext.request.contextPath}/board/view/${boardList.seq}">${boardList.title}</a></td>
							 			<td align="center">${boardList.reg_date}</td>
							 		</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
						 			<td colspan="3" align="center">내용이 없습니다.</td>
						 		</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div><!-- board-list end  -->
		</div><!-- content end -->
	
		<div class="buttons">
			<c:if test="${! empty sessionScope.SESSION_MEMBER_ID}">
				<a href="${pageContext.request.contextPath}/board/write">write</a>&nbsp;&nbsp;
			</c:if>
		</div><!-- buttons end -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>
