<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TenderItem_fabiao</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }

        input {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        function queryAllPerson(pageNum, pageSize) {
            selectgfundsIntByCondition(pageNum, pageSize);
        }

        function selectgfundsIntByCondition(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectgfundsIntByCondition").submit();
        }

        //分期发标
        function fenqi(id, money) {
            $("#loanid").val(id);
            $("#allmoney").val(money);
            $("#myModal").modal();
        }

        //分期发标输入金额
        function amount() {
            var money = $("#allmoney").val();
            var loanid = $("#loanid").val();
            var nowmoney = $("#exampleInputAmount").val();
            if (nowmoney > money) {
                alert("提示:输入金额不能大于借款金额");
            } else {
                var action = "${pageContext.request.contextPath}/admin/tenderItem/amount.action";
                var param = {
                    "loanid": loanid,
                    "nowmoney": nowmoney
                }
                var callback = function (data) {
                    if (data == "succ") {//可以发标
                        window.location.href = "${pageContext.request.contextPath }/admin/tenderItem/insert_TenderItem_Ui.action?id=" + loanid + "&nowmoney=" + nowmoney;
                    }
                    if (data == "fail") {
                        alert("提示：您的发标金额大于剩余金额!");
                    }
                }
                $.post(action, param, callback, 'json');
            }
        }

        //查看已投金额
        function alreadymoney(loanid) {
            var action = "${pageContext.request.contextPath}/admin/tenderItem/alreadymoney.action";
            var param = {
                "loanid": loanid,
            }
            var callback = function (data) {
                alert(data);
            }
            $.post(action, param, callback);
        }
        function builde(loanname,id){
            var flag=confirm("确定要为："+loanname+"建标吗？");
            if(flag==true){
                window.location.href="${pageContext.request.contextPath }/admin/tenderItem/insert_TenderItem_Ui.action?id="+id;
            }
        }
    </script>
</head>
<body>
<div class="container" style="width:90%;">
    <form id="selectgfundsIntByCondition" method="post"
          action="${pageContext.request.contextPath}/admin/tenderItem/selectloanAppbyappStatus.action">
        <br/>
        <input type="hidden" id="pageNum" name="pageNum"/>
        <input type="hidden" id="pageSize" name="pageSize"/>
        借款编号：<input type="text" name="loanno" style="width: 180px;height: 30px;" value="${loanno}"> &nbsp;&nbsp;&nbsp;
        借款标题：<input type="text" name="loanname" style="width: 180px;height: 30px;" value="${loanname}">&nbsp;&nbsp;&nbsp;
        申请时间：
        <input type="text" class="Wdate" name="startAppTime" style="width: 180px;height: 30px;"
               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="startAppTime" value="${startAppTime}"/> ---
        <input type="text" class="Wdate" name="endAppTime" style="width: 180px;height: 30px;"
               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="endAppTime" value="${endAppTime}"/>&nbsp;&nbsp;&nbsp;
        <input type="submit" value="提交" style="width: 80px;height: 30px;"> <input type="reset"
                                                                                  style="width: 80px;height: 30px;"
                                                                                  value="重置">
    </form>
    <br/>

    <table class="table table-hover">
        <thead>
        <tr class="text-center" style="background: #ccc;">
            <td>借款编号</td>
            <td>借款标题</td>
            <td>借款人</td>
            <td>标类型</td>
            <td>借款金额</td>
            <td>借款期限</td>
            <td>借款状态</td>
            <td>申请时间</td>
            <!-- <td>借款人资料</td>
            <td>图片资料</td> -->
            <td>资料审核状态</td>
            <td>分期借款</td>
            <td>操作</td>
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
                <td>${gfundsInt.loanamountstr}元</td>

                <td>${gfundsInt.appday}${gfundsInt.unit}</td>

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
                <td><fmt:formatDate value="${gfundsInt.apptime}" type="date" pattern="yyyy-MM-dd"/></td>
                    <%-- <td><a href="${pageContext.request.contextPath}/loan/selectloan.action?liano=${gfundsInt.liano}">借款人资料</a></td>
                    <td><a href="${pageContext.request.contextPath}/picpath/pictureadmin.action?liano=${gfundsInt.liano}">查看图片</a></td> --%>
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
                        <%-- <c:if test="${gfundsInt.ismulttender ==1}">
                             <button class="btn btn-default" onclick="fenqi('${gfundsInt.id}','${gfundsInt.loanamount}')">
                                 分期发标
                             </button>
                             <button class="btn btn-default" onclick="alreadymoney('${gfundsInt.id}')">已发金额</button>
                         </c:if>--%>
                    不分期
                </td>
                <td>
                    <c:if test="${gfundsInt.ismulttender ==0}">
                        <button type="button" class="btn btn-default"  onclick="builde('${gfundsInt.loanname}','${gfundsInt.id}');">建标</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <%--模态框--%>
    <div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
         aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog" id="modalDialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">分期发标</h4>
                </div>
                <div class="modal-body" id="modal-body">
                    <input type="hidden" id="loanid"/>
                    <input type="hidden" id="allmoney">
                    <form class="form-inline">
                        <div class="form-group" style="margin-left: 30px;">
                            <label class="sr-only" for="exampleInputAmount">请输入金额</label>
                            <div class="input-group">
                                <div class="input-group-addon">$</div>
                                <input type="text" class="form-control" id="exampleInputAmount" placeholder="请输入金额">
                                <div class="input-group-addon"></div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="amount()">提交</button>
                    </form>
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