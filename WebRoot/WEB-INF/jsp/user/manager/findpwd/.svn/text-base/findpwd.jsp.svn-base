<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
 <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情_干将网贷会员找回密码</title>
<meta name="keywords" content="干将网贷、借出、借款">
<meta name="description" content="干将网贷">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">var basePath = "${basePath}"</script>
<link href="${basePath}/resources/resource/Css/common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/sea.css" rel="stylesheet" type="text/css">
<link href="${basePath}/resources/resource/Css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
 <%@include file="/WEB-INF/jsp/common/pwdUserHead.jsp" %>
 		<!--layout start-->
		<div class="w1000 shadow page149_1 mh580">
			<div class="topper fs_24">找回密码</div>
 		    <div class="question" style="width:700px;">
		        <div class="look_step2">
		        	<table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
		                  <tbody>
		                      <tr>
		                          <th class="leftside">用户名\手机号\邮箱</th>
		                          <th class="rightside">
		                                <input type="text" class="input_all" value = "" name="uname" id="uname" />
		                                <br/>
			                            <span class="prompt_1 error_1" id="unameMSG" style="display:none"><i></i></span>
		                          </th>
		                      </tr>
		                      
		                      <tr>
		                      <th class="leftside">验证码</th>
		                          <th class="rightside">
		                          	<div id="div_embed"></div>
									<input type="hidden" id="imgcode" name="imgcode" />
									<span id="imgCodeMSG" style="display: none"></span>
			                            <!-- <div id="div_geetest_lib"></div>
										<div id="div_id_embed"></div>
										<input class="channel" type="hidden" name="channel" value="2">
						    			<input type="hidden" id="imgcode" name="imgcode" value="123456"/>
										<span class="prompt_1 error_1" id="imgMSG" style="display:none"><i></i></span> -->
		                          </th>
		                      </tr>
		                      <tr>
		                        <th></th>
		                         <th class="rightside">
		                         		<button type="button" class="btn special01" id="submit" onclick="FindPwd.userSubmit(this)">下一步，重置密码</button>
		                        </th>
		                     </tr>
		              </tbody>
		          </table>
		        </div>
		        <div class="look_step2" style="display:none;">
		            <table class="table_child fc_3 fs_14" cellpadding="0" cellspacing="0" width="100%">
		              <tbody>
		                  <tr>
		                      <th class="leftside">用户名：</th>
		                      <th class="rightside">
		                        h****1
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
		          <div class="look_step2 fs_26 lh_32 txcenter" style="display:none;">
		              <i class="ico_all size48 img_true48 true48 mr_10 no"></i>密码重置成功，请牢记新的登录密码！
		              <p class="mt_30"><a class="btn btnSize_2 btn_blue" href="/login.html">登录你我贷</a></p>
		          </div>
		    </div>
 		</div>
		<!--layout end-->
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/findpwd.js"></script>
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
</body>
</html>