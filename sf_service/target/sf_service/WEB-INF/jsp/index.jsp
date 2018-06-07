<%--
  Created by IntelliJ IDEA.
  User: Zhangxq
  Date: 2016/7/16
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
    <a href="/">Home</a><br/>
    <c:if test="${!empty userList}">
        <c:forEach var="user" items="${userList}">
            姓名：${user.userName} &nbsp;&nbsp;手机号：${user.number}<br>
        </c:forEach>
    </c:if>
    <hr/>
    <c:if test="${!empty list}">
        <c:forEach var="user" items="${list}">
            姓名：${user.userName} &nbsp;&nbsp;手机号：${user.number}<br>
        </c:forEach>
    </c:if>
    
    Redis: Jtest = ${Jtest}
</body>
</html>
