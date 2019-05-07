<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>投资项目_新标专区_散标专区 - 干将网贷</title>
<meta name="keywords" content="投资项目,新标,散标,干将网贷">
<meta name="description" content="干将网贷新标专区致力于为广大投资者提供准确及时以及全面专业的个性化个人投资理财新标、散标，让投资者达到收益最大化。"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%-- <link href="${basePath}/resources/resource/Css/common.css" rel="stylesheet" type="text/css">  --%>
<%-- <link href="${basePath}/resources/resource/Css/sea.css" rel="stylesheet" type="text/css"> --%>
<%-- <link href="${basePath}/resources/resource/Css/style.css" rel="stylesheet" type="text/css"> --%>
<link href="${basePath}/resources/resource/Css/new.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
.first{background: none!important}
.totall .line_1 label{position: relative;z-index: 6}
.totall .type input[type='checkbox'] {
    display: inline-block;
    width: 66px;
    position: absolute;
    /* visibility: hidden; */
    z-index: 9;
    opacity: 0;
    filter:alpha(opacity=0); /* IE */  
    -moz-opacity:0; /* 老版Mozilla */  
    -khtml-opacity:0; /* 老版Safari */  
    opacity: 0; /* 支持opacity的浏览器*/  
}
#first,#first2,#first3{width: 40px;}
</style>
<body class="page212">

<!-- 公告头部 -->
<%@ include file="/WEB-INF/jsp/front/About/head.jsp" %>

<div class="clearfix mb_10 mt_10 columns">
	<div class="in">
    	<ul class="clearfix fl l">
	    		<li class="mr_30">
                <a href="{:U('Xiangmu/financialDetail')}">嘉财有道</a>
            	</li>
            <li class="mr_30 ">
                <a href="{:U('Xiangmu/newPeople')}">新手专区</a>
            </li>
            <li class="mr_30 cur">
                <a href="{:U('Xiangmu/xiangmu')}">新标专区</a>
            </li>
            <li class="mr_30">
                <a href="{:U('Xiangmu/zhuanrang')}">转让专区</a>
            </li>
        </ul>
    </div>

</div>
<div class="clearfix main">
	<!--left s-->
    <div class="fl fluid mr_10 item_side">
    	<!--nav s-->
        <div class="module">
        	<ul class="mb_10 item_nav">
                <li>
					<a href="javascript:;" class="cur 
							bidTypeChose">
						<input type="hidden" value="-1">
                    	<span class="fc_3">全部</span>
                    	<span class="airal fc_orange">(40)</span><br>
                    </a>
                </li>
          				<li>
							<a href="javascript:;" class="
							bidTypeChose">
								<input type="hidden" value="9">
		                    	<span class="fc_3">培训成长标</span>
		                    	<span class="airal fc_orange">(0)</span><br>
		                        	<span class="fc_9">爱心助学，安全保障</span>
		                    </a>
		                </li>
          				<li>
							<a href="javascript:;" class="
							bidTypeChose">
								<input type="hidden" value="1">
		                    	<span class="fc_3">网络信用标</span>
		                    	<span class="airal fc_orange">(40)</span><br>
		                        	<span class="fc_9">网络征信，高息便捷</span>
		                    </a>
		                </li>
          				<li>
							<a href="javascript:;" class="
							bidTypeChose">
								<input type="hidden" value="2">
		                    	<span class="fc_3">实地信用标</span>
		                    	<span class="airal fc_orange">(0)</span><br>
		                        	<span class="fc_9">实地征信，高息保障</span>
		                    </a>
		                </li>
          				<li>
							<a href="javascript:;" class="
							bidTypeChose">
								<input type="hidden" value="4">
		                    	<span class="fc_3">房产抵押标</span>
		                    	<span class="airal fc_orange">(0)</span><br>
		                        	<span class="fc_9">房产抵押，安全无忧</span>
		                    </a>
		                </li>
            </ul>
        </div>
        <!--nav e-->
        <div class="item_banner">
        	<a href="/member/autoLoan.html"><img src="${basePath}/resources/resource/Picture/item_banner3.png" width="200" alt=""></a>
        </div>
    </div>
    <!--left e-->
    <!--right s-->
    <div class="fl item_main">
    	<div class="item_banner2">
        	<div class="fr num_box">
            	<span class="fc_white">昨日成交金额：</span><br>
                <span class="fs_18 airal bold s">23,092,100.00</span><span class="fs_18 s">元</span>
            </div>
        </div>
        <form method="post" id="for">
        	<!--筛选 s-->
            <div class="totall">
                <div class="clearfix type" id="tbqxDiv">
                    <div class="fl">
                        <em class="fc_9 mr_10">投标期限</em>
                      		<label for="all_1" class="all first" id="timetypeAll"><input class="input" name="loantime" type="checkbox" value="-1" checked="checked" id="first" onclick="first1ko()">全部</label>
                    </div>
                    <div class="fl line_1">
                      	<label for="a5" id=""><input class="input" onclick="ko()" name="loantime" type="checkbox" value="0,1">0~1个月</label>
                      	<label for="a5" class="" id=""><input onclick="ko()" class="input" name="loantime" type="checkbox" value="2,3">2~3个月</label>
                      	<label for="a6" class="" id=""><input onclick="ko()" class="input" name="loantime" type="checkbox" value="4,5,6">4~6个月</label>
                      	<label for="a7" class="" id=""><input onclick="ko()" class="input" name="loantime" type="checkbox" value="7,8,9,10,11,12">7~12个月</label>
                      	<label for="a8" class="" id=""><input onclick="ko()" class="input" name="loantime" type="checkbox" value="-2">12个月以上</label>
                    </div>                                
                </div>
                <div class="itmo_off">
                	<div class="on_off"><a href="javascript:;" class="a_on">更多<i></i></a></div>
                </div>
                <div class="clearfix type on_off_box hidden" id="hkfsDiv">
                    <div class="fl">
                        <em class="fc_9 mr_10">还款方式</em>
                      		<label for="all_2" class="all first" id="retypeAll"><input class="input" name="repaymentpro" type="checkbox" value="-1" checked="checked" id="first2" onclick="first2ko()">全部</label>	
                    </div>
                    <div class="fl line_1">
                      		<label for="a9" class="" id=""><input class="input" onclick="ko2()" name="repaymentpro" type="checkbox" value="3">等额本息</label>
                            <label for="a9" class="" id=""><input class="input" onclick="ko2()"  name="repaymentpro" type="checkbox" value="2">等额本金</label>
                      		<label for="a10" class="" id=""><input class="input" onclick="ko2()" name="repaymentpro" type="checkbox" value="1">一次还本付息</label> 
                            <label for="a11" class="" id=""><input class="input" onclick="ko2()" name="repaymentpro" type="checkbox" value="4">按期付息到期还本</label> 
                    </div>
                </div>
                <div class="clearfix type on_off_box hidden" id="zbztDiv">
                    <div class="fl">
                        <em class="fc_9 mr_10">招标状态</em>
    						<label for="all_3" class="all first" id="stypeAll"><input checked="checked" name="tstatus" class="input" type="checkbox" value="-1" onclick="first3ko()" id="first3">全部</label>
                    </div>
                    <div class="fl line_1">
                          	<label for="a11" id=""><input class="input" onclick="ko3()" name="tstatus" type="checkbox" value="3">正在招标</label>
                          	<label for="a13" class="" id=""><input class="input" onclick="ko3()" name="tstatus" type="checkbox" value="5">成功借款</label>
                    </div>                         
                </div>
            </div>
            <!--新标专区-->
            <div class="fluid">
                <!-- 内容 -->
                <div id="more"></div>
            </div>
        </form>
        <!--item e-->
        <div class="fy">
            <!--分页 str -->
            <div class="pageout">
      	        <span class="first"><i></i>上一页</span>
          		  <span class="active">1</span>
          			<a href="">2</a>
          			<a href="">3</a>
          			<a href="">4</a>
          			<a href="">5</a>
                <a href="" title="下一页" class="last">下一页<i></i></a>  
      	   </div>
    			<!--分页 end -->
    		  <input type="hidden" value="1" id="curPage">
      </div>
    
    </div>
    <!--right e-->    	
</div>
<!--foot-->

<!-- 底部开始 -->
<%@ include file="/WEB-INF/jsp/front/Index/footer.jsp" %>
<!-- 底部结束 -->
<%-- <script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery.js"></script> --%>
<%-- <script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script> --%>
<script type="text/javascript">

     // 标的列表
    $(document).ready(function(){
         $.get("${basePath}/send/moremoneyallpage.action",function(d){
         var data;
         var html="";
         var m= eval("data="+d); 
        // console.log(m);
         $.each(m,function (k,v){
             html+='<div class="mb_10 item_out"><a href="javascript:;"><div class="add_bor item_pos_box"><span class="b_jingdu b_jd80">'+v.progressbar+'</span><a target="_blank" href="${basePath}/user/tenderBase/investing.action?id='+v.id+'" class="btn btnSize_1 btn_orange mt_5">立即投标</a></div><div class="clearfix module item_in"><div class="l"><h3 class="tit"><i class="ico_all size24 img_icon sItem_12100" title="网络信用标"></i><span class="fc_9">'+v.tname+'</span><i class="ico_all size22 img_icon bao_pos mr_10 ml_10"></i><em class="fc_9">适用本金保障计划</em></h3><ul class="clearfix item_con"><li class="w1"><span class="fc_6">借款年利率</span><span class="airal fc_orange fs_28">'+v.interestrate+'</span></li><li class="w2"><span class="fc_6">投资期限</span><span class="airal fs_30">'+v.loantime+'</span><span>'+v.dayormonth+'</span></li><li class="w3"><span class="fc_6">投资金额</span><span class="airal fs_20">'+v.tamountstr+'</span></li></ul></div></div></a></div>'
            })
           $("#more").html(html);
        })
    })
// 更多
 $(".on_off").click(function(){
    $("#hkfsDiv,#zbztDiv").toggle();
 })
 // 选中状态
 $(".totall label").click(function(){
    if($(this).hasClass("cur")){
        $(this).removeClass("cur").find("input[type='checkbox']").attr("checked",false);
    }else{
        $(this).addClass("cur").find("input[type='checkbox']").attr("checked",true);
    }
 })
 
// 筛选 传参
$(function(){
    $("#first").attr ("checked", true);
    $("#first2").attr ("checked", true);
    $("#first3").attr ("checked", true);
    
     $.post("${basePath}/send/moremoneyclk.action",$("#for").serialize(),function(data){
          //console.log(data);
          var s = JSON.parse(data);
          var html="";
          $.each(s,function (k,v){
          html+='<div class="mb_10 item_out"><a href="javascript:;"><div class="add_bor item_pos_box"><span class="b_jingdu b_jd80">'+v.progressbar+'</span><a target="_blank" href="${basePath}/user/tenderBase/investing.action?id='+v.id+'" class="btn btnSize_1 btn_orange mt_5">立即投标</a></div><div class="clearfix module item_in"><div class="l"><h3 class="tit"><i class="ico_all size24 img_icon sItem_12100" title="网络信用标"></i><span class="fc_9">'+v.tname+'</span><i class="ico_all size22 img_icon bao_pos mr_10 ml_10"></i><em class="fc_9">适用本金保障计划</em></h3><ul class="clearfix item_con"><li class="w1"><span class="fc_6">借款年利率</span><span class="airal fc_orange fs_28">'+v.interestrate+'</span></li><li class="w2"><span class="fc_6">投资期限</span><span class="airal fs_30">'+v.loantime+'</span><span>'+v.dayormonth+'</span></li><li class="w3"><span class="fc_6">投资金额</span><span class="airal fs_20">'+v.tamountstr+'</span></li></ul></div></div></a></div>'
          })
           $("#more").html(html);
       });
 })
    function ko(){
      $("#first").removeAttr("checked").parent("label").removeClass("first");
      $.post("${basePath}/send/moremoneyclk.action",$("#for").serialize(),function(data){
          var s = JSON.parse(data);
          var html="";
          $.each(s,function (k,v){
          html+='<div class="mb_10 item_out"><a href="javascript:;"><div class="add_bor item_pos_box"><span class="b_jingdu b_jd80">'+v.progressbar+'</span><a target="_blank" href="${basePath}/user/tenderBase/investing.action?id='+v.id+'" class="btn btnSize_1 btn_orange mt_5">立即投标</a></div><div class="clearfix module item_in"><div class="l"><h3 class="tit"><i class="ico_all size24 img_icon sItem_12100" title="网络信用标"></i><span class="fc_9">'+v.tname+'</span><i class="ico_all size22 img_icon bao_pos mr_10 ml_10"></i><em class="fc_9">适用本金保障计划</em></h3><ul class="clearfix item_con"><li class="w1"><span class="fc_6">借款年利率</span><span class="airal fc_orange fs_28">'+v.interestrate+'</span></li><li class="w2"><span class="fc_6">投资期限</span><span class="airal fs_30">'+v.loantime+'</span><span>'+v.dayormonth+'</span></li><li class="w3"><span class="fc_6">投资金额</span><span class="airal fs_20">'+v.tamountstr+'</span></li></ul></div></div></a></div>'
          })
           $("#more").html(html);
        });
    }
    function ko2(){
      $("#first2").removeAttr ("checked").parent("label").removeClass("first");;
      $.post("${basePath}/send/moremoneyclk.action",$("#for").serialize(),function(data){
           //console.log(data);
           var s = JSON.parse(data);
          var html="";
          $.each(s,function (k,v){ 
          html+='<div class="mb_10 item_out"><a href="javascript:;"><div class="add_bor item_pos_box"><span class="b_jingdu b_jd80">'+v.progressbar+'</span><a target="_blank" href="${basePath}/user/tenderBase/investing.action?id='+v.id+'" class="btn btnSize_1 btn_orange mt_5">立即投标</a></div><div class="clearfix module item_in"><div class="l"><h3 class="tit"><i class="ico_all size24 img_icon sItem_12100" title="网络信用标"></i><span class="fc_9">'+v.tname+'</span><i class="ico_all size22 img_icon bao_pos mr_10 ml_10"></i><em class="fc_9">适用本金保障计划</em></h3><ul class="clearfix item_con"><li class="w1"><span class="fc_6">借款年利率</span><span class="airal fc_orange fs_28">'+v.interestrate+'</span></li><li class="w2"><span class="fc_6">投资期限</span><span class="airal fs_30">'+v.loantime+'</span><span>'+v.dayormonth+'</span></li><li class="w3"><span class="fc_6">投资金额</span><span class="airal fs_20">'+v.tamountstr+'</span></li></ul></div></div></a></div>'
          })
           $("#more").html(html);
        });
    }
    function ko3(){
      $("#first3").removeAttr ("checked").parent("label").removeClass("first");;
      $.post("${basePath}/send/moremoneyclk.action",$("#for").serialize(),function(data){
           console.log(data);
           var s = JSON.parse(data);
          var html="";
          $.each(s,function (k,v){ 
          html+='<div class="mb_10 item_out"><a href="javascript:;"><div class="add_bor item_pos_box"><span class="b_jingdu b_jd80">'+v.progressbar+'</span><a target="_blank" href="${basePath}/user/tenderBase/investing.action?id='+v.id+'" class="btn btnSize_1 btn_orange mt_5">立即投标</a></div><div class="clearfix module item_in"><div class="l"><h3 class="tit"><i class="ico_all size24 img_icon sItem_12100" title="网络信用标"></i><span class="fc_9">'+v.tname+'</span><i class="ico_all size22 img_icon bao_pos mr_10 ml_10"></i><em class="fc_9">适用本金保障计划</em></h3><ul class="clearfix item_con"><li class="w1"><span class="fc_6">借款年利率</span><span class="airal fc_orange fs_28">'+v.interestrate+'</span></li><li class="w2"><span class="fc_6">投资期限</span><span class="airal fs_30">'+v.loantime+'</span><span>'+v.dayormonth+'</span></li><li class="w3"><span class="fc_6">投资金额</span><span class="airal fs_20">'+v.tamountstr+'</span></li></ul></div></div></a></div>'
          })
           $("#more").html(html);
        });
    } 
    //选择全部,取消选择项
    function first3ko(){
        $("input[name='repaymentpro']").each(function () {
          $(this).attr('checked',false);
          });
    }
    function first2ko(){
        $("input[name='tstatus']").each(function () {
          $(this).attr('checked',false);
          });
    } 
    function first1ko(){
        $("input[name='loantime']").each(function () {
          $(this).attr('checked',false);
          });
    } 


// $.get("http://my.ganjiangps.com/send/moremoneyallpage.action?pageNum=1&pageSize=1",function(a){
//         var s = JSON.parse(a);
//         var htmls="";
//         console.log(s)
//         // $.each(s,function (k,v){
//         //   htmls+='<li><a href="'+v.hyperlink+'"><i class=""><img src="'+v.titlepic+'"></i><span><strong>'+v.title+'</strong>'+v.subtitle1+'<br>'+v.subtitle2+'</span></a></li>'
//         //  })
//         // $("#cause").html(htmls);
//     })



</script>

</body>
</html>