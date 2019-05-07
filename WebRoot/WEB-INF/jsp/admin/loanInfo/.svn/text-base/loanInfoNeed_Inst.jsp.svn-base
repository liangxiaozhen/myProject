<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>loanInfoNeed_Inst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        .laber_from {
            color: #222;
            font-weight: normal;
            font-family: 微软雅黑;
        }

        .route_bg {
            background-color: #E7E7E7;
            border-radius: 4px;
            padding: 5px;
            margin-right: 5px;
            margin-left: 5px;
            margin-top: 5px;
        }

        .route_bg i {
            color: #ccc;
            font-weight: 400;
            font-size: 12px;
            padding-right: 5px;
            line-height: 25px;
        }

        .route_bg a {
            font-size: 12px;
            color: #666;
            text-decoration: none;
            line-height: 1.6;
            font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei", "\9ED1\4F53", Arial, sans-serif !important;
        }

        .route_bg a:hover {
            color: #888;
            text-decoration: none;
        }
    </style>
    <script type="text/javascript">
        function callback() {
            var url = "${pageContext.request.contextPath}/admin/loanInfo/selectAllNeed.action";
            window.location.href = url;
        }
    </script>
</head>
<body>
<div class="route_bg">
    <a href="#">借款资料管理</a><i class="glyphicon glyphicon-chevron-right"></i>
    <a href="#">借款资料自填类选项设置</a>
</div>
<%--增加页面--%>
<c:if test="${empty infoNeed}">
    <div class="container" style="margin-top: 25px;">
        <form action="${pageContext.request.contextPath}/admin/loanInfo/addloanInfoNeed.action" method="post"
              class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">资料名称</label>
                <div class="col-sm-3">
                    <input type="text" name="infoname" id="InfoName" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">资料类型</label>
                <div class="col-sm-2">
                    <select name="infotype" id="InfoType" class="form-control"<%-- onchange="selectPicOrText(this)--%>">
                    <option value="">请选择</option>
                    <option value="2">文本</option>
                    <option value="1">上传图片</option>
                    </select>
                </div>
            </div>

                <%--文字--%>
            <div class="form-group" id="charlength">
                <label class="col-sm-3 control-label laber_from">字符长度(或图片张数)限制</label>
                <div class="col-sm-3">
                    <input type="text" name="charlength" id="CharLength" class="form-control" maxlength="2"/>
                </div>
            </div>
                <%--    &lt;%&ndash;图片&ndash;%&gt;
                <div class="form-group" id="charlength2">
                    <label class="col-sm-3 control-label laber_from">张数限制</label>
                    <div class="col-sm-3">
                        <input type="text" name="charlength" id="CharLength" class="form-control" maxlength="2"/>
                    </div>
                </div>--%>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-3 control-label laber_from">引用对象</label>
                <div class="col-sm-6">
                    <c:if test="${!empty objectQuotes }">
                        <c:forEach items="${objectQuotes }" var="ugr" varStatus="status">
                            <label class="checkbox-inline" style="width:80px;">
                                <input type="checkbox" name="quoteObjects" value="${ugr.serialno}"/>${ugr.objectname}
                            </label>
                            <c:if test="${status.count%4==0 }">
                                <br/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">是否启用</label>
                <div class="col-sm-2">
                    <select name="isneed" id="isNeed" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
                <%--前端说明--%>
            <div class="form-group" id="charlength">
                <label class="col-sm-3 control-label laber_from">前端说明</label>
                <div class="col-sm-3">
                    <input type="text" name="infodescription" id="CharLength" class="form-control" maxlength="20"/>
                </div>
            </div>

            <input type="hidden" name="iscite" value="0"/>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">备注</label>
                <div class="col-sm-3 ">
                    <textarea rows="3" name="remark" class="form-control"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-1">
                    <button type="submit" class="btn btn-default" id="failButton">保存</button>
                </div>
                <div class="col-sm-1">
                    <button type="button" class="btn btn-default">返回列表</button>
                </div>
            </div>
        </form>
    </div>
</c:if>
<%--修改页面--%>
<c:if test="${!empty infoNeed}">
    <div class="container" style="margin-top: 25px;">
        <form action="${pageContext.request.contextPath}/admin/loanInfo/updateloanInfoNeedtwo.action" method="post"
              class="form-horizontal">
            <input type="hidden" name="id" value="${infoNeed.id}"/>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">资料名称</label>
                <div class="col-sm-3">
                    <input type="text" name="infoname" id="InfoName" class="form-control" value="${infoNeed.infoname}"
                           readonly="readonly"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">资料类型</label>
                <div class="col-sm-2">
                    <select name="infotype" id="InfoType" class="form-control">
                        <c:if test="${!empty infoNeed.infotype}">
                            <c:if test="${infoNeed.infotype eq 1}">
                                <option value="1">上传图片</option>
                            </c:if>
                            <c:if test="${infoNeed.infotype eq 2}">
                                <option value="2">文本</option>
                            </c:if>
                        </c:if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">字符长度限制</label>
                <div class="col-sm-3">
                    <input type="text" name="charlength" id="CharLength" class="form-control"
                           value="${infoNeed.charlength}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-3 control-label laber_from">引用对象</label>
                <input type="hidden" id="quoteObjectlist" value="${allowcugrades}"/>
                <div class="col-sm-6">
                    <c:if test="${!empty objectQuotes }">
                        <c:forEach items="${objectQuotes }" var="ugr" varStatus="status">
                            <label class="checkbox-inline" style="width:80px;">
                                <input type="checkbox" name="quoteObjects" value="${ugr.serialno}"/>${ugr.objectname}
                            </label>
                            <c:if test="${status.count%4==0 }">
                                <br/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">前端说明</label>
                <div class="col-sm-3">
                    <input type="text" name="infodescription" id="" class="form-control"
                           value="${infoNeed.infodescription}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">是否启用</label>
                <div class="col-sm-2">
                    <select name="isneed" id="isNeed" class="form-control">
                        <c:if test="${!empty infoNeed.isneed}">
                            <c:if test="${infoNeed.isneed eq 1}">
                                <option value="1">是</option>
                            </c:if>
                            <c:if test="${infoNeed.isneed eq 0}">
                                <option value="0">否</option>
                            </c:if>
                        </c:if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">备注</label>
                <div class="col-sm-3 ">
                    <textarea rows="3" name="remark" class="form-control">${infoNeed.remark}</textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-1">
                    <button type="submit" class="btn btn-default" id="failButton">修改</button>
                </div>
                <div class="col-sm-1">
                    <button type="button" class="btn btn-default" onclick="callback()">返回列表</button>
                </div>
            </div>
        </form>
    </div>
    <%--回显--%>
    <script type="text/javascript">

        var ugrades1 = eval("(" + $("#quoteObjectlist").val() + ")");
        if (ugrades1.length > 0) {
            $.each(ugrades1, function (index, value) {
                $("input[type=checkbox]").each(function () {
                    if ($(this).val() == value) {
                        $(this).attr("checked", true);
                    }
                });
            });
        }
    </script>
</c:if>
<%--<script>
    $(function () {
        var div_charlength1 = $('#charlength1');
        var div_charlength2 = $('#charlength2');
        div_charlength1.hide();
        div_charlength2.hide();
    });
    /*选择图片或文本的change事件*/
    function selectPicOrText(e) {
        var val = $(e).val();
        if (val == 1) {
            var div_charlength1 = $('#charlength1');
            $('#charlength1 input').val('');
            div_charlength1.hide();
            var div_charlength2 = $('#charlength2');
            div_charlength2.show();
        } else if (val == 2) {
            var div_charlength2 = $('#charlength2');
            $('#charlength2 input').val('');
            div_charlength2.hide();
            var div_charlength1 = $('#charlength1');
            div_charlength1.show();
        } else {
            var div_charlength1 = $('#charlength1');
            var div_charlength2 = $('#charlength2');
            $('#charlength1 input').val('');
            $('#charlength2 input').val('');
            div_charlength1.hide();
            div_charlength2.hide();
        }
    }
</script>--%>
</body>
</html>