<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>

	   <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
        .TextArea {
		    position: relative;
		    display: inline-block;
		    background: gray;
		    border: 1px solid gray;
		    border-radius: 4px;
		    padding: 4px 12px;
		    overflow: hidden;
		    color: gray;
		    text-decoration: none;
		    text-indent: 0;
		    line-height: 30px;
		    width: 80px;
		    height: 40px;
		    text-align: center;
		    background: white;
		  
} 
    </style>
<script type="text/javascript">
function queryAllPerson(pageNum, pageSize){
	selectgfundsIntByCondition(pageNum,pageSize);
}

function selectgfundsIntByCondition(pageNum, pageSize){
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#selectgfundsIntByCondition").submit();
}
</script>

<script type="text/javascript">
function deleteById(id){
	var msg="您确定删除吗？";
	if(confirm(msg)==true){
		window.location.href="${pageContext.request.contextPath}/admin/aheadRepay/deleteAwardById.action?id="+id;
	}else{
		return false;
	}
     }
function viewDetail(id){
	layer.open({
		  type: 2,
		  title:"查看详情信息",
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '600px'], //宽高
		  content: '${pageContext.request.contextPath}/admin/aheadRepay/viewDetailById.action?id='+id
		});
}
</script>
</head>
<body>
     <div class="container" style="width:90%;font-family: 微软雅黑;font-size: 13px;">
      <form id="selectgfundsIntByCondition" method="post" action="${pageContext.request.contextPath}/admin/aheadRepay/selectAheadRepayZYStyle.action">
			<br/>
			<input type="hidden" id="pageNum" name="pageNum"/>
			<input type="hidden" id="pageSize" name="pageSize"/>
		             奖励编号：<input type="text" name="aheadRepayANo" style="width: 180px;height: 30px;" value="${aheadRepayPNo}">&nbsp;&nbsp;&nbsp;
		             申请时间：
          <input type="text" class="Wdate" name="startAppTime"  style="width: 180px;height: 30px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"id="startAppTime" value="${startAppTime}"/> ---
          <input type="text" class="Wdate" name="endAppTime" style="width: 180px;height: 30px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="endAppTime" value="${endAppTime}"/>&nbsp;&nbsp;&nbsp;
		  <input type="submit" value="提交" style="width: 80px;height: 30px;"> <input type="reset" style="width: 80px;height: 30px;" value="重置"> 
		</form>
		<br/>
		<a href="${pageContext.request.contextPath}/admin/aheadRepay/insert_AheadRepayAward_Ui.action">增加</a>
   				<table class="table table-hover">
					<thead>
						  <tr class="text-center" style="background: #ccc;">
							<td>ID</td>
							<td>标号ID</td>
							<td>提前还款奖品奖励编号</td>
							<td>累计增益欠收最小利息</td>
							<td>累计增益欠收最高利息</td>
							<td>增益奖励方式</td>
							<td>增益平台罚金奖励名称</td>
							<td>增益平台罚金定额</td>
							<!-- <td>增益平台罚金百分比</td>
							<td>增益平台罚金最大值</td>
							<td>增益平台奖励奖品名称</td> -->
							<!-- <td>增益平台奖励奖品编号</td>
							<td>增益平台奖励奖品份数</td> -->
							<td>资金清算是否需要审核</td>
							<td>是否为模板</td>
							<td>添加人</td>
							<td>设置时间</td>
							<td>备注</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="aheadRepayAward">
							 <tr class="text-center">
								<td>${aheadRepayAward.id}</td>
								<td>${aheadRepayAward.tid}</td>
								<td>${aheadRepayAward.aheadrepayano}</td>
								<td>${aheadRepayAward.minplusnoreceiveint}</td>
								<td>${aheadRepayAward.maxplusnoreceiveint}</td>
								<td>
								  <c:if test="${aheadRepayAward.plusawardtype==1}">平台罚金</c:if>
								  <c:if test="${aheadRepayAward.plusawardtype==2}">平台奖励</c:if>
								  <c:if test="${aheadRepayAward.plusawardtype==3}">平台罚金+平台奖励</c:if>
								</td>
								<td>${aheadRepayAward.pluspenaltyname}</td>
								<td>${aheadRepayAward.pluspenaltyquota}</td>
							<%-- 	<td>${aheadRepayAward.pluspenaltyrate}</td>
								<td>${aheadRepayAward.plusmaxpenalty}</td>
								<td>${aheadRepayAward.pluspawardname}</td> --%>
								<%-- <td>${aheadRepayAward.pluspawardno}</td>
								<td>${aheadRepayAward.pluspawardquota}</td> --%>
								<td>
								 <c:if test="${aheadRepayAward.isaudit==1}">是</c:if>
								 <c:if test="${aheadRepayAward.isaudit==0}">否</c:if>
								</td>
								<td>
								  <c:if test="${aheadRepayAward.istemplet==0}">否</c:if>
								  <c:if test="${aheadRepayAward.istemplet==1}">是</c:if>
								</td>
								<td>${aheadRepayAward.addman}</td>
								<td><fmt:formatDate value="${aheadRepayAward.addtime}" /></td>
								<td>${aheadRepayAward.remark}</td>
								<td>
								<%-- <button class="btn btn-default" onclick="toUpdateUi('${aheadRepayAward.id}')">修改</button> --%>
								<a onclick="viewDetail(${aheadRepayAward.id})" href="javascript:;" class="TextArea">查看详情</a>
							    <a onclick="deleteById(${aheadRepayAward.id})" href="javascript:;" class="TextArea">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				</div>
					<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
				
</body>
</html>