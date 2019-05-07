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

<title>Plus</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
   <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		selectplusByCondition(pageNum,pageSize);
	}
	$(function(){
		$(".remark-p").each(function(i) {
			var num = $(this).text();
			if (num.length > 5) {
				$(this).text(num.substr(0, 5) + "...");
			}
		});
		$("#reset").click(function() {
			$("#plusno").val("");
			$("#isaint").val("");
		})
	});
	
	function selectplusByCondition(pageNum, pageSize){
		var atype=$("#atype1").val();
		if(atype=="请选择"){
			$("#atype1").val("");
		}
		
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectplusByCondition").submit();
	}
	
	//详情
	function todetailUi(tid){
		var action="${pageContext.request.contextPath}/admin/plus/selectPlusByPrimaryKey.action";
		var param={
				"tid":tid
		}
		var callback=function(data){
			$("#myModal").modal({
	    		backdrop : 'static',
				keyboard : false
	    	});
	    	$("#modal-body").html(data);
		}
		$.post(action,param,callback);
	}
	
	function toUpdateUi(tid){
		url="${pageContext.request.contextPath}/admin/plus/toUpdateUi.action?tid="+tid;
		window.location.href=url;
}
	
	
	function insert_Plus_Ui(){
		var url="admin/plus/insert_Plus_Ui.action";
		window.location.href=url;
	}
	
	
	function deleteById(id,tid){
		var url="admin/plus/deletePlus.action";
		var data={
				"id":id,
				"tid":tid
		}
		var deleteCallBack=function(data){
			if($.trim(data)=="删除成功"){
				alert(data);
				window.location.href="${pageContext.request.contextPath }/admin/plus/selectPlusByCondition.action";
			}else{
				alert(data);
			}
		}
		 var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");   
         if(flag){
        	 jQuery.post(url, data, deleteCallBack);
         }
	}
</script>
</head>

<body  style="font-family:'微软雅黑'; font-size: 13px;">

<div class="container" style="width:100%">
		<div class="row clearfix">
			<div class="col-md-12 column">
      

	<h3>标的增益设置列表</h3>
		<form id="selectplusByCondition" method="post" action="admin/plus/selectPlusByCondition.action">
			<input type="hidden" id="pageNum" name="pageNum"/>
			<input type="hidden" id="pageSize" name="pageSize"/>
			<label>增益设置编号</label><input type="text" name="plusno" id="plusno" value="${plus.plusno }"> 
			<div>
				<button id="query_btn" class="btn btn-default" onclick="selectplusByCondition(1,9)">查询</button>
				<input type="button" value="重置" class="btn btn-default" id="reset" />
			</div>
			<div  class="btn btn-default" onclick="insert_Plus_Ui()" style="margin-left: 85%">新增</div>
		</form>
				<table class="table table-hover" id="personList_table">
					<thead>
						<tr class="text-center" style="background: #ccc;">
						    <td>序号</td>
							<td>增益设置编号</td>
							<td>是否允许加息卷</td>
							<td>是否允许类现金卷</td>
							<td>是否允许假现金卷</td>
							<td>单次允许的增益方式</td>
							<td>总计允许的增益方式</td>
							<td>增益清算付款人</td>
							<td>清算方式</td>
							<td>是否为模板</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="plus" varStatus="plussta">
							<tr class="text-center">
							    <td>${plussta.count}</td>
								<td>${plus.plusno }</td>
								<td>
									<c:choose>
											<c:when test="${plus.isaint==0 }">
												不允许
											</c:when>
											<c:when test="${plus.isaint==1 }">
												允许
											</c:when>										
										</c:choose>
								</td>
								<td>
									<c:choose>
											<c:when test="${plus.isavoucher==0 }">
												不允许
											</c:when>
											<c:when test="${plus.isavoucher==1 }">
												允许
											</c:when>										
										</c:choose>
								</td>
								<td>
									<c:choose>
											<c:when test="${plus.isalikevoucher==0 }">
												不允许
											</c:when>
											<c:when test="${plus.isalikevoucher==1 }">
												允许
											</c:when>										
										</c:choose>
								</td>
								<td>${plus.aonceplusmode }种</td>
								<td>${plus.atotalplusmode }种</td>
								<td>${plus.payforplusman }</td>
								<td>
									<c:choose>
											<c:when test="${plus.clearmode==1 }">
												结标清算
											</c:when>
											<c:when test="${plus.clearmode==2 }">
												首期清算
											</c:when>	
											<c:when test="${plus.clearmode==3 }">
												每期清算
											</c:when>	
											<c:when test="${plus.clearmode==4 }">
												尾期清算
											</c:when>										
										</c:choose>
								</td>
								<td>
									<c:choose>
											<c:when test="${plus.istemplet==0 }">
												否
											</c:when>
											<c:when test="${plus.istemplet==1 }">
												是
											</c:when>										
									</c:choose>
								</td>
								<td>
									<button type="button" class="btn btn-default"  onclick="todetailUi('${plus.tid}')" >详细信息</button>
									<button class="btn btn-default" onclick="toUpdateUi('${plus.tid}')">修改</button>
									<button class="btn btn-default" id="modify" onclick="deleteById('${plus.id }','${plus.tid}');">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 模态框 -->
						
				<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel"></h4>
							</div>
							<div class="modal-body" id="modal-body">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
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
