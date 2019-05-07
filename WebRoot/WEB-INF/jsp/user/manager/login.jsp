<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var basePath = "${basePath}"
</script>
<link rel="stylesheet" type="text/css" href="${basePath}/resources/resource/Css/new-login.css">
<link rel="stylesheet" type="text/css" href="${basePath}/resources/resource/Css/style_https.1.0.3.css">
<link href="${basePath}/resources/resource/Css/common2.css" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/findpwd.js"></script>
<script src="https://static.geetest.com/static/tools/gt.js"></script>
	
<title>登录页面</title>
<script type="text/javascript"
	src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var dd = $("#yideng").html().trim();
		if (dd != "null") {
			var register = "账户异地被登入，" + dd;
			alert(register);
		}
	}
</script>
<script type="text/javascript" src="<%=basePath%>/static/js/jquery.tips.js"></script>
</head>

<body>
	<div class="newLogin">
		<div class="bg-white newLogin-head">
			<div class="wid-1000 clearfix">
				<div class="newLogin-head-left fl" style="margin-top: 10px;">
					<a href="http://test.ganjiangps.com/" target="_self"><img
						src="<%=request.getContextPath()%>/resources/Images/logo.png">&nbsp;&nbsp;&nbsp;<span
						style="font-size: 20px; color: #999999; font-weight: bold;">欢迎登陆</span></a>
				</div>
				<div class="newLogin-head-right fr">
					<span class="fs_14 fc_9">没有账号? </span>
					<%-- <a href="${basePath}/register/userBaseInfo/register.action" target="_self">免费注册</a> --%>
					<a href="${basePath}/register/userBaseInfo/register.action"
						target="_self">免费注册</a>
				</div>
			</div>
		</div>
		<div class="bg-white newLogin-banner">
			<div class="wid-1000 clearfix newLogin-banner-content">
				<a href="" target="_blank" class="fl ad_loginCon"></a>
				<div class="login-module fr pl_20 pr_20 pt_20">
					<div class="login-module-head clearfix pb_5 tab_u">
						<div class="fl fs_18 fc_3">登录干将网贷</div>
						<div class="fr dongtai">
							<a href="javascript:;" class="fc_grey fs_14 SwapTabBtn"
								data-msg="phoneLog" title="手机动态密码登录">手机动态密码登录</a>
						</div>
						<div style="display: none;" id="yideng"><%=request.getAttribute("aabb")%></div>
					</div>
					<div class="login-module-content">
						<!--手机登录 start-->
						<div class="clearfix nr form">
							<form action="" class="nwd-formUi" method="post" id="loginForm">
								<input type="hidden" name="codeType" value="0" id="codeType" />
								<input type="hidden" name="geetestCode" value="geetest" />
								<table border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td class="info-area">
											<div class="error fs_14 fc_6 load_bc" id="errorMsg"
												style="display: none"></div>
										</td>
									</tr>
									<tr>
										<td class="pt_10 pb_20"><label class="touzi01 long">
												<input type="text" name="loginname" id="username"
												value="jiangxy" class="input_1 pos_u gray_border wid_258" />
												<span id="usernameShow">请输入手机号/邮箱/用户名</span>
										</label></td>
									</tr>
									<tr>
										<td class="pb_15"><label class="touzi01 long"> <input
												type="password" name="password" id="pwd" value="123456789a"
												class="input_1 pos_p gray_border wid_258" /> <span
												id="pwdShow">请输入密码</span>
										</label></td>
									</tr>
									<tr class="hidden " id="validate">
										<td>
											<!--<label class="touzi01 short">
                                            <input type="text" name="imgCode" id="imgCode" maxlength="4" class="input_1 pos_i red gray_border" />
                                            <span>图形验证码</span>
                                        </label>
                                        <a id="refush" href="javascript:void(0);" title="换一张" class="blue ml_5">
                                        <img src="/Public/Picture/refresh.htm" name="code" id="code" width="90" height="38" title="点击图片刷新验证码" alt="图形验证码" class="ml_5">
                                        </a>-->
											<div id="div_geetest_lib"></div>
											<div id="div_id_embed"></div> <input class="channel"
											type="hidden" name="channel" value="3"> <input
											type="hidden" id="imgCode" name="imgCode" />
										</td>
									</tr>
									<tr class="hidden" id="validatePhone">
										<td class="pb_15"><label class="touzi01 short"> <input
												type="text" class="input_1 pos_t gray_border wid_158"
												name="phoneCode" id="phoneCode" maxlength="6" /> <span>手机验证码</span>
										</label> <input type="button" class="yzm wid_100" value="点击获取"
											id="phoneCodeBut"> <input type="button"
											class="yzm ml_5" value="语音验证码" id="yybtn"
											style="display: none"> <input type="button"
											class="yzm ml_5" value="语音验证码" id="yybtn2"
											style="display: none"></td>
									</tr>
									<tr>
										<td class="pb_15">
											<div class="user clearfix fs_14 fc_3">
												<p class="fl">
													<input type="checkbox" checked id="rem" class="mr_5" /><label
														for="rem" class="fc_9">记住用户名</label>
												</p>
												<a href="${basePath}/user/findpwd.action" class="fr">忘记密码？</a>
											</div>
										</td>
									</tr>
									<tr>
										<td class="pb_15"><a href="javascript:void(0)"
											onclick="gjLogin.login(this);"> <input type="button"
												class="submit wid_310" value="登录" id="userLoginBtn" />
										</a></td>
									</tr>
									<tr>
										<td class="fc_grey pb_5">使用合作网站账户登录：</td>

									</tr>
									<tr>
										<td class="pb_15">
											<div class="boder-bt w258">
												<span class="bt-left mr_20" id="qqLogin"><i
													class="qq com icon16"></i> <a
													href="${basePath }/qq/Lgtoqq.action" title="QQ登录"
													class="fc_grey va-m"> <i style="font-family: Arial">QQ</i>
												</a> </span> <span id="weiboLogin"><i class="sina com icon16"></i>
													<a href="${basePath }/sian/toweibo.action" title="微博登录"
													class="fc_grey va-m">微博</a> </span>
											</div>

										</td>
									</tr>
								</table>
							</form>
						</div>
						<!--手机登录 end-->

						<!--2-->
						<div class="clearfix nr hidden form">
							<form action="" id="phoneForm" method="post">
								<table border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td class="info-area">
											<div class="error fs_14 fc_6 load_bc" id="quickLoginErrorMsg"
												style="display: none;"></div>
										</td>
									</tr>
									<tr>
										<td class="pt_10 pb_20"><label class="touzi01 long">
												<input type="text" id="mobile" name="mobile" onblur="chkvalue(this)"
												class="input_1 pos_u gray_border num wid_258" maxLength="11" />
												<span>请输入手机号</span>
										</label></td>
									</tr>
									
									<!-- 验证码 -->
									<tr id="bindAccountImgCode">
		                             <th class="leftside"></th>
										<th class="rightside" style="position: relative; left: -310px; top: -4px;">
											<div id="div_embed"></div> 
											<input type="hidden" id="imgcode"name="imgcode" /> 
											<span id="imgCodeMSG" style="display: none;"></span>
										</th>
									</tr>
							       				
							       						        
									<tr>
										<td class="pt_10 pb_20">
											<label class="touzi01 long"> 
											<input
													type="text" class="input_1 pos_t gray_border wid_158"
													id="smCode" onKeyUp="value=value.replace(/\W/g,'')"
													name="smCode" maxLength="6" />
										 	<span>手机验证码</span>
											</label> 
									    	<input type="button" class="yzm wid_100"  id="btnSubmit1" onclick="severCheck()" value="点击获取">
										    <input type="button" class="yzm wid_100" id="yybtn" value="重新获取" style="display: none">
											    <!-- <input type="button" class="yzm2" id="countDown" style=""> -->
											    <!-- <input type="button" id="btn" value="获取验证码" onclick="settime(this)" style="width:120px;height:40px;background-color: pink;"/> -->
											    <!-- <span id="fucodeMSG" style="display: none"></span> -->
											<!-- <input type="submit" class="yzm ml_5" value="重新获取">
	                                        <input type="submit" class="yzm2 ml_5" value="倒计时用的按钮"> -->
										</td>
									</tr>
									<tr>
										<td class="pb_15">
											<div class="user clearfix fs_14 fc_3">
												<p class="fl">
													<input type="checkbox" checked id="remPhone" class="mr_5" /><label
														for="rem" class="fc_9">记住用户名</label>
												</p>
												<a href="${basePath}/user/findpwd.action" class="fr">忘记密码？</a>
											</div>
										</td>
									</tr>
									<tr>
										<td class="pb_15"><input type="button" id="msg1"
										    onclick="severCheck1()" value="登录" class="submit wid_310 special02" />
										   <%-- <input type="hidden" id="msg" value="${msg}" > --%>
										  <%--  <input type="button" id="regSdMsgCodeRst" value="${regSdMsgCodeRst}" class="btn2" /> --%>
										   </td>
									</tr>
									<tr>
										<td class="fc_grey pb_5">使用合作网站账户登录：</td>
									</tr>
									<tr>
										<td class="pb_15">
											<div class="clearfix boder-bt2 w258">
												<span id="qqLogin1" class="mr_20"> <i
													class="qq com icon16"></i> <a
													<%-- href="${basePath }/qq/toqq.action" title="QQ登录" --%>  //
													href="${basePath }/qq/Lgtoqq.action" title="QQ登录"   
													class="fc_grey va-m"> <i style="font-family: Arial">QQ</i>
												</a>
												</span> <span id="weiboLogin1" class="in-block"> <i
													class="sina com icon16"></i> <a
													href="${basePath }/sian/toweibo.action" title="微博登录"
													class="fc_grey va-m">微博</a>
												</span>
											</div>

										</td>
									</tr>
								</table>
							</form>
						</div>
						<!--2 end-->
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<div class="bg-grey">
			<div class="wid-1000 newLogin-foot pt_15">
				<div class="mar_l20 txt_center">
					<div class="fs_12 fc_6 Numfont txt_center pt_15 pb_10">
						Copyright © 2016 干将网贷 版权所有；杜绝借款犯罪，倡导合法借贷，信守借款合约</div>
				</div>
			</div>
		</div>
	</div>
	<!--foot end-->
</body>


<script type="text/javascript"
	src="${basePath}/resources/resource/JxUserScript/userlogin.js"></script>
<script type="text/javascript"
	src="${basePath}/resources/resource/JxUserScript/useryzm.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//changeVerifyCode();
		var name=$("#mobile");
		/*var pwd=$("#pwd2");*/
		//图形验证码
		var code=$("#bindAccountImgCode");
		//短信验证码
		var codedx=$("#smCode");
		//alert(codex);
		name.focus(function(){
			name.css("background-color","#FFFFCC");
		    
		  });
		/*pwd.focus(function(){
			pwd.css("background-color","#FFFFCC");
			  });*/
		code.focus(function(){
			code.css("background-color","#FFFFCC");
			  });
	});

	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			$("#btnSubmit").trigger("click");
		}
	});
		
		
			function chkvalue(txt){
					var id=txt.id;
					var name=txt.name;
					$("#"+id).css("background-color","#FFFFFF");
					 /*if(txt.value=="") {
						 $("#"+id).tips({
								side : 2,
								msg : name+'不能为空',
								bg : 'red',
								time : 3
							});
					 }*/
			}
			//客户端校验
			function check() {
				var name=$("#mobile");
				var pwd=$("#pwd2");
				var code=$("#bindAccountImgCode");
				//短信验证码
				var codedx=$("#smCode");
	 			if (name.val() == "" || name.val().length != 11) {
	 				name.tips({
						side : 2,
						msg : '请输入正确的手机号',
						/* bg : '#AE81FF', */
						bg : '#FF3300',
						time : 3
					});
	 				name.focus();
					return false;
					
				} else {
					/*name.val(jQuery.trim(name.val()));
					if (pwd.val() == "") {
						pwd.tips({
							side : 2,
							msg : '密码不得为空',
							 bg : '#AE81FF', 
							bg : '#FF3300',
							time : 3
						});
						pwd.focus();
						return false;
					}
					  else {
						
						pwd.val(jQuery.trim(pwd.val()));
						if (code.val() == "") {
							code.tips({
								side :2,
								msg : '验证码不能为空',
								 bg : '#AE81FF', 
								bg : '#FF3300',
								time : 2
							});

							code.focus();
							return false;
						}else if(code.val().length!=5){
							code.tips({
								side :2,
								msg : '验证码长度为5',
								 bg : '#AE81FF', 
								bg : '#FF3300',
								time : 2
							});

							code.focus();
							return false;
							
						}else{
							code.val(jQuery.trim(code.val()));
						}
					} */ 
				}
				$("#loginbox").tips({
					side : 1,
					msg : '正在登录 , 请稍后 ...',
					bg : '#68B500',
					time : 10
				});
				return true;
			}
	
	
	function severCheck(){
		if(check()==true){
			var clicktag = 0;  
	        $('#btnSubmit1').click(function () {  
	            if (clicktag == 0) {
	                clicktag = 1;  
	                $(this).disabled=false;  
	                setTimeout(function () { clicktag = 0 }, 5000);  
	            }  
	        });
			var codeID = $("#bindAccountImgCode");
			var codeVal = codeID.val();
			
			 var imgcodeVal = $('#imgcode').val();
			 if(imgcodeVal == "success"){
			 }else{
				 $("#imgCodeMSG").css("display","block");
				 $("#imgCodeMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请拖动滑块完成验证！");
				 return false;
			 }
			//提交前先异步检查验证码是否输入正确     // 6/10修改
			 /* var verifyUrl = "${pageContext.request.contextPath}/servlet/VerifyServlet?verifyCode="+codeVal; */
			 //alert(verifyUrl);
			 /* $.ajax({
			  type:"GET",
			  url:verifyUrl,
			  success:function(returnData){
				  //alert(returnData);
					  if(returnData=="Y") {
						  	codeID.tips({
									side :1,
									msg : '验证码不正确',
									bg : '#FF3300',
									time : 3
								});
						  	changeCode();
						  	codeID.focus();
							return false;
					  }else { */
						    var loginname = $("#mobile").val();
						  	var codedx=$("#smCode").val();
							var fwurl = "<%=basePath%>/Mobilelogin.action";
							var fwu =  "<%=basePath%>/yzm.action";
							 $.post(fwurl,{loginname:loginname},function(data){
								if(data=="fail"){
									$("#mobile").tips({
										side : 1,
										msg : '该手机号码未被注册',
										bg : '#FF5080',
										time : 15
									});
									$("#mobile").focus();
									return false; 
		 						}else if(data=="success"){
		 							getCheck();
		 							var fwu =  "<%=basePath%>/yzm.action";
									 $.post(fwu,{loginname:loginname},function(data){
										//alert("1");
									 });	
								}
							});
					  /* }					
			  },
			  error:function(e){
			  }
			 });*/
		}else{
			return false;
		}
	} 
	

	//刷新验证码
	function changeCode(){
	$("#kaptchaImage").attr("src",basePath+'/Kaptcha.jpg?d='+new Date().getTime());
	}
 	var countdown=60; 
	var obj =document.getElementById("btnSubmit1");
	function getCheck(){
	   if (countdown == 0) { 
		        obj.removeAttribute("disabled");    
		        obj.value="获取验证码"; 
		        countdown = 60; 
		        return;
		    }else{ 
		        obj.setAttribute("disabled", true); 
		        obj.value="重新发送(" + countdown + ")"; 
		        countdown--; 
		    } 
		setTimeout(function(){getCheck()} ,1000)
	
	}
	</script>
	<script type="text/javascript">
	 // var str = '${msg}';
	 
	 
	    <%-- function severCheck1(){
			  var codedx=$("#smCode").val();
			  window.location.href = '<%=basePath%>/yzmcheck.action?codedx='+codedx;
			  
 } --%>
	    
	 function severCheck1(){
		if(check()==true){
			var clicktag = 0;  
			var codeID = $("#smCode");
			var codeVal = codeID.val();
			var codedx=$("#smCode").val();
			var loginname = $("#mobile").val();
		    var verifyUrl = '<%=basePath%>/yzmcheck.action';
			 //alert(verifyUrl);
			 $.ajax({
			  type:"GET",
			  url:verifyUrl,
			  data:{codedx:codedx},
			  success:function(returnData){
			 //alert(${msg});
			 /* alert(returnData); */
					   if(returnData=="fail") {
						  	codeID.tips({
									side :1,
									msg : '手机验证码不正确,请重新输入',
									bg : '#FF3300',
									time : 3
								});
						  	codeID.focus();
							return false;
					  }else if(returnData=="success"){
						     window.location.href='${pageContext.request.contextPath}/successful.action?loginname='+loginname;
					  }
			  },
			  error:function(e){
			  }
			 });
		}else{
			return false;
		}
	} 
	</script>
		<script src="https://static.geetest.com/static/tools/gt.js"></script>
	<script>
		var handlerEmbed = function(captchaObj) {
			captchaObj.appendTo("#div_embed");
			captchaObj.onSuccess(function(data) {
			     var validate = captchaObj.getValidate();
		            $.ajax({
		                url: "${pageContext.request.contextPath }/pc-geetest/validate", // 进行二次验证
		                type: "post",
		                dataType: "json",
		                data: {
		                    // 二次验证所需的三个值
		                    geetest_challenge: validate.geetest_challenge,
		                    geetest_validate: validate.geetest_validate,
		                    geetest_seccode: validate.geetest_seccode
		                },
		                success: function (data) {
		                    if (data && (data.status === "success")) {
			                    $("#imgcode").val("success");
			                    $('#imgCodeMSG').css("display", "none");
			          			$("#imgCodeMSG").removeClass();
		                    } else {
		                       
		                    }
		                }
		            });
		        });
		};
		// 验证开始需要向网站主后台获取id，challenge，success（是否启用failback）
		$.ajax({
			url : "${pageContext.request.contextPath }/pc-geetest/register?t="
					+ (new Date()).getTime(), // 加随机数防止缓存
			type : "get",
			dataType : "json",
			success : function(data) {
				// 使用initGeetest接口
				// 参数1：配置参数
				// 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
				initGeetest({
					gt : data.gt,
					challenge : data.challenge,
					product : "float", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
					offline : !data.success,
					https : true
				// 表示用户后台检测极验服务器是否宕机，一般不需要关注
				// 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
				}, handlerEmbed);
			}
		});
	</script>	
</html>