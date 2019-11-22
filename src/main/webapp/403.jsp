<%--
  Created by IntelliJ IDEA.
  User: venkateswarlu.s
  Date: 25-06-2019
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>HTTP Status 403 - Access is denied</h1>
<h2>${msg}</h2>

<a href="#" onclick="return theFunction();">Back</a>

<script type="text/javascript">
    function theFunction () {
        window.location.href = document.referrer;
    }
</script>

</body>
</html>
