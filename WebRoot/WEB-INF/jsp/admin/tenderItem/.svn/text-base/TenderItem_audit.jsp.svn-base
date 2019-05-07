<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TenderItem_audit.jsp</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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

        function selecttenderItemByCondition(pageNum, pageSize) {
            var atype = $("#atype1").val();
            if (atype == "请选择") {
                $("#atype1").val("");
            }

            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selecttenderItemByCondition").submit();
        }

        //审核通过(通过借款申请状态改为成功)
        function through(id, operate) {
            var flag = false;
            if (operate == 1) {
                flag = window.confirm("是否要录入到银行？");
            } else if (operate == 0) {
                flag = window.confirm("真的要放弃录入？");
            }
            if (flag) {
                $.ajax({
                    "type": "POST",
                    "url": "${pageContext.request.contextPath}/admin/tenderItem/through.action",
                    "data": {"id": id, "operate": operate},
                    "success": function (data) {
                        var obj = $.parseJSON(data);
                        if (obj.result == "success") {
                            alert("成功");
                            window.location.reload();
                        } else if (obj.result == "fail") {
                            alert("失败");
                            window.location.reload();
                        } else if (obj.result == "timeout") {
                            alert("只能在投标截止期之前录入");
                            window.location.reload();
                        } else if (obj.result == "fangqi") {
                            alert("放弃成功");
                            window.location.reload();
                        }
                    }
                });
            }

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
    </script>
</head>
<body>
<div class="container" style="width:100%">
    <div class="row clearfix">
        <div class="col-md-12 column">


            <h3>标审核列表页面</h3>
            <form id="selecttenderItemByCondition" method="post"
                  action="${pageContext.request.contextPath}/admin/tenderItem/selectTenderItemByaudit.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>标号</label><input type="text" name="tno" id="tno" value="${tno}">
                <label>标的名称</label><input type="text" name="tname" id="tname" value="${tname}">
                <div style="margin: 10px;">
                    <button id="query_btn" class="btn btn-default" onclick="selecttenderItemByCondition(1,9)">查询
                    </button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
            </form>
            <table class="table table-hover">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>标号</td>
                    <td>标的名称</td>
                    <td>借款人</td>
                    <td>建标时间</td>
                    <td>标的金额</td>
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
                        <td>${tenderItem.tname }</td>
                        <td>${tenderItem.repayman} - ${tenderItem.loginname}</td>
                        <td>
                            <fmt:formatDate value="${tenderItem.addtime}" pattern="yyyy-MM-dd HH:mm"/>
                        </td>
                        <td>${tenderItem.tamountstr }</td>

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
                            <c:if test="${tenderItem.tstatus==1||tenderItem.tstatus==9}"><!-- 审核中(发送即信录入) -->
                            <button class="btn btn-default" onclick="through('${tenderItem.id}','1')">录入银行</button>
                            <button class="btn btn-default" onclick="through('${tenderItem.id}','0')">放弃录入</button>
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