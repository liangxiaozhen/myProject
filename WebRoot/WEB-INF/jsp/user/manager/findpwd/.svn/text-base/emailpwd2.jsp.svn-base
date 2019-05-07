<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
 <!DOCTYPE html>
<html>
<head>
 <title>账户详情_干将网贷会员找回密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">var basePath = "${basePath}"</script>
<link href="${basePath}/resources/resource/Css/common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/sea.css" rel="stylesheet" type="text/css">
 <style type="text/css">
  .steps_3 ul {
    width: 24%;
}
</style>
</head>
<body>
 <%@include file="/WEB-INF/jsp/common/pwdUserHead.jsp" %>
 <!--layout start-->
<div class="w1000 shadow page149_1 mh580">
	<div class="topper fs_24">邮箱找回</div>
     <div class="question">
      <div class="steps steps_3">
            <ul class="active">
                <li class="sz"><span>1</span></li>
                <li class="sm">1.输入邮箱</li>
            </ul>
            <ul class="active">
                <li class="sz"><span>2</span></li>
                <li class="sm">2.邮箱验证</li>
            </ul>
            <ul class="active">
                <li class="sz"><span>3</span></li>
                <li class="sm">3.重置密码</li>
            </ul>
            <ul>
                <li class="sz"><span>4</span></li>
                <li class="sm">完成</li>
            </ul>
        </div>
                
         <!--3-->
          <div class="look_step2">
            <table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
              <tbody>
                  <tr>
                      <th class="leftside">用户名：</th>
                      <th class="rightside">
                      	<span id="loginname">${ubai.loginname}</span>
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
                     <input type="hidden" id="userId" value="${ubai.uasi.id}">
                     <input type="button" value="下一步" class="btn special01" onclick="reset_password();">
                    </th>
                 </tr>
               </tbody>
          </table>
          </div>
        </div>
        
        <!-- 修改密码成功 -->
          <div class="look_step2 fs_26 lh_32 txcenter" style="display:none">
              <i class="ico_all size48 img_true48 true48 mr_10"></i>密码重置成功，请牢记新的登录密码！
              <p class="mt_30"><a class="btn btnSize_2 btn_blue" href="${basePath}/user/tologin.action">登录</a></p>
          </div>
          
    </div>
 <!--layout end-->

<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/findpwd.js"></script>
<script type="text/javascript">
var pwd_flag = false;
$(function(){
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
	var id = $("#userId").val();
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
				$(".steps_3").find("ul:eq(3)").addClass("active");
				$(".look_step2").css("display","none");
				$(".look_step2.txcenter").css("display","block");
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
</script>
</body>
</html>