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

    <title>标的风险保证金列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/jquery-ui.min.js"></script>
    <!-- 日历 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>

    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">

        $(function () {
            $(".remark-p").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
            $("#reset").click(function () {
                $("#riskgmno").val("");
                $("#ugrade").val("");
                document.getElementById("chargetype").options[0].selected = true;
            })
        });

        //可拖曳模态框
        $(document).ready(function () {
            modalDialog
            $("#modalDialog").draggable();//为模态对话框添加拖拽
            $("#myModal").css("overflow", "hidden");//禁止模态对话框的半透明背景滚动
        });

        function queryAllPerson(pageNum, pageSize) {
            selectriskGuarantyMoneyByCondition(pageNum, pageSize);
        }

        function selectriskGuarantyMoneyByCondition(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectriskGuarantyMoneyByCondition").submit();
        }

        //查看详情
        function todetailUi(tid) {
            var action = "${pageContext.request.contextPath}/admin/riskGuarantyMoney/selectRiskGuarantyMoneyByPrimaryKey.action?tid=" + tid;
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

        //更新
        function toUpdateUi(tid) {
            url = "admin/riskGuarantyMoney/toUpdateUi.action?tid=" + tid;
            window.location.href = url;
        }


        function insert_RiskGuarantyMoney_Ui() {
            var url = "admin/riskGuarantyMoney/insert_RiskGuarantyMoney_Ui.action";
            window.location.href = url;
        }

        //删除
        function deleteById(id, tid) {
            var url = "admin/riskGuarantyMoney/deleteRiskGuarantyMoney.action";
            var data = {
                "id": id,
                "tid": tid
            }
            var deleteCallBack = function (data) {
                if ($.trim(data) == "删除成功") {
                    alert(data);
                    window.location.href = "${pageContext.request.contextPath }/admin/riskGuarantyMoney/selectRiskGuarantyMoneyByCondition.action";
                } else {
                    alert(data);
                }
            }
            var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");
            if (flag) {
                jQuery.post(url, data, deleteCallBack);
            }
        }
        ;


    </script>

</head>

<body>

<div class="container" style="width: 100%;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>标的风险保证金设置</h3>
            <form id="selectriskGuarantyMoneyByCondition" method="post"
                  action="admin/riskGuarantyMoney/selectRiskGuarantyMoneyByCondition.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>编号</label><input type="text" name="riskgmno" id="riskgmno"
                                        value="${riskGuarantyMoney.riskgmno }">
                <label>会员等级</label><input type="text" name="ugrade" id="ugrade" value="${riskGuarantyMoney.ugrade }">
                <label>收费类型</label>
                <select name="chargetype" id="chargetype">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${riskGuarantyMoney.chargetype==1 }">selected="selected"</c:if>>结标收取
                    </option>
                    <option value="2" <c:if test="${riskGuarantyMoney.chargetype==2 }">selected="selected"</c:if>>
                        投标时收取
                    </option>
                </select>
                <div>
                    <button id="query_btn" class="btn btn-default" onclick="selectriskGuarantyMoneyByCondition(1,9)">
                        查询
                    </button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
                <div class="btn btn-default" onclick="insert_RiskGuarantyMoney_Ui()" style="margin-left: 91%">新增</div>
            </form>
            <table class="table table-hover" id="personList_table">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>编号</td>
                    <td>收款人</td>
                    <td>收费类型</td>
                    <td>结标分段金额低值</td>
                    <td>结标分段金额高值</td>
                    <td>风险保证金定额</td>
                    <td>风险保证金百份比</td>
                    <td>该段最高风险保证金额</td>
                    <td>会员等级</td>
                    <td>风险保证金费率</td>
                    <td>风险保证金最高收费</td>
                    <td>是否为模板</td>
                    <td>标的ID</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="riskGuarantyMoney" varStatus="risksta">
                    <tr class="text-center">
                        <td>${risksta.count}</td>
                        <td>${riskGuarantyMoney.riskgmno }</td>
                        <td>${riskGuarantyMoney.rgmrecman }</td>
                        <td>
                            <c:choose>
                                <c:when test="${riskGuarantyMoney.chargetype eq 1 }">结标收取</c:when>
                                <c:when test="${riskGuarantyMoney.chargetype eq 2 }">投标时收取</c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${!empty riskGuarantyMoney.minrgmmoney}">
                                ${riskGuarantyMoney.minrgmmoney }
                            </c:if>
                            <c:if test="${empty riskGuarantyMoney.minrgmmoney}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty riskGuarantyMoneny.maxrgmmoney}">
                                ${riskGuarantyMoney.maxrgmmoney }
                            </c:if>
                            <c:if test="${empty riskGuarantyMoneny.maxrgmmoney}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty riskGuarantyMoney.rgmquota}">
                                ${riskGuarantyMoney.rgmquota }
                            </c:if>
                            <c:if test="${empty riskGuarantyMoney.rgmquota}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty riskGuarantyMoney.rgmpercent}">
                                ${riskGuarantyMoney.rgmpercent }
                            </c:if>
                            <c:if test="${empty riskGuarantyMoney.rgmpercent}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty riskGuarantyMoney.maxrgmfee}">
                                ${riskGuarantyMoney.maxrgmfee }
                            </c:if>
                            <c:if test="${empty riskGuarantyMoney.maxrgmfee}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <c:if test="${!empty riskGuarantyMoney.ugrade}">
                            <td title="${riskGuarantyMoney.ugrade }" class="remark-p">${riskGuarantyMoney.ugrade }</td>
                        </c:if>
                        <c:if test="${empty riskGuarantyMoney.ugrade}">
                            <td><label>......</label></td>
                        </c:if>
                        <td>
                            <c:if test="${!empty riskGuarantyMoney.rgmrate}">
                                ${riskGuarantyMoney.rgmrate }
                            </c:if>
                            <c:if test="${empty riskGuarantyMoney.rgmrate}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty riskGuarantyMoney.maxrgmamount}">
                                ${riskGuarantyMoney.maxrgmamount }
                            </c:if>
                            <c:if test="${empty riskGuarantyMoney.maxrgmamount}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${riskGuarantyMoney.istemplet==0 }">否</c:when>
                                <c:when test="${riskGuarantyMoney.istemplet==1 }">是</c:when>
                            </c:choose>
                        </td>
                        <td>${riskGuarantyMoney.tid}</td>
                        <td>
                            <button class="btn btn-default" id="detairisk"
                                    onclick="todetailUi('${riskGuarantyMoney.tid}')">查看详情
                            </button>
                            <button class="btn btn-default" onclick="toUpdateUi('${riskGuarantyMoney.tid}')">修改</button>
                            <button class="btn btn-default" id="modify"
                                    onclick="deleteById('${riskGuarantyMoney.id }','${riskGuarantyMoney.tid}');">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 模态框 -->

            <div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
                 aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog modal-lg" id="modalDialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">标的风险保证金详情</h4>
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
