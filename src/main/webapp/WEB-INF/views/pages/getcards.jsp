<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<h3><spring:message code="initial.list"/></h3>
<c:forEach items="${gameNotSorted.data.cards}" var="card">
<div class="card" style="background :url(${pageContext.request.contextPath}/resources/img/${card.value}${card.category}.png) ;  background-size: 70px ;">

</div>
</c:forEach>


