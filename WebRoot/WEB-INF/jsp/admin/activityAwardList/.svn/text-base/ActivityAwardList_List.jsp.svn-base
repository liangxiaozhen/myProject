<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
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

td {
		text-align: center;
		vertical-align: text-top !important;
		border: 1px solid #666;
	}
</style>
<script type="text/javascript">
	
	//用以为jQuery扩展一个实例函数，jQuery对象可以调用此函数。
	jQuery.fn.limit = function() {
		var self = $("div[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	}
	
	/*控制备注里面的备注*/
	$(function() {
		//调用
		$("div[limit]").limit();
		//激活tooltip工具，并向提示工具插入html
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		
		//重置
		$("#reset_").click(function(){
			document.getElementById("username").value="";
			document.getElementById("actid").value="";
			document.getElementById("awardno").value="";
			document.getElementById("awardname").value="";
			document.getElementById("status").options[0].selected=true;
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
	
	//详细信息
	function todetailUi(obj){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/selectActivityAwardListByPrimaryKey.action";
		var id=$(obj).data("opid");
		var param={
			"id":id
		};
		var callback = function(data){
			$("#detailModal").modal({
				backdrop:'static',
				keyboard:false
			});
			$("#detailModal-body").html(data);
		};
		$.post(action,param,callback);
	}

	//审核用户，需要区分站内和站外奖品
	function auditActivityAward(obj){
		var action = "${pageContext.request.contextPath}/admin/activityAwardList/auditActivityAward.action";
		var id = $(obj).data("opid");
		var status = $(obj).data("status");
		var awardattribute = $(obj).data("awardattribute");
		var param={
			"id":id,
			"status":status,
			"awardattribute":awardattribute
		}
		var callback = function(data){
			$("#auditModal").modal({
				backdrop:'static',
				keyboard:false
			});
			$("#auditModal-body").html(data);
		}
		$.post(action,param,callback);	
	}
	
	//用户奖品名单审核通过
	function auditAwardPass(obj){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/auditAwardPass.action";
		var id = $("#actAwardId").text();
		//alert("id===="+id);
		var status = $("#actAwardStatus").text();
		var auditman = $("#auditmanId").text();
		var awardattribute = $("#actAwardAttribute").text();
		var param={
			"id":id,
			"status":status,
			"auditman":auditman,
			"awardattribute":awardattribute
		};
		var callback = function(data){
			var json=$.parseJSON(data);
			alert(json["result"]);
			$("#selectawardByCondition").submit();
		}
		$.post(action,param,callback);
	}
	
	//不通过
	function passedFailure(){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/passedFailure.action";
		var id = $("#actAwardId").text();
		var status = $("#actAwardStatus").text();
		var auditman = $("#auditmanId").text();
		var param={
			"id":id,
			"status":status,
			"auditman":auditman
		}
		var callback = function(data){
			var json = $.parseJSON(data);
			alert(json["result"]);
			$("#selectawardByCondition").submit();
		}
		$.post(action,param,callback);
	}
	
	/* 全选或者不全选 */
	function select_toggle(cb){
		var checkboxs = $(".checkbox_id");
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked = cb.checked;
		}
	}
	
	/* 批量审核 */
	function batchAudit(obj){
		
		var audit = $(obj).data("audit");//1.审核通过  2.审核不通过
		//alert("audit===="+audit);
		
		var checkboxs = $(".checkbox_id:checked");
		var size = checkboxs.size();
		//alert("size===="+size);
		var ids ="";
		for(var i = 0;i<size;i++){
			ids += checkboxs.eq(i).val()+",";
		}
		
		ids = ids.substring(0, ids.lastIndexOf(","))
		//alert("ids==="+ids);
		
	 	if(size>0){
			$.ajax({
				url:"<%=basePath%>admin/activityAwardList/batchAudit.action",
				type:"post",
				dataType:"json",
				data:{
					audit:audit,
					ids:ids
				},
				async: false,
				success:function(result){
					//alert("result==="+result);
					if(result=="success"){
						alert("操作成功");
					}else{
						alert("操作失败");
					}
					$("#selectawardByCondition").submit();
				}
			});
		}else{
			alert("请选择");
		}
		
	}
</script>
</head>
<body  style="font-family:'微软雅黑'; ">
<div class="container" style="width: 80%;margin-top:20px">
		<div class="row clearfix">
			<div class="col-md-12 column" style="border-style:none; border-width:5px; border-color:Black; background-color:none;">
		<h3>获奖名单审核</h3>
		<form id="selectawardByCondition" method="post" action="${pageContext.request.contextPath}/admin/activityAwardList/selectActivityAwardListByCondition.action">
			<input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}"/>
			<input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}"/>
			<label>用户名：</label><input type="text" name="username" id="username" value="${aal.username }" placeholder="--请输入用户名--">
			<label>活动编号：</label><input type="text" name="actid" id="actid" value="${aal.actid }" placeholder="--请输入活动编号--"> 
			<label>奖品编号：</label><input type="text" name="awardno" id="awardno" value="${aal.awardno }" placeholder="--请输入奖品编号--"> 
			<label>奖品名称：</label><input type="text" name="awardname" id="awardname" value="${aal.awardname }" placeholder="--请输入奖品名称--"> 
			<label>发放状态：</label>
			<select name="status" id="status">
						<option value="">请选择</option>
						<c:if test="${!empty statusauditmaps}">  
						<c:forEach items="${statusauditmaps }" var="st" >
								<c:choose>
									<c:when test="${aal.status==st.key }">
										<option value="${st.key }" selected="selected">${st.value }</option>
									</c:when>
									<c:otherwise>
								 		<option value="${st.key }">${st.value }</option>
									</c:otherwise>
								</c:choose>
						</c:forEach>
					</c:if> 
			</select>
			<button id="query_btn" class="btn btn-default" onclick="selectawardByCondition(1,9)">查询</button>
			<button type="button" id="reset_" class="btn btn-default">重置</button>
			<button class="btn btn-default" data-audit="1" onclick = "batchAudit(this);">批量审核通过</button>
			<button class="btn btn-default" data-audit="2" onclick = "batchAudit(this);">批量审核不通过</button>
		</form>
				<table class="table  table-hover" id="tb_personlist">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td><input type="checkbox" onclick="select_toggle(this);"></td>
							<td>序号</td>
							<!-- <td>奖品ID</td> -->
							<td>用户名</td> 
							<td>活动编号</td>
							<td>奖品编号</td>
							<td>奖品名称</td>
							<td>奖品个数</td>
							<td>发放状态</td>
							<td>备注</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="activityAwardList" varStatus="status">
							<tr class="text-center">
								<td><input type="checkbox" class="checkbox_id" value="${activityAwardList.id}"></td>
								<td>${status.index+1}</td>
								<%-- <td>${activityAwardList.id }</td> --%>
								<td>${activityAwardList.username }</td>
								<td>${activityAwardList.actid }</td>
								<td>${activityAwardList.awardno }</td>
								<td>${activityAwardList.awardname }</td>
								<td>${activityAwardList.awardquantity }</td>
								<td>
									<c:choose>
									    <c:when test="${activityAwardList.status ==1}">待审核</c:when>
										<c:when test="${activityAwardList.status ==2}">待处理</c:when>
										<c:when test="${activityAwardList.status ==4}">待确认</c:when>
										<c:when test="${activityAwardList.status ==7}">领取失败</c:when>
									</c:choose>
								</td>
								<td><p id="promptExplanation" data-toggle="tooltip" class="remark-p text-center" title="<h5>${activityAwardList.remark }<h5>" limit="10">${activityAwardList.remark } </p></td>
								<td>
									<c:choose>
										<c:when test="${activityAwardList.status ==1}">
											<button class="btn" onclick="auditActivityAward(this)" data-opid="${activityAwardList.id }"
											data-status="${activityAwardList.status}" data-awardattribute="${activityAwardList.awardattribute}" 
											>审核</button>
										</c:when>
									</c:choose>
									<button type="button" class="btn"  onclick="todetailUi(this)" data-opid="${activityAwardList.id }">详情</button>
								</td>
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

	<!-- 详情模态框（Modal） -->
	<div id="detailModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看用户使用券详情</h4>
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
					<h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在进行审核操作</h4>
				</div>
				<div class="modal-body" id="auditModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="auditAwardPass(this)">通过</button>
					<button type="button" class="btn btn-default" onclick="passedFailure(this)">不通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>