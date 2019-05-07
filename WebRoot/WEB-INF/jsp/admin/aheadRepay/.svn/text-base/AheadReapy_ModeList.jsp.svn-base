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
	</style>
	<script type="text/javascript">
        $(function(){
            $("#reset").click(function () {
                $("#tno").val("");
            });
        });
        function queryAllPerson(pageNum, pageSize) {
            selectgfundsIntByCondition(pageNum, pageSize);
        }

        function selectAheadRepayModeByCondition(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectAheadRepayModeByCondition").submit();
        }

	</script>
	<script type="text/javascript">

        //详情
        function detail_UI(id) {
            var action = "${pageContext.request.contextPath}/admin/aheadRepay/detail_UI.action";
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

<div class="container" style="width:90%;font-family: 微软雅黑;">
	<form id="selectAheadRepayModeByCondition" method="post"
		  action="${pageContext.request.contextPath}/admin/aheadRepay/selectAheadRepayModeByCondition.action">
		<br/>
		<input type="hidden" id="pageNum" name="pageNum"/>
		<input type="hidden" id="pageSize" name="pageSize"/>
		<label>标编号</label><input type="text" name="tenderitem.tno" id="tno" value="${aheadRepayMode.tenderitem.tno}">
		<div style="margin: 10px;">
			<button id="query_btn" class="btn btn-default" onclick="selectAheadRepayModeByCondition(1,20)">查询
			</button>
			<input type="button" value="重置" class="btn btn-default" id="reset"/>
		</div>
	</form>
	<br/>
	<table class="table table-hover">
		<thead>
		<tr class="text-center" style="background: #ccc;">
			<td>序号</td>
			<td>标编号</td>
			<td>还款方式</td>
			<td>还款类型</td>
			<td>提前期数</td>
			<td>还款利息方式</td>
			<td>添加人</td>
			<td>设置时间</td>
			<td>备注</td>
			<td>操作</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${pagehelper.list }" var="apheadMode" varStatus="v">
			<tr class="text-center">
				<td>${v.count}</td>
				<td>${apheadMode.tenderitem.tno}</td>
				<td>
					<c:if test="${apheadMode.repaytype==1}">一次性还本付息</c:if>
					<c:if test="${apheadMode.repaytype==2}">等额本金</c:if>
					<c:if test="${apheadMode.repaytype==3}">等额本息</c:if>
					<c:if test="${apheadMode.repaytype==4}">按期付息到期还本</c:if>
				</td>
				<td>
					<c:if test="${apheadMode.arepaymode==1}">全部</c:if>
					<c:if test="${apheadMode.arepaymode==2}">部分</c:if>
				</td>
				<td>${apheadMode.aperiods}</td>
				<td>
					<c:if test="${apheadMode.intmode==1}">占天利息</c:if>
					<c:if test="${apheadMode.intmode==2}">全额利息</c:if>
				</td>
				<td>${apheadMode.addman}</td>
				<td><fmt:formatDate value="${apheadMode.addtime}"/></td>
				<td>${apheadMode.remark}</td>
				<td>
					<button class="btn btn-default" data-toggle="modal"
							data-target="#Modal" data-backdrop="static"
							onclick="detail_UI(${apheadMode.id})">详细信息
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



</body>
</html>