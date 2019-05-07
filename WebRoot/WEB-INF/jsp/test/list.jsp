<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户日志表列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 <%@include file="/WEB-INF/jsp/common/public.jsp" %>
 <style>
 	.text-center td{vertical-align: text-top!important;border: 1px solid #666;}
 	.input-group-addon a{text-decoration:none;}
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
 <script type="text/javascript">
 
 var userLogTest = {
		 //显示模态框
		 editshow:function(obj){
			 alert("editshow  模态框进来了");
			 var username = $(obj).data("username");
			 var opercontent = $(obj).data("opercontent");
			 var remark = $(obj).data("remark");
			 var opid = $(obj).data("opid");
			 
			 $("#username").val(username);
			 $("#opercontent").val(opercontent);
			 $("#remark").val(remark);
			 $("#opid").val(opid);
 			 
			 $("#editsauditModal").modal("show");
		 },
 
 		// 提交修改
 		submit:function(obj){
 			 var username = $("#username").val();
 			 var opercontent = $("#opercontent").val();
 			 var remark =  $("#remark").val();
 			 var opid =  $("#opid").val();
 			 
 			 
 			if(isEmpty(username)){
 				alert("请输入用户名");
 				$("#username").focus();
 				return ;
 			}
 			 
 			if(isEmpty(opercontent)){
 				alert("请输入操作内容");
 				$("#opercontent").focus();
 				return ;
 			}
 			 
 			$("#editsauditModal").modal("hide");//隐藏模态框
 			
 			 $.ajax({
 	 			 type:"post",
 	 			 url:basePath + "/test/subittest.action",
 	 			 data:{"username":username,"opercontent":opercontent,"remark":remark,"opid":opid},
 	 			 success:function(data){
 	 				$("#editsauditModal").modal("hide");
 	  				  var obj = $.parseJSON(data);
 	  				  alert(obj.Msg); 
 	 			 }
 	 			 
 	 		 });
 		}
 }
 
//空判断
 function isEmpty(val) {
 	val = $.trim(val);
 	if (val == null)
 		return true;
 	if (val == undefined || val == 'undefined')
 		return true;
 	if (val == "")
 		return true;
 	if (val.length == 0)
 		return true;
 	if (!/[^(^\s*)|(\s*$)]/.test(val))
 		return true;
 	return false;
 }
 
 //还款查看分页查询通用方法
	function queryAllPerson(pageNo,pageSize){
 		$("#queryall_list").html("数据正在加载中.....");
 		 var pageSize = "20";
 		 $.ajax({
 			 type:"post",
 			 url:basePath + "/test/template.action",
 			 data:{"pageSize":pageSize,"pageNo":pageNo},
 			 success:function(data){
  				 $("#queryall_list").html(data);
 			 }
 			 
 		 });
	}
 </script>
 </head>
<body>

	<!-- 编辑模态框  -->
			<div class="modal fade" id="editsauditModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">用户日志修改</h4>
						</div>
						<div class="modal-body">
							 <div class="row">
								<div class="col-md-12">
									 <label class="col-sm-4 text-left">用户名：</label>
									 <input type="text" id="username">
								</div>
								<div class="col-md-12">
									<label class="col-sm-4 text-left">操作内容</label>
									 <input type="text" id="opercontent"> 
								</div>
  								<div class="col-md-12">
									<label class="col-sm-4 text-left">备注:</label>
									<textarea style="min-height:80px;max-width:360px;min-width:280px;" id="remark"></textarea>
								</div>
								<input type="text" id="opid" readonly="readonly"/>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-success" onclick="userLogTest.submit(this)" >提交</button>
  							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
				<!-- modal  end -->
			</div>



 	<div class="container" style="width: 85%;">
		<div class="row">
			<div class="col-md-12" style="margin-top:15px;">
				<div id="box">
					 <div class="col-md-12 input-group column">
 					      <div class="col-md-12">
							<ul class="list-inline">
								<li><label>用户名:</label> <input type="text" name=""
									id="userLog_username" /></li>
 								<li><label>用户类型:</label>
									<select id="userLog_usertype" name="usertype">
										<option value=>--请选择--</option>
										<option value="1">普通用户</option>
										<option value="2">管理员用户</option>
 									</select>
								 </li>
								 <li><label>cookie:</label> <input type="text" name=""
									id="userLog_cookie" /></li>
								  <li><label>IP:</label> <input type="text" name=""
									id="userLog_IP" /></li>
	 							 <li>
									<button class="btn btn-default" onclick="search(this)"
										id="userLog_search">查询</button>
									<button class="btn btn-default"
										onclick="userLog.low_reset(this)" id="userLog_reset">重置</button>
 								</li>
							</ul>
						</div>
				     </div>
 					<div id="queryall_list" style="margin-top:40px;">
	 					<jsp:include page="/WEB-INF/jsp/test/listTemplate.jsp"></jsp:include>	  
	  				</div>
  				</div>
			</div>
   		</div>
	</div>
	 
 </body>
</html>