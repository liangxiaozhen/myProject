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
            <ul>
                <li class="sz"><span>2</span></li>
                <li class="sm">2.邮箱验证</li>
            </ul>
            <ul>
                <li class="sz"><span>3</span></li>
                <li class="sm">3.重置密码</li>
            </ul>
            <ul>
                <li class="sz"><span>4</span></li>
                <li class="sm">完成</li>
            </ul>
        </div>
        <!--密保问题找回密码step1-->
        
         <!-- 1 -->
          <div class="look_step2" id="emailPwdOne">
            <table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
                  <tbody>
                      <tr>
                          <th class="leftside" valign="top">邮箱地址</th>
                          <th class="rightside" valign="top">
                          		<span id="sid"></span>
                                <input type="hidden" name="email" id="email" class="input_all" value="${userfindpwduserbase.email}" readonly="readonly">
                              <span class="prompt_1 error_1" id="emailMSG" style="display:none"></span>
                          </th>
                      </tr>
                      <tr>
                        <th></th>
                         <th class="rightside">
                          <button onclick="FindPwd.emailPwdCheck(this)" type="button" class="btn special01">下一步，重置密码</button>
                            <a href="${basePath}/findpwd/pwdtypeList.action" class="blue">选择其它找回方式</a>
                        </th>
                     </tr>
                   </tbody>
              </table><!--邮箱找回密码step1-->
         </div>
         
          <!-- 2 -->
          <div class="look_step2" id="emailPwdTwo"  style="display:none">
            <div class="pad20">
                <script type="text/javascript">
                    function openMailUrl(obj){
                        var mailStr =document.getElementById("myEmail").innerHTML;
                        var mailSplitStr = mailStr.split("@");
                        window.open('http://mail.'+mailSplitStr[1]);
                    }
                </script>
                <p>邮件已发送至您的新邮箱：<a id="myEmail" href="javascript:;" onclick="openMailUrl();" class="blue" title="去验证"></a>请在24小时内登录邮箱并点击邮件中的链接，完成验证。</p>
                <br>
                <p>没收到邮件?</p>
                <ul class="email">
                    <li>• 请查看邮箱地址有没有写错</li>
                    <li>• 检查邮件是否在垃圾箱里</li>
                    <li>• 让系统给您 <a href="javascript:reSendEmail();" class="blue pad_5a" title="重新发送邮件">重新发送邮件</a>|<a href="${basePath}/findpwd/pwdtypeList.action" class="blue pad_5a">选择其他找回方式  </a></li>
                </ul>
            </div><!--邮箱找回密码step2-->
         </div>
         
           <!--3-->
          <div class="look_step2" style="display:none">
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
                      <input type="button" value="下一步" class="btn special01" onclick="next();">
                    </th>
                 </tr>
               </tbody>
          </table>
          </div>
        </div>
        
        <!-- 修改密码成功 -->
          <div class="look_step2 fs_26 lh_32 txcenter" style="display:none">
              <i class="ico_all size48 img_true48 true48 mr_10"></i>密码重置成功，请牢记新的登录密码！
              <p class="mt_30"><a class="btn btnSize_2 btn_blue" href="/index.php/Home/Log/login.html">登录你我贷</a></p>
          </div>
          
    </div>
 <!--layout end-->

<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/findpwd.js"></script>
<script type="text/javascript">
$(function(){
	var email = $("#email").val();
	var s_email = email.substring(0,1)+"*****"+email.substring(email.indexOf("@")-1);
	$("#sid").text(s_email);
});
var time =45;
//重新发送邮件
function reSendEmail(){
	var email = $("#myEmail").text();
	//alert("time: "+time);
	while(time>0){
		//alert("里面："+time);
		settime();
	}
	$.ajax({
			type:"post",
			url:basePath + "/findpwd/emailPwdCheck123.action",
			data:{"email":email},
		    success:function(data){
		    	var obj = $.parseJSON(data);
		    	if(obj.result == "user_error"){
		    		alert("操作超时，重新验证用户名");
		    		setTimeout(function(){
						window.location.href = basePath +"/user/findpwd.action";
					},2000);
		    	}else if(obj.result == "success"){
		    		 alert("邮件已发送，请注意查收");
		    	}else if(obj.result == "fail"){
		    		alert("邮件发送失败！请重新操作");
		    		setTimeout(function(){
						window.location.href = basePath +"/findpwd/emailpwd.action";
					},2000);
		    	}
		    }
		});
}
function settime() {
	var c = null;
	//alert("wait: "+wait+" ,:  "+o.val());
	if (time == 0) {
		time = 45;
		clearTimeout(c);
	} else { 
		time--;
		c = setTimeout(function() {
			settime();
		},1000)
	}
}
</script>
 </body>
</html>