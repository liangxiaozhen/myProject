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

<title>现金红包对账</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<style type="text/css">
	
	.text-center td {
		vertical-align: text-top !important;
		border: 1px solid #666;
	} 
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
		queryAllRedByCondition(pageNum,pageSize);
	}
	
	function queryAllRedByCondition(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#queryAllRedByCondition").submit();
	}
	
	/*查看详情*/
	function to_view_detail(obj){
		
		var id= $(obj).data("opid");
		//alert("id===="+id);
		
		var action = "<%=basePath%>admin/checkRecord/toViewDetail.action";
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
	
	//人工勾兑
	function redEnvelopeBlend(obj){
		
		if(confirm("确定要勾兑吗?")){
			
			/* var action = "${pageContext.request.contextPath}/admin/huifuBlend/userRedBlend.action";//汇付对账测试请求地址 */
			/* var action = "${pageContext.request.contextPath}/admin/moneyMoreMoreBlend/userRedBlend.action";//乾多多对账测试请求地址  */
			/*徽商银行没有对账*/
			var params = {
					id:$(obj).data("opid")
			};
			
			//$.post(action,params,function(){
				//刷新页面
				//queryAllPerson("${pagehelper.pageNum}","${pagehelper.pageSize}");
				
			//});	
		}
	}
	
	//系统勾兑
	function redEnvelopeSysBlend(){
		/* var action = "${pageContext.request.contextPath}/admin/moneyMoreMoreBlend/redEnvelopeSysBlend.action";//乾多多系统对账测试 */
		/* var action = "${pageContext.request.contextPath}/admin/huifuBlend/redEnvelopeSysBlend.action";//汇付系统对账测试  */
		//window.open(action);
	}
	
</script>

</head>

<body  style="font-family:'微软雅黑'; ">

<div class="container" style="margin-top:20px">
		<div class="row clearfix">
		<div class="col-md-12 column" style="border-style:none; border-width:5px; border-color:Black; background-color:none;">
      
		<h4>现金红包对账</h4>
		<form id="queryAllRedByCondition" method="post" action="${pageContext.request.contextPath }/admin/checkRecord/queryAllRedByCondition.action">
			<input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}"/>
			<input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}"/>
			<label>用户名：</label><input type="text" name="username" id="username" value="${reli.username}" placeholder="--请输入用户名--">
			<label>红包名称：</label><input type="text" name="redenvelopename" id="redenvelopename" value="${reli.redenvelopename}" placeholder="--请输入红包名称--">
			<label>金额：</label><input type="text" name="reamount" id="reamount" value="${reli.reamount}" placeholder="--请输入红包金额--">
			<label>人工勾兑：</label><select name="ismanblending">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${reli.ismanblending eq 0}">selected</c:if>>未勾兑</option>
				<option value="1" <c:if test="${reli.ismanblending eq 1}">selected</c:if>>已勾兑</option>
			</select>
			<label>系统勾兑：</label><select name="isblending">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${reli.isblending eq 0}">selected</c:if>>未勾兑</option>
				<option value="1" <c:if test="${reli.isblending eq 1}">selected</c:if>>已勾兑</option>
			</select>
			<button id="query_btn" class="btn btn-default" onclick="queryAllRedByCondition(1,9)">查询</button>
			<input type="button" value="重置" class="btn btn-default" id="reset" />
			<div class="pull-right">
				<button class="btn btn-default" onclick="redEnvelopeSysBlend();">系统对账</button>
			</div>
		</form>
				<table class="table table-bordered table-hover" id="personList_table">
					<thead>
						<tr style="background:#ccc;vertical-align: text-top!important;border:1px solid #666;">
							<td>序号</td>
							<td>流水号</td>
							<td>红包名称</td>
							<td>金额</td>
							<td>用户名</td>
							<td>业务类型</td>
							<td>托管公司</td>
							<td>系统勾兑</td>
							<td>人工勾兑</td>
							<td>操作</td>
							<td>查看</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="pl" varStatus="vs">
							<tr class="text-center">
								<td>${vs.index+1}</td>
									
								<td>${pl.orderno}</td>
									
								<td>${pl.redenvelopename}</td>
									
								<td>${pl.reamount}</td>
								
								<td>${pl.username}</td>
								
								<td>${pl.businesstype}</td>
								
								<td>${pl.paycompany}</td>
									
								<!-- 系统勾兑 -->
								<td>
									<c:choose>
										<c:when test="${pl.isblending eq 0}">未勾兑</c:when>
										<c:when test="${pl.isblending eq 1}">已勾兑</c:when>
									</c:choose>
								</td>
								<!-- 人工勾兑 -->
								<td>
									<c:choose>
										<c:when test="${pl.ismanblending eq 0}">未勾兑</c:when>
										<c:when test="${pl.ismanblending eq 1}">已勾兑</c:when>
									</c:choose>
								</td>
								<!-- 操作 -->
								<td>
									<button class="btn" onclick="redEnvelopeBlend(this);" data-opid="${pl.id}">勾兑</button>	
								</td>
								<!-- 查看 -->
								<td>
									<button class="btn" onclick="to_view_detail(this);" data-opid="${pl.id}">详情</button>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
						
				<div id="detailModal" class="modal fade bs-example-modal-lg" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog" style="width:750px;">
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
