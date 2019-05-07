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

<title>奖品列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<style type="text/css">
	td {
		text-align: center;
		vertical-align: text-top !important;
		border: 1px solid #666;
	}
	#bz{
		text-align: left;
	}
</style>
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		selectDiffAwardByCondition(pageNum,pageSize);
	}
	
	function selectDiffAwardByCondition(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectDiffAwardByCondition").submit();
	}
	
	/*备注的字数约束*/
	$(function(){
		$(".remark-p").each(function(i){
			var num = $(this).text();
			if(num.length>5){
				$(this).text(num.substr(0,5)+"...");
			}
		});
	});
	
	
	/*插入奖品总开关*/
	function insert_DiffAward_Ui(){
		
		var url="<%=basePath%>admin/diffAwardSwitch/insertDiffAwardUi.action";
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
			url="<%=basePath%>admin/diffAwardSwitch/insertDiffAwardUi.action";
		}
		window.location.href=url;
		
	}
	
	/*查看详情*/
	function to_view_detail(obj){
		
		var id= $(obj).data("opid");
		//alert("id===="+id);
		
		var action = "<%=basePath%>admin/diffAwardSwitch/toViewDetail.action";
		var params = {
			"id":id	
		};
		var callback = function(data){
			$("#detailModal").modal({
				backdrop:'static',
				keyboard:false
			});
			$("#detailModal-body").html(data);
		}
		$.post(action,params,callback);
	}
	
	/*更新总开关*/
	function to_update_switch(id){
		window.location.href = "<%=basePath%>admin/diffAwardSwitch/toUpdateSwitch.action?id="+id;
	}
</script>

</head>

<body  style="font-family:'微软雅黑'; ">

<div class="container" style="margin-top:20px;">
		<div class="row clearfix">
		<div class="col-md-12 column" style="border-style:none; border-width:5px; border-color:Black; background-color:none;">
      
		<h4>奖品交易总开关</h4>
		<form id="selectDiffAwardByCondition" method="post" action="${pageContext.request.contextPath }/admin/diffAwardSwitch/selectDiffAwardByCondition.action">
			<input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}"/>
			<input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}"/>
			<label>属性名称：</label><input type="text" name="awardType" id="awardType" value="${das.awardType}" placeholder="--请输入奖品属性名称--">
			<label>交易方式总开关：</label>
			<select name="allSwitch" id="allSwitch">
				<option value="">请选择</option>
				<option value="1" <c:if test="${das.allSwitch eq 1}">selected</c:if>>开</option>
				<option value="2" <c:if test="${das.allSwitch eq 2}">selected</c:if>>关</option>
			</select>
			<button id="query_btn" class="btn btn-default" onclick="selectDiffAwardByCondition(1,9)">查询</button>
			<input type="button" value="重置" class="btn btn-default" id="reset" />
			<div class="btn btn-default pull-right" onclick="insert_DiffAward_Ui();">新增交易总开关</div>
		</form>
				<table class="table table-hover" id="personList_table">
					<thead>
						<tr style="background:#ccc;vertical-align: text-top!important;border:1px solid #666;">
							<td>序号</td>
							<td>属性名称</td>
							<td>交易方式总开关</td>
							<td>操作时间</td>
							<td>操作人</td>
							<td>备注</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="das" varStatus="vs">
							<tr>
								<td>${vs.index+1}</td>
									
								<td>${das.awardType}</td>
									
								<td>
									<c:if test="${das.allSwitch eq 1}">开</c:if>
									<c:if test="${das.allSwitch eq 2}">关</c:if>
								</td>
									
								<td>
									<fmt:formatDate value="${das.operateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
									
								<td>${das.operateMan}</td>
								
								<td class="remark-p" title="${das.remark}" limit="10">${das.remark}</td>
								<td>
									<button class="btn" onclick="to_update_switch(${das.id});">修改</button>	
									<button class="btn" onclick="to_view_detail(this);" data-opid="${das.id}">详情</button>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
						
				<div id="detailModal" class="modal fade bs-example-modal-lg" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">提示：你正在查看详情</h4>
							</div>
							<div class="modal-body" id="detailModal-body"></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
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
