<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta name="robots" content="all" />
<meta charset="utf-8">
<title>登录-干将网贷-最专业的民间借贷网络平台_个人借款_无抵押小额借款_信用借款_短期借款_中小企业借款_小额借款公司网</title>
<meta name="keywords" content="民间借贷,个人借款,小额借款,无抵押借款,信用借款,中小企业借款,网络借贷,P2P信贷">
<meta name="description" content="干将网贷-中国最大的民间借贷网络平台(网上借款和借出)-最便捷的个人借款,信用借款,无抵押小额借款和中小企业借款服务,最安全的民间借贷和个人无抵押小额借款网络,满足个人无抵押借款和中小企业借款需求.短期借款-个人信用借款-网络借款-网络借贷-小额信贷" />
<meta name="Robots" content="index,follow" />
<meta property="wb:webmaster" content="ac04ec3477e29c81" />
<meta property="qc:admins" content="1533374623661774116375" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<style type="text/css">
.page154 .padding1{padding: 36px 20px 80px 20px;}
 .xuanze_btn{margin: 47px 0 120px 0;}
    .xuanze_btn span{
        border: 1px solid  #2980b9;
        padding: 4px 10px;
        margin: 0 30px 0 0;
    }
</style>
<body>
<!--head-->
<div class="clearfix simpleHead01">
	<div class="fl">
    	<a href="{:U('Index/index')}"><img src="<%=basePath%>resources/resource/Images/logo.png" width="173" height="57" alt="干将网贷" /></a><span>联合登录</span>
    </div>
    <div class="fr"></div>
</div>
<!--head end-->
<!--layout start-->
<div class="main registerAll page154">
	<div class="main page154">
	<div class="clearfix fluid">
	<div class="clearfix module padding1">
        <div class="content left">
        	<div class="fs_18 fc_9 tableHead1" style="color:red;">登录失败</div>
        	<p>您的微博没有绑定任何干将网贷账户</p>
            <p>您可使用本微博注册一个新账户，或将此微博绑定到已有账户。</p>
            <div class="xuanze_btn">
                <!-- <span><a href="javascript:regAccount()">注册一个新账户</a></span> -->
                <span><a href="<%=basePath%>register/userBaseInfo/register.action">注册一个新账户</a></span>
            </div>
        </div>
        <div class="fl righ">
        	<div class="fs_18 fc_9 tableHead2">已有干将网贷账号，直接绑定。</div>
        	<form action="" method="post" id="loginForm" class="nwd-formUi" >
            			<ul class="dl">
			                <li class="pad">
			                    <div class="user">
			                      <div class="ico-p pos1"></div>
			                         <input type="text" name="用户名" id="uname2" onblur="chkvalue(this)" class="userName1 gray_border " autocomplete="off" placeholder="请输入用户名/手机号/邮箱"/>
			                         <i id="uname_right_2"></i>
			                      </div>
			                   <div class="tishi"><span id="unameMSG2"></span></div>
			                </li>
			                <li class="pad">
			                  <div class="user">
			            					 <div class="ico-p pos2"></div>
			            						<input type="password" name="密码" id="pwd2" onblur="chkvalue(this)" class="userName2 gray_border " autocomplete="off" placeholder="请输入密码" />
			            						<i id="pwd_right_2" class="ico_all size15"></i>
			            						<!-- <a href="{:U('Log/findPwd')}" class="blue">忘记密码?</a> -->
			            						<a href="${basePath}/ptpjx/user/findpwd.action" class="fr">忘记密码？</a>
			                      </div>
			                   <div class="tishi"><span id="pwdMSG2"></span></div>
			                </li>
			                <li class="pad">
			                  <div class="user">
			                        <input type="text" class="input_b" onblur="chkvalue(this)" style="width:144px;"name="验证码" id="bindAccountImgCode" maxlength="5" autocomplete="off" placeholder="请输入验证码">
			                        <img src="${pageContext.request.contextPath }/Kaptcha.jpg" name="验证码" id="bindAccountCode" width="90" height="36" onclick="changeVerifyCode()" style="vertical-align:middle; margin-right:10px;" alt="图形验证码" />
									<i id="bindAccountCode_right" class="ico_all size15"></i><a href="javascript:;" onclick="changeVerifyCode()" class="blue" id="bindAccountRefresh">换一张</a>
			                    </div>
			                   <div class="tishi"><span id="bindAccountImgCodeMSG"></span></div>
			                </li>
			                <li>
			                  <input type="button" class="btn special01 mt_10" value="立即绑定" id="btnSubmit" onclick="severCheck()"/> 
			                </li>
			            </ul>
            </form>
        </div>
    
        </div>
    </div>
    
</div>
  <div class="bg-grey">
            <div class="wid-1000 newLogin-foot pt_15">
                <div class="mar_l20 txt_center">
                   <div class="fs_12 fc_6 Numfont txt_center pt_15">Copyright © 2016 干将网贷　版权所有；杜绝借款犯罪，倡导合法借贷，信守借款合约</div>
              </div>
            </div>
        </div>
</div>
<!--foot end-->
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/appmeasurement-1.2.1-min.js"></script>
<link href="<%=basePath%>resources/resource/Css/common.css" rel="stylesheet" type="text/css"> 
<link href="<%=basePath%>resources/resource/Css/sea.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>resources/resource/Css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/appmeasurement-1.2.1-min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/commonquick.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/Js/gt.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/register1.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/jquery.tips.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/jquery.cookie.js"></script>


<script type="text/javascript">
$(document).ready(function() {
	changeVerifyCode();
	var name=$("#uname2");
	var pwd=$("#pwd2");
	var code=$("#bindAccountImgCode");
	name.focus(function(){
		name.css("background-color","#FFFFCC");
	    
	  });
	pwd.focus(function(){
		pwd.css("background-color","#FFFFCC");
		  });
	code.focus(function(){
		code.css("background-color","#FFFFCC");
		  });
});

$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		$("#btnSubmit").trigger("click");
	}
});
	</script>
	<!-- 文本框失去焦点事件 -->
	<script type="text/javascript">
		function chkvalue(txt){
				var id=txt.id;
				var name=txt.name;
				$("#"+id).css("background-color","#FFFFFF");
				 if(txt.value=="") {
					 $("#"+id).tips({
							side : 2,
							msg : name+'不得为空',
							bg : 'red',
							time : 3
						});
				 }
		}
		//客户端校验
		function check() {
			var name=$("#uname2");
			var pwd=$("#pwd2");
			var code=$("#bindAccountImgCode");
 			if (name.val() == "") {
 				name.tips({
					side : 2,
					msg : '用户名不得为空',
					/* bg : '#AE81FF', */
					bg : '#FF3300',
					time : 3
				});
 				name.focus();
				return false;
			} else {
				name.val(jQuery.trim(name.val()));
				if (pwd.val() == "") {
					pwd.tips({
						side : 2,
						msg : '密码不得为空',
						/* bg : '#AE81FF', */
						bg : '#FF3300',
						time : 3
					});
					pwd.focus();
					return false;
				}else {
					pwd.val(jQuery.trim(pwd.val()));
					if (code.val() == "") {
						code.tips({
							side :2,
							msg : '验证码不得为空',
							/* bg : '#AE81FF', */
							bg : '#FF3300',
							time : 2
						});

						code.focus();
						return false;
					}else if(code.val().length!=5){
						code.tips({
							side :2,
							msg : '验证码长度为5',
							/* bg : '#AE81FF', */
							bg : '#FF3300',
							time : 2
						});

						code.focus();
						return false;
						
					}else{
						code.val(jQuery.trim(code.val()));
					}
				}
			}
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});
			return true;
		}
	</script>
<script type="text/javascript">
//点击切换验证码  
function changeVerifyCode() {
	var random = Math.random();
	$("#bindAccountCode").attr("src",
			"${pageContext.request.contextPath }/Kaptcha.jpg?r=" + random);
}
function severCheck(){
	if(check()==true){
		var clicktag = 0;  
        $('#btnSubmit').click(function () {  
            if (clicktag == 0) {  
                clicktag = 1;  
                $(this).disabled=false;  
                setTimeout(function () { clicktag = 0 }, 5000);  
            }  
        });
		var codeID = $("#bindAccountImgCode");
		var codeVal = codeID.val();
		//提交前先异步检查验证码是否输入正确
		 var verifyUrl = "${pageContext.request.contextPath}/servlet/VerifyServlet?verifyCode="+codeVal;
		 $.ajax({
		  type:"GET",
		  url:verifyUrl,
		  success:function(returnData){
				  if(returnData!="Y") {
					  	codeID.tips({
								side :1,
								msg : '验证码不正确',
								bg : '#FF3300',
								time : 3
							});
					  	changeVerifyCode();
					  	codeID.focus();
						return false;
				  }else {
					  	changeVerifyCode();
					  	var loginname = $("#uname2").val();
						var password = $("#pwd2").val();
						var fwurl="<%=basePath%>weibo/bdYonghu.action";
						$.post(fwurl,{loginname:loginname,password:password},function(data){
							if(data=="fail"){
								$("#uname2").tips({
									side : 1,
									msg : "用户名或密码有误",
									bg : '#FF5080',
									time : 15
								});
								$("#uname2").focus();
								return false;
	 						}else if(data=="success"){
	 							<%-- window.location.href="<%=basePath%>weibo/bangDingWBLogin.action"; --%>
	 							window.location.href="<%=basePath%>user/tologin.action";
							}
						});
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
<script>
	//TOCMAT重启之后 点击左侧列表跳转登录首页 
	if (window != top) {
		top.location.href = location.href;
	}
	function regAccount(){
		window.location.href="<%=basePath%>weibo/rsForWBafterLgFail.action";
	}
</script>
</body>
</html>