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

<title>AwardPackage</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<style type="text/css">
td {
		text-align: center;
		vertical-align: text-top !important;
		border: 1px solid #666;
	}

#bz {
	text-align: left;
}
</style>
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		selectawardPackageByCondition(pageNum,pageSize);
	}
	$(function(){
		$(".remark-p").each(function(i) {
			var num = $(this).text();
			if (num.length > 5) {
				$(this).text(num.substr(0, 5) + "...");
			}
		});
		$("#reset").click(function() {
			$("#apname").val("");
			document.getElementById("iscancel").options[0].selected = true;
		})
	});
	
	function selectawardPackageByCondition(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectawardPackageByCondition").submit();
	}
	
	//查看详情
	function todetailUi(id){
			url="<%=basePath%>admin/awardPackage/selectAwardPackageByPrimaryKey.action?id="+id;
			var explorer = window.navigator.userAgent ;
			//ie 
			if (explorer.indexOf("MSIE") >= 0) {
				url="${pageContext.request.contextPath }/admin/awardPackage/selectAwardPackageByPrimaryKey.action?id="+id;
			}
			window.location.href=url;
	}
	
	//修改奖品包
	function toUpdateUi(id){
		url="<%=basePath%>admin/awardPackage/toUpdateUi.action?id="+id;
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
			url="${pageContext.request.contextPath }/admin/awardPackage/toUpdateUi.action?id="+id;
		}
		window.location.href=url;
	}
	
	//新增礼品包
	function insert_AwardPackage_Ui(){
		var url="<%=basePath%>admin/awardPackage/insert_AwardPackage_Ui.action";
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
			url="${pageContext.request.contextPath }/admin/awardPackage/insert_AwardPackage_Ui.action";
		}
		window.location.href=url;
	}
	
	//删除奖品包
	function deleteById(id){
		var url="<%=basePath%>admin/awardPackage/deleteAwardPackage.action?id="+id;
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
			url="${pageContext.request.contextPath }/admin/awardPackage/deleteAwardPackage.action?id="+id;
		}
		if(confirm("确定要删除吗？")){
			window.location.href=url;
		}
	}
</script>

</head>

<body style="font-family: '微软雅黑';">
	<div class="container" style="margin-top:20px;">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">

				<h3>奖品包列表</h3>
				<form id="selectawardPackageByCondition" method="post"
					action="admin/awardPackage/selectAwardPackageByCondition.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum}" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> <label>奖品包名称：</label><input
						type="text" name="apname" id="apname" value="${awardPackage.apname }"
						placeholder="--请输入奖品包编号--"> <label>是否下架：</label><select
						id="iscancel" name="iscancel">
						<option value="">--请选择--</option>
						<c:if test="${!empty iscancelmap}">
							<c:forEach items="${iscancelmap}" var="im">
								<c:choose>
									<c:when test="${im.key eq awardPackage.iscancel}">
										<option value="${im.key}" selected="selected">${im.value}</option>
									</c:when>
									<c:otherwise>
										<option value="${im.key}">${im.value}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</select>
					
						<button id="query_btn" class="btn btn-default"
							onclick="selectawardPackageByCondition(1,9)">查询</button>
						<input type="button" value="重置" class="btn btn-default" id="reset" />
					
					<div class="btn btn-default" onclick="insert_AwardPackage_Ui();"
						style="margin-left: 95%">新增</div>
				</form>
				<table class="table table-hover"
					id="personList_table">
					<thead>
						<tr
							style="background: #ccc; vertical-align: text-top !important; border: 1px solid #666;">
							<td>序号</td>
							<td>奖品包编号</td>
							<td>奖品包名称</td>
							<td>是否下架</td>
							<td>奖品包剩余数</td>
							<!-- <td>是否为模板</td> -->
							<td>添加时间</td>
							<td>添加人</td>
							<td>备注</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="awardPackage" varStatus="vs">
							<tr>
								<td>${vs.index+1}</td>
								<td>${awardPackage.apno }</td>
								<td>${awardPackage.apname }</td>
								<td><c:forEach items="${iscancelmap}" var="im">
										<c:if test="${im.key eq awardPackage.iscancel}">${im.value}</c:if>
									</c:forEach></td>
								<td>${awardPackage.quantityrest }</td>
							<%-- 	<td><c:forEach items="${istempletmap}" var="tm">
										<c:if test="${tm.key eq awardPackage.isTemplet}">
									${tm.value}
								</c:if>
									</c:forEach></td> --%>
								<td><fmt:formatDate value="${awardPackage.addtime}"
										type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${awardPackage.addman }</td>
								<td id="bz" class="remark-p" title="${awardPackage.remark }">${awardPackage.remark }</td>
								<td>
									<button type="button" class="btn"
										onclick="todetailUi('${awardPackage.id}')">详情</button>
									<button class="btn" onclick="toUpdateUi('${awardPackage.id}')">修改</button>
									<c:if test="${awardPackage.quantityall eq awardPackage.quantityrest}">
										<button class="btn" id="modify" onclick="deleteById('${awardPackage.id }');">删除</button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 模态框 -->

				<div id="myModal" class="modal fade bs-example-modal-lg"
					tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel"></h4>
							</div>
							<div class="modal-body" id="modal-body"></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									onclick="updateAwardPackage();" id="update">修改</button>
								<button type="button" class="btn btn-default"
									onclick="insert_AwardPackage()" id="insert">新增</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
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
