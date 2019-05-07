/**
 * config配置项
 * ajaxUrl
 * object
 * flag
 * form
 * @param config
 */
function selectShouyiTab(object,flag){
	var config={
			flag:flag,
			ajaxUrl:"",
			jsUrl:"",
			container:".tab_content"
	}
	//切换高亮状态
	if(flag==1){
		$(".ui-select-listBox-l-blue").attr("style","left:20px;width:80px");
	}else{
		$(".ui-select-listBox-l-blue").attr("style","left:150px;width:80px");
	}
	$(object).siblings().removeClass("ui-select-listBox-list--now").addClass("ui-select-listBox-list");
	$(object).removeClass("ui-select-listBox-list").addClass("ui-select-listBox-list--now")
	doAjax(config)
}	

/**
 * config配置项
 * ajaxUrl
 * jsUrl
 * flag
 * form
 * @param config
 * doAjax({jsUrl:"/js/moneyRecord/profitItem.js?v=<@sp.cssVersion/>.js",ajaxUrl:"/member/getProfitItemsAjax.do",flag:"1",container:".tab_content"})
 */
function doAjax(config){
	//先从local html中取，如果没取到，说明刷新页面了，此时再从sessionStorage中查，如果不支持sessionStorage，则发起查询
	var flag= config.flag;
	if(!config.param){
		config.param={};
	}
	var getSessionKey = function(){
		var localHtmlId="specialdiv"+flag;
		var sessionKey = localHtmlId;
		if (flag==2){
			var param =config.param
			if(!param){
				param={}
			}
			var moreCode = $("#moreCode").val() ||param.moreCode;
			if(moreCode){
				sessionKey += "M"+moreCode;
			}
			var selecteddate = $("#selecteddate").val() ||param.selecteddate;
			if(selecteddate){
				sessionKey +="_"+selecteddate
			}
			var timeCondition = $("#timeCondition").val() ||param.timeCondition;
			if(timeCondition){
				sessionKey +="T"+timeCondition
			}
			var startdate = $("#startdate").val() ||param.startdate;
			if(startdate){
				sessionKey +="S"+startdate;
			}
			var enddate = $("#enddate").val() ||param.enddate;
			if(enddate){
				sessionKey +="E"+enddate;
			}
			var pageNo = param.pageNo;
			if(pageNo){
				sessionKey+="P"+pageNo;
			}
		}
		return sessionKey;
	}
	var sessionKey = getSessionKey();
	var $localHtml = $("#"+sessionKey);
	if($localHtml.size()>0){
		window.data=window[sessionKey]
		//有现成的直接返回
		$(config.container).html($localHtml.html())
		if(flag==2){
			//异步加载javascript，因为此时的页面组件已更新，因此要重新load下javascript
			$.ajaxSetup({
				cache: true
			})
			$.getScript(config.jsUrl)
		}
		return
	}
	$("<div id='"+sessionKey+"' style='display:none;'/>").appendTo("body");
	$localHtml = $("#"+sessionKey);
	var process = function(data){
	 	$.getScript("/js/common/template.js",function(){
	 		window.data=data;
	 		window[sessionKey]=data;
	 		var initPage = function(){
	 			$("#"+sessionKey+" .pageDiv").each(function(i,item){
	 				var pageObjName = $(this).attr("page");
	 				var pageObj = window.data[pageObjName]
	 				//补充一个分页的方法，此方法在java中有实现
	 				pageObj.getTotalPages=function(){
	 					if (this.totalCount <= 0)
	 						return 1;
	 		
	 					var count = Math.floor(this.totalCount / this.pageSize);
	 					if (this.totalCount % this.pageSize > 0) {
	 						count++;
	 					}
	 					return count;
	 				}
	 				var templateDiv = $("#pageTemplate");
	 				var data = window.data;
	 				data.pu=pageObj;
	 				var generateHtmlStr = tmpl(templateDiv,data);
	 				$(this).html(generateHtmlStr)
	 			});	
	 		}	 		
	 		var templateDiv=$("#templateDiv");
	 		if(config.templateDiv){
	 			templateDiv = $(config.templateDiv);
	 		}
	 		var generateHtmlStr = tmpl(templateDiv,data);
			$localHtml.html(generateHtmlStr);
			if(flag==2){
				//异步加载javascript，因为此时的页面组件已更新，因此要重新load下javascript
				$.ajaxSetup({
					cache: true
				})
				$.getScript(config.jsUrl)
				initPage();
			}	
			$(config.container).html($localHtml.html())
	 	})
	}
	var doRealAjax=function(config){
		config.param.tabIndex=config.flag;
		if(window.data){
			config.param.startdate=window.data.startdate;
		}
		if(window.data){
			config.param.enddate=window.data.enddate;
		}	
		if(window.data){
			config.param.timeCondition=window.data.timeCondition;
		}
		if(window.data && window.data.moreCode){
			config.param.moreCode=window.data.moreCode;
		}
		var param =config.param;
		$.ajax({
	     type: "post",
	     url: config.ajaxUrl,
	     dataType: "json",
	     data:param,
	     async: true,
	     contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	     success: function(d){
	     	//暂存为50个
	     	if(sessionStorage && !sessionStorage[sessionKey] && sessionStorage.length<50){
	     		if(JSON){
	     			if(!sessionStorage["userid"]){
	         			sessionStorage["userid"]=hex_md5(_uname);
	     			}
	     			//只能存字符串
	     			sessionStorage[sessionKey] = JSON.stringify(d);
	     		}
	     	}
	     	process(d);
	     },
	     error:function(d){
	     	//出错了直接刷新页面（这里出错有两种可能，一是后台出错，二是session过期，需要用户重新登陆)
	     	location.reload();
	     }
	 });
   }
	//如果支持sessionStorage并且有sessionStorage中userid存储并且有这个key
	if(sessionStorage && sessionStorage["userid"]){
		if((sessionStorage["userid"] ==hex_md5(_uname)) ){
			if(sessionStorage[sessionKey]){
				var data= $.parseJSON(sessionStorage[sessionKey]);
				process(data);
			}else{
				 doRealAjax(config);
			}
		}else{
			 sessionStorage.clear();
			 doRealAjax(config);
		}
	}else{
	  doRealAjax(config);
	}
}	