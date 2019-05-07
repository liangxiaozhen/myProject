<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>干将系统管理员列表</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
 $(function(){
		$(".tzui-tips").tzTip();
	});
 	//查询全部用户方法
    function gj_findalluser(){
    	var pageSize = "20";
    	var pageNo = "1";
    	$("#search_username").val("");
    	queryAllPerson(pageNo,pageSize);
     }
  
    //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var username = $("#search_username").val();
 		var pageSize = "20";
  		$.ajax({
			type:"post",
			data:{"pageSize":pageSize,"pageNo":pageNo,"username":username},
			url:basePath+"/admin/adminuser/template.action",
			success:function(data){
				$("#queryall_list").html(data);
				$(".tzui-tips").tzTip();
				//关键词高亮
				gjKeywordHi(username);
			}
		});
  	}
  	//绑定参数
 	function bindID(id){
 		$("#opid").val(id);
 	}
 	
 	//修改密码
 	function gj_updatepassword(obj){
  		 var opid = $("#opid").val();
  		 var password = $("#password").val();
  		 var psw = $("#password1").val();
  		 if(isEmpty(password)){
  			$("#password").select(); 
  			 $("#font_password").css({"color":"red","font-size":"12px"}).text("请输入密码...");
  			 return false;
  		 }
  		 if(isNotEmpty(password)){
   			 $("#font_password").css({"color":"green","font-size":"22px"}).text("√");
  		 }
  		 
  		 if(isEmpty(psw)){
  			$("#password1").select(); 
  			$("#font_password1").css({"color":"red","font-size":"12px"}).text("请输入密码..."); 
  			return false;
  		 }
  		 if(isNotEmpty(psw) && isNotEmpty(password) && psw != password){
  			$("#font_password").css({"color":"red","font-size":"12px"}).text("密码不一致..."); 
  			$("#font_password1").css({"color":"red","font-size":"12px"}).text("密码不一致...");
  			return false;
  		 }else{
  			$("#font_password").css({"color":"green","font-size":"22px"}).text("√");
  			$("#font_password1").css({"color":"green","font-size":"22px"}).text("√");
   		 }
  		$.ajax({
 			type:"post",
 			url:basePath+"/updatepsw.action",
 			data:{"id":opid,"password":password},
 			success:function(data){
 				var obj = $.parseJSON(data);
  				if(obj.result=="password_error"){
 					$("#font_password").css({"color":"red","font-size":"12px"}).text("请输入密码..."); 
 	   				$("#font_password1").css({"color":"red","font-size":"12px"}).text("请输入密码...");
 				}else if(obj.result =="fail"){
 					alert("操作失败,请重新操作");
 				}else if(obj.result=="success"){
 					alert("密码修改成功");
   					$("#updatepswModal").modal("hide");
 				}
 			}
 		}); 
 	}
 	
 	//模糊搜索
 	function search(obj){
 		var username = $("#search_username").val();
 		if(isEmpty(username)){
 			alert("请输入用户名搜索....");
 			$("#search_username").select();
 			return false;
 		}
 		  $.ajax({
 			  type:"post",
 			  url:basePath+"/admin/adminuser/template.action",
 			  data:{"username":username},
 			  success:function(data){
    			  $("#queryall_list").html(data);
    			  $(".tzui-tips").tzTip();
    			  gjKeywordHi(username);
   			  }
 		  });
 	}
 	
 	//代码高亮
 	function gjKeywordHi(keyword){
 		$("#queryall_list").find(".gj_keyword").each(function(){
			  var text = $(this).text();
 			  var regex = new RegExp(keyword,"ig");
			  var rtext = text.replace(regex,"<span class='red'>"+keyword+"</span>");
			  $(this).html(rtext);
		  });
 	}
 	
 	//更改登录状态
 	var timer = null;
 	function save(obj){
 		var $this = $(obj);
 		var opid = $this.data("opid");
 		var forbid = $this.data("forbid");
 		var username=$this.data("uana");
  		var mark = (forbid==0 ? 1 : 0 );
 		var ct = (forbid==0 ? "禁止":"允许" );
 		var params = {"id":opid,"forbid":mark};
 		$.tzAlert({title:"设置登录状态",content:"您确定设置<span class='red'>"+username+"</span>为"+ct+"登录状态吗？","icon":"fail",drag:false,callback:function(ok){
 			if(ok){
 			clearTimeout(timer);
 			timer = setTimeout(function(){
  				$.ajax({
	 				type:"post",
	 				url:basePath+"/admin/adminuser/update.action",
	 				data:{"id":opid,"forbid":mark},
	 				success:function(data){
	 					  var obj = $.parseJSON(data);
	 					  if(obj.result=="success"){
	 						  loading("设置成功",4);
 	 						$this.data("forbid",mark).toggleClass(mark == 0 ? "red":"green").text(mark==0?"设置禁止登录":"设置允许登录");
	 						$("#gj_forbid_"+opid).toggleClass(mark == 0 ? "green":"red").text(mark== 0 ? "未禁止": "禁止登录");
	 					  }else if(obj.result=="fail"){
	 						  loading("操作失败",4);
	 					  }
 	     	 			}
	 			}); 
 			}, 200);
 			}
 		}});
   	}
</script>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div class="col-md-4 input-group">
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-search"></span></span> <input type="text"
						name="search_username" id="search_username" class="form-control"
						placeholder="请输入用户名搜索..."> <span class="input-group-addon"><a
						href="javascript:search(this)">搜索</a></span>
					<div class="col-md-2 col-md-offset-3">
						<button class="btn " onclick="gj_findalluser(this)">查询全部用户</button>
					</div>
				</div>
				<div id="queryall_list" style="margin-top: 20px;">
					<jsp:include page="/WEB-INF/jsp/admin/adminuser/template.jsp"></jsp:include>
				</div>
			</div>

			<!-- 模态框updatepswModal -->
			<div class="modal fade" id="updatepswModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">修改密码</h4>
						</div>
						<div class="modal-body">
							<form role="form" id="gj_updatepsw">
								<div class="form-group">
									<label for="name">密码</label> <input type="password"
										class="form-control" id="password" name="password"
										placeholder="请输入密码..."> <font id="font_password"
										color="red"
										style="position: absolute; top: 49px; right: 90px;"></font>
								</div>
								<div class="form-group">
									<label for="name">密码</label> <input type="password"
										class="form-control" id="password1" name="password1"
										placeholder="请再次输入密码..."> <font id="font_password1"
										color="red"
										style="position: absolute; bottom: 36px; right: 90px;"></font>
									<input type="hidden" id="opid" />
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" onclick="gj_updatepassword(this)"
								class="btn btn-primary">提交更改</button>
						</div>
					</div>
				</div>
				<!-- modal  end -->
			</div>
		</div>
	</div>

</body>
</html>