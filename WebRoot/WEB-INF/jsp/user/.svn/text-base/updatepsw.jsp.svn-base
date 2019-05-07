<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改密码页面</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style type="text/css">
body {
	font: normal 15px/1.5 Arial, Helvetica, Free Sans, sans-serif;
	color: #222;
	background: url(pattern.png);
	overflow-y: scroll;
	padding: 60px 0 0 0;
}

#my-form {
	width: 755px;
	margin: 0 auto;
	border: 1px solid #ccc;
	padding: 3em;
	border-radius: 3px;
	box-shadow: 0 0 2px rgba(0, 0, 0, .2);
}

#comments {
	width: 350px;
	height: 100px;
}

.mobilesubmit {
	padding: 5px 10px;
	background: #F39C12;
	border-radius: 5px;
	line-height: 30px;
}

a:hover {
	text-decoration: none;
	color: red;
}
</style>
</head>
<body>
	<div class="row">

		<div class="eightcol last">

			<!-- Begin Form -->

			<form class="form-horizontal" role="form" id="my-form">
				<div class="form-group">
					<label class="col-sm-10 control-label">
						<h3>您的账号存在风险异常,为了您的资金安全,请修改密码再使用...</h3>
					</label>
				</div>
				<div class="form-group">
					<label for="mobilephone" class="col-sm-3 control-label">手机验证码：</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="mobilephonecode"
							placeholder="请输手机验证码..."
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</div>
					<a href="javascript:void(0)" class="mobilesubmit" id="send"
						onclick="updatePassword.low_sendSsm(this)"
						data-phone="${userBaseAccountInfo.mobilephone}">发送短信验证码</a> <a
						href="javascript:void(0)" class="mobilesubmit" id="send2"
						style="display: none;"></a> <span style="color: red"
						id="mobilephonecodeerror"></span>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-3 control-label">密码：</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="password"
							name="password" maxlength="20" placeholder="请输入密码">
					</div>
					<span style="color: red" id="passworderror"></span>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-3 control-label">密码：</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="password1"
							name="password1" maxlength="20" placeholder="请再次输入密码">
					</div>
					<span style="color: red" id="password1error"></span>
				</div>
				<div class="form-group">
					<div class="col-xs-6 col-md-offset-1">
						<button type="button" class="btn btn-primary" id="pswsubmit"
							onclick="updatePassword.low_updatepsw(this)">马上修改</button>
						<span style="color: red" id="pswsubmiterror"></span>
					</div>
				</div>
			</form>
			<!-- End Form -->
		</div>
	</div>
	<script type="text/javascript">
		var timer = null;
		var updatetimer = null;
		var updatePassword = {
				//发送短信
 				low_sendSsm:function(obj){
 					var time2 = getCookieValue("tellPhone") ? getCookieValue("tellPhone"):0;
  					if(time2 > 0){
 						tellTimer(60, "send", "send2", "updatePassword.low_sendSsm");
  					}else{
	 					var phone = $(obj).data("phone");
						clearTimeout(timer);
						timer = setTimeout(function(){
							$.ajax({
								type:"post",
								data:{"phone":phone},
								url:basePath+"/updatepsw/sendssm.action",
								success:function(data){
	 								var obj = $.parseJSON(data);
									if(obj.result == "success"){
										tellTimer(60, "send", "send2", "updatePassword.low_sendSsm");
									}
								}
							});
	 					},200);
   					}
				},
				low_updatepsw:function(obj){
					var phonecode = $("#mobilephonecode").val();
					var password = $("#password").val();
					var password1 = $("#password1").val();
					if(isEmpty(phonecode)){
						$("#mobilephonecodeerror").text("请输入验证码...");
						$("#mobilephonecode").focus();
						return false;
					}else{
						$("#mobilephonecodeerror").text("");
					}
					if(isEmpty(password)){
						$("#passworderror").text("请输入密码...");
						$("#password").focus();
						return false;
					}else{
						var reg =/^(?=.*[a-zA-Z])(?=.*[\d]).{6,16}$/;
						if(reg.test(password)){
							$("#passworderror").text("");
 						}else{
 							$("#passworderror").text("密码格式不正确 ! 必须包含 6-16位字母、数字 组合");
 							return false;
 						}
					}
					if(isEmpty(password1)){
						$("#password1error").text("请再输入密码...");
						$("#password1").focus();
						return false;
					}else{
						$("#password1error").text("");
					}
					
					if(!isEmpty(password) && !isEmpty(password1)){
						 if(password != password1){
							 $("#password1error").text("两次输入密码不一致...");
							 $("#passworderror").text("两次输入密码不一致...");
							 return false;
						 }else{
							 $("#password1error").text("");
							 $("#passworderror").text("");
						 }
					}
					$("#pswsubmit").removeAttr("onclick").text("提交中...");
					clearTimeout(updatetimer);
					updatetimer = setTimeout(function(){
						$.ajax({
							type:"post",
							data:{"phonecode":phonecode,"password":password},
							url:basePath+"/updatepassword.action",
							error:function(){$("#pswsubmit").attr("onclick","updatePassword.low_updatepsw(this)").text("马上修改");},
							success:function(data){
								$("#pswsubmit").attr("onclick","updatePassword.low_updatepsw(this)").text("马上修改");
								$("#pswsubmiterror").text("");
 								var obj = $.parseJSON(data);
								if(obj.result == "success"){
									alert("修改成功！请重新登录！");
									window.location.href=basePath+"/user/tologin.action";
 								}else if(obj.result == "ps_null"){
 									$("#pswsubmiterror").text("请输入手机验证码和密码");
 								}else if(obj.result == "code_error"){
 									$("#pswsubmiterror").text("请输入正确的手机验证码...");
 								}else if (obj.result == "logout"){
 									alert("session超时,请重新登录操作！");
 									window.location.href=basePath+"/user/tologin.action";
 								}else if (obj.result == "fail"){
 									$("#pswsubmiterror").text("保存失败！请重新操作！");
  								}
							} 
						});
 					},200);
				}
 		}
		
		function tellTimer(timer,btnId,tipsId,clickName){
			 timer = getCookieValue("tellPhone") ? getCookieValue("tellPhone"):timer;
				//显示短信发送框
				$("#"+btnId+"").hide();
				//隐藏正在发送框
				$("#"+tipsId+"").show();
				$("#"+tipsId+"").text((timer<=0)?"发送短信验证码":(""+(timer)+"秒后可以发送短信验证码"));
				var senderTime = setInterval(function(){
				 if(timer <= 0 ){
					 clearInterval(senderTime);
					//显示短信发送框
					 $("#"+btnId+"").show();
					 $("#"+tipsId+"").hide();
					 $("#"+btnId+"").text("发送短信验证码");
					 $("#"+btnId+"_error").text("");
					 //恢复发送短信点击事件
					 $("#"+btnId+"").attr("onclick",""+clickName+"(this)");
					 return false;
				 }else{
					 $("#"+tipsId+"").text(""+(timer--)+"秒后可以发送短信验证码");
					 editCookie("tellPhone",timer,timer+1);
				 }
			},1000);
		};

		//发送手机验证码时添加cookie
		function addCookie(name,value,expiresHours){ 
		    var cookieString=name+"="+escape(value); 
		    //判断是否设置过期时间,0代表关闭浏览器时失效
		    if(expiresHours>0){ 
		        var date=new Date(); 
		        date.setTime(date.getTime()+expiresHours*1000); 
		        cookieString=cookieString+";expires=" + date.toUTCString(); 
		    } 
		        document.cookie=cookieString; 
		}; 

		//修改cookie的值
		function editCookie(name,value,expiresHours){ 
		    var cookieString=name+"="+escape(value); 
		    if(expiresHours>0){ 
		      var date=new Date(); 
		      date.setTime(date.getTime()+expiresHours*1000); //单位是毫秒
		      cookieString=cookieString+";expires=" + date.toGMTString(); 
		    } 
		      document.cookie=cookieString; 
		}; 

		//根据名字获取cookie的值
		function getCookieValue(name){ 
		      var strCookie=document.cookie; 
		      var arrCookie=strCookie.split("; "); 
		      for(var i=0;i<arrCookie.length;i++){ 
		        var arr=arrCookie[i].split("="); 
		        if(arr[0]==name){
		          return unescape(arr[1]);
		          break;
		        } 
		      } 
		 };
	</script>
</body>
</html>