<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<title>ActivityAwardList_List</title>
<style type="text/css">
.remark-p {
	width: 85px;
}

.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">

	/* 限制备注显示字符个数 */
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
				//				$(this).attr("title", objString);
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	}
	
	$(function() {
		$("[limit]").limit();
	})
	
	/* 备注tips */
	$(function() {
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
	});

	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		selectawardByCondition(pageNum,pageSize);
	}
	function selectawardByCondition(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectawardByCondition").submit();
	}
	
	//添加或者修改备注
	function addOrModifyRemark(obj){
		var action ="${pageContext.request.contextPath}/user/outsideAward/addOrModifyRemark.action";
		var id=$(obj).data("opid");
		var status=$(obj).data("status");
		var awardattribute=$(obj).data("awardattribute");
		var awardname=$(obj).data("awardname");
		var param ={
			"id":id,
			"status":status,
			"awardattribute":awardattribute,
			"awardname":awardname
		}
		var callback=function(data){
			$("#remarkModal").modal({
				backdrop:'static',
				keyboard:false
			});
			$("#remarkModal-body").html(data);
		}
		$.post(action,param,callback);
	}
	//用户填写完备注后并确定
	function sureAwardRemark(obj){
		var action ="${pageContext.request.contextPath}/user/outsideAward/sureAwardRemark.action";
		var id=$("#actAwardId").text();
		var remark=$("#actAwardRemark").val();
		var param={
			"id":id,
			"remark":remark
		}
		var callback= function(data){
			var json=$.parseJSON(data);
			alert(json["result"])
			$("#selectawardByCondition").submit();
		}
		$.post(action,param,callback);
	}
	
	//用户必须勾选我已按要求填写才能确定
	function agree(){
		if($("#demand").is(":checked")){
			$("#remarkSure").attr("disabled",false);
		}else{
			$("#remarkSure").attr("disabled","disabled");
		}
	}
	function remarkClosed(){
		$("#remarkSure").attr("disabled","disabled");
	}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<h3>用户奖品信息</h3>
				<form id="selectawardByCondition" method="post"
					action="${pageContext.request.contextPath}/user/outsideAward/queryAwardInfo.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum}" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> <label>奖品编号：</label><input
						type="text" name="awardno" id="awardno" value="${aals.awardno }"
						placeholder="--请输入奖品编号--"> <label>奖品名称：</label><input
						type="text" name="awardname" id="awardname"
						value="${aals.awardname }" placeholder="--请输入奖品名称--"> <label>发放状态：</label>
					<select name="status" id="status">
						<option value="">请选择</option>
						<c:if test="${!empty statusmaps}">
							<c:forEach items="${statusmaps }" var="st">
								<c:choose>
									<c:when test="${aals.status eq st.key }">
										<option value="${st.key }" selected="selected">${st.value }</option>
									</c:when>
									<c:otherwise>
										<option value="${st.key }">${st.value }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</select>
					<button id="query_btn" class="btn btn-default"
						onclick="selectawardByCondition(1,9)">查询</button>
					<button type="reset" class="btn btn-default">重置</button>
				</form>
				<table class="table  table-hover" id="tb_personlist">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<!-- <td>用户ID</td> -->
							<td>用户名</td>
							<td>活动编号</td>
							<td>奖品编号</td>
							<td>奖品名称</td>
							<td>奖品个数</td>
							<td>发放状态</td>
							<td>领奖规则</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="activityAwardList"
							varStatus="status">
							<tr class="text-center">
								<td>${status.index+1 }</td>
								<%-- <td>${activityAwardList.baseid}</td> --%>
								<td>${activityAwardList.username }</td>
								<td>${activityAwardList.actid }</td>
								<td>${activityAwardList.awardno }</td>
								<td class="ugrade">${activityAwardList.awardname }</td>
								<td>${activityAwardList.awardquantity }</td>
								<td><c:forEach items="${statusmaps }" var="st">
										<c:choose>
											<c:when test="${activityAwardList.status eq st.key }">
												${st.value }
											</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 领奖规则 -->
								<td><c:forEach items="${awardMap}" var="award"
										varStatus="sta">
										<c:choose>
											<c:when test="${activityAwardList.awardname eq award.key }">
												<p data-toggle="tooltip" title="<h5>${award.value}</h5>"
													limit="15">${award.value}</p>
											</c:when>
										</c:choose>
									</c:forEach></td>

								<td><c:choose>
										<c:when test="${activityAwardList.status ==4}">
											<button class="btn" onclick="addOrModifyRemark(this);"
												data-opid="${activityAwardList.id}"
												data-status="${activityAwardList.status}"
												data-awardattribute="${activityAwardList.awardattribute}"
												data-awardname="${activityAwardList.awardname}">添加/修改备注</button>
										</c:when>
										<c:otherwise>
											<button class="btn text-muted" disabled="disabled">添加/修改备注</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>

	<!-- 备注模态框（Modal） -->
	<div id="remarkModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在进行审核操作
					</h4>
				</div>
				<div class="modal-body" id="remarkModal-body"></div>
				<div class="modal-footer">
					<button id="remarkSure" type="button" class="btn btn-success"
						disabled="disabled" onclick="sureAwardRemark(this)">确定</button>
					<button id="remarkClose" onclick="remarkClosed()" type="button"
						class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>

</html>