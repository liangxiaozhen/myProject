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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta name="viewport"
          content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            font-family: "微软雅黑";
            font-size: 13px;
        }

        label {
            font-weight: normal;
        }

        td {
            border: 1px solid #666;
            vertical-align: middle !important;
        }

        label {
            font-weight: normal;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            /*** 重置查询条件 */
            $("#reset").click(function () {
                $("#orderno").val('');
                $("#tproperty").val('');
                $("#originclient").val('');
                $("#outaccount").val('');
                $("#inaccount").val('');
                $("#tenderitem").val('');
                $("#utproperty").val('');
            });
        })

        /*** 分页查询投标记录 */
        function queryAllPerson(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#tform").submit();
        }

        var userTender = {
            /*** 查看投标记录详情 */
            queryBidDetail: function (id) {
                var url = "queryBidRecordDetail.action";
                var param = {
                    "id": id
                };
                var callback = function (data) {
                    if (data == 'logout') {
                        window.location.href = "${pageContext.request.contextPath}/admin/login.action";
                        return;
                    }
                    $("#detailModal").modal({
                        backdrop: 'static',
                        keyboard: false
                    });
                    $("#detailModal-body").html(data);
                };
                $.post(url, param, callback);
            },
            selectAll: function (obj) {
                var checkboxs = document.getElementsByName("orderid");
                for (i = 0; i < checkboxs.length; i++) {
                    checkboxs[i].checked = obj.checked;
                }
            },
            singleAudit: function (orderno) {
                var url = "queryForAudit.action";
                var param = {
                    "orderno": orderno
                };
                var callback = function (data) {
                    if (data == 'logout') {
                        window.location.href = "${pageContext.request.contextPath}/admin/login.action";
                        return;
                    }
                    $("#auditModal").modal({
                        backdrop: 'static',
                        keyboard: false
                    });
                    $("#auditModal-body").html(data);
                };
                $.post(url, param, callback);
            },
            batchAudit: function () {
                var checkboxs = $("input[name='orderid']:checked");
                var bankDate = $("#bankDate").val();
                var size = checkboxs.size();
                if (size <= 0) {
                    alert("请选择要审核的投标订单！");
                } else {
                    var ordernos = "";
                    for (i = 0; i < checkboxs.length; i++) {
                        ordernos += checkboxs[i].value + ",";
                    }
                    var url = "queryForAudit.action";
                    var param = {
                        "orderno": ordernos,
                        "bankDate": bankDate
                    };
                    var callback = function (data) {
                        if (data == 'logout') {
                            window.location.href = "${pageContext.request.contextPath}/admin/login.action";
                            return;
                        }
                        $("#auditModal").modal({
                            backdrop: 'static',
                            keyboard: false
                        });
                        $("#auditModal-body").html(data);
                    };
                    $.post(url, param, callback);
                }
            },
            auditTender: function (obj) {
                $(obj).removeAttr('onclick').html('处理中......');
                var isaudit = $(obj).data("isaudit");
                var ordernos = $("#ordernos").val();
                var bankDate = $("#bankDate").val();
                var url = "checkTenderRecord.action";
                var param = {
                    "orderno": ordernos,
                    "isaudit": isaudit,
                    "bankDate": bankDate
                };
                var callback = function (data) {
                    if (data == 'logout') {
                        window.location.href = "${pageContext.request.contextPath}/admin/login.action";
                        return;
                    }
                    var json = $.parseJSON(data);
                    alert(json.result);
                    window.location.href = "queryBidRecordForAudit.action";
                };
                $.post(url, param, callback);
            },
            queryByTno: function (obj) {
                $("#tenderid").val(obj);
                $("#tform").submit();
            },
            downLoad: function () {
                var url = "test.action";
                var callback = function (data) {
                    if (data == "") {
                        alert("没有要处理的文件");
                    } else {
                        alert(data);
                    }
                };
                $.post(url, callback);
            }
        };
    </script>
</head>

<body>
<div class="container" style="width: 90%;">
    <div class="row clearfix">
        <div class="col-md-12 column"
             style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
            <div id="area_div" style="margin-top: 30px">
                <form method="post" role="form" id="tform" action="${action}">
                    <input type="hidden" id="pageNum" name="pageNum" value=""/> <input
                        type="hidden" id="pageSize" name="pageSize" value=""/> <input
                        type="hidden" id="tenderid" name="tenderid" value=""/>
                    <ul class="list-inline">
                        <li><label>标的属性：</label> <select name="tproperty"
                                                         id="tproperty">
                            <option value="">--请选择--</option>
                            <c:if test="${!empty tproperty_map}">
                                <c:forEach items="${tproperty_map}" var="ttype">
                                    <c:choose>
                                        <c:when test="${echodata.tproperty==ttype.key}">
                                            <option value="${ttype.key}" selected="selected">${ttype.value}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${ttype.key}">${ttype.value}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:if>
                        </select></li>
                        <li><label>投标设备来源：</label> <select name="originclient"
                                                           id="originclient">
                            <option value="">--请选择--</option>
                            <c:if test="${!empty originclient_map}">
                                <c:forEach items="${originclient_map}" var="origin">
                                    <c:choose>
                                        <c:when test="${echodata.originclient==origin.key}">
                                            <option value="${origin.key}" selected="selected">${origin.value}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${origin.key}">${origin.value}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:if>
                        </select></li>
                        <c:if test="${action eq 'queryBidRecordList.action'}">
                            <li><label>投标属性：</label> <select name="utproperty"
                                                             id="utproperty">
                                <option value="">--请选择--</option>
                                <c:if test="${!empty utproperty_map}">
                                    <c:forEach items="${utproperty_map}" var="utp">
                                        <c:choose>
                                            <c:when test="${echodata.utproperty==utp.key}">
                                                <option value="${utp.key}" selected="selected">${utp.value}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${utp.key}">${utp.value}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:if>
                            </select></li>
                        </c:if>
                        <li><label>投标订单号：</label> <input type="text" name="orderno"
                                                         id="orderno" value="${echodata.orderno}"/></li>
                        <li>
                        <li><label>标号：</label> <input type="text"
                                                      name="tenderitem.tno" id="tenderitem"
                                                      value="${echodata.tenderitem.tno}"/></li>
                        <li><label>投资人</label> <input type="text"
                                                      name="outaccount.loginname" id="outaccount"
                                                      value="${echodata.outaccount.loginname}"
                                                      placeholder="请输入投资方用户名..."/></li>
                        <li><label>借款人</label> <input type="text"
                                                      name="inaccount.loginname" id="inaccount"
                                                      value="${echodata.inaccount.loginname}"
                                                      placeholder="请输入借款方用户名..."/></li>
                        <li><input type="submit" value="查询" class="btn"/> <input
                                type="button" value="重置" class="btn" id="reset"/></li>
                    </ul>
                </form>
                <c:if test="${action eq 'queryBidRecordForAudit.action'}">
                    <div>
                        <label>徽商银行批处理文件时间 (测试环境时使用): </label><input type="text" id="bankDate" name="bankDate"
                                                                     value="20160621"/>
                        <input type="button" class="btn" value="批量操作"
                               onclick="userTender.batchAudit(this)"/>
                        <input type="button" class="btn" value="下载文件"
                               onclick="userTender.downLoad(this)"/>
                    </div>
                </c:if>
                <div class="table-responsive">
                    <table class="table table-hover table-condensed text-center">
                        <caption>
                            <strong>投标记录表</strong>
                        </caption>
                        <thead>
                        <tr style="background: #ccc;">
                            <c:if test="${action eq 'queryBidRecordForAudit.action'}">
                                <td><input type="checkbox" id="all"
                                           onclick="userTender.selectAll(this)"/></td>
                            </c:if>
                            <td>序号</td>
                            <%--<td>投标属性</td>--%>
                            <td>标号</td>
                            <td>投资方</td>
                            <td>借款方</td>
                            <td>投标时间</td>
                            <td>借款金额</td>
                            <td>已入账金额</td>
                            <td>投标金额（元）</td>
                            <td>投标状态</td>
                            <td>是否勾兑</td>
                            <td>详情信息</td>
                            <c:if test="${action eq 'queryBidRecordForAudit.action'}">
                                <td>审核</td>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${!empty pagehelper.list}">
                            <c:forEach items="${pagehelper.list}" var="tender"
                                       varStatus="varStatus">
                                <tr>
                                    <c:if test="${action eq 'queryBidRecordForAudit.action'}">
                                        <td><input type="checkbox" id="single" name="orderid"
                                                   value="${tender.orderno}"/></td>
                                    </c:if>
                                    <td>${varStatus.index+1}</td>
                                        <%--<td><c:if test="${!empty utproperty_map}">
                                                <c:forEach items="${utproperty_map}" var="utp">
                                                    <c:choose>
                                                        <c:when test="${tender.utproperty==utp.key}">
                                                            <c:if test="${tender.utproperty eq 1}">
                                                                <font>${utp.value}</font>
                                                            </c:if>
                                                            <c:if test="${tender.utproperty eq 2}">
                                                                <font color="blue">${utp.value}</font>
                                                            </c:if>
                                                            <c:if test="${tender.utproperty eq 3}">
                                                                <font color="red">${utp.value}</font>
                                                            </c:if>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:if></td>--%>
                                    <td><a
                                            onclick="userTender.queryByTno('${tender.tenderid}')"
                                            style="cursor: pointer;color:blue;">${tender.tenderitem.tno}</a></td>
                                    <td>${tender.outaccount.loginname}-${tender.outaccount.realname}</td>
                                    <td>${tender.inaccount.loginname}-${tender.inaccount.realname}</td>
                                    <td><fmt:formatDate value="${tender.tbegintime}" type="both"/></td>
                                    <td>${tender.tenderitem.tamount}</td>
                                    <td>${tender.tenderitem.finishtamount}</td>

                                    <td><fmt:formatNumber minFractionDigits="2"
                                                          value="${tender.amount}"></fmt:formatNumber></td>
                                    <td><c:if test="${!empty tstatus_map}">
                                        <c:forEach items="${tstatus_map}" var="status">
                                            <c:choose>
                                                <c:when test="${tender.tstatus==status.key}">
                                                    <c:if test="${tender.tstatus eq 0 || tender.tstatus eq 5}">
                                                        <font>${status.value}</font>
                                                    </c:if>
                                                    <c:if test="${tender.tstatus eq 1}">
                                                        <font color="blue">${status.value}</font>
                                                    </c:if>
                                                    <c:if
                                                            test="${tender.tstatus eq 2 || tender.tstatus eq 3 || tender.tstatus eq 6}">
                                                        <font color="red">${status.value}</font>
                                                    </c:if>
                                                    <c:if test="${tender.tstatus eq 4}">
                                                        <font color="green">${status.value}</font>
                                                    </c:if>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </c:if></td>
                                    <td><c:if
                                            test="${!empty isblending_map && !empty ismanblending_map}">
                                        <c:forEach items="${isblending_map}" var="sys">
                                            <c:forEach items="${ismanblending_map}" var="man">
                                                <c:choose>
                                                    <c:when
                                                            test="${tender.isblending==sys.key && tender.ismanblending==man.key}">
                                                        <c:if
                                                                test="${tender.isblending eq 0 && tender.ismanblending eq 0}">
                                                            <font>${sys.value}</font>
                                                        </c:if>
                                                        <c:if test="${tender.isblending eq 1}">
                                                            <font color="green">${sys.value}</font>
                                                        </c:if>
                                                        <c:if test="${tender.ismanblending eq 1}">
                                                            <font color="green">${man.value}</font>
                                                        </c:if>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </c:forEach>
                                    </c:if></td>
                                    <td>
                                        <button class="btn"
                                                onclick="userTender.queryBidDetail('${tender.id}')">查看详情
                                        </button>
                                    </td>
                                    <c:if test="${action eq 'queryBidRecordForAudit.action'}">
                                        <td>
                                            <button class="btn"
                                                    onclick="userTender.singleAudit('${tender.orderno}')">审核
                                            </button>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty pagehelper.list}">
                            <tr>
                                <td colspan="100">没有相关数据</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <div id="page_div">
                    <%@ include file="./../../common/pagehelper.jsp" %>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 详情模态框（Modal） -->
<div id="detailModal" class="modal fade bs-example-modal-lg"
     tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看投标记录详情
                </h4>
            </div>
            <div class="modal-body" id="detailModal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 审核模态框（Modal） -->
<div id="auditModal" class="modal fade bs-example-modal-lg"
     tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在进行审核投标记录操作
                </h4>
            </div>
            <div class="modal-body" id="auditModal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-isaudit="2"
                        onclick="userTender.auditTender(this)">放款
                </button>
                <button type="button" class="btn btn-danger" data-isaudit="3"
                        onclick="userTender.auditTender(this)">撤销
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>