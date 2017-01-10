<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<nav>
	<a href="${pageContext.request.contextPath}/"><img class="logo" src="${pageContext.request.contextPath}/resources/img/logo.png" width="100"></a>
	<ul id="menu">
		<li><a href="${pageContext.request.contextPath}/"><spring:message code="home"/></a></li>
       <li><a href="${pageContext.request.contextPath}/getCards"><spring:message code="getcards"/></a></li>
       <li><a href="${pageContext.request.contextPath}/sortCards"><spring:message code="sortcards"/></a></li>
	</ul>
</nav>