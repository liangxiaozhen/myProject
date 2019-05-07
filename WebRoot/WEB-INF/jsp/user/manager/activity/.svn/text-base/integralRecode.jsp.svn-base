<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分记录</title>
<link href="${pageContext.request.contextPath}/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		
		//获取开始时间
		var startdate = $("#startdate").val();
    	//alert("开始时间： "+startdate);
    	var enddate = $("#enddate").val();
    	//alert("结束时间： "+enddate);
    	var action = "${pageContext.request.contextPath}/user/activity/integralRecode.action";
    	if(startdate && enddate){
    		action = "${pageContext.request.contextPath}/user/activity/integralRecodePeriod.action"
    	}
		
		$.ajax({
    		url:action,
    		data:{
    			"pageNum":pageNum,
    			"startdate":startdate,
    			"enddate":enddate
    		},
    		async:false,
    		success:function(data){
    			$("#integralRecord").html(data);
    		}
    	});
	}
	
	$(document).ready(function(){
		
		/*$(".integralDetail").click(function(){
			
			 var id = $(this).data("opid");
			alert("id: "+id);
			
			$("#dialogid").dialog();
			
			$.ajax({
				url:"${pageContext.request.contextPath}/user/activity/integralDetail.action",
				type:"post",
				data:{
					"id":id
				},
				async:false,
	    		success:function(data){
					
					$(data).dialog({
						bgiframe: true,
						resizable: true,
        				height:500,
        				width:800,
        				modal:true,
        				draggable:true,
        				minWidth:250,
        				buttons:{
        					"关闭":function(){
        						$("#dialogid").dialog("destroy");
        					}
        					        				
        				}
        			});
					
					$("#dialogid").html(data);
				},
				error:function(XMLResponse){
					alert("error");
				}
				
			});
			
			
		}); */
		
		
		$(".integralDetail").click(function(){
			
			var id = $(this).data("opid");
			
			layer.open({
				type:2,
				title:"用户积分详情",
				area:["500px","550px"],
				fix:true,
				content:["${pageContext.request.contextPath}/user/activity/integralDetail.action?id="+id,"no"],
				btn:["关闭"]
			});
			
		});
	});
	
</script>
</head>
<body>
	<tbody>
		<table cellspacing="0" cellpadding="0" class="table fc_9 bor_t mar_t5">
			<tr>
				<th class="fc_3">名称</th>
				<th class="fc_3">类型</th>
				<th class="fc_3">积分来源</th>
				<th class="fc_3">积分数</th>
				<th class="fc_3">时间</th>
				<th class="fc_3">说明</th>
			</tr>
			<c:forEach items="${pagehelper.list}" var="pl">
				<tr>
					<td>${pl.award.aname}</td>
					<td>
						<c:if test="${pl.bpUseType eq 4}">交易积分</c:if>
						<c:if test="${pl.bpUseType eq 5}">系统积分</c:if>
					</td>
					<td>
						<c:if test="${pl.bptype eq 1}">注册</c:if>
						<c:if test="${pl.bptype eq 2}">累投</c:if>
						<c:if test="${pl.bptype eq 3}">单标</c:if>
						<c:if test="${pl.bptype eq 4}">首投</c:if>
						<c:if test="${pl.bptype eq 5}">手动颁奖</c:if>
						<c:if test="${pl.bptype eq 6}">自主颁奖</c:if>
					</td>
					<td class="Numfont fc_green">${pl.bonuspoints}</td>
					<td class="Numfont">
						<fmt:formatDate value="${pl.bpdealtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<a class="integralDetail" href="javascript:void(0);" data-opid="${pl.id}">详情</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</tbody>
</body>
		<div id="page_div">
			<%@ include file="../../../common/pagehelper.jsp"%>
		</div>
		
</html>