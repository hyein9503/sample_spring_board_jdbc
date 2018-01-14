<%@ page import="com.simpolor.app.member.vo.MemberVO"%>
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
			<c:choose>
				<c:when test="${! empty sessionScope.SESSION_MEMBER_ID}">
					<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a><br />
					<a href="${pageContext.request.contextPath}/member/view">마이페이지</a><br />
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/member/join">회원가입</a><br />
					<a href="${pageContext.request.contextPath}/member/login">로그인</a><br />
				</c:otherwise>
			</c:choose>
			
			<br />
			<br />
			<a href="${pageContext.request.contextPath}/board/list">게시판</a><br />
		</div><!-- content end -->
		
		<br/>
		<br/>
		회원_아이디 : ${sessionScope.SESSION_MEMBER_ID}<br/>
		회원_이름 : ${sessionScope.SESSION_MEMBER_NAME}<br/>
		회원_등급 : ${sessionScope.SESSION_MEMBER_LEVEL}<br/>
				
	</tiles:putAttribute>
</tiles:insertDefinition>