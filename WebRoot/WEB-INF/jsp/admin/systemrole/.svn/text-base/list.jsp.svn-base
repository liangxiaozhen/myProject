<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色模块 主页面</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}

.input-group-addon a {
	text-decoration: none;
}

body {
	padding-bottom: 40px;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>

</head>
<script type="text/javascript">
$(function(){
	$(".tzui-tips").tzTip();
});
  	var systemrole = {
  			//保存操作
  			save:function(obj){
 				$("#queryall_list").load(basePath+"/admin/systemrole/add.action");
 			},
 			//角色分配权限
 			gave:function(obj){
 				var roleId = $(obj).data("opid");
 				var roleUsername = $(obj).data("username");
 				$.tzIframe({width:520,height:420,title:"<span class='red'>"+roleUsername+"</span>角色授权",
 					drag:true,content:basePath+"/admin/systemrole/tree.action",
 					callback:function(iframe,$dialog,opts){
	 					if(iframe){
	 						//保存角色授权
	 						iframe.saveRoleAndResources(roleId,$dialog,opts);
	  					}
	 				},loadSuccess:function(iframe){
	 					//加载权限
	 					iframe.tree(function(){
 	 						//回调已经赋予的权限
	 						$.ajax({
	 							type:"post",
	 							url:basePath+"/admin/systemResource/findResourceId.action",
	 							data:{"roleId":roleId},
	 							success:function(data){
 	 								var obj = $.parseJSON(data);
 	 								var arr = new Array();
 	 								for(var i = 0;i<obj.result.length;i++){
 	 									arr = obj.result.split(",");
 	 								}
  			 						//选中已授权的权限
		 							for(var i=0;i<arr.length;i++){
										$(iframe.document).find("body").find(".tm-tree-checkbox[opid="+arr[i]+"]").
										addClass("tm-tree-checkbox-checked");
									}
 	 							}
	 						});
	 					});
	 				}
 				});
 			},
 			
 			//角色删除操作
 			gj_delete:function(obj){
 				var opid = $(obj).data("opid");
 				var username=$(obj).data("username");
 		 		 $.tzAlert({"content":"您確定刪除<span class='red'>"+username+"</span>角色嗎？","title":"删除提示？",
 		 			 callback:function(ok){
	 					 if(ok){
	 						 $.tzAjax.request({"model":"admin/systemrole","method":"/delete.action",callback:function(data){
	 							  var obj = $.parseJSON(data);
	 		 					  if(obj.result=="success"){
	 		 						 $("#gj_systemrole_"+opid).remove();
	 								  loading("刪除成功",4);
	 							  }else if(obj.result=="fail"){
	 								  loading("刪除失敗,請重新操作",4);
	 							  }
	 						 }},{"id":opid});
	 					 }
 				 }});
 			},
 			
 			//角色更新操作
  			gj_update:function(obj){
 				var opid = $(obj).data("opid");
 				var rolename = $(obj).data("username");
 		  		$("#queryall_list").load(basePath+"/admin/systemrole/add.action?opid="+opid+"&rolename="+rolename);
 			},
 			//角色分配用户操作方法
 			saveroleuser:function(obj){
 				var opid = $(obj).data("opid");
 				var rolename = $(obj).data("username");
 				$.tzIframe({width:880,height:620,title:"<span class='red'>"+rolename+"</span>角色分配用户",
 					drag:true,content:basePath+"/admin/roleuser/list.action",
 					callback:function(iframe,$dialog,opts){
	 					if(iframe){
	 						//保存角色授权
	 						iframe.roleuser.save(opid,$dialog,opts);
  	  					}
 	 				} 
 				});
  			},
  			//查找已经分配的角色用户
  			findroleuser:function(obj){
  				//角色ID
  				var opid = $(obj).data("opid");
 				var rolename = $(obj).data("username");
 				$("#queryall_list").load(basePath+"/admin/roleuser/havelist.action?opid="+opid);
     		}
 	};
  
	//分页查询通用方法
	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
 		var pageSize = "20";
  		$.ajax({
			type:"post",
			data:{"pageSize":pageSize,"pageNo":pageNo},
			url:basePath+"/admin/systemrole/template.action",
			success:function(data){
				$("#queryall_list").html(data);
				$(".tzui-tips").tzTip();
  			}
		});
 	}
  </script>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div class="btn-group">
					<button class="btn btn-default" onclick="systemrole.save()">增加角色</button>
				</div>
				<div id="queryall_list" style="margin-top: 30px;">
					<jsp:include page="/WEB-INF/jsp/admin/systemrole/template.jsp"></jsp:include>
				</div>
				<div id="gj_ifrma"></div>
			</div>
		</div>
	</div>
</body>
</html>