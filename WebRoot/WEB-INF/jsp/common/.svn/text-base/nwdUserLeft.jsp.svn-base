<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/resources/resource/ScriptsP/accountIdentity.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery/1.11.3/jquery-1.11.3.js"></script>
<style type="text/css">
    .user-security-pop.j_userSecurityPop {
        display: none
    }

    .icon_sm, .icon_yh, .icon_sj, .icon_mm {
        display: inline-block;
    }

    .icon_sm:hover .user-security-pop {
        display: block;
    }

    .icon_yh:hover .user-security-pop {
        display: block;
    }

    .icon_sj:hover .user-security-pop {
        display: block;
    }

    .icon_mm:hover .user-security-pop {
        display: block;
    }

    .Text {
        position: relative;
        display: inline-block;
        background: white;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #2577E3;
        text-decoration: none;
        text-indent: 0;
        line-height: 30px;
        width: 80px;
        height: 30px;
        text-align: center;
    }

    .TextArea {
        position: relative;
        display: inline-block;
        background: #D0EEFF;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #1E88C7;
        text-decoration: none;
        text-indent: 0;
        line-height: 30px;
        width: 160px;
        height: 30px;
        text-align: center;
        background: white;
    }

    .Text input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
    }

    .Text:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
    }
</style>


<div class="panelbox wid_w480" id="recharge" style="display: none;width: 300px;height:100px;">
    <div class="panelbg"></div>
    <div class="panelwrap3">
        <div class="paneltitle">
            <span class="text">友情提示</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close" onclick="closefunleft();"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
                    <div class="form">
                        <div class="item clearfix">
                            <p class="txt_center" id="txt_center2"></p>
                        </div>
                        <div class="item clearfix txt_center">
                            <a href="" class="btn btn_36c btn_size120" id="links2" style="cursor:hand">确认</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--left nav start-->
<div class="fl sidbar" style="position: relative;">
    <div class="my_msgCon mar_t20">
        <div class="my_pic">
            <a href="javascript:;" class="myphoto radius94 platlog_img"
               id="myphototip"> <input type="hidden" id="ph"> <input
                    type="hidden" id="ph1"> <img id="bigtx-mask"
                                                 src="${basePath}/resources/resource/Images/bigtx.png"> <img
                    src="${basePath}/resources/resource/Images/touxiang.jpg"
                    class="bigmytxPic" id="exists"
                    style="display: none; width: 94px; height: 94px; top: 50%; margin-top: -47px;">
                <img src="${basePath}/resources/resource/Images/touxiang.jpg"
                     class="bigmytxPic" id="noExists"
                     style="width: 94px; height: 94px; top: 50%; margin-top: -47px;">
                <span class="xgtx">编辑资料</span>
            </a>
            <p class="my_name mar_t20 fc_9">用户名</p>
            <p class="my_tel  pad_t10 fs_16 fc_9" id="phoneInfo"></p>
            <p class="my_tel  pad_t10 fs_14 fc_9">
                <a href="javascritp:;" class="mystepshow"
                   id="memberLevelDiv"><span class="vip_icon step1_14 mar_r5">&nbsp;</span><span>普通会员</span></a>
            </p>
        </div>
        <div id="assetUserWelcome" class="u-ext mar_b20">
            <div class="u-security pad_t10">
                <!-- 1 -->
                <div class="icon_sm">
                    <a class="" id="IsReallyId"
                       href="" target="_self" data-msg="完成实名认证！&lt;"></a>
                    <div class="user-security-pop j_userSecurityPop"
                         style="z-index: 211; top: 211px; left: -39px; opacity: 1;">
                        <em class="user-security-pop-ico"></em>
                        <div class="user-security-pop-content" id="IsReallyText">
                            您已通过实名认证。<a class="font-link" href="${basePath}/user/securitycenter/list.action"
                                        target="_self">查看详情</a>
                        </div>
                        <div class="user-security-pop-content" id="IsReallyText2">
                            您没有实名认证！<a class="font-link"
                                       href="${basePath}/user/securitycenter/realNameAuthenticationlist.action"
                                       target="_self">请认证。</a>
                        </div>
                    </div>
                </div>
                <a class="&#39;font-link&#39;"
                   href="39; target=&#39;_blank&#39;&gt;立即认证&lt;/a&gt;"></a>
                <!-- 2 -->
                <div class="icon_sj">
                    <a id="phoneImage" class=""
                       href="${basePath}/user/securitycenter/list.action" target="_self"
                       data-msg="您已绑定手机130****1731。&lt;a class=&#39;font-link&#39;"
                       target="&#39;_blank&#39;&gt;更改&lt;/a&gt;"></a>
                    <div class="user-security-pop j_userSecurityPop"
                         style="z-index: 10001; top: 211px; left: -12px; opacity: 1;">
                        <em class="user-security-pop-ico"></em>

                        <div class="user-security-pop-content" id="phoneText">
                            您已绑定手机<span class="phoneRep"></span>。 <a class="font-link"
                                                                     href="${basePath}/user/securitycenter/list.action"
                                                                     target="_self">更改</a>
                        </div>
                        <div class="user-security-pop-content" id="phoneText2">
                            您没有绑定手机! <a class="font-link" href="${basePath}/user/securitycenter/list.action"
                                        target="_self">请绑定。</a>
                        </div>

                    </div>
                </div>


                <!-- 3 -->
                <div class="icon_yh">
                    <a id="bankCard"
                       class=""
                       href="${basePath}/user/securitycenter/list.action" target="_self"
                       data-msg="完成银行卡绑定！&lt;"></a><a class="&#39;font-link&#39;"
                                                      href="${basePath}/user/securitycenter/list.action"
                                                      target="&#39;_blank&#39;&gt;立即认证&lt;/a&gt;"></a>
                    <div class="user-security-pop j_userSecurityPop"
                         style="z-index: 10001; top: 211px; left: 15px; opacity: 1;">
                        <em class="user-security-pop-ico"></em>
                        <div class="user-security-pop-content" id="bankCardText" style="display: none">
                            完成银行卡验证！<a class="font-link" href="${basePath}/user/securitycenter/list.action"
                                       target="_self">点击查看</a>
                        </div>
                        <div class="user-security-pop-content" id="bankCardText2" style="display: block">
                            没有完成银行卡验证！<a class="font-link" href="${basePath}/user/securitycenter/list.action"
                                         target="_self">立即验证</a>
                        </div>
                    </div>
                </div>
                <!-- 4 -->
                <div class="icon_mm">
                    <a id="accountPass" class=" " href="${basePath}/user/securitycenter/list.action" target="_self"
                       data-msg="完成密码验证！"></a>
                    <div class="user-security-pop j_userSecurityPop"
                         style="z-index: 10001; top: 211px; left: 45px; opacity: 1;">
                        <em class="user-security-pop-ico"></em>
                        <div class="user-security-pop-content" id="accountText">
                            完成密码验证！
                            <a class="font-link" href="${basePath}/user/securitycenter/list.action"
                               target="_self">点击查看</a>
                        </div>
                        <div class="user-security-pop-content" id="accountText2">
                            没有完成密码验证！
                            <a class="font-link" href="${basePath}/user/securitycenter/list.action"
                               target="_self">立即验证</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="side_nav">
        <dl class="nav_name" id="leftnav0">
            <dt><a href="${pageContext.request.contextPath}/user/userBaseInfo/flushIndex.action"
                   class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow" target="_self">账户中心</a><i>&nbsp;</i></dt>
        </dl>
        <dl class="nav_name" id="leftnav1">
            <dt>
                <a href="javascript:;"
                   class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">徽商存管账户</a><i>&nbsp;</i>
            </dt>
            <dd class="bor_r bor_t bor_b">
                <ul>
                    <li style="cursor:pointer"><a onclick="rechargeleft();">我要充值</a></li>
                    <li style="cursor:pointer"><a onclick="withdrawscashLeft();">我要提现</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/userAccInExRecord/list.action"
                           id="fundRecordLi">资金记录</a></li>
                </ul>
            </dd>
        </dl>
        <dl class="nav_name" id="leftnav2">
            <dt>
                <a href="${pageContext.request.contextPath}/user/tender/myTenderRecord.action"
                   class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">投标管理</a><i>&nbsp;</i>
            </dt>
            <%--<dd class="bor_r bor_t bor_b">
                 <ul>
                    <li><a href="#">嘉财有道</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/tender/myTenderRecord.action">我的债权</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/userdebtattorn/userdebtrron.action">债权转让</a></li>
                    <li><a href="#">新手专区</a></li>
                    <li id="excellenceNav" style="display: none"><a
                        href="#">卓越专区</a></li>
                    <li><a href="#">嘉猜宝</a></li>
                    <!--  <li><a href="addmoney.html">有道添金</a></li> -->
                    <li><a href="${pageContext.request.contextPath}/user/gfundsIntNotes/queryMyGfundsIntNotes.action">站岗利息</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/failtcrecord/query.action">流标补偿</a></li>
                </ul>
            </dd>--%>
        </dl>
        <dl class="nav_name" id="leftnav5">
            <dt>
                <a href="javascript:;"
                   class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">借款管理</a><i>&nbsp;</i>
            </dt>
            <dd class="bor_r bor_t bor_b">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/user/loan/loanappui.action">我的借款</a></li>
                    <li><a href="javascript:void(0);" onclick="borrowing();">我要借款</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/loan/jumpusercommon.action">普通资料</a></li>
                    <li><a href="${basePath}/user/userLoanApp/list.action">我要还款</a></li>
                </ul>
            </dd>
        </dl>

        <dl class="nav_name" id="leftnav3">
            <dt>
                <a href="javascript:;"
                   class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">账户管理</a><i>&nbsp;</i>
            </dt>
            <dd class="bor_r bor_t bor_b">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/user/activity/exchange.action">我的积分</a></li>
                    <li><a href="${basePath}/user/securitycenter/list.action">安全中心</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/msg/toMemberMsg.action">消息中心</a></li>
                    <li><a href="${pageContext.request.contextPath}/third/bingDing.action">账户绑定</a></li>
                    <li><a href="${basePath}/user/bankcard/bindCard.action">银行卡管理</a></li>
                </ul>
            </dd>
        </dl>


        <dl class="nav_name" id="leftnav4">
            <dt>
                <a href="javascript:;"
                   class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">活动管理</a><i>&nbsp;</i>
            </dt>
            <dd class="bor_r bor_t bor_b">
                <ul>
                    <!-- <li><a href="{:U('Message/coupon')}">我的礼品</a></li> -->
                    <li><a href="${pageContext.request.contextPath}/user/activity/userAward.action">我的礼品</a></li>
                    <!-- <li><a href="{:U('Message/morecode')}">活动奖励</a></li> -->
                    <li><a href="${pageContext.request.contextPath}/user/activity/activityAward.action">活动奖励</a></li>
                    <li><a href="#">推荐有奖</a></li>
                    <li><a href="#" target="_blank">我要推荐</a></li>
                </ul>
            </dd>
        </dl>
    </div>
    <div class="leftCode txt_center pad_t30 mar_t20 bor_t pad_b30">
        <img src="https://static.niwodai.com/Public/Static/201603/css/font/leftCode.png">
        <p class="fc_9 fs_14 mar_t5"></p>
    </div>

</div>

<!-- 头像上传 -->
<div class="panelbox wid_w740" id="myphoto-pop-manage">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
            <span class="text">修改头像</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close" id="closeImg"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30 clearfix">
            <div class="container clearfix">
                <div class="analog-success pad_l30 cleafix">
                    <a class="myphoto radius94 fl" href="#"><img id="chooseImgShow" src="">

                    </a>
                    <div class="fl pad_l30 pad_t10">
                        <div class="files form relative">
                            <%-- <form action="${pageContext.request.contextPath}/user/userBaseInfo/updateImg.action" method="post" enctype="multipart/form-data" name="memberPhoto" id="mbPhoto">
                                <input type="hidden" name="loginname" value="" id="LoginNameHidden"/>
                                <input type="text" name="textfields" id="mbphoto" class="txt ui-input w200-input" />
                                <input type="button" class="btn_up button button-w120 mar_l10" value="浏览..." />
                                <input type="file" name="file" class="file" size="23" style="width:340px;" onchange="document.getElementById('mbphoto').value=this.value" />
                                <input type="submit" class="btn_up button button-w120 mar_l10" name="photoSubmit" onclick="savembphoto()" value="点击上传" />

                            </form> --%>
                            <form action="${pageContext.request.contextPath}/user/userBaseInfo/updateImg.action"
                                  method="post" enctype="multipart/form-data" id="form">
                                <input type="hidden" name="loginname" value="" id="LoginNameHidden"/>
                                <a href="javascript:;" class="TextArea"></a>
                                <a href="javascript:;" class="Text">浏览... <input type="file" name="imagepath"
                                                                                 id="SelectW"></a>
                                <a href="javascript:;" class="Text" onclick="btnSubmit()">上传文件<input type="button"></a>
                            </form>

                        </div>

                        <p class="fc_9 pad_t15">
                            注意：请选择jpg、gif、bmp、png、jpeg格式，尺寸为200*200，<br/> <i
                                style="displany: inline-block; width: 42px;">&nbsp;</i>且文件大小不超过2M的图片。
                        </p>

                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="bg"></div>

<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

    $("#myphototip").click(function () {
        $("#myphoto-pop-manage").show();
        $(".bg").show();
    })
    $("#closeImg").click(function () {
        $("#myphoto-pop-manage").hide();
        $(".bg").hide();
    });
</script>
<script>
    $(function () {
        $(".messagesleft").hide();
        var loginname = '${sessionScope.user.loginname}';//去空格
        $("#LoginNameHidden").val(loginname);
    });
    $(function () {
        $("#SelectW").change(function () {
            $(".TextArea").html($(this).val());
        });
    });
    function btnSubmit() {
        var text = $(".TextArea").text();
        var obj_file = document.getElementById("SelectW");
        filesize = obj_file.files[0].size;  //获取上传的图片的大小
        if (text == null || text == '') {
            alert("请上传图片...");
        } else {
            var fileType = text.substring(text.lastIndexOf(".") + 1);
            var ImgArr = new Array("jpg", "JPG", "BMP", "bmp", "GIF", "gif", "PNG", "png", "JPEG", "jpeg"); //判断时候是上传的这些文件
            for (var i = 0; i < ImgArr.length; i++) {
                if (ImgArr[i] == fileType) {
                    if (filesize <= 2097152) {
                        $("#form").submit();
                        break;
                    } else {
                        alert("图片大小超过2M...");
                        break;
                    }

                } else {
                    if (i == 9) {
                        alert("请上传规定格式的图片...谢谢合作");
                    }
                }
            }
        }
    }
   //点击我要充值
     function rechargeleft(){
   	  var action = "${pageContext.request.contextPath}/user/userRecharge/rechargePre.action";
   	  $.post(action,function(result){
   		 if(result=="1"){
   			 $("#txt_center2").text("");
			 $("#txt_center2").text("你还没有开户,请到安全中心开户");
			 document.getElementById("links2").href="";
			 document.getElementById("links2").href="${basePath}/user/securitycenter/findopenAnAccountList.action";
			 $("#recharge,.bg").show();
		  }else if(result=="3"){
			  $("#txt_center2").text("");
			  $("#txt_center2").text("您还没有绑卡,请前往去绑卡");
			  document.getElementById("links2").href="";
			  document.getElementById("links2").href="${basePath}/user/bankcard/bindCard.action";
			  $("#recharge,.bg").show();
		  }else if(result=="0"){
			  $("#txt_center2").text("");
			  $("#txt_center2").text("您还没有实名认证,请前往认证");
			  document.getElementById("links2").href="";
			  document.getElementById("links2").href="${basePath}/user/securitycenter/findopenAnAccountList.action";
			  $("#recharge,.bg").show();
		  }else{
			  window.location.href="${basePath}/user/userRecharge/rechargeList.action";
		  }
   	  },'json');
     }  
   
     function withdrawscashLeft(){ 
   	  var action = "${pageContext.request.contextPath}/user/userwithdrawscash/withdrawPro.action";
   	  $.post(action,function(result){
   		   if(result=="1"){
   			   $("#txt_center2").text("");
   			   $("#txt_center2").text("你还没有开户,请到安全中心开户");
   			   document.getElementById("links2").href="";
   			   document.getElementById("links2").href="${pageContext.request.contextPath}/user/securitycenter/findopenAnAccountList.action";
   			   $("#recharge,.bg").show();
   		  }else if(result=="0"){
   			  $("#txt_center2").text("");
   			  $("#txt_center2").text("您还没有绑卡,请前往去绑卡");
   			  document.getElementById("links2").href="";
   			  document.getElementById("links2").href="${pageContext.request.contextPath}/user/bankcard/bindCard.action";
   			  $("#recharge,.bg").show();
   		  }else if(result=="2"){
   			  $("#txt_center2").text("");
   			  $("#txt_center2").text("您还没有实名认证,请前往认证");
   			  document.getElementById("links2").href="";
   			  document.getElementById("links2").href="${pageContext.request.contextPath}/user/securitycenter/findopenAnAccountList.action";
   			  $("#recharge,.bg").show();
   		  }else if(result=="3"){
 			  $("#txt_center2").text("");
 			  $("#txt_center2").text("请先设置交易密码");
 			  document.getElementById("links2").href="";
 			  document.getElementById("links2").href="${pageContext.request.contextPath }/user/securitycenter/list.action";
 			  $("#recharge,.bg").show();
 		  }else{//表示已经开户,并且绑卡
   			  window.location.href="${pageContext.request.contextPath}/user/userwithdrawscash/withdraw.action";
   		  }
   	  },'json');
     }
   //关闭按钮
 	function closefunleft(){
 		$("#recharge").hide();
 		$(".bg").hide();
   }
 	function closefunwithdraw(){
 		$("#withdraw").hide();
 		$(".bg").hide();
   }
<%--    $(function(){
        var loginname='${sessionScope.user.loginname}';//去空格
        var action ="<%=request.getContextPath()%>/user/userBaseInfo/getInfoByLoginName.action";
 	    var param={
 			   "loginname":loginname
 	   }
 	   $.post(action,param,function(returndata){
 		var text= JSON.parse(returndata);
 		 if(text.isreally==1){//验证是否实名
 		    	$("#IsReallyId").addClass("j_userSecurityIcon u-security-icon u-security-icon-person");
 		    	$("#IsReallyText").css('display','block'); 
 		    	$("#IsReallyText2").css('display','none'); 
 		    }else{
 		    	$("#IsReallyId").addClass("j_userSecurityIcon u-security-icon u-security-icon-person risky");
 		    	$("#IsReallyText").css('display','none'); 
 		    	$("#IsReallyText2").css('display','block'); 
 		    }
 		    //验证手机号是否绑定
 		    if(text.ismobileverify==1){
 		    	$("#phoneImage").addClass("j_userSecurityIcon u-security-icon u-security-icon-phone");
 		    	$("#phoneText").css('display','block'); 
 		    	$("#phoneText2").css('display','none');
 		    	var phone=text.mobilephone;
 		 		var phoneRep=phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');//替换字符串
 		 		$(".phoneRep").text(phoneRep);
 		    }else{
 		    	$("#phoneImage").addClass("j_userSecurityIcon u-security-icon u-security-icon-phone risky");
 		    	$("#phoneText").css('display','none'); 
 		    	$("#phoneText2").css('display','block');
 		    }
 	  });
 	   }); --%>
 	   $(function(){
 		  if('${sessionScope.user.isreally}'==1){//验证是否实名
 		    	$("#IsReallyId").addClass("j_userSecurityIcon u-security-icon u-security-icon-person");
 		    	$("#IsReallyText").css('display','block'); 
 		    	$("#IsReallyText2").css('display','none'); 
 		    	$("#IsReallyId").attr('href','${basePath}/user/securitycenter/list.action');
 		    }else{
 		    	$("#IsReallyId").addClass("j_userSecurityIcon u-security-icon u-security-icon-person risky");
 		    	$("#IsReallyText").css('display','none'); 
 		    	$("#IsReallyText2").css('display','block'); 
 		    	$("#IsReallyId").attr('href','${basePath}/user/securitycenter/realNameAuthenticationlist.action');
 		    }
 		    //验证手机号是否绑定
 		    if('${sessionScope.user.ismobileverify}'==1){
 		    	$("#phoneImage").addClass("j_userSecurityIcon u-security-icon u-security-icon-phone");
 		    	$("#phoneText").css('display','block'); 
 		    	$("#phoneText2").css('display','none');
 		    	var phone='${sessionScope.user.mobilephone}';
 		 		var phoneRep=phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');//替换字符串
 		 		$(".phoneRep").text(phoneRep);
 		    }else{
 		    	$("#phoneImage").addClass("j_userSecurityIcon u-security-icon u-security-icon-phone risky");
 		    	$("#phoneText").css('display','none'); 
 		    	$("#phoneText2").css('display','block');
 		    }
 	   });
 	 
 	   $(function(){
 		  var baseid='${sessionScope.user.id}';
 		 var action ="<%=request.getContextPath()%>/user/bankcard/getInfoByBaseiD.action";
 	 	   var param={
 	 			   "baseid":baseid
 	 	   }
 	 	 $.post(action,param,function(returndata){
 	 		 var bankCard= JSON.parse(returndata);
 	 		   if(bankCard.bindstatus==1){
 	 			$("#bankCard").addClass("j_userSecurityIcon u-security-icon u-security-icon-bank");
 	 			$("#bankCardText").css('display','block'); 
 		    	$("#bankCardText2").css('display','none');
               
 	 		   }else{
 	 			$("#bankCard").addClass("j_userSecurityIcon u-security-icon u-security-icon-bank risky");
  	 			$("#bankCardText").css('display','none'); 
  		    	$("#bankCardText2").css('display','block');
 	 		   }
 	 	   });
 	 	   
 	   });
 	  $(function(){
 		  var baseid='${sessionScope.user.id}';
 		 var action ="<%=request.getContextPath()%>/user/bankcard/getPassWordStutsByBaseID.action";
 	 	   var param={
 	 			   "baseid":baseid
 	 	   }
 	 	 $.post(action,param,function(returndata){
 	 		var accountFs= JSON.parse(returndata);
 	 		   if(accountFs.tradepass==1){
 	 			$("#accountPass").addClass("j_userSecurityIcon u-security-icon u-security-icon-pwd");
 	 			$("#accountText").css('display','block'); 
 		    	$("#accountText2").css('display','none');
 	 		   }else{
 	 			 $("#accountPass").addClass("j_userSecurityIcon u-security-icon u-security-icon-pwd risky");
  	 			 $("#accountText").css('display','none'); 
  		    	 $("#accountText2").css('display','block');
 	 		   }
 	 	   });
 	 	});
 	 //我要借款
 	    function borrowing() {
 	        $.ajax({
 	            "type": "post",
 	            "url": "${pageContext.request.contextPath}/user/loan/borrowing.action",
 	            "success": function (backData) {
 	                var obj = $.parseJSON(backData);
 	                if (obj.result == "该用户还没有开通托管账户") {
 	                    alert(obj.result);
 	                }
 	                if (obj.result == "请重新登陆") {
 	                    alert(obj.result);
 	                    window.location.href = "${pageContext.request.contextPath}/user/manager/login.action";
 	                }
 	                if (obj.result == "success") {
 	                    window.location.href = "${pageContext.request.contextPath}/user/loan/borrowing2.action";
 	                }

 	            }
 	        });
 	    }
 </script>
