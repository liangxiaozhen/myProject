<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
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

        //是否允许发标
        function toUpdateUi(id, isaitender, appstatus) {

            url = "loanappPass.action?id=" + id + "&isaitender=" + isaitender + "&appstatus=" + appstatus;
            window.location.href = url;
        }

        //预审核
        function prepareisadut(id) {
            var url = "${pageContext.request.contextPath}/admin/loan/prepareisadut.action?id=" + id;
            window.location.href = url;
        }

        //审核确认
        function isaudok(loanno, id) {
            var action = "${pageContext.request.contextPath}/admin/loan/isaudok.action"
            var param = {
                "loanno": loanno,
                "id": id
            }
            var callback = function (data) {
                if (data == "fail") {
                    alert("提示:资料未审核完毕");
                }
                if (data == "succ") {
                    window.location.href = "${pageContext.request.contextPath}/admin/loan/selectloanappAll.action"
                }
            }
            $.post(action, param, callback, 'json');
        }

        function todetailUi(id) {
            var action = "${pageContext.request.contextPath}/admin/loan/todetailUi.action";
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
<div class="container" style="width:100%;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>借款申请列表</h3>
            <form id="selectgfundsIntByCondition" method="post"
                  action="${pageContext.request.contextPath}/admin/loan/selectloanappAll_look.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>用户编号:</label><input type="text" name="baseid" id="gfundsintno">
                <label>查看范围：</label>
                <select name="appstatus" id="clearmethod">
                    <option value="">全部</option>
                    <option value="0">审核中</option>
                    <option value="1">审核通过</option>
                    <option value="2">审核失败</option>
                </select>
                <label>借款利率：</label><input type="text" name="loanrate">
                <label>还款方式：</label><select name="repaymenttype">
                <option value="" selected="selected">--请选择--</option>
                <option value="1">一次还本付息</option>
                <option value="2">等额本金</option>
                <option value="3">等额本息</option>
                <option value="4">按期付息到期还本</option>
            </select>
                <label>借款金额：</label><select name="loanamount">
                <option value="" selected="selected">--请选择--</option>
                <option value="10000">一万以下</option>
                <option value="50000">五万以下</option>
                <option value="100000">十万以下</option>
                <option value="100001">十万以上</option>
            </select>
                <div>
                    <button id="query_btn" class="btn btn-default" onclick="selectgfundsIntByCondition(1,9)">查询</button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
            </form>
            <table class="table table-hover " id="personList_table">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>借款编号</td>
                    <td>借款标题</td>
                    <td>借款人</td>
                    <td>标属性</td>
                    <td>借款金额</td>
                    <td>借款期限</td>
                    <td>还款方式</td>
                    <td>借款状态</td>
                    <td>申请时间</td>
                    <td>投标期（开始）</td>
                    <td>资料审核状态</td>
                    <td>详情</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="gfundsInt">
                    <tr class="text-center">
                        <td>${gfundsInt.loanno}</td>
                        <td>${gfundsInt.loanname}</td>
                        <td>${gfundsInt.loginname}-${gfundsInt.realname}</td>
                        <td>
                            <c:forEach items="${objectQuotes}" var="item">
                                <c:if test="${gfundsInt.loantype eq item.serialno}">
                                    ${item.objectname}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${gfundsInt.loanamountstr}元</td>
                        <td>${gfundsInt.appday}${gfundsInt.unit}</td>
                        <td>
                            <c:choose>
                                <c:when test="${gfundsInt.repaymenttype ==1}">一次还本付息</c:when>
                                <c:when test="${gfundsInt.repaymenttype ==2}">等额本金</c:when>
                                <c:when test="${gfundsInt.repaymenttype ==3}">等额本息</c:when>
                                <c:when test="${gfundsInt.repaymenttype ==4}">按期付息到期还本</c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:forEach items="${loanapp_appstatus}" var="s">
                                <c:if test="${gfundsInt.appstatus==s.key}">
                                    <c:choose>
                                        <c:when test="${s.key==2||s.key==7||s.key==12||s.key==13||s.key==14}">
                                            <font style="color: red;">${s.value}</font>
                                        </c:when>
                                        <c:otherwise>
                                            <font style="color: blue;">${s.value}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><fmt:formatDate value="${gfundsInt.apptime}" type="both"/></td>
                        <td>
                            <c:if test="${gfundsInt.tbegintime!=null}">
                                <fmt:formatDate value="${gfundsInt.tbegintime}" type="both"/>
                            </c:if>
                            <c:if test="${gfundsInt.tbegintime==null}">
                                未建标
                            </c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${gfundsInt.mastatus ==1}">未填写</c:when>
                                <c:when test="${gfundsInt.mastatus ==2}">待审核</c:when>
                                <c:when test="${gfundsInt.mastatus ==3}">审核中</c:when>
                                <c:when test="${gfundsInt.mastatus ==4}">合格</c:when>
                                <c:when test="${gfundsInt.mastatus ==5}">部分合格</c:when>
                                <c:when test="${gfundsInt.mastatus ==6}">不合格</c:when>
                            </c:choose>
                        </td>

                        <td>
                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#Modal" data-backdrop="static"
                                    onclick="todetailUi('${gfundsInt.id}')">详细信息
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