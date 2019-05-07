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

    <title>TenderItem</title>
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
            selecttenderItemByCondition(pageNum, pageSize);
        }
        $(function () {

            $(".remark-p").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
            $("#reset").click(function () {
                $("#tno").val("");
                $("#tname").val("");
            });
        });

        function selecttenderItemByCondition(pageNum, pageSize) {
            var atype = $("#atype1").val();
            if (atype == "请选择") {
                $("#atype1").val("");
            }
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selecttenderItemByCondition").submit();
        }

        function todetailUi(id) {
            var action = "${pageContext.request.contextPath}/admin/tenderItem/selectTenderItemByPrimaryKey.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#modal-body").html(data);
            };
            $.post(action, params, callback);
        }

        function toUpdateUi(id) {
            url = "admin/tenderItem/toUpdateUi.action?id=" + id;
            var explorer = window.navigator.userAgent;
            //ie
            if (explorer.indexOf("MSIE") >= 0) {
                url = "${pageContext.request.contextPath }/admin/tenderItem/toUpdateUi.action?id=" + id;
            }
            window.location.href = url;
        }


        //删除标的
        function deleteById(id) {
            var flag = window.confirm("确定要 删除么!");
            if (flag) {
                window.location.href = "${pageContext.request.contextPath}/admin/tenderItem/deleteTenderItem.action?id=" + id;
            }
        }

        //生成还款计划
        function createplan(id) {
            $.post("${pageContext.request.contextPath}/admin/tenderItem/ManuallyGeneratedRepayMent.action", {opid: id},
                function (data) {
                    var otd = eval('(' + data + ')');
                    if (otd.result == "opid_null") {
                        alert("提示:输入的参数为空");
                    } else if (otd.result == "pullofftime_error") {
                        alert("提示:下架时间没有超过30分钟,不能生成还款计划");
                    } else if (otd.result == "tstatus_error") {
                        alert("提示:标的状态有误,无法生成");
                    } else if (otd.result == "audit_error") {
                        alert("提示:还有待审核的标,无法生成");
                    } else if (otd.result == "success") {
                        alert("提示:生成还款计划成功");
                    } else {
                        alert("提示:生成还款计划失败");
                    }
                    window.location.href = "${pageContext.request.contextPath}/admin/tenderItem/selectTenderItemByCondition.action";
                });
        }

        //上架
        function shelves(id) {
            $.post("${pageContext.request.contextPath}/admin/tenderItem/shelves.action", {id: id},
                function (data) {
                    var otd = eval('(' + data + ')');
                    if (otd.result == "tstatus_1") {
                        alert("提示:标还没有录入到徽商，不能上架");
                    } else if (otd.result == "tstatus_4") {
                        alert("提示:已流标的标不能再上架");
                    } else if (otd.result == "shelves_success") {
                        alert("提示:上架成功");
                    } else {
                        alert("提示：上架失败");
                    }
                    window.location.href = "${pageContext.request.contextPath}/admin/tenderItem/selectTenderItemByCondition.action";
                });
        }

        //下架
        function theshelves(id) {
            $.post("${pageContext.request.contextPath}/admin/tenderItem/theshelves.action", {id: id},
                function (data) {
                    if (data == "fail") {
                        alert("提示：下架失败");
                    }
                    if (data == "succ") {
                        alert("提示：下架成功");
                        window.location.href = "${pageContext.request.contextPath}/admin/tenderItem/selectTenderItemByCondition.action";
                    }
                }, 'json');
        }

        //流标
        function flowstandard(id) {
            $.post("${pageContext.request.contextPath}/admin/tenderItem/flowstandard.action", {id: id}, function (data) {
                var otd = eval('(' + data + ')');
                if (otd.result == "id_null") {
                    alert("提示:输入的参数有误");
                } else if (otd.result == "onanddown_on") {
                    alert("要进行流标操作，请先下架");
                } else if (otd.result == "fail") {
                    alert("提示:失败,已有投标资金被放款成功或被冻结中，不能流标");
                } else if (otd.result == "pullofftime_error") {
                    alert("下架时间没有超过30分钟，不能流标");
                } else if (otd.result == "flows_success") {
                    alert("提示:流标成功");
                } else {
                    alert("提示:流标失败，请重新设置");
                }
                window.location.href = "${pageContext.request.contextPath}/admin/tenderItem/selectTenderItemByCondition.action";
            });
        }

        //放款UI
        function loan(id) {
            window.location.href = "${pageContext.request.contextPath}/admin/tender/queryBidRecordForAudit.action?tenderid=" + id;
        }
    </script>

</head>

<body>

<div class="container" style="width:90%">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>标设置列表</h3>
            <form id="selecttenderItemByCondition" method="post"
                  action="${pageContext.request.contextPath}/admin/tenderItem/fangKuan_List.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>标号</label><input type="text" name="tno" id="tno" value="${tenderItem.tno}">
                <label>标的名称</label><input type="text" name="tname" id="tname" value="${tenderItem.tname}">
                <div style="margin: 10px;">
                    <button id="query_btn" class="btn btn-default" onclick="selecttenderItemByCondition(1,20)">查询
                    </button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
            </form>
            <table class="table  table-hover" id="personList_table">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>标号</td>
                    <td>标名</td>
                    <td>借款人</td>
                    <td>标的金额</td>
                    <td>已完成投标金额</td>
                    <td>标的类型</td>
                    <td>借款周期</td>
                    <td>投资收益</td>
                    <td>标状态</td>
                    <td>操作</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="tenderItem" varStatus="tendsta">
                    <tr class="text-center">
                        <td>${tendsta.count}</td>
                        <td>${tenderItem.tno }</td>
                        <td>${tenderItem.tname}</td>
                        <td>${tenderItem.repayman} - ${tenderItem.loginname}</td>
                        <td>${tenderItem.tamountstr }</td>
                        <td>${tenderItem.finishtamount }</td>
                        <!-- 标的类型 -->
                        <td>
                            <c:forEach items="${objectQuotes}" var="item">
                                <c:if test="${tenderItem.tpro eq item.serialno}">
                                    ${item.objectname}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${tenderItem.loantime }${tenderItem.dayormonth }</td>
                        <td>${tenderItem.interestrate}</td>

                        <td style="text-align: center;">
                            <c:forEach items="${tenderItem_tstatus }" var="tstatus">
                                <c:if test="${tenderItem.tstatus==tstatus.key }">
                                    <c:choose>
                                        <c:when test="${tstatus.key==4||tstatus.key==9||tstatus.key==10||tstatus.key==11}">
                                            <font style="color: red;">${tstatus.value}</font>
                                        </c:when>
                                        <c:otherwise>
                                            <font style="color: blue;">${tstatus.value}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </td>

                        <td id="operate">
                            <c:if test="${tenderItem.tstatus == 1||tenderItem.tstatus == 9}"><%--待录入--%>
                                <button class="btn btn-default" onclick="toUpdateUi('${tenderItem.id}')">修改</button>
                            </c:if>

                            <c:if test="${(tenderItem.tstatus == 3||tenderItem.tstatus==5)&&tenderItem.isfroze==0}"><!-- （投标中或待放款） 且 没有任意一笔投标是的资金是被冻结中的-->
                            <button class="btn btn-default" onclick="flowstandard('${tenderItem.id}')">手动流标</button>
                            </c:if>

                            <c:if test="${tenderItem.tstatus == 5}"><!-- 待放款 -->
                            <button class="btn btn-default" onclick="loan('${tenderItem.id}')">放款</button>
                            </c:if>

                            <c:if test="${tenderItem.tstatus == 6}"><!-- 待生成还款计划 -->
                            <button class="btn btn-default" onclick="createplan('${tenderItem.id}')">生成还款计划</button>
                            </c:if>

                        </td>
                        <td>
                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#Modal" data-backdrop="static"
                                    onclick="todetailUi('${tenderItem.id}')">详细信息
                            </button>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 模态框 -->
            <div class="modal fade" id="Modal" tabindex="-1" role="dialog"
                 aria-labelledby="delModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="delModalLabel">
                                <span class="glyphicon glyphicon-info-sign"></span>提示：
                            </h4>
                        </div>
                        <div id="modal-body" class="modal-body"></div>
                        <input type="hidden" id="crud"/>
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
