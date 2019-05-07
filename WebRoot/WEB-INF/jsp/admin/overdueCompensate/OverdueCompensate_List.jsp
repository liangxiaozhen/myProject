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

    <title>OverdueCompensate</title>
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
            selectoverdueCompensateByCondition(pageNum, pageSize);
        }
        $(function () {
            $(".ugrade").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
            $(".remark-td").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
            $("#reset").click(function () {
                $("#ano").val("");
                $("#aname").val("");
                document.getElementById("atype1").options[0].selected = true;
            })
        });

        function selectoverdueCompensateByCondition(pageNum, pageSize) {
            var atype = $("#atype1").val();
            if (atype == "请选择") {
                $("#atype1").val("");
            }

            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectoverdueCompensateByCondition").submit();
        }

        //详细信息
        function todetailUi(tid) {
            var action = "${pageContext.request.contextPath}/admin/overdueCompensate/selectOverdueCompensateByPrimaryKey.action";
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
            url = "admin/overdueCompensate/toUpdateUi.action?tid=" + tid;
            window.location.href = url;
        }


        function insert_OverdueCompensate_Ui() {
            var url = "admin/overdueCompensate/insert_OverdueCompensate_Ui.action";
            window.location.href = url;
        }

        function deleteById(id, tid) {
            var url = "${pageContext.request.contextPath}/admin/overdueCompensate/deleteOverdueCompensate.action";
            var data = {
                "id": id,
                "tid": tid
            }
            var deleteCallBack = function (data) {
                if ($.trim(data) == "删除成功") {
                    alert(data);
                    window.location.href = "${pageContext.request.contextPath }/admin/overdueCompensate/selectOverdueCompensateByCondition.action";
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

<body>

<div class="container" style="width: 90%;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>标的逾期代偿设置列表</h3>
            <form id="selectoverdueCompensateByCondition" method="post"
                  action="admin/overdueCompensate/selectOverdueCompensateByCondition.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>逾期代偿编号</label><input type="text" name="overduecno" id="overduecno"
                                            value="${overdueCompensate.overduecno }">
                <label>逾期代偿人</label><input type="text" name="cmanno" id="cmanno" value="${overdueCompensate.cmanno }">
                <div>
                    <button id="query_btn" class="btn btn-default" onclick="selectoverdueCompensateByCondition(1,9)">
                        查询
                    </button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
                <div class="btn btn-default" onclick="insert_OverdueCompensate_Ui()" style="margin-left: 80%">新增</div>
                <a href="${pageContext.request.contextPath}/admin/overdueCompensate/insert_overdueRecovery_Ui.action">平台追偿</a>
                <a href="${pageContext.request.contextPath}/admin/overdueCompensate/insert_overdueFeeRate_UI.action">逾期迟纳金</a>
            </form>
            <table class="table table-hover" id="personList_table">
                <thead>
                     <tr class="text-center" style="background: #ccc;">
                        <td>序号</td>
                        <td>逾期代偿编号</td>
                        <td>逾期代偿人</td>
                        <td>会员等级</td>
                        <td>本金垫付比例</td>
                        <td>本金垫付最高金额</td>
                        <td>利息垫付比例</td>
                        <td>利息垫付最高金额</td>
                        <td>迟纳金垫付比例</td>
                        <td>迟纳金垫付最高金额</td>
                        <td>是否为模板</td>
                        <td>备注</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pagehelper.list }" var="overdueCompensate" varStatus="oversta">
                        <tr class="text-center">
                            <td>${oversta.count}</td>
                            <td>${overdueCompensate.overduecno }</td>
                            <td>${overdueCompensate.cmanno }</td>
                            <td class="ugrade" title="${overdueCompensate.ugrade}">${overdueCompensate.ugrade}</td>
                            <td>${overdueCompensate.pfprincipalrate}%</td>
                            <td>${overdueCompensate.maxpfprincipal}元</td>
                            <td>${overdueCompensate.pfintrate}%</td>
                            <td>${overdueCompensate.maxpfint}元</td>
                            <td>${overdueCompensate.latefeerate}%</td>
                            <td>${overdueCompensate.maxlatefee}元</td>
                            <td>
                                <c:choose>
                                    <c:when test="${overdueCompensate.istemplet==1 }">是</c:when>
                                    <c:when test="${overdueCompensate.istemplet==0 }">否</c:when>
                                </c:choose>
                            </td>
                            <td class="remark-td" title="${overdueCompensate.remark}">${overdueCompensate.remark}</td>
                            <td>
                                <button type="button" class="btn btn-default" onclick="todetailUi('${overdueCompensate.tid}')">详细信息</button>
                                <button type="button" class="btn btn-default" onclick="toUpdateUi('${overdueCompensate.tid}')">修改</button>
                                <button type="button" class="btn btn-default" id="modify"
                                        onclick="deleteById('${overdueCompensate.id }','${overdueCompensate.tid}');">删除
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
