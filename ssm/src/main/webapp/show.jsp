<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\16 0016
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<c:forEach items="${list}" var="u">
    ${u.userid}------------${u.username}----------${u.password}<br>
</c:forEach>
</body>
</html>
