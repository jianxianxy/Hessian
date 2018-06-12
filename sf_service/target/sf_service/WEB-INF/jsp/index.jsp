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
    Redis: Jtest = ${Jtest}
    <hr/>
    <c:if test="${!empty brandList}">
        <c:forEach var="brand" items="${brandList}">
            brandId：${brand.brandId}<br>
            brandLogo：${brand.brandLogo}<br>
            brandName：${brand.brandName}<br>
            brandSort：${brand.brandSort}<br>
            englistName：${brand.englistName}<br>
        </c:forEach>
    </c:if>
</body>
</html>
