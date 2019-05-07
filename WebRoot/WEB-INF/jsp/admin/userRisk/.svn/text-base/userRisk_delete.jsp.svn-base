<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>删除用户</title>
</head>
<body>
<div style="text-align: center">
    您确定要删除 <label><font style="color: red">
    <td><c:choose>
        <c:when test="${userRisk.type ==4}">风险名单</c:when>
        <c:when test="${userRisk.type ==3}">应急改密名单</c:when>
        <c:when test="${userRisk.type ==2}">黑名单</c:when>
        <c:when test="${userRisk.type ==1}">白名单</c:when>
    </c:choose>
    </td>
</font></label> 中的
    <lable>
        <font style="color: red">${userRisk.username}</font></lable>
    用户吗? <input type="hidden" value="${userRisk.id}" id="del-rname-id">
    <input type="hidden" value="${userRisk.baseid}" id="del-rname-baseid">
</div>
</body>
</html>