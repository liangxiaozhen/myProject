$(document).ready(function(){
	/**
	 * 猜你喜欢的产品  
	 * 在需要的页页中先加入以下html然后再  引用js
	 */
	/*
	<div class="mya-line mar_t20"><div class="ui-dbline icon_user icon_user_line "></div></div>
	<div class="mya-ulike pad_b30 mar_b20">
		<h3 class="u-likeTitle fs_16 fc_3 pad_l30 pad_b20 pad_t20">
	        <span class="mya-title">猜你喜欢</span>
	    </h3>
	    <div id="drLikeDiv" class="u-like clearfix">
		    
	    </div>
	</div>
	*/	
	
	
	 $.ajax({
			type : "POST", 
			url : "/guess/guessYouLoveProduct.do", 
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) { 
				var content ='';
				if(data && data.guessYouLove && data.guessYouLove.length>0){
					for(var i=0;i<data.guessYouLove.length;i++){
						var financialDto=data.guessYouLove[i];
						var status=financialDto.fpstatus;
						var cycle=financialDto.contractPeriod;
						var planSign=financialDto.planSign;
						var version=financialDto.versionNO;
						var remark=financialDto.remark;
						var msg="";
						var href=financialDto.url;
						var style='';
						var artContent='';
						if(status ==0){
							msg = "立即抢购";
						}else if(status ==1){
							msg = "立即预约";
						}else if(status ==2){
							msg = "售罄";
							href="#";
						}
						if(financialDto)
						if(i==2){
							style='style="margin-right: 0px;"';
						}
						/* 
						 * 季季丰 版本号1&D
						 * 双季赢 版本号1&G
						 * 嘉利宝 版本号1&F
						 */
						if(planSign== "H" && data.indexYYS!=null && data.indexYYS.artContent!=null && data.indexYYS.artContent!=""){
							artContent  ='<div class="hd-tips fs_14 fl">'+data.indexYYS.artContent+'</div>';
						}else if(planSign== "E" && data.indexYYY!=null && data.indexYYY.artContent!=null && data.indexYYY.artContent!=""){
							artContent  ='<div class="hd-tips fs_14 fl">'+data.indexYYY.artContent+'</div>';
						}else if(planSign== "F" && data.indexJLB!=null && data.indexJLB.artContent!=null && data.indexJLB.artContent!=""){
							artContent  ='<div class="hd-tips fs_14 fl">'+data.indexJLB.artContent+'</div>';
						}else if(planSign== "G" && data.indexSJY!=null && data.indexSJY.artContent!=null && data.indexSJY.artContent!=""){
							artContent  ='<div class="hd-tips fs_14 fl">'+data.indexSJY.artContent+'</div>';
						}else if(planSign== "D" && data.indexJJf!=null && data.indexJJf.artContent!=null && data.indexJJf.artContent!=""){
							artContent  ='<div class="hd-tips fs_14 fl">'+data.indexJJf.artContent+'</div>';
						}
						if(version !="2" && planSign != "TYB"){
							//version=2表示财神道(这几个推荐产品里面version=2的只有财神道)，planSign="TYB"表示体验宝
							cycle = Math.round(cycle/30)+"个月";
						}else{
							cycle = cycle+"天";
						}
						if(planSign == "TYB"){
							remark = "我出本金，你赚收益";
						}
						content += '<div class="u-l-list pad_t20 pad_b20" '+style+'>'+
										'<input type="hidden" value="'+financialDto.fPId+'" id="fpid"/>'+
									    '<div class="item-hd clearfix lin_20 pad_l10 ">'+
								    	'<h2 class="ellipsis fl fc_3 fs_18 mar_r10">'+financialDto.proName+'</h2>'+
								    	artContent+
								    	'</div>'+
								    '<div class="item-bd cf clearfix pad_t10 pad_l10 ">'+
									   '<div class="fl txt_left boxList1">'+
										    '<div class="cat-box-hd fs_24 fc_f60">'+financialDto.rate+'%</div>'+
										    '<div class="cat-box-bd fs_14 fc_9">平均历史年化收益</div>'+
									    '</div>'+
									    '<div class="fr txt_left boxList2">'+
										   ' <div class="cat-box-hd fs_20 fc_6">'+
										   	cycle
										    +'</div>'+
										    '<div class="cat-box-bd fs_14 fc_9 mar_t5">期限</div>'+
									    '</div>'+
								    '</div>'+
								    '<div class="pad_t10 txt_left fc_c pad_l10">'+remark+'</div>'+
								    '<div class="u-l-l-button"><a href="'+href+'" target="_blank" class="ui-button fs_16 pad_l20 pad_r20">'+msg+'</a></div>'+
								   '</div>';	
					}
					$("#drLikeDiv").html(content);
				}
			}
	 });
});