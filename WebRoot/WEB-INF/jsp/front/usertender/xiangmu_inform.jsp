<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html class="newVersion">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="${basePath}/resources/resource/Css/invest_detail_b18d767.css"/>
<link rel="stylesheet" href="${basePath}/resources/resource/Css/invest_detail_common_borrowinfo_4da7cd0.css">
<%-- <link href="${basePath}/resources/resource/Css/common.css" rel="stylesheet" type="text/css">  --%>
<%-- <link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css">  --%>
<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">
<link href="${basePath}/resources/resource/Css/mobiscroll.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/mobiscroll_date.css" rel="stylesheet" />
<style type="text/css">
.bg { background: #000;border: 0 none;height: 100%;left: 0;opacity: 0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;-khtml-opacity: 0.5;position: fixed;top: 0;width: 100%;z-index: 16;display: none;}
.line3{ border: 1px solid #ddd;padding: 13px 10px;}
.line3 .bg_line span{border:1px solid #2577e3; color: #2577e3; padding: 0 5px;-moz-border-radius: 3px;-webkit-border-radius: 3px;border-radius: 3px;cursor: pointer;}
.selbox{text-align: center;}
.selbox li{display: inline-block; margin: 0 10px;cursor: pointer;}
.selbox li p i {display: block;width: 116px;height: 116px; margin: 0 auto 18px auto;}
.m2-detHideicon-pri {background: url(${basePath}/resources/resource/Images/m2-mainIcon.png) no-repeat 0px -149px;}
.m2-detHideicon-red {background: url(${basePath}/resources/resource/Images/m2-mainIcon.png) no-repeat -116px -149px;}
.m2-detHideicon-add {background: url(${basePath}/resources/resource/Images/m2-mainIcon.png) no-repeat -232px -149px;}
.selbox li p.p2 {position: relative;display: block;width: 68px;height: 16px;line-height: 16px;margin: 0 auto;font-size: 14px;text-indent: 22px;}
.selbox li p span{display: block;position: absolute; width: 16px;height: 16px;top: 0px;left: 0px;cursor: pointer;    background: url(${basePath}/resources/resource/Images/m2-mainIcon.png) no-repeat -18px -126px;}
.selbox li.active p span{ background: url(${basePath}/resources/resource/Images/m2-mainIcon.png) no-repeat -34px -126px;}
.jiangli_bottom{padding: 20px;}
a{text-decoration:none}
p.btns{text-align: center;}
p.btns a{background-color: #2577e3;color: #fff;width: 120px;height: 34px;line-height:34px;-moz-border-radius: 5px;-webkit-border-radius: 5px;border-radius: 5px;margin: 0 auto;display: inline-block;text-align: center;    margin: 0 10px;}
</style>
<body class="detail-page">
    <!-- 头部-->
    <%@ include file="/WEB-INF/jsp/front/About/head.jsp" %>
    <!-- 内容 -->
    <div class="main pg_invest page131">
        <!--面包屑-->
        <div class="crumbs">
            <a href="http://my.ganjiangps.com/user/tologin.action" class="first">首页</a><span>&gt;</span>
            <a href="javascript:void(0);" class="two">债权列表</a><span>&gt;</span>
            <a class="three">快速小额借款</a>
        </div>
       <!-- 中间 -->
        <div class="clearfix fluid mb_10" style="display: flex;">
            <!--left-->
            <!-- 标的详情 -->
            <div class="clearfix module fl lBox" id="bd_inform"></div>
            <!--left end-->
            <!--right-->
            <div class="clearfix module fl rBox" style="clear: none;">
                <div class="title">
                    <span class="fs_18">投资金额</span>
                </div>
                <!-- 未登录 -->
                <c:if test="${status eq 'logout'}">
                <div class="pad pad_one">
                     <form id="tenderForm" method="post" class="nwd-formUi">
                        <input style="display:none"> 
                        <input type="hidden" value="c43dcf57-ed35-4865-9f91-b8102c553274" name="stok">
                        <!-- 未登录 -->
                        <ul class="no1">
                            <li class="line1" id="line1"><span>账户余额 <a href="${basePath}/user/tologin.action"  class="blue">登录</a> 后可见</span></li>
                            <li class="line2">
                                <div class="gray_k">
                                    <input id="investmentAmount" value="">元
                                </div>
                                <div class="promptRed hidden"><div class="inBox fc_red"><i class="ico_all size15 img_icon s_cuo"></i>余额不足</div></div>
                            </li>
                            <p class="budget">预期收益：<span class="fc_orange" id="budgetResult">0.00</span>元</p>
                            <li class="line3" id="line3">
                                <p class="bg_line"><span onclick="jiangli()">使用奖励</span></p>
                                <!-- 未登录 -->
                                <p class="youhui2 youhui"> 只有<a style="color:#2577e3" href="${basePath}/user/tologin.action">登陆</a>后可使用</p></li>
                            <li class="line4">
                                <input id="agreement" name="agreement" type="checkbox" value="" checked="checked"> <span class="fc_6">我已阅读并同意</span>
                                    <!-- 线上 --> 
                                    <a href="{:U('Xiangmu/jiacai')}" class="blue">《借款协议》</a>
                                    <!-- 红包贷 -->
                            </li>
                            <li class="line5"><a href="javascript:;" class="btn btnWidth280 btn_orange" id="bidBtn">马上投资</a></li>
                        </ul>
                        </form>
                </div>
                </c:if>
                <!-- 已登录 -->
                <c:if test="${status ne 'logout'}">
                <div class="pad pad_two">
                     <form id="bidForm" method="post" class="nwd-formUi" action="${basePath}/user/tenderBase/initiativeTender.action">
                        <input style="display:none"> 
                        <input type="hidden" name="id" id="id" value="<%=request.getParameter("id") %>">
                        <input type="hidden" name="iscancheattender" id="iscancheattender" value="${iscancheattender}"/>
                        <input type="hidden" value="3e8baea9-42bc-468f-a1ed-01476c047ba1" name="stok">
                        <!-- 登录状态 -->
                       
                        <ul class="no1">
                        <%-- ${basePath}/user/userRecharge/rechargeList.action --%>
                            <li class="line1" id="line1"><span id="memberAmount" style="display:none">0</span><span>可用余额：${df.format(avlbalance)}<a href="javascript:void(0);" class="blue pl_10">充值</a><a href="javascript:void(0);" id="allInvestBtn" class="blue pl_10" onclick="quantou()">全投</a></span></li>
                            <li class="line2">
                                <div class="gray_k">
                                    <input id="amount" name="amount" class="" placeholder="">元
                        			<p id="tips" style="color: red;" hidden="hidden"></p>
                                </div>
                                <div class="hidden promptRed"><div class="inBox fc_red"><i class="ico_all size15 img_icon s_cuo"></i>余额不足</div></div>
                            </li>
                            <p class="budget">预期收益：<span class="fc_orange" id="budgetResult">0.00</span>元</p>
                            <!-- 使用奖励 -->
                            <div class="line3">
                                <p class="bg_line"><span onclick="jiangli()">使用奖励</span></p>
                                <!-- 已登录 -->
                                <p class="youhui1 youhui"> 您有21.91元奖励金 可供选择</p>
                                <!-- 未使用任何奖励 -->
                                <p class="youhui3 youhui"> 未使用任何奖励</p>
                            </div>
                            <li class="line4">
                                <input id="agreement" name="agreement" type="checkbox" value="" checked="checked"> <span class="fc_6">我已阅读并同意</span>
                                    <!-- 线上 --> 
                                    <!--                                7月28添加房易贷借款协议2
                                        <a href="javascript:void(0);" onclick="loanOnAgreement()" class="blue">《借款协议》</a> 
                                      -->
                                    
                                    
                                        <!-- 房易贷 -->
                                            <a href="javascript:void(0);" onclick="loanOnAgreement()" class="blue">《借款协议》</a>  
                            </li>
                            <li class="line5"><a href="javascript:;" id="bidBtn" class="btn btnWidth280 btn_orange">马上投资</a></li>
                        </ul>   
                    </form>
                </div>
                </c:if>
            </div>
            <!--right end-->
        </div>
        <!-- 模块类别 -->
        <div class="clearfix fluid mb_10">
            <div class="detail-page-body">
                <div class="inner">
                    <!-- 切换按钮 -->
                    <div class="tab-btns-wrapper clearfix">
                        <ul class="tab-btns-bg" id="nav">
                           <li rel="" class="current first_cur"></li>
                        </ul>
                    </div>
                    <!-- 模块内容 -->
                    <ul class="tab-content">
                        <li class="tab-content-introduction" id="tab_content"></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 底部-->
    <include file="Index/footer" />
    <div class="bg"></div>
    <!-- 弹窗确认 -->
    <div class="panelbox wid_w480" id="openMsg" style="display: none; position: absolute; top: 42px; left: 475px;">
        <div class="panelbg"></div>
        <div class="panelwrap">
            <div class="paneltitle">
                <span class="text">投资确认</span>
                <span class="panelclose nwd_icon nwd_icon_close pop-close"></span>
            </div>
            <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
                <div class="container" style="text-align: center;">
                   <div class="middle" id="changyong"> 
                        <div class="content" id="msgContent">您确认投资<span class="money"></span>元吗？</div> 
                        <div class="btnbox">
                            <button class="btn btnSize_1 bgyellow plus_c pop-close" id="cancle">取消</button>
                            <button class="btn btnSize_1 btn_blue plus_c" id="confirm" onclick="toTender()">确认</button>
                        </div> 
                        <div></div>
                    </div> 
                </div>
            </div>
        </div>
    </div>

    <!-- 使用奖励弹窗 -->
     <div class="panelbox wid_w480" id="jiangli_box" >
        <div class="panelbg"></div>
        <div class="panelwrap">
            <div class="paneltitle">
                <span class="text">使用奖励</span>
                <span class="panelclose nwd_icon nwd_icon_close pop-close"></span>
            </div>
            <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30" style="border-bottom: 1px solid #ddd;">
                <ul class="selbox">
                    <li onclick="xuanze(this,1)" class="active">
                        <p><i class="m2-detHideicon-pri"></i></p>
                        <p class="p2"><span></span>奖励金</p>
                    </li>
                    <li onclick="xuanze(this,2)">
                        <p><i class="m2-detHideicon-red"></i></p>
                        <p class="p2"><span></span>红&nbsp;包</p>
                    </li>
                    <li onclick="xuanze(this,3)">
                        <p><i class="m2-detHideicon-add"></i></p>
                        <p class="p2"><span></span>加息劵</p>
                    </li>
                </ul>
            </div>
            <div class="jiangli_bottom">
                <div class="xuanze1 xuanzea">
                	<c:if test="${!empty fakeVouchers}">
                		<c:forEach items="${fakeVouchers}" var="fake">
                    		<p><em class="checkbox"></em>奖励金：<span style="color:#2577e3"><em class="sum_money">${fake.redenvelope}</em>元</span></p>
                		</c:forEach>
                	</c:if>
                    <div class="user_money" style="padding: 10px 0;height: 65px;">
                        <p><input type="text" name="" value="0" style="border:1px solid #ddd;text-indent:12px;"><span onclick="allUser()" style="color:#2577e3;cursor:pointer;padding-left:10px;">全用</span></p>
<!--                         <b style="color:red;display:none;">投资满10000元才可以使用奖励金</b> -->
                    </div>
                    
                </div>
                <div class="xuanze2 xuanzea" style="display:none;">
                	<c:if test="${!empty likeVouchers}">
                		<c:forEach items="${likeVouchers}" var="like">
                    		<p><em class="checkbox"></em>红包：<span style="color:#2577e3"><em class="sum_money">${like.redenvelope}</em>元</span></p>
                		</c:forEach>
                	</c:if>
                    <p style="height:106px;">已选择红包金额：<span style="color:#2577e3"><em class="sum_money">0</em>元</span></p>
                </div>
                <div class="xuanze3 xuanzea" style="display:none;">
                	<c:if test="${!empty interestRateCoupons}">
                		<c:forEach items="${interestRateCoupons}" var="coupon">
                    		<p><em class="checkbox"></em>加息劵：<span style="color:#2577e3"><em class="sum_money">${coupon.icrate}</em></span></p>
                		</c:forEach>
                	</c:if>
                    <p style="height:106px;">加息劵带来额外收益：<span style="color:#2577e3"><em class="sum_money"></em></span></p>
                </div>
                <p class="btns"><a class="quxiao" style="background: #626262;" href="javascript:;">取消</a><a href="javascript:;">确定</a></p>
            </div>
        </div>
    </div>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
  
    // 投标界面标的详细信息
    $(document).ready(function(){
        var id="${id}";
//         $("#id").val(id);
       $.get('${basePath}/send/moremoneydetail.action?id='+id,function(d){
            var s = JSON.parse(d);
            var html1="";
            html1+='<div class="title"><i class="ico_all size24 img_icon sItem_12100" title="网络信用标"></i><a href="/xiangmu/v-ADZUNlYwVTkFY1RgUDJebAoxVWECYgJlBTIFNgBvU28=.html" class="fs16">'+s.tname+'</a><em class="fc_9">项目ID：'+s.tno+'</em></div><div class="pad"><ul class="clearfix line1"><li class="l first"><div class="t">债权总额：</div><div class="b"><span class="fs_32">'+s.tamountstr+'</span><span class="fs_18">元</span></div> </li><li class="c l_border2"><div class="t">借款年利率：<span class="tkOut tkOut_b"><i class="ico_all size15 img_icon s_wen"></i><div class="tkIn tkIn01 hidden" style="display: none;"><span class="jian"></span><span class="tknr tknr01" style="height:20px; width:210px">每计息月等额返还利息，到期归还本金</span></div></span></div><div class="b fc_orange"><span class="fs_32">'+s.interestrate+'</span> </div></li><li class="r l_border2"><div class="t">还款期限：</div><div class="b"><span class="fs_32">'+s.loantime+'</span><span class="fs_18">'+s.dayormonth+'</span></div></li></ul><ul class="clearfix line2 b_border"><li style="width:58%">保障范围：<i class="ico_all size22 img_icon bao_pos mr_10 ml_10"></i><em class="fc_9"><a href="javascript:void(0);" target="_blank">适用本金保障计划</a></em>                 </li><li>还款方式：<span><input type="hidden" id="repayment" value="2">'+s.repaymentprostr+'</span></li></ul><ul class="clearfix line2"><li style="width:58%" id="progressBar">投标进度：<span class="progressBar"><span class="barIn" style="width:100%"></span></span>'+s.progressbar+'</li><li>剩余金额：<span class="fs_16 fc_orange" id="needAmountHtml"><span id="needAmount" style="display:none">'+s.lastmoney+'</span>'+s.lastmoney+'</span>元<a href="javascript:void(0);" id="investAllFundsBtn" class="blue pl_10" onclick="quantou()">全投</a></li><li style="width:58%">投标人数：<span>5人</span></li><li>剩余时间：<span id="time" style="display:none">项目招标已截止</span><span id="timer">'+s.tendtimestr+'</span><p style="margin:-1px 0 0 70px">'+s.valuerulestr+'</p></li><li style="width:58%">温馨提示：市场有风险，投资需谨慎</li> </ul></div>'
            $("#bd_inform").html(html1);
            //$("#now_title").html(s[0].title)
            var minoncetamount=s.minoncetamount;
       /*
            $("#amount").blur(function(){
                var nowvalue=$(this).val();
                //alert(nowvalue)
                if(nowvalue%minoncetamount==0&&nowvalue!=0){
                    
                }else{
                    alert("输入金额需为最低金额的整数倍");
                    $(this).val(minoncetamount);
                }
            });*/
            $("#amount").attr("placeholder",minoncetamount);
        })
     })
    // 模块类别接口
    $(document).ready(function(){
        var id="${id}";
       
        $.get("http://my.ganjiangps.com/send/callbackmoduleType.action?id="+id,function(d){
            var s=JSON.parse(d);
            
            var nav="";
            var rel="";
            $(".first_cur").html(s[0].moduletype)
            // 循环列表
            for(var i=1;i<s.length;i++){
                nav+='<li class="" rel="'+s[i].moduletype+'" >'+s[i].moduletype+'</li>'
                console.log(s[i].moduletype)
             } $("#nav .first_cur").after(nav);
        })
    })
    // 模块内容接口
    $(document).ready(function(){
        var id="${id}"; // 项目中用这个
        console.log(id)
        var content="";
        $.post("http://my.ganjiangps.com/send/callbackitemDesc.action?moduletype=项目介绍&id="+id,function(d){
            var s=JSON.parse(d);
            var content="";
           console.log(s)
            $.each(s,function (k,v){ 
                content+='<div class="clearfix introduction-item"><div class="item-name">'+v.categoryname+'</div><div class="item-content">'+v.categorydetail+'</div></div>'
                $("#tab_content").html(content)
            })
        });
    })
    
    $(document).on('click','#nav li',function(){
        $("#nav  li").removeClass("current")
        $(this).addClass("current")
        var rel= $(this).html();
        //var id="1662"  // 这里默认id=1662做测试
        var id="${id}"; // 项目中用这个
        console.log(rel)
       // 点击获取当前点击的事件
       $.get("${basePath}/send/callbackitemDesc.action?id="+id+'&moduletype='+rel,function(d){
        
            var s = JSON.parse(d);
            var content="";
            console.log(s)
            $.each(s,function (k,v){ 
                console.log(v.categorydetail)
                if(v.categorydetail==undefined){
                    v.categorydetail='';
                }
                content+='<div class="clearfix introduction-item"><div class="item-name">'+v.categoryname+'</div><div class="item-content">'+v.categorydetail+'</div></div>'
                $("#tab_content").html(content)
            })
            $("#tab_content").html(s[0].content);
        })
    });
    //默认是第一个加类




	showDiv();
	function showDiv(obj) {
		$(obj).show();
		center(obj);
		$(window).scroll(function() {
			center(obj);
		});
		$(window).resize(function() {
			center(obj);
		});
	}

	function center(obj) {
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $(obj).height();
		var popupWidth = $(obj).width();
		$(obj).css({
			"position" : "absolute",
			"top" : (windowHeight - popupHeight) / 2 + $(document).scrollTop(),
			"left" : (windowWidth - popupWidth) / 2
		});
	}

	//删除
	$(".pop-close,.btns .quxiao").click(function() {
		$("#openMsg,.bg,#jiangli_box").hide();
		$(".ico_all.size15.img_icon.s_wen").show()
	});
	//实名认证
	$(".line5").click(function() {
		var money = $("#amount").val();
		$(".money").text(money);
		$("#openMsg,.bg").show();
		showDiv("#openMsg");
		$(".ico_all.size15.img_icon.s_wen").hide();
	})

	/** 立即投资 */
	function toTender() {
		$("#openMsg,.bg").hide();
		var url = "${basePath}/user/tenderBase/tenderCheck.action";
		var param = $('#bidForm').serializeArray();
		var callback = function(data) {
			var json = $.parseJSON(data);
			if (json.info == "logout") {
				window.location.href = "${basePath}/user/tologin.action";
			} else if (json.info == "success") {
				$("#bidForm").submit();
			} else {
				$("#tips").show();
				$("#tips").text(json.info);
			}
		};
		$.post(url, param, callback);
	}

	//全投
	function quantou() {
		var qtmoney = document.querySelector("#needAmount").innerHTML;
		var jiner = document.querySelector("#amount").value = qtmoney;
	};
	//奖励弹窗  
	function jiangli() {
		$("#jiangli_box,.bg").show();
		$(".ico_all.size15.img_icon.s_wen").hide();
		showDiv("#jiangli_box");
	};
	//奖励选择
	function xuanze(obj, lei) {
		$(".xuanzea").hide();
		$(".xuanze" + lei).show();
	};
	$(".selbox li").click(function() {
		if ($(this).hasClass("active")) {
			$(this).removeClass("active");
		} else {
			$(this).addClass("active");
		}
	});
	//奖励金全用
	function allUser() {
		var sum = $(".xuanze1 .sum_money").html();
		var user_money = $(".user_money input[type=text]").val(sum);
		var touzhi = 300;
		if (touzhi < 10000) {
			$(".user_money b").show();
		} else {
			$(".user_money b").hide();
		}
	};
	//奖励选择
	$(document).on("click", ".xuanzea p", function() {
		if ($(this).hasClass("active")) {
			$(this).removeClass("active");
		} else {
			$(this).addClass("active");
		}
	})
</script>
</body>
</html>