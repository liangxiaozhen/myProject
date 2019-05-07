<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单模块 主页面</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<link
	href="${basePath}/resources/treeTable/themes/vsStyle/treeTable.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${basePath}/resources/treeTable/jquery.treeTable.min.js"></script>
<style>
.row {
	margin-bottom: 20px
}
</style>
</head>
<script type="text/javascript">
 	$(function(){
 		 $("#treeTable").treeTable();
 	});
 	
 	var menu = {
 			//跳转添加页面
 			menu_add_button:function(){
 				$("#queryall_list").load(basePath+"/admin/memu/add.action");
 			},
 			//更新按钮
			menu_update_button:function(obj){
 		 			var opid = $(obj).data("opid");
 		 			var menuname = $(obj).data("menuname");
 		 			if(isEmpty(menuname)){
 		 				loading("请输入菜单名称..",4);
 		 				$("#menuname").select();
 		 				return false;
 		 			}
		 			var resourceurl = $(obj).data("resourceurl");
		 			var numbercode = $(obj).data("numbercode");
		 			var params = {"id":opid,"menuname":menuname,"resourceurl":resourceurl,"numbercode":numbercode};
			 		$.tzAjax.request({
			 			model:"/admin/memu",
			 			method:"/add.action",
			 			callback:function(data){
			 				$("#queryall_list").html(data);
			 			}
			 		},params);	
  			 },
			 //删除
			menu_delete_button:function(obj){
				var opid = $(obj).data("opid");
				var menuname = $(obj).data("menuname");
 			 		$.tzAlert({
			 			"title":"删除提示",
			 			"content":"您确定删除<span class='red'>"+menuname+"</span>菜单吗？",
			 			callback:function(ok){
			 				if(ok){
			 					 $.tzAjax.request({
			 						 model:"/admin/memu",
			 						 method:"/delete.action",
			 						 callback:function(data){
 			 							 var obj = $.parseJSON(data);
			 							 if(obj.result=="opid_null"){
			 								 loading("网络异常",4);
			 							 }else if(obj.result=="fail"){
			 								 loading("删除失败",4);
			 							 }else if(obj.result=="success"){
			 								loading("删除成功",4);
			 								$("#"+opid+"").remove();
			 								$("."+opid+"").remove();
 			 							 }
			 						}
			 					 },{"opid":opid});
			 				}
			 			}
			 		});	
			 },
			 //添加下级菜单
			 menu_childrensave_button:function(obj){
				 	var opid = $(obj).data("opid");
		 			var menuname = $(obj).data("menuname");
		 			if(isEmpty(menuname)){
 		 				loading("请输入菜单名称..",4);
 		 				$("#menuname").select();
 		 				return false;
 		 			}
  		 			var params = {"fathernumber":opid,"fathermenuname":menuname};
 		 			$.tzAjax.request({
			 			model:"/admin/memu",
			 			method:"/add.action",
			 			callback:function(data){
			 				$("#queryall_list").html(data);
			 			}
			 		},params);
			 },
			 //保存或更新操作
 			menu_save:function(){
				var fatheropid = $("#fatheropid").val();
    			var opid = $("#opid").val();
 				var menuname = $("#menuname").val();
 				if(isEmpty(menuname)){
	 				loading("请输入菜单名称..",4);
	 				$("#menuname").select();
	 				return false;
	 			}
 				var resourceurl = $("#resourceurl").val();
 				var numbercode = $("#numbercode").val();
  			 	var action = "/save.action";
  			 	if(isNotEmpty(opid)){
  			 		action = "/update.action";
  			 	}
  			 	if(isNotEmpty(numbercode)){
  			 		var reg = new RegExp("^[0-9]*$"); 
  			 	 	if(!reg.test(numbercode)){  
  			       	 	alert("请输入数字!");  
  			       	 	return ;
  			   	 	} 
  			 	}
  			 	
  			 	var params={"menuname":menuname,"resourceurl":resourceurl,"numbercode":numbercode,"id":opid,"fathernumber":fatheropid};
  				$.tzAjax.request({
  					model:"/admin/memu",
  					method:action,
  					callback:function(data){
  					    var obj = $.parseJSON(data);
  					    if(obj.result=="success"){
  					    	alert("保存成功");
  					    	window.location.href=basePath+"/admin/memu/list.action";
  					    }else if(obj.result=="fail"){
  					    	loading("保存失败",4);
  					    }else if(obj.result=="update_success"){
   					    	alert("更新成功");
  					    	window.location.href=basePath+"/admin/memu/list.action";
  					    }else if(obj.result=="update_fail"){
  					    	loading("更新失败",4);
  					    }else if(obj.result=="resource_null"){
  					    	loading("网络异常",4);
  					    } 
  					}
  				},params);
 			},
 			//返回页面
 			menu_back:function(){
 				window.location.href=basePath+"/admin/memu/list.action";
  			}
  	};
 </script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div class="btn-group">
					<button class="btn btn-default" onclick="menu.menu_add_button()">增加菜单</button>
				</div>
				<div id="queryall_list" style="margin-top: 30px;">
					<table id="treeTable"
						class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>菜单名称</th>
								<th>菜单链接</th>
								<th>菜单编号</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${maps}" var="test">
								<tr id="${test.id}"
									pId="${test.fathernumber==null?'0':test.fathernumber}">
									<td nowrap><i class="icon- hide"></i><a href="#">${test.menuname}</a></td>
									<td title="">${test.resourceurl}</td>
									<td title="">${test.numbercode}</td>
									<td nowrap><a href="javascript:void(0)"
										onclick="menu.menu_update_button(this)" data-opid="${test.id}"
										data-menuname="${test.menuname}"
										data-resourceurl="${test.resourceurl}"
										data-numbercode="${test.numbercode}"> 修改 </a> <a
										href="javascript:void(0)"
										onclick="menu.menu_delete_button(this)" data-opid="${test.id}"
										data-menuname="${test.menuname}"> 删除 </a> <a
										href="javascript:void(0)"
										onclick="menu.menu_childrensave_button(this)"
										data-opid="${test.id}" data-menuname="${test.menuname}"
										data-resourceurl="${test.resourceurl}"
										data-numbercode="${test.numbercode}"> 添加下级菜单 </a></td>
								</tr>
								<gj:systemResources opid="${test.id}" var="test">
									<tr id="${test.id}"
										pId="${test.fathernumber==null?'0':test.fathernumber}">
										<td nowrap><i class="icon- hide"></i><a href="#">${test.menuname}</a></td>
										<td title="">${test.resourceurl}</td>
										<td title="">${test.numbercode}</td>
										<td nowrap><a href="javascript:void(0)"
											onclick="menu.menu_update_button(this)"
											data-opid="${test.id}" data-menuname="${test.menuname}"
											data-resourceurl="${test.resourceurl}"
											data-numbercode="${test.numbercode}"> 修改 </a> <a
											href="javascript:void(0)"
											onclick="menu.menu_delete_button(this)"
											data-opid="${test.id}" data-menuname="${test.menuname}">删除</a>
										</td>
									</tr>
								</gj:systemResources>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>