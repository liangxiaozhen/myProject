<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情_干将网贷会员找回密码</title>
<script type="text/javascript">var basePath = "${basePath}"</script>
<link href="${basePath}/resources/resource/Css/common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/sea.css" rel="stylesheet" type="text/css">
</head>
<body>
		<!--layout start-->
<div class="w1000 shadow page149_1 mh580">
  <div class="topper fs_24">手机找回密码</div>
  <form action="" id="submitForm" name="submitForm" method="post">
    <div class="question" style="width:700px;">
       <!-- 验证码 -->
       <div class="steps steps_3">
            <ul class="active">
                <li class="sz"><span>1</span></li>
                <li class="sm">验证身份</li>
            </ul>
            <ul>
                <li class="sz"><span>2</span></li>
                <li class="sm">重置密码</li>
            </ul>
            <ul>
                <li class="sz"><span>3</span></li>
                <li class="sm">完成</li>
            </ul>
        </div>
        <div class="look_step2 one">
          <table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
                  <tbody>
                      <tr>
                          <th class="leftside">手机号</th>
                          <th class="rightside">
                          		<span id="sid"></span>
                                <input type="hidden" class="input_all" id="phone" name="phone" value="${userfindpwduserbase.mobilephone}" readonly="readonly"/><!-- onblur="check_phone();" -->
                                <br/>
                              <span class="prompt_1 error_1" id="phoneMSG" style="display:none"><i></i></span>
                          </th>
                      </tr>
                      <tr>
                      <th class="leftside">验证码</th>
                      <th class="rightside">
					  	<input type="text" class="input_all mr_5" style="width: 130px;" id="checkCode" maxlength="6" onblur="check_code();">
					  	<input type="button" class="yzm" id="click_get" value="点击获取" style="display: inline;">
					  	<span class="prompt_1 error_1" id="codeMSG" style="display:none"><i></i></span>
                        <!-- <div id="div_geetest_lib"></div>
		                <div id="div_id_embed"></div>
		                <input type="text" name="channel" id="channel" class="input_all" autocomplete="off">
		                <input type="hidden" id="imgcode" name="imgcode" />
		                <span class="prompt_1 error_1" id="imgMSG" style="display:none"><i></i></span> -->
                      </th>
                      </tr>
                      <tr>
                        <th></th>
                         <th class="rightside">
                            <p class="btn special01"><span id="phone_reset_password">下一步，重置密码</span></p>
                        </th>
                     </tr>
              </tbody>
          </table>
        </div>
        <!-- 修改密码 -->
        <div class="look_step2 two" style="display:none;">
            <table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <th class="leftside">用户名：</th>
                        <th class="rightside">
                        	<span id="loginname">${userfindpwduserbase.loginname}</span>
                        </th>
                    </tr>
                    <tr>
                        <th class="leftside">新的登录密码：</th>
                        <th class="rightside">
	                        <input type="password" name="pwd" id="pwd" class="input_all" autocomplete="off">
	                        <span style="display:none" class="prompt prompt-red" id="pwdMSG"></span>
                        </th>
                    </tr>

                    <tr>
                        <th class="leftside">确认新的登录密码：</th>
                        <th class="rightside">
                        <input type="password" name="repwd" id="repwd" class="input_all" autocomplete="off">
                        <span style="display:none" class="prompt prompt-red" id="repwdMSG"></span>
                        </th>
                    </tr>
                    <tr>
                        <th></th>
                         <th class="rightside">
                         <input type="hidden" id="userId" value="${userfindpwdusersafe.id}">
                          <input type="button" value="下一步" class="btn special01" onclick="reset_password();">
                        </th>
                   </tr>
               </tbody>
          </table>
        </div>
        <!-- 修改密码成功 -->
          <div class="look_step2 fs_26 lh_32 txcenter three" style="display:none;">
              <i class="ico_all size48 img_true48 true48 mr_10"></i>密码重置成功，请牢记新的登录密码！
              <p class="mt_30"><a class="btn btnSize_2 btn_blue" href="${basePath}/user/tologin.action">登录</a></p>
          </div>
    </div>
    </form>
</div>
<!--layout end-->

<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/findpwd.js"></script>
<script type="text/javascript">
	
	var flag = false;
	var b = false;
	var pwd_flag = false;
	//检查电话号码是否符合规范
	/* function check_phone(){
		$('#phoneMSG').html("");
		var phoneNumber = $("#phone").val();
		//alert("电话号码： "+phoneNumber);
		if(phoneNumber){
			var reg = /^((1[3-8][0-9])+\d{8})$/;
			//alert(reg.test(phoneNumber));
			if(reg.test(phoneNumber)){
				flag = true
				$('#phoneMSG').html("");
			}else{
				flag = false;
				$('#phoneMSG').css("display", "");
				$('#phoneMSG').removeClass().addClass(
						"prompt_1 error_1").html("<i></i>手机号码格式错误");
			}
		}else{
			flag = false;
			$('#phoneMSG').css("display", "");
			$('#phoneMSG').removeClass().addClass(
					"prompt_1 error_1").html("<i></i>手机号码不能为空");
		}
	} */
	
	//检测验证码
	function check_code(){
		var codeValue = $("#checkCode").val().trim();
		if(codeValue){
			if(codeValue.length != 6){
				flag = false;
				$('#codeMSG').css("display", "");
				$('#codeMSG').removeClass().addClass(
						"prompt_1 error_1").html("<i></i>请填入6位有效验证码");
			}else{
				flag = true;
				$('#codeMSG').html("");
			}
		}else{
			flag = false;
			$('#codeMSG').css("display", "");
			$('#codeMSG').removeClass().addClass(
					"prompt_1 error_1").html("<i></i>验证码不能为空");
		}
	}
	
	$(function(){
		var phone = $("#phone").val();
		//将手机号屏蔽后放入span元素中
		var s_phone = phone.substring(0,3)+"****"+phone.substring(7);
		$("#sid").text(s_phone);
		//点击获取验证码
		$("#click_get").click(function(){
			var o = $(this);
			//alert("phone: "+phone)
			/*if(phone==""){
				$('#phoneMSG').css("display", "");
				$('#phoneMSG').removeClass().addClass(
						"prompt_1 error_1").html("<i></i>请填写手机号码");
				return false;
			} */
			//alert("phone: "+phone);
			/* if(!flag){
				return false;
			} */
			$.ajax({
				url:"${basePath}/findpwd/clickGet.action",
				type:"post",
				dataType:"json",
				data:{
					"phone":phone
				},
				success:function(data){
					$("#phoneMSG").html("");
					if(data["result"]=="success"){
						b = true;
						$('#codeMSG').html("");
						alert("验证码已发出");
						//这里需要写个定时器
						//定时器!点击完获取验证码之后进入倒计时
						settime(o);
					}
					if(data["result"]=="fail"){
						alert("验证码发送失败");
					}
					if(data["result"]=="phone_null"){
						//手机号码为空
						$("#phoneMSG").css("display","block");
						$("#phoneMSG").removeClass().addClass("prompt_1 error_1")
						.html("<i></i>" + "请填写手机号码");
					}
					if(data["result"]=="phone_error"){
						//手机号码格式错误
						$("#phoneMSG").css("display","block");
						$("#phoneMSG").removeClass().addClass("prompt_1 error_1")
						.html("<i></i>" + "手机号码格式错误");
					}
					if(data["result"]=="unRegistered"){
						//手机号码格式错误
						$("#phoneMSG").css("display","block");
						$("#phoneMSG").removeClass().addClass("prompt_1 error_1")
						.html("<i></i>" + "用户未注册该手机号");
					}
					if(data["result"]=="unVerified"){
						//手机号码格式错误
						$("#phoneMSG").css("display","block");
						$("#phoneMSG").removeClass().addClass("prompt_1 error_1")
						.html("<i></i>" + "手机号码未验证");
					}
				}
			});
		});
		
		//手机找回密码 下一步，重置密码
		$("#phone_reset_password").click(function(){
			//获取用户输入的手机号，以防用户又改掉了
			var phone = $("#phone").val();
			if(phone==""){
				$('#phoneMSG').css("display", "");
				$('#phoneMSG').removeClass().addClass(
						"prompt_1 error_1").html("<i></i>手机号码不能为空");
				return false;
			}
			//获取用户输入的验证码
			var code = $("#checkCode").val();
			if(code==""){
				$('#codeMSG').css("display", "");
				$('#codeMSG').removeClass().addClass(
						"prompt_1 error_1").html("<i></i>验证码不能为空");
				return false;
			}
			if(!flag){
				return false;
			}
			if(!b){
				$("#codeMSG").css("display","block");
				$("#codeMSG").removeClass().addClass("prompt_1 error_1")
				.html("<i></i>" + "请点击获取验证码");
				return false;
			}else{
				$("#codeMSG").html("");
			}
			$.ajax({
				url:"${basePath}/findpwd/phoneResetPassword.action",
				type:"post",
				dataType:"json",
				data:{
					"phone":phone,
					"code":code
				},
				success:function(data){
					$("#phoneMSG").html("");
					$("#codeMSG").html("");
					if(data["result"]=="phone_error"){
						//手机号码错误
						$("#phoneMSG").css("display","block");
						$("#phoneMSG").removeClass().addClass("prompt_1 error_1")
						.html("<i></i>" + "手机号码错误");
					}
					if(data["result"]=="code_error"){
						//验证码错误
						$("#codeMSG").css("display","block");
						$("#codeMSG").removeClass().addClass("prompt_1 error_1")
						.html("<i></i>" + "验证码输入错误");
					}
					if(data["result"]=="success"){
						//可以重置密码了
						$(".steps_3").find("ul:eq(1)").addClass("active");
						$(".look_step2.one").css("display","none");
						$(".look_step2.two").css("display","block");
					}
					if(data["result"]=="session_error"){
						//session已过期
						alert("您的操作已超时");
					}
				}
			});
		});
		
		//验证新的登录密码
		$("#pwd").blur(function(){
			var pwd = $("#pwd").val();
			var pattern = /^(?!\D+$)(?![^a-zA-Z]+$)\S{8,20}$/;
			if(pwd==""){
				pwd_flag = false;
				$('#pwdMSG').css("display", "");
				$('#pwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>密码不能为空");
			}else if(pwd.length<8 || pwd.length>20){//密码长度为8~20位
				pwd_flag = false;
				$('#pwdMSG').css("display", "");
				$('#pwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>密码长度为8～20位字符");
			}else if(!pwd.match(pattern)){
				pwd_flag = false;
				$('#pwdMSG').css("display", "");
				$('#pwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>必须包含数字和字母");
			}else if(!pwd.match(/^\S+$/)){
				pwd_flag = false;
				$('#pwdMSG').css("display", "");
				$('#pwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>密码不能包含空格");
			}else{
				pwd_flag = true;
				$('#pwdMSG').html("");
			}
		});
		
		//验证再确认密码
		$("#repwd").blur(function(){
			//获取用户输入的密码的值
			var pwdVal = $("#pwd").val();
			var repwdVal = $("#repwd").val();
			//密码只能包含中文，数字，符号，表达式
			var pattern = "([ `~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？])|(^[a-zA-Z0-9-]+$)";
			if(pwdVal!=repwdVal){
				pwd_flag = false;
				$('#repwdMSG').css("display", "");
				$('#repwdMSG').removeClass().addClass(
						"prompt_1 error_1").html("<i></i>两次输入的密码不一致");
			}else{
				if(repwdVal == ""){
					pwd_flag = false;
					$('#repwdMSG').css("display", "");
					$('#repwdMSG').removeClass().addClass(
							"prompt_1 error_1").html("<i></i>重复密码不能为空");
				}else if(repwdVal.length<8 || repwdVal.length>20){
					pwd_flag = false;
					$('#repwdMSG').css("display", "");
					$('#repwdMSG').removeClass().addClass(
							"prompt_1 error_1").html(
							"<i></i>密码长度为8~20位字符");
				}else if(!repwdVal.match(pattern)){
					pwd_flag = false;
					$('#repwdMSG').css("display", "");
					$('#repwdMSG').removeClass().addClass(
							"prompt_1 error_1").html(
							"<i></i>密码只能包含英文、数字、符号");
				}else if(!repwdVal.match(/^\S+$/)){
					//密码不能包含空格
					pwd_flag = false;
					$('#repwdMSG').css("display", "");
					$('#repwdMSG').removeClass().addClass(
							"prompt_1 error_1").html("<i></i>密码不能包含空格");
				}else{
					pwd_flag = true;
					$('#repwdMSG').html("");
				}
			}
		});
		
	});
	
	//下一步
	function reset_password(){
		//var loginname = encodeURI($("#loginname").text(),"UTF-8");//用户名
		var id = /* "${userfindpwdusersafe.id}" */$("#userId").val();
		//alert("id: "+id);
		var pwd = encodeURI($("#pwd").val(),"UTF-8");//新密码
		var repwd = encodeURI($("#repwd").val(),"UTF-8");//确认新密码
		if(pwd==""){
			$('#pwdMSG').css("display", "");
			$('#pwdMSG').removeClass().addClass("prompt_1 error_1").html(
					"<i></i>密码不能为空");
			pwd_flag = false;
		}
		if(repwd==""){
			$('#repwdMSG').css("display", "");
			$('#repwdMSG').removeClass().addClass(
					"prompt_1 error_1").html("<i></i>重复密码不能为空");
			pwd_flag = false;
		}
		if(!pwd_flag){
			return false;
		}
		$.ajax({
			url:"${basePath}/findpwd/resetPassword.action",
			dataType:"json",
			type:"post",
			data:{
				//"loginname":loginname,
				"id":id,
				"pwd":pwd,
				"repwd":repwd
			},
			success:function(data){
				$('#pwdMSG').html("");
				$('#repwdMSG').html("");
				if(data["result"]=="success"){
					//alert("重置密码成功");
					$(".steps_3").find("ul:eq(2)").addClass("active");
					$(".look_step2.one").css("display","none");
					$(".look_step2.two").css("display","none");
					$(".look_step2.three").css("display","block");
				}
				// 密码验证结果
				if (data['pwdErrMsg'] != null) {
					$('#pwdMSG').css("display", "");
					$('#pwdMSG').removeClass().addClass("prompt_1 error_1")
							.html("<i></i>" + data['pwdErrMsg']);
				}
				// 重复密码验证结果
				if (data['repwdErrMsg'] != null) {
					$('#repwdMSG').css("display", "");
					$('#repwdMSG').removeClass().addClass("prompt_1 error_1")
							.html("<i></i>" + data['repwdErrMsg']);
				}
			}
		});
	}
	
	//1分钟的定时器（针对验证码）
	var wait=60;
	function settime(o) {
		var c = null;
		//alert("wait: "+wait+" ,:  "+o.val());
		if (wait == 0) {
			o.prop("disabled","");   
			o.val("点击获取");
			wait = 60;
			clearTimeout(c);
		} else { 
			o.prop("disabled", "disabled");
			o.val(wait + "秒");
			wait--;
			c = setTimeout(function() {
				settime(o);
			},1000)
		}
	}
	
	
</script>
</body>
</html>