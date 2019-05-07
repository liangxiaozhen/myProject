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

#bz {
	text-align: left;
}

td {
	text-align: center;
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		selectawardByCondition(pageNum,pageSize);
	}
	$(function(){
		$(".remark-p").each(function(i) {
			var num = $(this).text();
			if (num.length > 5) {
				$(this).text(num.substr(0, 5) + "...");
			}
		});
		
		$(".awRules").each(function(i) {
			var num = $(this).text();
			if (num.length > 8) {
				$(this).text(num.substr(0, 8) + "...");
			}
		});
		
		$("#reset").click(function() {
			$("#ano").val("");
			$("#aname").val("");
			document.getElementById("atype1").options[0].selected = true;
		})
	});
	
	function selectawardByCondition(pageNum, pageSize){
		var atype=$("#atype1").val();
		if(atype=="请选择"){
			$("#atype1").val("");
		}
		
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectawardByCondition").submit();
	}
	
	//查看详细信息
	function todetailUi(id){
		url="<%=basePath%>admin/award/selectAwardByPrimaryKey.action?id="+id+"&pageNum="+${pagehelper.pageNum};
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
			url="${pageContext.request.contextPath }/admin/award/selectAwardByPrimaryKey.action?id="+id+"&pageNum="+${pagehelper.pageNum};
		}
		window.location.href=url;
	}
	
	//修改奖品信息
	function toUpdateUi(id){
		url="<%=basePath%>admin/award/toUpdateUi.action?id="+id+"&pageNum="+${pagehelper.pageNum};
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
			url="${pageContext.request.contextPath }/admin/award/toUpdateUi.action?id="+id+"&pageNum="+${pagehelper.pageNum};
		}
		window.location.href=url;
	}
	
	//新增奖品
	function insert_Award_Ui(){
		var url="<%=basePath%>admin/award/insert_Award_Ui.action";
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
			url="${pageContext.request.contextPath }/admin/award/insert_Award_Ui.action";
		}
		window.location.href=url;
	}
	
	//删除某一个奖品
	function deleteSomeAward(obj){
		var action ="${pageContext.request.contextPath }/admin/award/deleteSomeAward.action";
		var id=$(obj).data("opid");
		var param ={
			"id":id
		}
		var callback= function(data){
			var json=$.parseJSON(data);
			alert(json["result"]);
			$("#selectawardByCondition").submit();
		
		}
		if(confirm("确定删除吗?")){
			$.post(action,param,callback);
		}
	}
</script>

</head>

<body style="font-family: '微软雅黑';">

	<div class="container" style="margin-top:20px;">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">

				<h3>所有奖品</h3>
				<form id="selectawardByCondition" method="post"
					action="${pageContext.request.contextPath }/admin/award/selectAwardByCondition.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum}" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> <label>奖品编号：</label><input
						type="text" name="ano" id="ano" value="${award.ano }"
						placeholder="--请输入奖品编号--"> <label>奖品名称：</label><input
						type="text" name="aname" id="aname" value="${award.aname }"
						placeholder="--请输入奖品名称--"> 
						<%-- <label>奖品类型：</label> <select
						name="atype" id="atype1">
						<option value="">请选择</option>
						<c:if test="${!empty atype_map}">
							<c:forEach items="${atype_map }" var="at">
								<c:choose>
									<c:when test="${award.atype==at.key }">
										<option value="${at.key }" selected="selected">${at.value }</option>
									</c:when>
									<c:otherwise>
										<option value="${at.key }">${at.value }</option>
									</c:otherwise>
								</c:choose>

						</c:forEach>
					</c:if> 
			</select> --%>
			
				<button id="query_btn" class="btn btn-default" onclick="selectawardByCondition(1,9)">查询</button>
				<input type="button" value="重置" class="btn btn-default" id="reset" />
			
			<div  class="btn btn-default" onclick="insert_Award_Ui();" style="margin-left: 90%">新增奖品</div>
		</form>
			
				<table class="table  table-hover" id="personList_table">
					
					<thead>
						<tr class="text-center" style="background: #ccc;border: 1px solid #666;">
							<td>序号</td>
							<td>奖品编号</td>
							<td>奖品名称</td>
							<!-- <td>奖品类型</td> -->
							<td>奖品属性</td>
							<!-- <td>奖品总份数</td> -->
							<td>奖品剩余数</td>
							<!-- <td>领奖规则</td> -->
							<td>备注</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="award" varStatus="vs">
							<tr>
								<td>${vs.index+1}</td>
								<td>${award.ano }</td>

								<td>${award.aname }</td>

								<%-- <td><c:forEach items="${atype_map }" var="am">
										<c:choose>
											<c:when test="${award.atype==am.key }">${am.value }</c:when>
										</c:choose>
									</c:forEach></td> --%>

								<td><c:forEach items="${attributeData_map }" var="at">
										<c:choose>
											<c:when test="${award.attribute==at.key }">${at.value }</c:when>
										</c:choose>
									</c:forEach></td>
								<%-- <td>${award.quantityall }</td> --%>
								<!-- 奖品剩余数 -->
								<td>${award.quantityrest }</td>
								<!-- 领奖规则 -->
								<%-- <td class="awRules" title="${award.awardRules}">${award.awardRules}</td> --%>

								<td id="bz" class="remark-p" title="${award.remark }">${award.remark }</td>
								<td>
									<button type="button" class="btn"  onclick="todetailUi('${award.id}');" >详情</button>
									<button class="btn" onclick="toUpdateUi('${award.id}');">修改</button>
									<c:if test="${award.quantityall eq award.quantityrest}">
										<button class="btn" onclick="deleteSomeAward(this);" data-opid="${award.id}">删除</button>
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
									onclick="updateAward();" id="update">修改</button>
								<button type="button" class="btn btn-default"
									onclick="insert_Award()" id="insert">新增</button>
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
