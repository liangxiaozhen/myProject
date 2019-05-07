<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <title>GfundsInt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- 日历 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">
        function queryAllPerson(pageNum, pageSize) {
            selectgfundsIntByCondition(pageNum, pageSize);
        }
        $(function () {
            $(".ugrade").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
            $("#reset").click(function () {
                $("#gfundsintno").val("");
                document.getElementById("clearmethod").options[0].selected = true;
            })
        });

        function selectgfundsIntByCondition(pageNum, pageSize) {
            var atype = $("#atype1").val();
            if (atype == "请选择") {
                $("#atype1").val("");
            }

            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectgfundsIntByCondition").submit();
        }

        //查看详情
        function todetailUi(tid) {
            var action = "${pageContext.request.contextPath}/admin/gfundsInt/selectGfundsIntByPrimaryKey.action";
            var param = {
                "tid": tid
            }
            var callback = function (data) {
                $("#myModal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body").html(data);
            }
            $.post(action, param, callback);
        }

        function toUpdateUi(tid) {
            url = "admin/gfundsInt/toUpdateUi.action?tid=" + tid;
            window.location.href = url;
        }


        function insert_GfundsInt_Ui() {
            var url = "admin/gfundsInt/insert_GfundsInt_Ui.action";
            window.location.href = url;
        }

        function deleteById(id, tid) {
            var url = "${pageContext.request.contextPath}/admin/gfundsInt/deleteGfundsInt.action";
            var data = {
                "id": id,
                "tid": tid
            }
            var deleteCallBack = function (data) {
                if ($.trim(data) == "删除成功") {
                    alert(data);
                    window.location.href = "${pageContext.request.contextPath }/admin/gfundsInt/selectGfundsIntByCondition.action";
                } else {
                    alert(data);
                }
            }
            var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");
            if (flag) {
                jQuery.post(url, data, deleteCallBack);
            }
        }
        function addGfundsIntUi() {
             url = "/admin/gfundsInt/insert_GfundsInt_Ui.action";
            window.location.href = url;
        }
    </script>

</head>

<body style="font-family:'微软雅黑'; font-size: 13px;">

<div class="container" style="width:90%">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>标的站岗利息设置列表</h3>
            <form id="selectgfundsIntByCondition" method="post"
                  action="admin/gfundsInt/selectGfundsIntByCondition.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>站岗利息编号</label><input type="text" name="gfundsintno" id="gfundsintno"
                                            value="${gfundsInt.gfundsintno }">
                <label>清算方式</label>
                <select name="clearmethod" id="clearmethod">
                    <option value="">请选择</option>
                    <option value="1"
                            <c:if test="${gfundsInt.clearmethod==1 }">selected="selected"</c:if> >结标清算
                    </option>
                    <option value="2"
                            <c:if test="${gfundsInt.clearmethod==2 }">selected="selected"</c:if> >最后一期还款清算
                    </option>
                </select>
                <div>
                    <button id="query_btn" class="btn btn-default" onclick="selectgfundsIntByCondition(1,9)">查询</button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
            </form>
            <button onclick="addGfundsIntUi()">增加</button>
            <table class="table table-hover " id="personList_table">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>站岗利息编号</td>
                    <td>清算方式</td>
                    <td>会员等级</td>
                    <td>分段最低投资金额</td>
                    <td>分段最高投资金额</td>
                    <td>定额补偿金</td>
                    <td>日奖励费率</td>
                    <td>最高补偿金额</td>
                    <td>是否为模板</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="gfundsInt" varStatus="status">
                    <tr class="text-center">
                        <td>${status.count}</td>
                        <td>${gfundsInt.gfundsintno }</td>
                        <td>
                            <c:choose>
                                <c:when test="${gfundsInt.clearmethod==1 }">结标清算</c:when>
                                <c:when test="${gfundsInt.clearmethod==2 }">最后一期还款清算</c:when>
                            </c:choose>
                        </td>
                        <td class="ugrade" title="${gfundsInt.ugrade }">${gfundsInt.ugrade }</td>
                        <td>
                            <c:if test="${!empty gfundsInt.minmoney}">
                                ${gfundsInt.minmoney}元
                            </c:if>
                            <c:if test="${empty gfundsInt.minmoney}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty gfundsInt.maxmoney}">
                                ${gfundsInt.maxmoney}元
                            </c:if>
                            <c:if test="${empty gfundsInt.maxmoney}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty gfundsInt.quota}">
                                ${gfundsInt.quota}元
                            </c:if>
                            <c:if test="${empty gfundsInt.quota}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty gfundsInt.dayawardrate}">
                                ${gfundsInt.dayawardrate }%
                            </c:if>
                            <c:if test="${empty gfundsInt.dayawardrate}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty gfundsInt.maxcompensate}">
                                ${gfundsInt.maxcompensate}元
                            </c:if>
                            <c:if test="${empty gfundsInt.maxcompensate}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${gfundsInt.istemplet==1 }">是</c:when>
                                <c:when test="${gfundsInt.istemplet==0 }">否</c:when>
                            </c:choose>
                        </td>
                        <td>
                            <button class="btn btn-default" onclick="todetailUi('${gfundsInt.tid}')">查看详情</button>
                            <button class="btn btn-default" onclick="toUpdateUi('${gfundsInt.tid}')">修改</button>
                            <button class="btn btn-default" id="modify" onclick="deleteById('${gfundsInt.id }','${gfundsInt.tid}');">删除</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 模态框 -->

            <div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
                 role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel"></h4>
                        </div>
                        <div class="modal-body" id="modal-body">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="page_div">
                <%@ include file="../../common/pagehelper.jsp" %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
