<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获奖信息</title>
<link href="${pageContext.request.contextPath}/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		$.ajax({
    		url:"${pageContext.request.contextPath}/user/activity/queryAwardInfo.action",
    		data:{
    			"pageNum":pageNum
    		},
    		async:false,
    		success:function(data){
    			$("#awardInfo").html(data);
    		}
    	});
	}
	
	$(document).ready(function(){
		
		$(".awardAddress").on("click",function(){
			var id = $(this).data("opid");
			//alert("id: "+id);
			layer.open({
				
				type: 2,
				title:"填写收货地址",
				area: ['450px', '450px'], //宽高
				fix:true,
				content: ["${pageContext.request.contextPath}/user/activity/addressSureUi.action?id="+id,"no"],
				btn:["确认",'取消'],
				yes:function(layero,index){
					
					var obj = layer.getChildFrame('body');
					//用户是否按要求填写
					var chk = obj.find("#chk");
										
					var flag = true;
					var remark = obj.find("#remark").val();
					if(!remark){
						flag = false;
						layer.alert("请按要求填写收货地址！");
					}else if(!chk.is(":checked")){
						flag = false;
						layer.alert("请勾选！");
						/* $(".layui-layer-btn .layui-layer-btn0").attr("disabled","disabled"); */
						//$(".layui-layer-btn .layui-layer-btn0").css("display","none");
					}
					
					if(flag){
						//获取备注（用户所填的收货地址信息）
						$.ajax({
							url:"${pageContext.request.contextPath}/user/activity/addressSure.action",
							type:"post",
							dataType:"json",
							data:{
								"id":id,
								"remark":remark
							},
							success:function(data){
								if(data["result"]=="success"){
									alert("操作成功");
									layer.closeAll();
									//window.location.href="${pageContext.request.contextPath}/user/activity/queryAwardInfo.action?pageNum="+$("#pageNum").val();
									queryAllPerson($("#pageNum").val(), "");
								}
							}
						}); 
					}
					
				}
				
			});
			
		}); 
		 
	}); 
</script>
<style>
	.bs{
		padding:0px 4px;
		border:1px solid #D3D3D3;
		border-radius:3px;
		background-color:#fff;
		height:31px
	}
</style>
</head>
<body style="height:100%;">
	<table cellspacing="0" cellpadding="0" class="table fc_9 mar_t5 bor_t">
		<input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> 		
		<thead></thead>
		<tbody>
			<tr>
				<th class="fc_3">获奖名称</th>
				<th class="fc_3">奖品个数</th>
				<th class="fc_3">发放状态</th>
				<th class="fc_3">获奖时间</th>
				<th class="fc_3">操作</th>
			</tr>
			
			<c:forEach items="${pagehelper.list}" var="pl">
				<tr>
					<td class="Numfont table_timelog">${pl.awardname}</td>
					<td class="fc_3">${pl.awardquantity}</td>
					<td class="fc_3">
						<c:forEach items="${statusmaps}" var="st">
							<c:choose>
								<c:when test="${pl.status==st.key }">
									${st.value }
								</c:when>
							</c:choose>
						</c:forEach>
					</td>
					<td class="fc_3">
						<fmt:formatDate value="${pl.dealtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<button class="awardAddress bs" type="button" data-opid="${pl.id}" ${pl.status == 4?'':'disabled'}>收货地址</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="page_div">
		<%@ include file="../../../common/pagehelper.jsp"%>
	</div>

</body>
</html>