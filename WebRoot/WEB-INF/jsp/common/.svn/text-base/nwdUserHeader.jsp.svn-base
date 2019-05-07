<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript" src="<%=request.getContextPath() %>/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/resource/Scripts/nwd_common.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/resources/resource/Scripts/nwdhead.js"></script>
 <script type="text/javascript">
 </script>
<div class="header">
	<div class="nwd_top">
		<div class="nwd_main pad_t10 clearfix">
			<span class="fl fs_16 fc_9"><i class="icon icon_tel">&nbsp;</i>客服热线<em class="Numfont mar_l10">&nbsp;400-791-0888</em></span>
			<span class="fr fc_9 mar_l10">市场有风险，投资需谨慎</span>
			<span class="fr"><a href="javascript:void(0)" target="_blank" class="link_weixin"><i class="icon icon_weixin">&nbsp;</i></a></span>
			<span class="fr mar_r10"><a href="javascript:()" class="link_weibo mar_r10" target="_blank"><i class="icon icon_weibo">&nbsp;</i>微信客服<img src="${basePath}/resources/resource/Picture/topbg_b14925.png" class="weixinImg"></a></span>
			<span class="fr"><a href="{:U('Index/index')}" class="link_mobile mar_r20" target="_blank"><i class="icon icon_moible">&nbsp;</i>手机客户端<img src="${basePath}/resources/resource/Picture/topbg_b14926.png" usemap="#Map" class="mobileImg">
			<map name="Map">
                    <area shape="rect" coords="154,40,256,153" href="javascript:;" target="_blank">
                    <area shape="rect" coords="424,41,517,150" href="javascript:;" target="_blank">
                  </map>
			</a></span>
			  <input type="hidden" value="" id="stok"/>
			  <input type="hidden" value="" id="log_userid">
	          <input type="hidden" value="" id="log_username">
	          	 <c:choose>
	          		<c:when test="${not empty sessionScope.user }">
					<span class="fr fc_9" id="login_bt">您好,${sessionScope.user.loginname}<a href="javascript:void(0)" class="mar_l10 toplogin"><span class="name"></span></a> 
					<a href="javascript:void(0)" onclick="logout(this)" class="mar_r10 mar_l10 toplogin">退出</a>
					</span>
				</c:when>
	          		<c:otherwise>
					<span class="fr fc_9" id="login_bt">您好<a
						href="${basePath}/user/tologin.action"
						class="mar_l10 toplogin" title="登录">请登录</a><a
						href="${basePath}/register/userBaseInfo/register.action"
						class="mar_r10 mar_l10 toplogin" title="注册">免费注册</a></span>
 				</c:otherwise>
 	          </c:choose>
		</div>
	</div>

<div class="nwd_menu">
				<div class="nwd_main">
					<h1 class="fl logo mar_t20"><a title="给梦想可能" href="http://test.ganjiangps.com/">
					<img height="52" alt="干将软件" src="<%=request.getContextPath() %>/resources/Images/logo.png"></a></h1>

					<div class="fr nwd_navCon  mar_t20">
						<ul class="nav clearfix">
							<li>
								<a title="首页" href="#" rel="nofollow" class="nav_link">首页</a>
							</li>
							<li class="two">
								<div class="investCon">
									<div class="invest_name" >
										<a href="#"  class="fs_16">我要投资<img style="padding: 5px;" src="<%=request.getContextPath() %>/resources/Images/down.png"/></a>
									</div>
									<div class="invest_list">
										<a href="#" class="investName_link">有道智投</a>
										<a href="#" class="investName_link">新手专区</a>
										<a href="http://test.ganjiangps.com/index.php/Home/Xiangmu/xiangmu.html" class="investName_link">新标专区</a>
										<a href="#" class="investName_link">转让专区</a>
									</div>
								</div>
							</li>
							<li class="rela">
		                        <div class="investCon">
		                            <div class="invest_name">
		                                <a title="我要借款" href="#" class="fs_16">我要借款<img style="padding: 5px;" src="<%=request.getContextPath() %>/resources/Images/down.png"/></a>
		                             </div>
		                            <div class="invest_list">
		                                <a href="#" class="investName_link">借款产品</a>
		                                <a href="#" class="investName_link">借款攻略</a>
		                            </div>
		                        </div>
		                    </li>
							<li class="rela">
								<a title="新手导航" href="#" target="_blank" class="nav_link">新手导航 </a>
							</li>
							<li>
								<a title="社区" href="#" target="_blank" class="nav_link">社区 </a>
							</li>
							<li class="two">
								<a href="#" class="nav_link">信息披露</a>
							</li>
							<li class="myuserCon" style="margin-right:0;">

								<div class="LedgerCon wid_w180">
									<a class="navphoto fs_16 fl mar_l10" href="#" title="我的账户"><i class="mar_r5 relative">
									<img id="small-tx" src="" >
									 <img id="smallImg"  src="" class="smallmytxPic"> 
									</i></a>
									<div class="Ledger_name pad_t5">
										<%-- <a title="我的账户" class="fs_16">${mapUser['nkName']}<img style="padding: 5px;" src="<%=request.getContextPath() %>/resources/Images/down.png"/></a> --%>
										<a title="我的账户" class="fs_16">${sessionScope.user.loginname}<img style="padding: 5px;" src=""/></a>
									</div>
									<div class="Ledger_list clearfix pad_l10 pad_r10" style="text-align: center;">
										<a href="${pageContext.request.contextPath}/user/msg/toMemberMsg.action" class="listName_link">消息中心</a>
										<a href="${pageContext.request.contextPath}/user/userAccInExRecord/list.action" class="listName_link">资金记录</a>
										<a  onclick="rechargeHeader();">充值</a>&nbsp;&nbsp;&nbsp;<a onclick="withdrawscashHeader();" >提现</a>
										<a onclick="logout(this)" href="javascript:;"  class="listName_link">我要退出</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
			<div class="panelbox wid_w480" id="divHeader" style="display: none;width: 300px;height:100px;top: 25%;left: 25%">
    <div class="panelbg"></div>
    <div class="panelwrap3">
        <div class="paneltitle">
         <span class="text">友情提示</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close" onclick="closefunHeader();"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item clearfix">
		                   <p class="txt_center" id="txt_center3"></p>
	                    </div>
		                <div class="item clearfix txt_center">
		                    <a href="" class="btn btn_36c btn_size120" id="links3" style="cursor:hand">确认</a>
	                    </div>
		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>
<script>
$(function(){
	var loginname='${sessionScope.user.loginname}';//去空格
	var action ="<%=request.getContextPath()%>/user/userBaseInfo/getImg.action";
	   var param={
			   "loginname":loginname
	   }
	   $.post(action,param,function(returndata){
		 if(returndata!=0){
			$(".smallmytxPic").attr("src","/headimg/"+returndata);
			$("#bigtx-mask").attr("src","/headimg/"+returndata);
		 }else{
			$("#small-tx").attr("src","<%=request.getContextPath() %>/resources/Images/smalltx.png");
			$(".smallmytxPic").attr("src","");
		 }
		 });
  });

</script>
 <script>
 function logout(obj){
	 var flag = confirm("您确定退出吗？");
     if(flag){
         $.ajax({
             type:"post",
             url:"<%=request.getContextPath()%>/user/logout.action",
             success:function(data){
                if(data=="success"){
                     window.location.href="<%=request.getContextPath()%>/user/tologin.action";
                 }
             }
         });
     }
 }
 //点击我要充值
 function rechargeHeader(){
	  var action = "${pageContext.request.contextPath}/user/userRecharge/rechargePre.action";
	  $.post(action,function(result){
		 if(result=="1"){
			 $("#txt_center3").text("");
		 $("#txt_center3").text("你还没有开户,请到安全中心开户");
		 document.getElementById("links3").href="";
		 document.getElementById("links3").href="${basePath}/user/securitycenter/findopenAnAccountList.action";
		 $("#divHeader,.bg").show();
	  }else if(result=="3"){
		  $("#txt_center3").text("");
		  $("#txt_center3").text("您还没有绑卡,请前往去绑卡");
		  document.getElementById("links3").href="";
		  document.getElementById("links3").href="${basePath}/user/bankcard/bindCard.action";
		  $("#divHeader,.bg").show();
	  }else if(result=="0"){
		  $("#txt_center3").text("");
		  $("#txt_center3").text("您还没有实名认证,请前往认证");
		  document.getElementById("links3").href="";
		  document.getElementById("links3").href="${basePath}/user/securitycenter/findopenAnAccountList.action";
		  $("#divHeader,.bg").show();
	  }else{
		  window.location.href="${basePath}/user/userRecharge/rechargeList.action";
	  }
	  },'json');
 }  

 function withdrawscashHeader(){ 
  	  var action = "${pageContext.request.contextPath}/user/userwithdrawscash/withdrawPro.action";
  	  $.post(action,function(result){
  		   if(result=="1"){
  			   $("#txt_center3").text("");
  			   $("#txt_center3").text("你还没有开户,请到安全中心开户");
  			   document.getElementById("links3").href="";
  			   document.getElementById("links3").href="${pageContext.request.contextPath}/user/securitycenter/findopenAnAccountList.action";
  			   $("#divHeader,.bg").show();
  		  }else if(result=="0"){
  			  $("#txt_center3").text("");
  			  $("#txt_center3").text("您还没有绑卡,请前往去绑卡");
  			  document.getElementById("links3").href="";
  			  document.getElementById("links3").href="${pageContext.request.contextPath}/user/bankcard/bindCard.action";
  			  $("#divHeader,.bg").show();
  		  }else if(result=="2"){
  			  $("#txt_center3").text("");
  			  $("#txt_center3").text("您还没有实名认证,请前往认证");
  			  document.getElementById("links3").href="";
  			  document.getElementById("links3").href="${pageContext.request.contextPath}/user/securitycenter/findopenAnAccountList.action";
  			  $("#divHeader,.bg").show();
  		  }else if(result=="3"){
			  $("#txt_center3").text("");
			  $("#txt_center3").text("请先设置交易密码");
			  document.getElementById("links3").href="";
			  document.getElementById("links3").href="${pageContext.request.contextPath }/user/securitycenter/list.action";
			  $("#divHeader,.bg").show();
		  }else{//表示已经开户,并且绑卡
  			  window.location.href="${pageContext.request.contextPath}/user/userwithdrawscash/withdraw.action";
  		  }
  	  },'json');
    }
//关闭按钮
	function closefunHeader(){
		$("#divHeader").hide();
		$(".bg").hide();
}

 </script>