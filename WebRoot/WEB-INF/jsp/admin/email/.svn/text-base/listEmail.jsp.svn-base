<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件通道展示设置</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
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
}

#bz {
	text-align: left;
}
</style>
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		selectgfundsIntByCondition(pageNum,pageSize);
	}
	$(function(){
		$(".ugrade").each(function(i) {
			var num = $(this).text();
			if (num.length > 5) {
				$(this).text(num.substr(0, 5) + "...");
			}
		});
		$("#reset").click(function() {
			$("#gfundsintno").val("");
			document.getElementById("clearmethod").options[0].selected = true;
		})
	});
	
	function selectgfundsIntByCondition(pageNum, pageSize){
		var atype=$("#atype1").val();
		if(atype=="请选择"){
			$("#atype1").val("");
		}
		
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectgfundsIntByCondition").submit();
	}

	
	function toUpdateUi(id,auditstatus,username){
		url="updatePass.action?id="+id+"&auditstatus="+auditstatus+"&username="+username;
		window.location.href=url;
}
	
</script>
</head>
<body>
	<div class="container" style="width: 80%">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<table class="table table-bordered table-hover"
					id="personList_table">
					<thead>
						<tr
							style="background: #ccc; vertical-align: text-top !important; border: 1px solid #666;">
							<td>邮箱编号</td>
							<td>邮箱接口名称</td>
							<td>邮箱公司</td>
							<td>邮箱版本</td>
							<td>邮箱SMTP</td>
							<td>发送端邮箱</td>
							<td>邮箱密码</td>
							<td>是否启用</td>
							<td>添加时间</td>
							<td>添加人</td>
							<td>备注</td>
							<td>操作</td>
							<td>启用</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list}" var="gfundsInt">
							<tr>
								<td>${gfundsInt.emailcno}</td>
								<td>${gfundsInt.emailcname}</td>
								<td class="ugrade">${gfundsInt.emailccompany}</td>
								<td class="ugrade">${gfundsInt.emailvertion}</td>
								<td>${gfundsInt.emailsmtp}</td>
								<td>${gfundsInt.emailsend}</td>
								<td>${gfundsInt.emailpass}</td>
								<td>${gfundsInt.isuse==0?'是':'否'}</td>
								<td><fmt:formatDate value="${gfundsInt.addtime}"
										type="date" pattern="yyyy-MM-dd" /></td>
								<td>${gfundsInt.addman}</td>
								<td>${gfundsInt.remark}</td>
								<td><a class="btn btn-default btn-sm active"
									href="${pageContext.request.contextPath}/admin/emaill/skip.action">新增</a>
									<a class="btn btn-default btn-sm active"
									href="${pageContext.request.contextPath}/admin/emaill/selectbyid.action?id=${gfundsInt.id}">修改</a>
									<a class="btn btn-default btn-sm active"
									href="${pageContext.request.contextPath}/admin/emaill/delete.action?id=${gfundsInt.id}">删除</a>
								</td>
								<td><c:choose>
										<c:when test="${gfundsInt.isuse==0}">
											<a class="btn btn-success"
												href="${pageContext.request.contextPath}/admin/emaill/isuse.action?id=${gfundsInt.id}&isuse=1">禁用</a>
										</c:when>
										<c:when test="${gfundsInt.isuse==1}">
											<a class="btn btn-danger"
												href="${pageContext.request.contextPath}/admin/emaill/isuse.action?id=${gfundsInt.id}&isuse=0">启用</a>
										</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>