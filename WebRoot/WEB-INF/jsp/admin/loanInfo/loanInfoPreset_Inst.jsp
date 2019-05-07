<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
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
        $(function () {
            $("#MultiNoSel").change(function () {
                var muno = $(this).val();
                if (muno != "") {
                    var action = "${pageContext.request.contextPath}/admin/loanInfo/tochange.action";
                    var param = {
                        "muno": muno
                    }
                    var callback = function (data) {
                        $("#labchange").text(data);
                    }
                    $.post(action, param, callback);
                }
            });
        });
        function callback() {
            var url = "${pageContext.request.contextPath}/admin/loanInfo/selectAllPreset.action";
            window.location.href = url;
        }
    </script>
</head>
<body>
<div class="route_bg">
    <a href="#">借款资料管理</a><i class="glyphicon glyphicon-chevron-right"></i>
    <a href="#">借款资料选择类项目设置</a>
</div>
<c:if test="${empty infoPreset}">
    <div class="container" style="margin-top: 25px;">
        <form action="${pageContext.request.contextPath}/admin/loanInfo/addloanInfoPreset.action" method="post"
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
                    <select name="infotype" id="InfoType" class="form-control">
                        <option value="1">选择类</option>
                    </select>
                </div>
            </div>
            <input type="hidden" name="iscite" value="0"/>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">多选内容名称的编号</label>
                <div class="col-sm-2">
                    <select name="multino" id="MultiNoSel" class="form-control">
                        <option value="">请选择</option>
                        <c:if test="${!empty MultiNo}">
                            <c:forEach items="${MultiNo}" var="mult">
                                <option value="${mult.multino}">${mult.multino}</option>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty MultiNo}">
                            <option value="">无数据,请添加</option>
                        </c:if>
                    </select>
                </div>
                <div class="col-sm-4">
                    <label id="labchange"></label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">单选或多选</label>
                <div class="col-sm-2">
                    <select name="oneormulti" id="oneOrMulti" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">单选</option>
                        <option value="2">多选</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-3 control-label laber_from">引用对象</label>
                <div class="col-sm-6">
                    <c:if test="${!empty objectQuotes }">
                        <c:forEach items="${objectQuotes }" var="ugr" varStatus="status">
                            <label class="checkbox-inline" style="width:80px;">
                                <input type="checkbox" name="quoteobjects" value="${ugr.serialno}"/>${ugr.objectname}
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
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">前端说明</label>
                <div class="col-sm-3">
                    <input type="text" name="infodescription" id="" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">备注</label>
                <div class="col-sm-3">
                    <textarea rows="3" name="remark" class="form-control"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-1">
                    <button type="submit" class="btn btn-default" id="failButton">保存</button>
                </div>
                <div class="col-sm-1">
                    <button type="button" class="btn btn-default" onclick="callback()">返回列表</button>
                </div>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${!empty infoPreset}">
    <div class="container" style="margin-top: 25px;">
        <form action="${pageContext.request.contextPath}/admin/loanInfo/updateloanInfoPreset.action" method="post"
              class="form-horizontal">
            <input type="hidden" value="${infoPreset.id}" name="id"/>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">资料名称</label>
                <div class="col-sm-3">
                    <input type="text" name="infoname" id="InfoName" class="form-control" value="${infoPreset.infoname}"
                           readonly="readonly"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">资料类型</label>
                <div class="col-sm-2">
                    <select name="infotype" id="InfoType" class="form-control">
                        <option value="1">选择</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">多选内容名称的编号</label>
                <div class="col-sm-2">
                    <select name="multinossss" id="MultiNoSel" class="form-control">
                        <c:if test="${!empty contentSets}">
                            <c:forEach items="${contentSets}" var="set">
                                <c:if test="${set.multino eq infoPreset.multino}">
                                    <option>${set.optionname}</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
                <div class="col-sm-4">
                    <label id="labchange"></label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">单选或多选</label>
                <div class="col-sm-2">
                    <select name="oneormulti" id="oneOrMulti" class="form-control">
                        <c:if test="${infoPreset.oneormulti eq 1}">
                            <option value="1" selected="selected">单选</option>
                            <option value="2">多选</option>
                        </c:if>
                        <c:if test="${infoPreset.oneormulti eq 2}">
                            <option value="1">单选</option>
                            <option value="2" selected="selected">多选</option>
                        </c:if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-3 control-label laber_from">引用对象</label>
                <input type="hidden" id="quoteObjectlist" value="${integers}"/>
                <div class="col-sm-6">
                    <c:if test="${!empty objectQuotes }">
                        <c:forEach items="${objectQuotes }" var="ugr" varStatus="status">
                            <label class="checkbox-inline" style="width:80px;">
                                <input type="checkbox" name="quoteobjects" value="${ugr.serialno}"/>${ugr.objectname}
                            </label>
                            <c:if test="${status.count%4==0 }">
                                <br/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
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
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">是否启用</label>
                <div class="col-sm-2">
                    <select name="isneed" id="isNeed" class="form-control">
                        <c:if test="${infoPreset.isneed eq 1}">
                            <option value="1">是</option>
                        </c:if>
                        <c:if test="${infoPreset.isneed eq 0}">
                            <option value="0">否</option>
                        </c:if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">前端说明</label>
                <div class="col-sm-3">
                    <input type="text" name="infodescription" id="" class="form-control" value="${infoPreset.infodescription}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">备注</label>
                <div class="col-sm-3">
                    <textarea rows="3" name="remark" class="form-control">${infoPreset.remark}</textarea>
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
</c:if>
</body>
</html>