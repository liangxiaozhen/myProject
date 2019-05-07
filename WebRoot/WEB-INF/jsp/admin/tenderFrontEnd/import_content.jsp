<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>莫邪科技</title>


</head>
<body>
<form id="import-form">
    <div class="row" style="line-height: 0px;">
        <div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
            <font color="red"><b>引用公共资料</b></font>
        </div>
        <hr>
    </div>
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                选择引用项目：
                <select name="liqno" id="liqno">
                    <option value="">请选择</option>
                    <c:forEach items="${userCommonMaterials}" var="item">
                        <option value="${item.liqno}">${item.materialname}</option>
                    </c:forEach>
                </select><span style="color:red;"><label id="insert-liqno-lb"></label></span>
            </div>
        </div>
        <hr>
        <input type="hidden" name="id" value="${tenderFrontEnd.id}"/>
</form>

</body>
</html>