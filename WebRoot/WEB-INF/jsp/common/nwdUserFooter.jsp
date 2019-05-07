<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
	.Code.weixin_code img{ width: 80px; height: 80px;}
	.Code,.police_log{background: none}
	.lianjie ul li{display: inline-block;}
	.lianjie ul li a{    height: 30px;line-height: 30px;color: #666;font-size: 14px;padding: 0 10px;border-right: 1px solid #666;}
</style>
	<body class="newindexbody">

	<div class="foot pad_b10" style="border: 1px solid red;margin-top: auto;">
	<div class="firedlink pad_t15 clearfix">
		<div class="nwd_main clearfix" id="firedBox">
			<div class="fs_18 fc_3">
				合作伙伴 <a href="javascript:;" class="actbtn perbtn fired_icon fired_icon_pre vertical_middle"></a> <a href="javascript:;" class="actbtn nextbtn fired_icon fired_icon_next vertical_middle"></a> 
			</div>
			<div class="play-list-con firedBox_con">
				<ul class="firdlinkMsg pad_b10 pad_t10 clearfix" style="width: 5320px;" id="partners">
					<!-- <li><a href="'+v.hyperlink+'" title="'+v.title+'"><span class="firedlink"><img src="'+v.titlepic+'" alt="'+v.title+'" /></span></a><br></li> -->
					
				</ul>
			</div>
		</div>
	</div>
	<div class="clearfix pad_l20 pad_r20 wt_1180 pad_t30">
		<div class="fl wid_w600 ">
			<ul class="clearfix fNav">
				<li>
					<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'关于我们'))}">关于我们</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'公司简介'))}">•公司简介</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'业绩报告'))}">•业绩报告</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'法律法规'))}">•法律法规</a><br>
				</li>
				<li>
					<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'安全保障'))}">安全保障</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'本金保障'))}">•本金保障</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'风控保障'))}">•风控保障</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'账户安全'))}">•账户安全</a><br>
				</li>
				<li>
					<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'账户安全'))}">账户安全</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'如何投资'))}">•如何投资</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'如何借款'))}">•如何借款</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'网站资费'))}">•网站资费</a><br>
				</li>
				<li>
					<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'用心服务'))}">用心服务</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'联系我们'))}">•联系我们</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'网站地图'))}">•网站地图</a><br>
					<a class="a01" target="_blank" href="{:U('Help/helps',array('itiname'=>'服务地区'))}">•服务地区</a><br>
				</li>
			</ul>
		</div>
		 <!-- 热线电话 -->
		<div class="fl">
			<ul class="host_phone">
				<!-- <li class="line1 fc_6">
					<span class="fs_16 pr_20 mar_r10">热线电话</span>(服务时间 09 : 00 - 21 : 00 )
				</li>
				<li class="line2 pad_t15 pad_b15">
					<span class="fs_32 bold fc_6 pr_20">400-7910-888</span>
				</li> -->
			</ul>
		</div>
		<div class="fr">
			<ul class="clearfix ewm">
	        </ul>
		</div>
	</div>
			</div>
		</div>
	</div>
</div>
<!-- 友情链接 -->
<div class="foot pad_b10 lianjie">
	<ul class="clearfix pad_l20 pad_r20 wt_1180 pad_t30 yq_lj" style="text-align: center;">
	</ul>
</div>
<div class="clearfix foot2">
	<div class="clearfix wt_1180 pad_t20 pad_b20">
		<!-- 页脚咨询 -->
		<div class="mar_l20 txt_center" id="zhixun">
			
		</div>
		<!-- 页脚备案 -->
		<div class="pad_t10 pad_b10 mar_r30 txt_center">
			<p class="fs_12 fc_4c Numfont"  id="beian">
				
			</p>
		</div>
	</div>
</div>

<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/new.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/newindex.js"></script>	
<script type="text/javascript">
   $(document).ready(function(){
   		//合作伙伴
     	$.get("http://116.30.195.26:8881/ptpjx/ui/imagetextsetting/getbyitiname.action?itiname=合作伙伴",function(a){
            var s = JSON.parse(a);
            var html="";
            $.each(s,function (k,v){
             	var names=v.title;
             	html+='<li><a href="'+v.hyperlink+'" title="'+v.title+'"><span class="firedlink"><img src="'+v.titlepic+'" alt="'+v.title+'" /></span></a><br></li>'
             })
            $("#partners").html(html);
        })
        //二维码
        $.get("http://116.30.195.26:8881/ptpjx/ui/imagetextsetting/getbyitiname.action?itiname=二维码",function(a){
            var s = JSON.parse(a);
            var htmld="";
            //console.log(s)
            $.each(s,function (k,v){
             	htmld+='<li class="fl mar_r30 pad_t10"><p class="Code weixin_code"><img src="'+v.titlepic+'"></p><p class="fc_9 pad_t5">'+v.title+'</p></li> '
             })
            $(".ewm").html(htmld);
        })
        //热线电话
        $.get("http://116.30.195.26:8881/ptpjx/ui/imagetextsetting/getbyitiname.action?itiname=热线电话",function(a){
            var s = JSON.parse(a);
            var htmlb="";
            //console.log(s)
            htmlb+='<li class="line1 fc_6"><span class="fs_16 pr_20 mar_r10">'+s[0].itiname+'</span>'+s[0].subtitle1+'</li><li class="line2 pad_t15 pad_b15"><span class="fs_32 bold fc_6 pr_20">'+s[0].title+'</span></li>'
            $(".host_phone").html(htmlb);
        })
        //关于我们
        $.get("http://116.30.195.26:8881/ptpjx/ui/imagetextsetting/getbyitiname.action?itiname=关于我们&title=公司简介",function(a){
            var s = JSON.parse(a);
            var htmlc="";
            //console.log(s)
            htmlc+='<li class="line1 fc_6"><span class="fs_16 pr_20 mar_r10">'+s[0].itiname+'</span>'+s[0].subtitle1+'</li><li class="line2 pad_t15 pad_b15"><span class="fs_32 bold fc_6 pr_20">'+s[0].title+'</span></li>'
            $("").html(htmlc);
        })
         //友情链接
        $.get("http://116.30.195.26:8881/ptpjx/ui/imagetextsetting/getbyitiname.action?itiname=友情链接",function(a){
            var s = JSON.parse(a);
            var html="";
            $.each(s,function (k,v){
	            html+='<li><a href="'+v.hyperlink+'">'+v.title+'</a></li>'
	        })
            $(".yq_lj").html(html);
        })
         //页脚资讯
        $.get("http://116.30.195.26:8881/ptpjx/ui/imagetextsetting/getbyitiname.action?itiname=页脚资讯",function(a){
            var s = JSON.parse(a);
            var html="";
            $.each(s,function (k,v){
	            html+='<a class="police_log police_img4 mar_r10" target="_blank" href="'+v.hyperlink+'"><img src="'+v.titlepic+'" width="93" height="36" title="'+v.title+'"></a> '
	        })
            $("#zhixun").html(html);
        })
         //页脚备案
        $.get("http://116.30.195.26:8881/ptpjx/ui/imagetextsetting/getbyitiname.action?itiname=页脚备案",function(a){
            var s = JSON.parse(a);
            var html="";
            $("#beian").html(s[0].resume);
        })
        
   })
   
</script>