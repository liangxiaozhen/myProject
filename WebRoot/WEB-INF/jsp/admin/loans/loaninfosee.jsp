<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借款人资料查看</title>
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
		selectgfundsIntByConditionsee(pageNum,pageSize);
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
	
	function selectgfundsIntByConditionsee(pageNum, pageSize){
		var atype=$("#atype1").val();
		if(atype=="请选择"){
			$("#atype1").val("");
		}
		
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectgfundsIntByConditionsee").submit();
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


				<h3>借款人资料查看</h3>
				<form id="selectgfundsIntByConditionsee" method="post"
					action="${pageContext.request.contextPath}/loan/selectIdAndauditstatussee.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <label>查看范围：</label>
					<select name="auditstatus" id="clearmethod">
						<option value="">全部</option>
						<option value="0">审核中</option>
						<option value="1">审核通过</option>
						<option value="2">审核失败</option>
					</select>
					<div>
						<button id="query_btn" class="btn btn-default"
							onclick="selectgfundsIntByConditionsee(1,9)">查询</button>
						<input type="button" value="重置" class="btn btn-default" id="reset" />
					</div>
				</form>
				<table class="table table-bordered table-hover"
					id="personList_table">
					<thead>
						<tr
							style="background: #ccc; vertical-align: text-top !important; border: 1px solid #666;">
							<td>用户姓名</td>
							<td>用户登录名</td>
							<td>教育程度</td>
							<td>婚姻状况</td>
							<td>月薪</td>
							<td>紧急联系人</td>
							<td>紧急联系人电话</td>
							<td>紧急联系人关系</td>
							<td>申请时间</td>
							<td>审核状态</td>
							<td>详细资料</td>
							<td>图片材料</td>
							<td>审核人</td>
							<td>审核时间</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="gfundsInt">
							<tr>
								<td>${gfundsInt.userbaseAccountInfo.realname }</td>
								<td>${gfundsInt.userbaseAccountInfo.loginname }</td>
								<td>${(gfundsInt.education==1 )? '初中':''}
									${(gfundsInt.education==2 )? '职高':''} ${(gfundsInt.education==3 )? '高中':''}
									${(gfundsInt.education==4 )? '专科':''} ${(gfundsInt.education==5 )? '本科':''}
									${(gfundsInt.education==6 )? '硕士':''} ${(gfundsInt.education==7 )? '博士':''}
									${(gfundsInt.education==8 )? '其他':''}</td>
								<td>${gfundsInt.maritalstatus==0 ? '已婚':'未婚'}</td>
								<td>${gfundsInt.salary}元</td>
								<td>${gfundsInt.contactsman}</td>
								<td>${gfundsInt.contactsphone}</td>
								<td>${(gfundsInt.contactsration==0 )? '父子':''}
									${(gfundsInt.contactsration==1 )? '母子':''}
									${(gfundsInt.contactsration==2 )? '兄妹':''}
									${(gfundsInt.contactsration==3 )? '兄弟':''}
									${(gfundsInt.contactsration==4 )? '亲朋':''}
									${(gfundsInt.contactsration==5 )? '其他':''}</td>
								<td><fmt:formatDate value="${gfundsInt.addtime}"
										type="date" pattern="yyyy-MM-dd" /></td>
								<td>${gfundsInt.auditstatus ==1  ? '审核成功':''}
									${gfundsInt.auditstatus ==0  ? '审核中':''}
									${gfundsInt.auditstatus ==2  ? '审核失败':''}</td>
								<td><a
									href="${pageContext.request.contextPath}/loan/selectminute.action?id=${gfundsInt.id}">详细资料</a></td>
								<td><a
									href="${pageContext.request.contextPath}/picpath/pictureadmin.action?baseid=${gfundsInt.baseid}">点我查看</a></td>
								<td>${gfundsInt.auditman}</td>
								<td><fmt:formatDate value="${gfundsInt.audittime}"
										type="date" pattern="yyyy-MM-dd" /></td>
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