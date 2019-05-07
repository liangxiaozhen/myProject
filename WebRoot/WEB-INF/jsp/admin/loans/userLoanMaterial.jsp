<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>loanTypeObjectQuote_list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/util.js"></script>
    <script src="${pageContext.request.contextPath}/js/validate.js"></script>
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">
        function pass(id, sta, rowNum, baseid, loanno) {
            $("#AuditStatus").val(sta);
            $("#myid").val(id);
            $("#rowNum").val(rowNum);
            $("#myModal").modal();
            $("#baseid").val(baseid);
            $("#loanno").val(loanno);
        }
        var baseid;
        var loanno;
        //通过或不通过
        function passorfail() {
            var rowNum = $("#rowNum").val();
            baseid = $("#baseid").val();
            loanno = $("#loanno").val();
            var id = $("#myid").val();
            var tstatus = $("#AuditStatus").val();
            var pageNum = "${pagehelper.pageNum}";
            var abc = "44";
            var efg = "22";
            $.ajax({
                "type": "POST",
                "url": "${pageContext.request.contextPath}/admin/loanmaterial/passorfail.action?" + new Date().getTime(),
                "data": {"id": id, "tstatus": tstatus, "pageNum": pageNum},
                "success": function (backData) {
                    var obj = $.parseJSON(backData);
                    var status = obj.auditStatus;
                    if (status == "合格") {
                        $("#td_auditstatus" + rowNum).html("<span style='color:rgb(94,184,92);'>" + status + "</span>");
                        $("#audit" + rowNum).html("");
                    } else if (status == "不合格") {
                        $("#td_auditstatus" + rowNum).html("<span style='color:rgb(217,83,79);'>" + status + "</span>");
                        $("#audit" + rowNum).html("<button class='btn btn-default' onclick='test()'>同步</button>");
                    }
                    $("#myModal").modal('hide');
                }
            });

        }

        function queryAllPerson(pageNum, pageSize) {
            selectaheadRepayByCondition(pageNum, pageSize);
        }
        function selectaheadRepayByCondition(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectgfundsIntByConditionsee").submit();
        }

        //通过编号查找同编号数据
        function selectMaterialByloan(loan) {
            var url = "${pageContext.request.contextPath}/admin/loanmaterial/selectAllUserLoanmat.action?loanno=" + loan;
            window.location.href = url;
        }

        function test() {
            together(baseid, loanno);
        }
        //详情
        function toDetail(id) {
            var action = "${pageContext.request.contextPath}/admin/loanmaterial/toDetail.action";
            var param = {
                "id": id
            };
            var callback = function (data) {
                $("#myModaltwo").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body-Detail").html(data);
            }
            $.post(action, param, callback);
        }

        //同步公共资料
        function together(baseid, loanno) {
            var action = "${pageContext.request.contextPath}/admin/loanmaterial/together.action";
            var param = {
                "baseid": baseid,
                "loanno": loanno
            }
            var callback = function (date) {
                alert(date);
                window.location.href = "${pageContext.request.contextPath}/admin/loanmaterial/selectAllUserLoanmat.action";
            }
            $.post(action, param, callback);
        }

        $(function () {
            var selectMethod = $(".selectMethod option:selected").text();
            $("#query_btn").click(function () {//如果是点击查询按钮查询全部就去掉隐藏的值
                if (selectMethod == '全部') {
                    $("#loannoNum").val("");
                }
            });
            $(".selectMethod").change(function () {
                $("#loannoNum").val("");//当下拉框改变是也去掉隐藏的值
            });
        });
    </script>
</head>
<body>
<div class="container" style="width:80%;margin-top: 25px;">
    <h3>借款人资料查看</h3>
    <form id="selectgfundsIntByConditionsee" method="post"
          action="${pageContext.request.contextPath}/admin/loanmaterial/selectAllUserLoanmat.action">
        <input type="hidden" id="pageNum" name="pageNum"/>
        <input type="hidden" id="pageSize" name="pageSize"/>
        <input type="hidden" name="loanno" id="loannoNum" value="${loanMaterial.loanno}"/>
        <label>查看范围：</label>
        <select name="auditstatus" id="clearmethod" class="selectMethod">
            <c:if test="${loanMaterial.auditstatus==1}">
                <option value="">全部</option>
                <option value="1" selected="selected">审核中</option>
                <option value="2">审核通过</option>
                <option value="3">审核失败</option>
            </c:if>
            <c:if test="${loanMaterial.auditstatus==2}">
                <option value="">全部</option>
                <option value="1">审核中</option>
                <option value="2" selected="selected">审核通过</option>
                <option value="3">审核失败</option>
            </c:if>
            <c:if test="${loanMaterial.auditstatus==3}">
                <option value="">全部</option>
                <option value="1">审核中</option>
                <option value="2">审核通过</option>
                <option value="3" selected="selected">审核失败</option>
            </c:if>
            <c:if test="${loanMaterial.auditstatus==null}">
                <option value="" selected="selected">全部</option>
                <option value="1">审核中</option>
                <option value="2">审核通过</option>
                <option value="3">审核失败</option>
            </c:if>
        </select>

        <div>
            <button id="query_btn" class="btn btn-default" onclick="selectgfundsIntByConditionsee(1,9)">查询
            </button>
            <input type="button" value="重置" class="btn btn-default" id="reset"/>
        </div>
    </form>
    <table class="table table-hover">
        <thead>
        <tr class="text-center" style="background: #ccc;">
            <td>序号</td>
            <td>用户名</td>
            <td>真实姓名</td>
            <td>借款编号</td>
            <td>资料类型</td>
            <td>资料名称</td>
            <td>资料内容</td>
            <td>提交时间</td>
            <td>审核状态</td>
            <td>操作</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagehelper.list}" var="loan" varStatus="sta">
            <tr class="text-center">
                <td>${sta.count}</td>
                <td>
                        ${loan.accountInfo.loginname}
                </td>
                <td>
                        ${loan.accountInfo.realname}
                </td>

                <td><a href="#" onclick="selectMaterialByloan('${loan.loanno}')">${loan.loanno}</a></td>
                <td>
                    <c:if test="${loan.materialtype eq 1}">公共</c:if>
                    <c:if test="${loan.materialtype eq 2}">补充</c:if>
                </td>
                <td>${loan.materialname}</td>
                <td>
                    <c:if test="${!empty loan.materialcontent}">
                        ${loan.materialcontent}
                    </c:if>
                    <c:if test="${empty loan.materialcontent}">
                        <a href="#"
                           onclick="open('http://localhost:8080/pic/${loan.materialpic}','介绍','width=500,height=440,left=550,top=250,resizable=no,scrollbars=no,status=yes,toolbar=no,location=no,menubar=no,menu=yes')">查看图片</a>
                    </c:if>
                </td>
                <td><fmt:formatDate value="${loan.addtime}" type="date" pattern="yyyy-MM-dd"/></td>
                <td id="td_auditstatus${sta.index}">
                    <span style="color:rgb(217,83,79);">${loan.auditstatus ==3  ? '不合格':''}</span>
                    <span style="color:rgb(94,184,92);">${loan.auditstatus ==2  ? '合格':''}</span>
                    <span>${loan.auditstatus ==0  ? '待审核':''}</span>
                    <span style="color: rgb(66,139,202)">${loan.auditstatus ==1  ? '审核中':''}</span>
                </td>
                    <%-- <td><a href="${pageContext.request.contextPath}/loan/selectminute.action?id=${gfundsInt.id}">详细资料</a></td>
                    <td><a href="${pageContext.request.contextPath}/picpath/pictureadmin.action?baseid=${gfundsInt.baseid}">点我查看</a></td> --%>
                <td id="audit${sta.index}">
                    <!-- 待审核 -->
                    <c:if test="${loan.auditstatus eq 0}">
                        <button class="btn btn-default" disabled="disabled" style="cursor: not-allowed;color:#A0A0A0;">
                            通过
                        </button>
                        <button class="btn btn-default" disabled="disabled" style="cursor: not-allowed;color:#A0A0A0;">
                            拒绝
                        </button>
                    </c:if>
                    <!-- 审核中 -->
                    <c:if test="${loan.auditstatus eq 1}">
                        <button class="btn btn-default" onclick="pass('${loan.id}','p','${sta.index}')">通过</button>
                        <button class="btn btn-default"
                                onclick="pass('${loan.id}','f',${sta.index},'${loan.baseid}','${loan.loanno}')">
                            拒绝
                        </button>
                        <button class="btn btn-default" onclick="together('${loan.baseid}','${loan.loanno}')"
                                title="点击可同步公共资料">同步
                        </button>
                    </c:if>
                    <c:if test="${loan.auditstatus eq 3}">
                        <button class="btn btn-default" onclick="together('${loan.baseid}','${loan.loanno}')"
                                title="点击可同步公共资料">同步
                        </button>
                    </c:if>
                </td>
                <td>
                    <button class="btn btn-default" onclick="toDetail('${loan.id}')">详情</button>
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
                    <input type="hidden" id="myid"/>
                    <input type="hidden" id="AuditStatus"/>
                    <input type="hidden" id="rowNum"/>
                    <input type="hidden" id="baseid"/>
                    <input type="hidden" id="loanno"/>
                    <span style="color: red;">提示： 审核通过/不通过</span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" onclick="passorfail()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框 -->
    <div id="myModaltwo" class="modal fade bs-example-modal-lg" tabindex="-1"
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
                <div class="modal-body" id="modal-body-Detail">
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
</body>
</html>