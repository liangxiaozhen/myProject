<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>干将网贷，安全透明灵活的网贷平台、网上投资理财、网上贷款借款、投融资信息中介平台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%-- 		<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"/>  --%>
<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"/> 
<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css"/>		
<link href="${basePath}/resources/resource/Css/index.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/resource/Css/claim.css" rel="stylesheet" type="text/css"/>
</head>
<style type="text/css">
.Code.weixin_code img{ width: 80px; height: 80px;}
.Code,.police_log{background: none}
.lianjie ul li{display: inline-block;}
.lianjie ul li a{    height: 30px;line-height: 30px;color: #666;font-size: 14px;padding: 0 10px;border-right: 1px solid #666;}
</style>
	<body class="newindexbody">
		<div class="foot pad_b10">
			<div class="clearfix pad_l20 pad_r20 wt_1180 pad_t30">
				<div class="fl wid_w600 ">
					<ul class="clearfix fNav">
						<li class="about1">
							<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'关于我们'))}"></a><br>
						</li>
						<li class="about2 ">
							<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'安全保障'))}"></a><br>
						</li>
						<li class="about3">
							<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'账户安全'))}"></a><br>
						</li>
						<li class="about4">
							<a class="span01" target="_blank" href="{:U('Help/helps',array('itiname'=>'用心服务'))}"></a><br>
						</li>
					</ul>
				</div>
				 <!-- 热线电话 -->
				<div class="fl">
					<ul class="host_phone">
					</ul>
				</div>
				<div class="fr">
					<ul class="clearfix ewm">
			        </ul>
				</div>
			</div>
			<!-- 友情链接 -->
			<div class="foot pad_b10 lianjie">
				<ul class="clearfix pad_l20 pad_r20 wt_1180 pad_t30 yq_lj" style="text-align: center;">
				</ul>
			</div>
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
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/newindex.js"></script>	
<script type="text/javascript">
   $(document).ready(function(){
   		
        //二维码
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=87",function(a){
            var s = JSON.parse(a);
            var htmld="";
            //console.log(s)
            $.each(s.list,function (k,v){
             	htmld+='<li class="fl mar_r30 pad_t10"><p class="Code weixin_code"><img src="'+v.titlepic+'"></p><p class="fc_9 pad_t5">'+v.title+'</p></li> '
             })
            $(".ewm").html(htmld);
        })
        //热线电话
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=86",function(a){
            var s = JSON.parse(a);
            var htmlb="";
           // console.log(s)
            htmlb+='<li class="line1 fc_6"><span class="fs_16 pr_20 mar_r10">'+s.itiname+'</span>'+s.list[0].subtitle1+'</li><li class="line2 pad_t15 pad_b15"><span class="fs_32 bold fc_6 pr_20">'+s.list[0].title+'</span></li>'
            $(".host_phone").html(htmlb);
        })
        //关于我们1
        $.getJSON("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=82",function(a){
           // var s = JSON.parse(a);
            var s=a;
            var htmlc="";
           // console.log(s)
            $.each(s.list,function (k,v){
            	//console.log(v.title)
	            htmlc+='<a class="a01" target="_blank" href="/index.php/Home/Help/helps/itino=82/title/'+v.title+'">•'+v.title+'</a><br>'
	        })
	        $(".about1").append(htmlc);
	        $(".about1 .span01").html(s.itiname)
        })
        //安全保障
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=83",function(a){
            var s = JSON.parse(a);
            var htmld="";
            //console.log(s)
            $.each(s.list,function (k,v){
            	htmld+='<a class="a01" target="_blank" href="/index.php/Home/Help/helps/itino=83/title/'+v.title+'">•'+v.title+'</a><br>'
	        })
	        $(".about2").append(htmld);
	        $(".about2 .span01").html(s.itiname)
        })
        //帮助中心
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=84",function(a){
            var s = JSON.parse(a);
            var htmle="";
            //console.log(s)
            $.each(s.list,function (k,v){
            	htmle+='<a class="a01" target="_blank" href="/index.php/Home/Help/helps/itino=84/title/'+v.title+'">•'+v.title+'</a><br>'
	        })
	        $(".about3").append(htmle);
	        $(".about3 .span01").html(s.itiname)
        })
        //用心服务
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=85",function(a){
            var s = JSON.parse(a);
            var htmlf="";
           // console.log(s)
            $.each(s.list,function (k,v){
            	
	            htmlf+='<a class="a01" target="_blank" href="/index.php/Home/Help/helps/itino=85/title/'+v.title+'">•'+v.title+'</a><br>'
	        })
	        $(".about4").append(htmlf);
	        $(".about4 .span01").html(s.itiname)
        })
         //友情链接
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=88",function(a){
            var s = JSON.parse(a);
            var html="";
            $.each(s.list,function (k,v){
	            html+='<li><a href="'+v.hyperlink+'">'+v.title+'</a></li>'
	        })
            $(".yq_lj").html(html);
        })
         //页脚资讯
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=90",function(a){
            var s = JSON.parse(a);
            var html="";
             //console.log(s)
            $.each(s.list,function (k,v){
	            html+='<a class="police_log police_img4 mar_r10" target="_blank" href="'+v.hyperlink+'"><img src="'+v.titlepic+'" width="93" height="36" title="'+v.title+'"></a> '
	        })
            $("#zhixun").html(html);
        })
         //页脚备案
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=89",function(a){
            var s = JSON.parse(a);
            var html="";
             //console.log(s)
            $("#beian").html(s.list[0].resume);
        })

        
   })
</script>
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
							
							<form action="uploadMemberPhoto.do" method="post" enctype="multipart/form-data" name="memberPhoto" id="mbPhoto">
							
								<input type="text" name="textfields" id="mbphoto" class="txt ui-input w200-input" />
								<input type="button" class="btn_up button button-w120 mar_l10" value="浏览..." />
								<input type="file" name="file" class="file" size="23" style="width:340px;" onchange="document.getElementById('mbphoto').value=this.value" />
								<input type="button" class="btn_up button button-w120 mar_l10" name="photoSubmit" onclick="savembphoto()" value="点击上传" />
								
							</form>
							</div>
	
							<p class="fc_9 pad_t15">注意：请选择jpg、gif、bmp、png、jpeg格式，尺寸为200*200，<br/><i style="displany:inline-block; width:42px;">&nbsp;</i>且文件大小不超过2M的图片。</p>
	
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
</div>
<div class="bg"></div>

<script type="text/javascript">
	// $("#myphototip").click(function(){
	// 	$("#myphoto-pop-manage").show();
	// 	$(".bg").show();
	// })
	$("#closeImg").click(function() {
		$("#myphoto-pop-manage").hide();
		$(".bg").hide();
	});
	// 弹窗居中
	$(document).ready(function(){
		showDiv();
			function showDiv(obj){
			 $(obj).show();
			 center(obj);
			 $(window).scroll(function(){
			  center(obj);
			 });
			 $(window).resize(function(){
			  center(obj);
			 }); 
			}
		function center(obj){
			 var windowWidth = document.documentElement.clientWidth;   
			 var windowHeight = document.documentElement.clientHeight;   
			 var popupHeight = $(obj).height();   
			 var popupWidth = $(obj).width();    
			 $(obj).css({   
			  "position": "absolute",   
			  "top": (windowHeight-popupHeight)/2+$(document).scrollTop(),   
			  "left": (windowWidth-popupWidth)/2   
			 });  
			}
			//$(".bg").show();
		//删除
		$("#myphototip").click(function(){
			$("#myphoto-pop-manage,.bg").show();
			showDiv("#myphoto-pop-manage");
		})
	})
</script>
<!-- 合作伙伴 -->
 <script type="text/javascript">
  	var hdlengt=$(".ful li").length;//获取个数  
	var hdwidth=$(".ful li").width()+6;//获取宽度  
	var hd=hdlengt*hdwidth;//宽度乘以个数  
	var wwidth=(hdlengt-4)*hdwidth;//个数减去显示的4个在乘以宽度  
	$(".ful").width(hd)//获取的宽度赋值给要显示的宽度  
	var hdjs=$(".hdjs_div ul");//变量  
	if(hdjs.scrollLeft()==0){ //如果边距为0  
	    $(".next").remove();//本身删除  
	    $(".hdjs_div").parent().append("<a href='javascript:;' class='nexta'></a>")//添加一个标签  
	}  
	function tpgd(id){
	  hdjs.animate({  
	    'left':-188*id 
	  })  
	}  
	$(".next").live("click",function(){ //点击事件  
	  id-- //每次点击id减值  
	  if(id==0){ // id如果等于0  
	    $(this).remove(); // 本身删除  
	    $(".hdjs_div").parent().append("<a href='javascript:;' class='nexta'></a>")//添加一个标签  
	  }  
	  $(".preva").addClass("prev");//添加一个为prev的样式  
	  $(".prev").removeClass("preva");//删除样式  
	  ii-- //每次点击ii减值  
	  tpgd(id) //执行这个函数,(把id的值传给函数)  
	    
	})  
	var id //命名一个变量  
	var ii=$(".ful li").index(); //ii变量  
	var ii=1  //ii为1  
	$(".prev").live("click",function(){ //点击事件  
	  id=ii++  //id每次点击加一  
	  if(hdwidth*id==wwidth){ //判断语句，如果宽度乘以点击的个数的值等于wwidth  
	    $(this).remove(); //删除本身  
	    $(".hdjs_div").parent().append("<a href='javascript:;' class='preva'></a>") //添加标签  
	    //return false  
	  }  
	  $(".nexta").addClass("next"); //添加样式  
	  $(".next").removeClass("nexta"); //删除样式  
	  tpgd(id)  //执行这个函数,(把id的值传给函数)  
	})  
	$.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=48",function(a){
        var s = JSON.parse(a);
        var html="";
        $.each(s.list,function (k,v){
         	var names=v.title;
         	// html+='<li><a href="'+v.hyperlink+'" title="'+v.title+'"><span class="firedlink"><img src="'+v.titlepic+'" alt="'+v.title+'" /></span></a><br></li>'
         	html+='<li>  <a href="'+v.hyperlink+'#">  <span class="spag"><img src="'+v.titlepic+'" />   </a>  </li> '
         })
        $(".ful").html(html);
        $(".huoban").html(s.itiname)
    })
  </script>
</body>
</html>