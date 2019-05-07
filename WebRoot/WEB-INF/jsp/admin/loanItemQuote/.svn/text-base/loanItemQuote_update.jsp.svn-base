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
            margin-top: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<form id="update-form" class="form-horizontal">
    <input type="hidden" name="id" value="${itemQuote.id}"/>
    <div class="form-group">
        <label class="col-sm-3 control-label laber_from">资料属性</label>
        <div class="col-sm-3">
            <select name="infoattribute" id="InfoAttribute" class="form-control">
                <c:if test="${itemQuote.infoattribute eq 1}">
                    <option value="1">公共</option>
                </c:if>
                <c:if test="${itemQuote.infoattribute eq 2}">
                    <option value="2">补充</option>
                </c:if>
            </select>
        </div>
    </div>
    <hr/>
    <div class="form-group">
        <label class="col-sm-3 control-label laber_from">引用项目</label>
        <div class="col-sm-3">
            <select name="quotenamesss" id="quoteName" class="form-control">
                <option value="${itemQuote.quoteobjectid}">${itemQuote.quotename}</option>
            </select>
        </div>
    </div>
    <hr/>
    <div class="form-group">
        <label class="col-sm-3 control-label laber_from">引用项目属性</label>
        <div class="col-sm-3">
            <select name="quoteproperty" id="quoteProperty" class="form-control">
                <c:if test="${itemQuote.quoteproperty eq 1}">
                    <option value="1">自填</option>
                </c:if>
                <c:if test="${itemQuote.quoteproperty eq 2}">
                    <option value="2">预设置</option>
                </c:if>
            </select>
        </div>
    </div>
    <hr/>
    <div class="form-group">
        <label class="col-sm-3 control-label laber_from">序号</label>
        <div class="col-sm-3">
           <input class="form-control" name="seriesno" value="${itemQuote.seriesno}" onblur="checkNum(this)" onkeyup="clearNoNum(event,this)">
        </div>
    </div>
    <hr/>

    <div class="form-group">
        <label class="col-sm-3 control-label laber_from">是否必填</label>
        <div class="col-sm-3">
            <select name="isneed" id="IsNeed" class="form-control">
                <c:if test="${itemQuote.isneed eq 1}">
                    <option value="1" selected="selected">是</option>
                    <option value="0">否</option>
                </c:if>
                <c:if test="${itemQuote.isneed eq 0}">
                    <option value="0" selected="selected">否</option>
                    <option value="1">是</option>
                </c:if>
            </select>
        </div>
    </div>
    <hr/>
    <div class="form-group">
        <label class="col-sm-3 control-label laber_from">是否使用</label>
        <div class="col-sm-3">
            <select name="isuse" id="isUse" class="form-control">
                <c:if test="${itemQuote.isuse eq 1}">
                    <option value="1" selected="selected">是</option>
                    <option value="0">否</option>
                </c:if>
                <c:if test="${itemQuote.isuse eq 0}">
                    <option value="0" selected="selected">否</option>
                    <option value="1">是</option>
                </c:if>
            </select>
        </div>
    </div>
    <hr/>
    <div class="form-group">
        <label class="col-sm-3 control-label laber_from">备注</label>
        <div class="col-sm-3">
            <textarea rows="3" name="remark" class="form-control">${itemQuote.remark}</textarea>
        </div>
    </div>
</form>
</body>
</html>