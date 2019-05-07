var num=0;
$(function(){
	var text=$('#menuWarp ul.asideList li');
	var tab=$('.container .loanProducts');

	setTimeout(function(){
		var location = window.location.href;
		var href= location+"";
		var href_part=href.split("?");
		var numB=(href_part[1]*1);
		num=numB;

		$("#save").click(function(){
			if(num==0){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "嘉保贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');
			}else if(num==1){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "嘉英贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');
			}else if(num==2){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "嘉房贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');
			}else if (num==3){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "嘉车贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');
			}else if (num==4){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "宅抵贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');
			}else if(location=="http://www.niwodai.com/daikuan/jiekuan.html"){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "嘉保贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');
			}else if(numB==6){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "嘉保贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');

			}else if(numB==7){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "宅抵贷立即申请3";
				s.events = "event100";
				s.tl(this, 'o', 'custom');

			}

		});


		if(numB<=5){
			if(numB==4){
				$(".textTitleS").text("宅抵贷");
				$(".textMessageS").text("借款额度15-500万");
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "宅抵贷导航";
				s.events = "event100";
				s.tl(this, 'o', 'custom');
			}else if(numB==0){
				var s = s_gi(s_account);
				s.linkTrackVars = "eVar75,events";
				s.linkTrackEvents = "event100";
				s.eVar75 = "嘉保贷导航";
				s.events = "event100";
				s.tl(this, 'o', 'custom');

			}
			text.eq(numB).addClass('hoverButton').siblings('li').removeClass('hoverButton');
			tab.eq(numB).show().siblings().hide()
		}else if (numB==6){
			$(".loanProducts").hide();
			$(".applyLoan ").show();
			$(".textTitleS").text("嘉保贷");
			var s = s_gi(s_account);
			s.linkTrackVars = "eVar75,events";
			s.linkTrackEvents = "event100";
			s.eVar75 = "嘉保贷立即申请";
			s.events = "event100";
			s.tl(this, 'o', 'custom');
		}else if (numB==7){
			$(".loanProducts").hide();
			$(".applyLoan ").show();
			$(".textTitleS").text("宅抵贷");
			$(".textMessageS").text("借款额度15-500万");
			$('#menuWarp ul.asideList li:last').addClass('hoverButton').siblings('li').removeClass('hoverButton');
			var s = s_gi(s_account);
			s.linkTrackVars = "eVar75,events";
			s.linkTrackEvents = "event100";
			s.eVar75 = "宅抵贷立即申请";
			s.events = "event100";
			s.tl(this, 'o', 'custom');

		}
	},0);

	text.click(function(){
		$(this).addClass('hoverButton').siblings('li').removeClass('hoverButton');
		num=$(this).index();
		tab.eq(num).show().siblings().hide();

		if(num==0){
			$(".applyLoan .topBanner_protect,.successful .topBanner_home,.successFail .topBanner_home").css({'background':'url(http://www.niwodai.com/Public/Static/201404/event/daikuan/images/banner_1.jpg)' });
			$(".applyLoan .textTitle,.successful .textTitle,.successFail .textTitle,.textTitleS").text('嘉保贷');
			$(".applyLoan .textMessage,.successful .textMessage,.successFail .textMessage").text('有寿险保单就能申请');
			$(".textMessageS").text("借款额度2-50万");
			var s = s_gi(s_account);
			s.linkTrackVars = "eVar75,events";
			s.linkTrackEvents = "event100";
			s.eVar75 = "嘉保贷导航";
			s.events = "event100";
			s.tl(this, 'o', 'custom');
		}else if(num==1){
			$(".applyLoan .topBanner_protect,.successful .topBanner_home,.successFail .topBanner_home").css({'background':'url(http://www.niwodai.com/Public/Static/201404/event/daikuan/images/banner_1.jpg)' });
			$(".applyLoan .textTitle,.successful .textTitle,.successFail .textTitle,.textTitleS").text('嘉英贷');
			$(".applyLoan .textMessage,.successful .textMessage,.successFail .textMessage").text('社保/公积金借款');
			$(".textMessageS").text("借款额度2-50万");
			var s = s_gi(s_account);
			s.linkTrackVars = "eVar75,events";
			s.linkTrackEvents = "event100";
			s.eVar75 = "嘉英贷导航";
			s.events = "event100";
			s.tl(this, 'o', 'custom');
		}else if(num==2	){
			$(".applyLoan .topBanner_protect,.successful .topBanner_home,.successFail .topBanner_home").css({'background':'url(http://www.niwodai.com/Public/Static/201404/event/daikuan/images/banner_1.jpg)' });
			$(".applyLoan .textTitle,.successful .textTitle,.successFail .textTitle,.textTitleS").text('嘉房贷');
			$(".applyLoan .textMessage,.successful .textMessage,.successFail .textMessage").text('按揭房贷借款');
			$(".textMessageS").text("借款额度2-30万");
			var s = s_gi(s_account);
			s.linkTrackVars = "eVar75,events";
			s.linkTrackEvents = "event100";
			s.eVar75 = "嘉房贷导航";
			s.events = "event100";
			s.tl(this, 'o', 'custom');
		}else if(num==3	){
			$(".applyLoan .topBanner_protect,.successful .topBanner_home,.successFail .topBanner_home").css({'background':'url(http://www.niwodai.com/Public/Static/201404/event/daikuan/images/banner_1.jpg)' });
			$(".applyLoan .textTitle,.successful .textTitle,.successFail .textTitle,.textTitleS").text('嘉车贷');
			$(".applyLoan .textMessage,.successful .textMessage,.successFail .textMessage").text('有车贷都能申请');
			$(".textMessageS").text("借款额度2-30万");
			var s = s_gi(s_account);
			s.linkTrackVars = "eVar75,events";
			s.linkTrackEvents = "event100";
			s.eVar75 = "嘉车贷导航";
			s.events = "event100";
			s.tl(this, 'o', 'custom');
		}else if(num==4	){
			$(".applyLoan .topBanner_protect,.successful .topBanner_home,.successFail .topBanner_home").css({'background':'url(http://www.niwodai.com/Public/Static/201404/event/daikuan/images/banner_1.jpg)' });
			$(".applyLoan .textTitle,.successful .textTitle,.successFail .textTitle,.textTitleS").text('宅抵贷');
			$(".applyLoan .textMessage,.successful .textMessage,.successFail .textMessage").text('有住宅就能申请');
			$(".textMessageS").text("借款额度15-500万");
			var s = s_gi(s_account);
			s.linkTrackVars = "eVar75,events";
			s.linkTrackEvents = "event100";
			s.eVar75 = "宅抵贷导航";
			s.events = "event100";
			s.tl(this, 'o', 'custom');
		}
	});

	function clickAdoboebao1(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉保贷立即申请";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}

	$(".btnbao1").click(function(){
		clickAdoboebao1();
	});
	function clickAdoboebao2(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉保贷立即申请2";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnbao2").click(function(){
		clickAdoboebao2()
	});
	function clickAdoboeying1(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉英贷立即申请";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnying1").click(function(){
		clickAdoboeying1()
	});

	function clickAdoboeying2(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉英贷立即申请2";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnying2").click(function(){
		clickAdoboeying2()
	});
	function clickAdoboefang1(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉房贷立即申请";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnfang1").click(function(){
		clickAdoboefang1()
	});
	function clickAdoboefang2(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉房贷立即申请2";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnfang2").click(function(){
		clickAdoboefang2()
	});
	function clickAdoboeche1(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉车贷立即申请";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnche1").click(function(){
		clickAdoboeche1()
	});

	function clickAdoboeche2(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "嘉车贷立即申请2";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnche2").click(function(){
		clickAdoboeche2()
	});
	function clickAdoboezhai1(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "宅抵贷立即申请";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnzhai1").click(function(){
		clickAdoboezhai1()
	});

	function clickAdoboezhai2(){
		var s = s_gi(s_account);
		s.linkTrackVars = "eVar75,events";
		s.linkTrackEvents = "event100";
		s.eVar75 = "宅抵贷立即申请2";
		s.events = "event100";
		s.tl(this, 'o', 'custom');
	}
	$(".btnzhai2").click(function(){
		clickAdoboezhai2()
	});
	$('.applyBto,.imgBtn').on('click',function(){
		tab.css('display','none');
		$('.applyLoan').css('display','block');
		$(window).scrollTop(0)
	});


	$(window).on('scroll',function(){
		if($(window).scrollTop()>530){
			$(".footerApply").css({
				'display':'block'
			});
		}else{
			$(".footerApply").css({
				'display':'none'
			});
		}
	});

	//幻灯效果开始
	var isRune=true;
	var imagesNum=$('#bannerWrap ul.imgToggle>li').length;
	var imagesHtml='<ul class="buttonDot">';
	for(var i=1;i<=imagesNum;i++){
		imagesHtml+='<li></li>'
	}
	imagesHtml+='</ul>';
	$('#bannerWrap ul.imgToggle').after(imagesHtml);

	function show(v){
		if(v==$('#bannerWrap ul.buttonDot>li').index($('#bannerWrap ul.buttonDot>li.choice'))
		){return null}

		$('#bannerWrap ul.imgToggle>li').stop().filter(':visible').fadeOut()
			.css({'z-index':0})//可以加入'display':'none'
			.end()
			.eq(v).fadeIn()
			.css({'z-index':1});//可以加入'display':'block'

		$('#bannerWrap ul.buttonDot>li').filter('.choice')
			.removeClass('choice')
			.end()
			.eq(v).addClass('choice')

	}
	show(0);
	$('#bannerWrap ul.buttonDot>li').on('click.hover',function(){
		show($('#bannerWrap ul.buttonDot>li').index(this))
	});

	$('#bannerWrap ul.imgToggle>li,#bannerWrap ul.buttonDot>li').hover(function(){
		isRune=false;
	},function(){
		isRune=true;
	});

	setInterval(function(){
		if(isRune){

			if($('#bannerWrap ul.buttonDot>li.choice').next().length==0
			){
				$('#bannerWrap ul.buttonDot>li').eq(0).trigger('click.hover')
			}else{
				$('#bannerWrap ul.buttonDot>li.choice').next().trigger('click.hover')
			}
		}
	},3000);

	var page=1;
	var r=1;
	$(".next").click(function(){
		var list=$(".imgToggle");
		var num=list.find("li").length;
		var len=Math.ceil(num/r);

		if (page==len){
			$('#bannerWrap ul.buttonDot>li').eq(0).trigger('click.hover');
			page=1
		}else {

			$('#bannerWrap ul.buttonDot>li.choice').next().trigger('click.hover');
			page++
		}
	});
	$(".prev").click(function(){
		var list=$(".imgToggle");
		var num=list.find("li").length;
		var len=Math.ceil(num/r);

		if (page==1){
			$('#bannerWrap ul.buttonDot>li').eq(len-1).trigger('click.hover');
			page=len
		}else {
			$('#bannerWrap ul.buttonDot>li.choice').prev().trigger('click.hover');
			page--
		}
	});
	//幻灯效果结束

	$("#bannerWrap .prev").hover(function(){
		$(this).stop().animate({
			"background-position-x":"6px",
			"opacity":"1"
		},500)
	},function(){
		$(this).stop().animate({
			"background-position-x":"20px",
			"opacity":".3"
		},500)
	});

	$("#bannerWrap .next").hover(function(){
		$(this).stop().animate({
			"background-position-x":"17px",
			"opacity":"1"
		},300)
	},function(){
		$(this).stop().animate({
			"background-position-x":"1px",
			"opacity":".3"
		},300)
	});

	$(".column ul.columnTitle li:eq(0)").hover(function(){
		$(".tagsImg:eq(0) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_11_1.png")
	},function(){
		$(".tagsImg:eq(0) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_11.png")
	});

	$(".column ul.columnTitle li:eq(1)").hover(function(){
		$(".tagsImg:eq(1) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_12_1.png")
	},function(){
		$(".tagsImg:eq(1) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_12.png")
	});

	$(".column ul.columnTitle li:eq(2)").hover(function(){
		$(".tagsImg:eq(2) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_13_1.png")
	},function(){
		$(".tagsImg:eq(2) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_13.png")
	});
	$(".column ul.columnTitle li:eq(3)").hover(function(){
		$(".tagsImg:eq(3) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_14_1.png")
	},function(){
		$(".tagsImg:eq(3) img").attr("src","http://www.niwodai.com/Public/Static/201404/event/daikuan/images/index_14.png")
	});

	$(".bottomImg .leftAppDown").hover(function(){
		$('.imgAppDown').stop().animate({'top':0})
	},function(){
		$('.imgAppDown').stop().animate({'top':160})
	});
	$(".bottomImg .rightWX").hover(function(){
		$('.imgWX').stop().animate({'top':0})
	},function(){
		$('.imgWX').stop().animate({'top':160})
	});

	/*计算器开始*/
	$(".calculatorMain .bottomCalc .calBar").click(function(){
		var val = $('.ipBoxS select option:selected').val();
		$(".ipBoxS").find("#ErorrMess").remove();
		if(0 == val)
		{
			var error=$("<p id='ErorrMess'>请选择期数</p>");
			$(".ipBoxS").append(error)
		}
		var UL = $(this).prev(".conResult");
		var numError=$("form #ErorrMess").length;
		if(numError){
			return false
		}else {
			if(UL.css("display")=="none"){
				$(".conResult").slideDown();
				$(".bottomCalc .pngArrow").css({
					"background-position-x":"-75px",
					"background-position-y":"-76px"
				});
				$(".calculatorMain").css("height","458px");
			} else {
				$(".conResult").slideUp();
				$(".bottomCalc .pngArrow").css({
					"background-position-x":"-118px",
					"background-position-y":"-76px"
				});
				$(".calculatorMain").css("height","256px");
			}
		}
	});

	$(".CalMainBtn").click(function(){
		$(".ipBoxS").find("#ErorrMess").remove();
		var val = $('.ipBoxS select option:selected').val();
		if(0 == val)
		{
			var error=$("<p id='ErorrMess'>请选择期数</p>");
			$(".ipBoxS").append(error)
		}
		$("form input#miniInput").trigger("blur");
		var numError=$("form #ErorrMess").length;
		if(numError){
			return false
		}else {
			$(".conResult").slideDown();
			$(".bottomCalc .pngArrow").css({
				"background-position-x":"-75px",
				"background-position-y":"-76px"
			})
		}
	});

	$(".calculatorRight .hover").click(function(){
		$(".calculatorMain ").fadeToggle();
		$(".conResult").hide();
		$("form input#miniInput").val("");
		$("select.timeBox option:eq(0)").attr("selected","selected");
		$(".ipBoxS").find("#ErorrMess").remove();
		$(".ipBox").find("#ErorrMess").remove();
		if($(".conResult").css("display")=="none"){
			$(".bottomCalc .pngArrow").css({
				"background-position-x":"-154px",
				"background-position-y":"-76px"
			})
		}
	});

	$(".textCalc span.close,#menuWarp ul.asideList li").click(function(){
		$(".calculatorMain ").fadeOut();
		$("form input#miniInput").val("");
		$("select.timeBox option:eq(0)").attr("selected","selected");
		$(".ipBoxS").find("#ErorrMess").remove();
		$(".ipBox").find("#ErorrMess").remove();
		$(".bottomCalc .pngArrow").css({
			"background-position-x":"-154px",
			"background-position-y":"-76px"
		})
	});

	$("form input#miniInput").blur(function(){
		var $parent=$(this).parent();
		$parent.find("#ErorrMess").remove();
		if ($(this).is($("#miniInput"))){
			if (this.value==""){
				var error=$("<div id='ErorrMess'>请输入借款金额</div>");
				$parent.append(error)
			}else if (this.value<20000|| this.value>500000){
				var error02=$("<div id='ErorrMess'>请输入2-50万之间的整数</div>");
				$parent.append(error02)
			}
		}
	});

	$("form .ipBoxS select").change(function(){
		$(".ipBoxS").find("#ErorrMess").remove();
	});
	/*计算器结束*/


});