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

    <title>标的担保费率列表</title>
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
            selectguaranteeFeeByCondition(pageNum, pageSize);
        }
        $(function () {
            $("#reset").click(function () {
                $("#tno").val("");
            });
            $(".ugrade").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
        });


        function selectguaranteeFeeByCondition(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectguaranteeFeeByCondition").submit();
        }

        function todetailUi(tid) {
            var action = "${pageContext.request.contextPath}/admin/guaranteeFee/selectGuaranteeFeeByPrimaryKey.action";
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


        //跟新
        function toUpdateUi(tid) {
            url = "${pageContext.request.contextPath}/admin/guaranteeFee/toUpdateUi.action?tid=" + tid;
            window.location.href = url;
        }


        //删除
        function deleteById(id, tid) {
            var url = "${pageContext.request.contextPath}/admin/guaranteeFee/deleteGuaranteeFee.action";
            var data = {
                "id": id,
                "tid": tid
            }
            var deleteCallBack = function (data) {
                if ($.trim(data) == "删除成功") {
                    alert(data);
                    window.location.href = "${pageContext.request.contextPath }/admin/guaranteeFee/selectGuaranteeFeeByCondition.action";
                } else {
                    alert(data);
                }
            }
            var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");
            if (flag) {
                jQuery.post(url, data, deleteCallBack);
            }
        }
    </script>

</head>

<body style="font-family:'微软雅黑';font-size: 13px;">

<div class="container" style="width: 100%;">
    <div class="row clearfix">
        <div class="col-md-12 column"
             style="border-style:none; border-width:5px; border-color:Black; background-color:none;">
            <h3>标的担保费率设置列表</h3>
            <form id="selectguaranteeFeeByCondition" method="post"
                  action="admin/guaranteeFee/selectGuaranteeFeeByCondition.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>标编号</label><input type="text" name="tno" id="tno"
                                         value="${guaranteeFee.tno}">
                <div>
                    <button id="query_btn" class="btn btn-default" onclick="selectguaranteeFeeByCondition(1,9)">查询
                    </button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
            </form>
            <table class="table table-hover" id="personList_table">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>担保费编号</td>
                    <td>标编号</td>
                    <td>担保服务费收款人</td>
                    <td>收费类型</td>
                    <td>等级</td>
                    <td>担保费费率</td>
                    <td>担保费最低-最高收费</td>
                    <td>是否为模板</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="guaranteeFee" varStatus="guarsta">
                    <tr class="text-center">
                        <td>${guarsta.count}</td>
                        <td>${guaranteeFee.guaranteefeeno }</td>
                        <td>${guaranteeFee.tno}</td>
                        <td>${guaranteeFee.gfrecman }</td>
                        <td>
                            <c:choose>
                                <c:when test="${guaranteeFee.chargetype eq 1 }">
                                    结标收取
                                </c:when>
                                <c:when test="${guaranteeFee.chargetype eq 2 }">
                                    投标时收取
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="ugrade" title="${guaranteeFee.ugrade }">${guaranteeFee.ugrade }</td>
                        <td>
                            <c:if test="${!empty guaranteeFee.gfrate}">
                                ${guaranteeFee.gfrate}%
                            </c:if>
                            <c:if test="${empty guaranteeFee.gfrate}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                                ${guaranteeFee.maxgfamount }
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${guaranteeFee.istemplet==0 }">
                                    否
                                </c:when>
                                <c:when test="${guaranteeFee.istemplet==1 }">
                                    是
                                </c:when>
                            </c:choose>
                        </td>
                        <td style="width:200px;">
                            <button class="btn btn-default" onclick="todetailUi('${guaranteeFee.tid}')">详情</button>
                            <button class="btn btn-default" onclick="toUpdateUi('${guaranteeFee.tid}')">修改</button>
                            <button class="btn btn-default" id="modify"
                                    onclick="deleteById('${guaranteeFee.id }','${guaranteeFee.tid}');">删除
                            </button>
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
