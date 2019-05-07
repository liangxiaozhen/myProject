<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情_干将网贷会员找回密码</title>
<link href="${basePath}/resources/resource/Css/common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/sea.css" rel="stylesheet" type="text/css">
</head>
<body>
 <%@include file="/WEB-INF/jsp/common/pwdUserHead.jsp" %>

	<!--layout start-->
<div class="w1000 shadow page149_1 mh580">
	<div class="topper fs_24">找回密码</div>
	<form action="" id="submitForm" name="submitForm" method="post">
    <div class="question">
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
        <!--密保问题找回密码step1-->
        <div id="step1">
        <form action="" method="post" class="nwd-formUi">
          <input type="hidden" name="baseid" id="baseid" value="${userfindpwdusersafe.baseid}">
          <div class="action">密保问题可用于找回登录密码、交易密码等操作</div>
          <table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <th class="leftside">问题一：</th>
                        <th class="rightside">
                            <!-- <div class="ques_1">我妈妈的生日是？</div> -->
                            <div class="ques_1">${userfindpwdusersafe.question1}</div>
                        </th>
                    </tr>
                    
                    <tr>
                        <th class="leftside">答案：</th>
                        <th class="rightside">
                            <input type="hidden" id="id0" value="2">
                              <input id="answer1" name="answer1" type="text" class="input_all">
                              <span class="prompt_1 error_1" id="answer1MSG" style="display:none"></span>
                        </th>
                    </tr>
                    <tr>
                        <th class="leftside">问题二：</th>
                        <th class="rightside">
                            <!-- <div class="ques_1">我妈妈的名字是？</div> -->
                            <div class="ques_2">${userfindpwdusersafe.question2}</div>
                        </th>
                    </tr>
                    
                    <tr>
                        <th class="leftside">答案：</th>
                        <th class="rightside">
                            <input type="hidden" id="id1" value="1">
                              <input id="answer2" name="answer2" type="text" class="input_all">
                              <span class="prompt_1 error_1" id="answer2MSG" style="display:none"></span>
                        </th>
                    </tr>
                    
                    <tr>
                        <th class="leftside">问题三：</th>
                        <th class="rightside">
                            <!-- <div class="ques_1">我妈妈的名字是？</div> -->
                            <div class="ques_3">${userfindpwdusersafe.question3}</div>
                        </th>
                    </tr>
                    
                    <tr>
                        <th class="leftside">答案：</th>
                        <th class="rightside">
                            <input type="hidden" id="id1" value="1">
                              <input id="answer3" name="answer3" type="text" class="input_all">
                              <span class="prompt_1 error_1" id="answer3MSG" style="display:none"></span>
                        </th>
                    </tr>
                    
                    <tr id="verification" style="display: none;">
                        <th class="leftside">验证码：</th>
                        <th class="rightside">
                            <input maxlength="6" type="text" name="imgCode" id="imgCode" class="input_all" style="width:140px">
                            <a id="refush" href="javascript:void(0);" title="换一张">
                                <img src="/validatecode/refresh.htm?date=new Date()" alt="换一张" name="code" width="90" height="27" id="code" class="ml_10">
                            </a>
                            <br>
                            <span class="prompt_1 error_1" id="imgMSG" style="display:none"><i></i></span>
                        </th>
                    </tr>
                    
                    <tr>
                      <th></th>
                       <th class="rightside">
                        <button class="btn special01" type="button" onclick="validate_question();">下一步，重置密码</button>
                          <a href="${basePath}/findpwd/pwdtypeList.action" class="blue">选择其它找回方式</a>
                      </th>
                   </tr>
                 </tbody>
            </table>
          </form><!--密保问题找回密码step1-->
          </div>
          <div id="step2" style="display:none;">
	          <div class="look_step2">
	            <table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
	              <tbody>
	                  <tr>
	                      <th class="leftside">用户名：</th>
	                      <th id="loginname" class="rightside">
	                      	${userfindpwduserbase.loginname}
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
          </div>
        </div>
        <!-- 修改密码成功 -->
        <div id="step3" style="display:none;">
          <div class="look_step2 fs_26 lh_32 txcenter three" >
              <i class="ico_all size48 img_true48 true48 mr_10"></i>密码重置成功，请牢记新的登录密码！
              <p class="mt_30"><a class="btn btnSize_2 btn_blue" href="${basePath}/user/tologin.action">登录</a></p>
          </div>
        </div>
    </div>
    </form>
        </div>
      </form>
</div>
<!--layout end-->

<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	
	var flag = false;//答案的flag
	var pwd_flag = false;//密码的flag
	//验证答案是否填写
	$(function(){
		//答案1
		$("#answer1").blur(function(){
			var answer1 = $("#answer1").val();
			if(answer1){
				flag = true;
				$("#answer1MSG").html("");
			}else{
				flag = false;
				$("#answer1MSG").css("display","block");
				$("#answer1MSG").html("<i></i>答案不能为空");
			}
		});
		//答案2
		$("#answer2").blur(function(){
			var answer2 = $("#answer2").val();
			if(answer2){
				flag = true;
				$("#answer2MSG").html("");
			}else{
				flag = false;
				$("#answer2MSG").css("display","block");
				$("#answer2MSG").html("<i></i>答案不能为空");
			}
		});
		//答案3
		$("#answer3").blur(function(){
			var answer2 = $("#answer3").val();
			if(answer2){
				flag = true;
				$("#answer3MSG").html("");
			}else{
				flag = false;
				$("#answer3MSG").css("display","block");
				$("#answer3MSG").html("<i></i>答案不能为空");
			}
		});
		
		//验证新密码
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
	
	//下一步，重置密码
	function validate_question(){
		//答案1
		var answer1 = encodeURI($("#answer1").val().trim(),"UTF-8");
		if(answer1==""){
			$("#answer1MSG").css("display","block");
			$("#answer1MSG").html("<i></i>答案不能为空");
			flag = false;
		}
		//答案2
		var answer2 = encodeURI($("#answer2").val().trim(),"UTF-8");
		if(answer2 == ""){
			$("#answer2MSG").css("display","block");
			$("#answer2MSG").html("<i></i>答案不能为空");
			flag = false;
		}
		//答案3
		var answer3 = encodeURI($("#answer3").val().trim(),"UTF-8");
		if(answer3==""){
			$("#answer3MSG").css("display","block");
			$("#answer3MSG").html("<i></i>答案不能为空");
			flag = false;
		}
		if(!flag){
			return false;
		}
		//用户的baseid
		var baseid = $("#baseid").val();
		$.ajax({
			url:"${basePath}/findpwd/validateQuestion.action",
			type:"post",
			dataType:"json",
			data:{
				"answer1":answer1,
				"answer2":answer2,
				"answer3":answer3,
				"baseid":baseid
			},
			success:function(data){
				if(data["result"]=="success"){
					$(".steps_3").find("ul:eq(1)").addClass("active");
					$("#step1").css("display","none");
					$("#step2").css("display","block");
				}else{
					$("#step1").css("display","block");
					$("#step2").css("display","none");
				}
				if(data["result1"]=="error1"){
					$("#answer1MSG").css("display","block");
					$("#answer1MSG").html('<font color="red">问题1答案错误</font>');
				}else{
					$("#answer1MSG").css("display","none");
				}
				if(data["result2"]=="error2"){
					$("#answer2MSG").css("display","block");
					$("#answer2MSG").html('<font color="red">问题2答案错误</font>');
				}else{
					$("#answer2MSG").css("display","none");
				}
				if(data["result3"]=="error3"){
					$("#answer3MSG").css("display","block");
					$("#answer3MSG").html('<font color="red">问题3答案错误</font>');
				}else{
					$("#answer3MSG").css("display","none");
				}
			}
			
		});
	}
	
	//下一步
	function reset_password(){
		//var loginname = encodeURI($("#loginname").text(),"UTF-8");//用户名
		var id = $("#userId").val();
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
					$("#step2").css("display","none");
					$("#step3").css("display","block");
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