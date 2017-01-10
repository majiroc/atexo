<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<c:if test="${error eq null}">
<h3><spring:message code="initial.list"/></h3>
<div class=row>
<c:forEach items="${gameNotSorted.data.cards}" var="card">
<div class="card image" style="background :url(${pageContext.request.contextPath}/resources/img/${card.value}${card.category}.png);background-size: 70px ; ">
</div>
</c:forEach>

</div>
<br/>
<h3><spring:message code="sorted.list"/></h3>
<div class=row>
<c:forEach items="${gameSorted.data.cards}" var="card">
<div class="card image" style="background :url(${pageContext.request.contextPath}/resources/img/${card.value}${card.category}.png);background-size: 70px ; ">
</div>
</c:forEach>

</div>
<br/>
<div class=row>

  <h3>
  <spring:message code="service.test.response"/> ${response}
  </h3>
  </div>
</c:if>


<c:if test="${error ne null}">
<div class=row>
 <spring:message code="${error}"/>
</div>
  </c:if>
