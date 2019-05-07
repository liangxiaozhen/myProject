<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>loanItemQuote_List</title>
    <style type="text/css">
        hr {
            margin: 10px;
        }
    </style>
    <script>

    </script>
</head>
<body>
<form id="insert-form">
    <div class="row">
        <label class="col-sm-3 control-label laber_from">资料属性</label>
        <div class="col-sm-3">
            <select name="infoattribute" id="InfoAttribute" class="form-control">
                <option value="">请选择</option>
                <option value="1">公共</option>
                <option value="2">补充</option>
            </select>
        </div>
    </div>
    <hr/>
    <div class="row">
        <label class="col-sm-3 control-label laber_from">引用项目</label>
        <div class="col-sm-4">
            <select name="quotename" id="quoteNameinput" class="form-control" onchange="aa(this);">
                <option value="">请选择</option>
                <c:if test="${!empty infoNeeds}">
                    <c:forEach items="${infoNeeds}" var="need">
                        <option value="${need.id},n,${need.infoname}">${need.infoname}</option>
                    </c:forEach>
                </c:if>
                <c:if test="${!empty infoPresets}">
                    <c:forEach items="${infoPresets}" var="pres">
                        <option value="${pres.id},p,${pres.infoname}">${pres.infoname}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
    </div>
    <hr/>
    <script type="text/javascript">
        function aa(e) {
            var val = e.value.split(",")[1];
            if (val == "n") {
                $("#type").val(1);
               /* $("#type option[value='2']").removeAttr("selected");*/
               /* $("#type option[value='1']").attr("selected", "selected");*/
            } else if (val == "p") {
                $("#type").val(2);
               /* $("#type option[value='1']").removeAttr("selected");
                $("#type option[value='2']").attr("selected", "selected");*/
            }
        }
    </script>
    <div class="row">
        <label class="col-sm-3 control-label laber_from">项目类型</label>
        <div class="col-sm-3">
            <select name="quoteproperty" id="type" class="form-control" disabled>
                <option value="">请选择</option>
                <option value="1">自填类</option>
                <option value="2">选择类</option>
            </select>
        </div>
    </div>
    <hr/>

    <div class="row">
        <label class="col-sm-3 control-label laber_from">是否必填</label>
        <div class="col-sm-3">
            <select name="isneed" id="IsNeed" class="form-control">
                <option value="">请选择</option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </div>
    </div>
    <hr/>
    <div class="row">
        <label class="col-sm-3 control-label laber_from">是否使用</label>
        <div class="col-sm-3">
            <select name="isuse" id="isUse" class="form-control">
                <option value="">请选择</option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </div>
    </div>
    <hr/>
    <input type="hidden" name="iscite" value="0"/>
    <div class="row">
        <label class="col-sm-3 control-label laber_from">备注</label>
        <div class="col-sm-3 ">
            <textarea rows="3" name="remark" class="form-control"></textarea>
        </div>
    </div>
</form>
</body>
</html>