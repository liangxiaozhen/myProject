<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<title>AheadRepay</title>
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
	function queryAllPerson(pageNum, pageSize){
		selectaheadRepayByCondition(pageNum,pageSize);
	}
	$(function(){
		$(".remark-p").each(function(i) {
			var num = $(this).text();
			if (num.length > 5) {
				$(this).text(num.substr(0, 5) + "...");
			}
		});
		$("#reset").click(function() {
			$("#aheadrepayno").val("");
			$("#ispicompensateon").val("");
			document.getElementById("atype1").options[0].selected = true;
		})
	});
	
	function selectaheadRepayByCondition(pageNum, pageSize){
		
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectaheadRepayByCondition").submit();
	}
	
	//查看详情
	function todetailUi(tid){
		var action="${pageContext.request.contextPath}/admin/aheadRepay/selectAheadRepayByPrimaryKey.action";
		var param={
			"tid":tid
		}
		var callback=function(data){
			$("#myModal").modal({
	    		backdrop : 'static',
				keyboard : false
	    	});
	    	$("#modal-body").html(data);
		}
		$.post(action,param,callback);
	}
	
	function toUpdateUi(tid){
		url="admin/aheadRepay/toUpdateUi.action?tid="+tid;
		window.location.href=url;
}
	
	
	function insert_AheadRepay_Ui(){
		var url="admin/aheadRepay/insert_AheadRepay_Ui.action";
		window.location.href=url;
	}
	
	/* function deleteById(id){
		var url="${pageContext.request.contextPath}/aheadRepay/deleteAheadRepay.action?id="+id;
		window.location.href=url;
		
	} */
	function deleteById(id,tid){
		var url="admin/aheadRepay/deleteAheadRepay.action";
		var data={
				"id":id,
				"tid":tid
		}
		var deleteCallBack=function(data){
			if($.trim(data)=="删除成功"){
				alert(data);
				window.location.href="${pageContext.request.contextPath }/admin/aheadRepay/selectAheadRepayByCondition.action";
			}else{
				alert(data);
			}
		}
		 var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");   
         if(flag){
        	 jQuery.post(url, data, deleteCallBack);
         }
	}
</script>

</head>

<body  style="font-family:'微软雅黑';font-size: 13px;">

<div class="container" style="width:100%">
		<div class="row clearfix">
			<div class="col-md-12 column">
      
	<h3>标的提前还款设置列表</h3>
		<form id="selectaheadRepayByCondition" method="post" action="admin/aheadRepay/selectAheadRepayByCondition.action">
			<input type="hidden" id="pageNum" name="pageNum"/>
			<input type="hidden" id="pageSize" name="pageSize"/>
			<label>提前还款编号</label><input type="text" name="aheadrepayno" id="aheadrepayno" value="${aheadRepay.aheadrepayno }"> 
			<%-- <label>本金利息补偿开关</label><input type="text" name="ispicompensateon" id="ispicompensateon" value="${aheadRepay.ispicompensateon }">  --%>
			<div>
					<button id="query_btn" class="btn btn-default" onclick="selectaheadRepayByCondition(1,9)">查询</button>
					<input type="button" value="重置" class="btn btn-default" id="reset" />
			</div>
			<div  class="btn btn-default" onclick="insert_AheadRepay_Ui()" style="margin-left: 88%">新增</div>
			<%--  <a href="${pageContext.request.contextPath}/admin/aheadRepay/insert_AheadRepayAward_Ui.action">增益</a>
			<a href="${pageContext.request.contextPath}/admin/aheadRepay/insert_AheadRepayPlatform_Ui.action">补偿平台</a>
			<a href="${pageContext.request.contextPath}/admin/aheadRepay/insert_AheadRepayMode_Ui.action">还款方式</a> --%>
		</form>
				<table class="table table-hover" id="personList_table">
					<thead>
						<tr class="text-center" style="background: #ccc;">
						    <td>序号</td>
							<td>提前还款编号</td>
							<td>累计本金欠收最小利息</td>
							<td>累计本金欠收最高利息</td>
							<td>奖励方式</td>
							<td>罚金奖励名称</td>
							<td>罚金定额</td>
							<td>罚金百分比</td>
							<td>罚金最大值</td>
							<td>平台奖励奖品名称</td>
							<td>是否为模板</td>
							<td>备注</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="aheadRepay" varStatus="aheadsta">
							<tr class="text-center">
							    <td>${aheadsta.count}</td>
								<td>${aheadRepay.aheadrepayno }</td>
								<td>
								<c:if test="${!empty aheadRepay.minnoreceiveint}">
								    ${aheadRepay.minnoreceiveint }元
								</c:if>
								<c:if test="${empty aheadRepay.minnoreceiveint}">
								    <label>......</label>
								</c:if>
								</td>
								<td>
								<c:if test="${!empty aheadRepay.maxnoreceiveint}">
							           ${aheadRepay.maxnoreceiveint }元
								</c:if>
								<c:if test="${empty aheadRepay.maxnoreceiveint}">
								    <label>......</label>
								</c:if>
								</td>
								<td>
									<c:choose>
										<c:when test="${aheadRepay.awardtype eq 1 }">惩罚借款人</c:when>
										<c:when test="${aheadRepay.awardtype eq 2 }">平台奖励</c:when>
										<c:when test="${aheadRepay.awardtype eq 3 }">惩罚借款人且平台奖励</c:when>
									</c:choose>
								</td>
								<td>
								<c:if test="${!empty aheadRepay.loanpenaltyname}">
								   ${aheadRepay.loanpenaltyname }
								</c:if>
								<c:if test="${empty aheadRepay.loanpenaltyname}">
								    <label>......</label>
								</c:if>
								</td>
								<td>
								   <c:if test="${!empty aheadRepay.penaltyquota}">
								     ${aheadRepay.penaltyquota }元
								   </c:if>
								   <c:if test="${empty aheadRepay.penaltyquota}">
								       <label>......</label>
								   </c:if>
								</td>
								<td>
								<c:if test="${!empty aheadRepay.penaltyrate}">
 								   ${aheadRepay.penaltyrate }%
								</c:if>
								<c:if test="${empty aheadRepay.penaltyrate}">
								      <label>......</label>
								</c:if>
								</td>
								<td>
								<c:if test="${!empty aheadRepay.maxpenalty}">
								   ${aheadRepay.maxpenalty}
								</c:if>
								<c:if test="${empty aheadRepay.maxpenalty}">
								    <label>......</label>
								</c:if>
								</td>
								<td>
								<c:if test="${!empty aheadRepay.pawardname}">
								   ${aheadRepay.pawardname}
								</c:if>
								<c:if test="${empty aheadRepay.pawardname}">
								   <label>......</label>
								</c:if>
								</td>
								<td>
								  <c:if test="${aheadRepay.istemplet eq 1}">
								     是
								  </c:if>
								  <c:if test="${aheadRepay.istemplet eq 0}">
								     否
								  </c:if>
								</td>
								<td class="remark-p" title="${aheadRepay.remark	}">${aheadRepay.remark}</td>
								<td>
										<button type="button" class="btn btn-default"  onclick="todetailUi('${aheadRepay.tid}')" >详细信息</button>
										<button class="btn btn-default" onclick="toUpdateUi('${aheadRepay.tid}')">修改</button>
										<button class="btn btn-default" id="modify" onclick="deleteById('${aheadRepay.id }','${aheadRepay.tid}');">删除</button>
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
								<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
							</div>
						</div>
					</div>
				</div>
				<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
</div>
</body>
</html>
