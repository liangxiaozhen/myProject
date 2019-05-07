/* 验证备注显示个数*/
//alert(basePath);
 /* 	$(function(){
		$("[data-toggle='tooltip']").tooltip({
		html : true
	}) ;
		$(".remark-p").each(function(i) {
		var num = $(this).html();
		if (num.length > 5) {
			$(this).html(num.substr(0, 5) + "...");
		}
	});
		//查询框回显
		 $("#ugrade_serlect").val("${rechargeRate.ugrade}"); 
});*/
    //刷新页面
	function refurbish() {
		//window.location.href = "${pageContext.request.contextPath}/user/tender/userdebtrron.action?pageNum=${pagehelper.pageNum}";
	   queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
	}
	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#form-select").submit();
	}
	function refurbish2(){
	    //window.location.href=basePath+"/user/userdebtattorn/userdebtrronzs.action";
	    window.location.href=basePath+"/user/tender/tenderRecord.action";
	}
	/*正常发布债转:此处采用多态的方式,如果是逾期发布债转的话,periods期数就不为null*/
	function install(orderno,periods) {
		$("#frozen").removeAttr("onclick");
		$("#frozen").attr("onclick","ReleaseYanz()").text("发布");
		var action = basePath+"/user/userdebtattorn/install.action";
		var params =null;
		if(periods!=null || periods!=''){
			params = {
					"orderno" : orderno,
					"periods":periods
				};	
		}else{
			params = {
					"orderno" : orderno,
				};	
		}
		 // window.location.href=basePath+"/user/userdebtattorn/install.action?orderno="+orderno;
		$.post(action, params,function(data) {
			$("#mymodal").html(data);
		});
	}
	
	/*模拟逾期页面的方法*/
	function overdue(orderno,identifying) {
		var action = basePath+"/user/userdebtattorn/overdue.action";
		var params = {
			"orderno" : orderno
		};
		 // window.location.href=basePath+"/user/userdebtattorn/install.action?orderno="+orderno;
		var callback = function(data) {
			$("#myTabContent").html(data);
		}
		$.post(action, params, callback);
	}
  		//判断债转金额
  		function checkBlur(obj) {
  			//为了去除最后一个. 
  			obj.value = obj.value.replace(/\.$/g, "");
  			var  identifying= $("#identifyinglb").val();//判断是逾期标还是正常标
  			var attornmoneylow = $("#attornmoneylowlb").val();//债转金额最低值
  			var attornmoney = $("#attornmoneylb").val();//债转金额最高值
  			var minattornratio = $("#minattornratiolb").val();//债转系数最低值
  			var maxattornratio = $("#maxattornratiolb").val();//债转系数最高值
  			var restprincipallb = $("#restprincipallb_input").val();//剩余本金
  			var isasplitlb = $("#isasplitlb").val();//是否全部
  			var coefficientlb = $("#coefficientlb").val();//债转系数
  			var daamountlb = $("#daamountlb").val();//债转金额
  			var orderno = $("#ordernolb_input").val();
  			var periods = $("#periodslb").val();
  			if(parseFloat(daamountlb)<parseFloat(attornmoneylow) || parseFloat(daamountlb)>parseFloat(attornmoney)){
  				$("#daamountlb_yanz").text("请输入"+attornmoneylow+"--"+attornmoney+"范围的值");
  			}else if(parseFloat(daamountlb)>parseFloat(restprincipallb)){
  				$("#daamountlb_yanz").text("输入的金额大于剩余本金,请重新输入");
  			}else{
  				$("#daamountlb_yanz").text("");
  				if(daamountlb!=null && daamountlb!=""){
  	  				var action = basePath+"/user/userdebtattorn/partfee.action";
  	  				var params = {
  	  						"daamountlb":daamountlb,
  	  						"orderno":orderno,
  	  						"periods":periods
  	  				}
  	  				$.post(action,params,function(totalamount){
  	  					if(totalamount=="false"){
  	  						$("#daamountlb_yanz").text("债转金额不符合要求");
  	  					}else{
  	  						$("#daamountlb_yanz").text("");
  	  						if(identifying=="2"){
  	  							var str = totalamount.split(",");
  	  							$("#Collect_fines").val(str[1]);
  	  							$("#Collecting_interest").val(str[0]);
  	  						}else{
  	  							$("#Collecting_interest").val(totalamount);
  	  						}
  	  					}
  	  				},'json');
  	  			}else{
  	  				$("#Collecting_interest").val("");
  	  				$("#daamountlb_yanz").text("请输入债转金额");
  	  			}
  	  		 
  			}
  			
  		}
  		//判断债转金额的债转系数
  		function checkBlur2(obj) {
  			//为了去除最后一个. 
  			obj.value = obj.value.replace(/\.$/g, "");
  			var minattornratio = $("#minattornratiolb").val();//债转系数最低值
  			var maxattornratio = $("#maxattornratiolb").val();//债转系数最高值
  			var coefficientlb = $("#coefficientlb").val();//债转系数
  			var daamountlb = $("#daamountlb").val();//债转金额
  			var orderno = $("#ordernolb_input").val();
  			if(parseFloat(coefficientlb)<parseFloat(minattornratio)){
  				$("#coefficientlb_yanz").text("请输入"+minattornratio+"--"+maxattornratio+"范围的值");
  			}else if(parseFloat(coefficientlb)>parseFloat(maxattornratio)){
  				$("#coefficientlb_yanz").text("请输入"+minattornratio+"--"+maxattornratio+"范围的值");
  			}else{
  				$("#coefficientlb_yanz").text("");
  				if(coefficientlb!=null && coefficientlb!=""){
  	  				var action = basePath+"/user/userdebtattorn/amount.action";
  	  				var params = {
  	  						"daamountlb":daamountlb,
  	  						"coefficientlb":coefficientlb,
  	  					    "orderno":orderno
  	  				}
  	  				$.post(action,params,function(totalamount){
  	  					var str = totalamount.split(",");
  	  					if(str[0]=="daamountFalse"){
  	  						$("#daamountlb_yanz").text("债转金额不符合要求,请重新输入");
  	  					}else if(str[0]=="coefficientFalse"){
  	  						$("#daamountlb_yanz").text("");
	  						$("#coefficientlb_yanz").text("");
  	  						$("#coefficientlb_yanz").text("债转系数不符合要求,请从新输入");
  	  					}else if(str[0]=="coffFalse"){
  	  						$("#daamountlb_yanz").text("");
	  						$("#coefficientlb_yanz").text("");
	  						$("#coefficientlb_yanz").text("请输入小于"+str[1]+"的债转系数");
  	  					}else{
  	  						$("#daamountlb_yanz").text("");
  	  						$("#coefficientlb_yanz").text("");
  	  						$("#amountcoefficient").val(totalamount);
  	  					}
  	  				},'json');
  	  			}else{
  	  				$("#amountcoefficient").val("");
  	  				//$("#interestcoefficient").attr("disabled",true);
  	  				$("#coefficientlb_yanz").text("请输入债转系数");
  	  			}
  			}
  		}
  		//判断代收利息债转系数
  		function checkBlur3(obj) {
  			//为了去除最后一个. 
  			obj.value = obj.value.replace(/\.$/g, "");
  			var interestcoefficient = $("#interestcoefficient").val();//待收利息债转系数
  			var  identifying= $("#identifyinglb").val();//判断是逾期标还是正常标
  			var orderno = $("#ordernolb_input").val();
	  			if(interestcoefficient!=null && interestcoefficient!=""){
	  				$("#Collecting_interestlb").text("");
	  				var action = basePath+"/user/userdebtattorn/amountFee.action";
	  				var params = {
	  						"interestcoefficient":interestcoefficient,
	  						"orderno":orderno
	  				}
	  				$.post(action,params,function(totalamount){
	  					var str = totalamount.split(",");
	  					if(identifying=="1"){//正常标
	  						if(str[0]=="InterestFalse"){
	  							$("#Collecting_interestlb").text("请输入小于"+str[1]+"的利息债转系数");
	  						}else{
	  							$("#Collecting_interestlb").text("");
	  							$("#interestfee").val(totalamount);
	  							totalLiXi(orderno);
	  						}
	  					}else{//逾期标
  							$("#interestfee").val(totalamount);
  							totalLiXi(orderno);
	  					}
	  					
	  				},'json');
	  			}else{
	  				$("#interestfee").val("");
	  				$("#Collecting_interestlb").text("请输入待收利息债转系数");
	  			}
	  		}
  		//算总金额
  		function totalLiXi(orderno){
  			//算汇总金额
			var action = basePath+"/user/userdebtattorn/total.action";
			var params = {
					"orderno":orderno
			}
			$.post(action,params,function(totalAmount){
				$("#totalfee").val(totalAmount);
			},'json');
  		}
  		
  		//判断滞纳金的债转系数
  		function checkBlur4(obj) {
  			//为了去除最后一个. 
  			obj.value = obj.value.replace(/\.$/g, "");
  			var latecoefficient = $("#latecoefficient").val();//待收滞纳金债转系数
  			var orderno = $("#ordernolb_input").val();
  			if(latecoefficient!=null && latecoefficient!=""){
  				if(parseFloat(latecoefficient)>1){
  					$("#Collect_fineslb").text("请输入小于1的滞纳金债转系数");
  				}else{
  					$("#Collect_fineslb").text("");
  					var action = basePath+"/user/userdebtattorn/lateFee.action";
  					var params = {
  							"orderno":orderno,
  							"latecoefficient":latecoefficient
  					}
  					$.post(action,params,function(totalamount){
  						$("#latefee").val(totalamount);
  					},'json');
  				}
  			}else{
  				$("#Collect_fineslb").text("请输入滞纳金债转系数");
  			}
  			
  		}
  	//上架天数
  		function deadlineFun(obj) {
  			//为了去除最后一个. 
  			obj.value = obj.value.replace(/\.$/g, "");
  			var deadline = $("#deadline").val();//上架天数
  			var orderno = $("#ordernolb_input").val();
  			if(deadline!=null && deadline!=""){
  				var action = basePath+"/user/userdebtattorn/deadline.action";
  				var params = {
  						"orderno":orderno,
  						"deadline":deadline
  				}
  				$.post(action,params,function(data){
  				//str[0]表示时间符合要求不,符合为success,不符合为false,str[1]表示可上架天数,str[2],表示此标是逾期标还是正常标:1为正常标,2为逾期
  					var str = data.split(",");
  					if(str[2]=="1"){
  						if(str[0]=="false"){
  							$("#deadline_yanz").text("可上架天数超限,只能上架"+str[1]+"及以下");
  						}else{
  							$("#deadline_yanz").text("");
  						}
  					}else{
  						$("#deadline_yanz").text("");
  					}
  					
  				},'json');
  			}else{
  				$("#deadline_yanz").text("请输入上架天数");
  			}
  			
  		}
  		//当键盘上某个按键被按放开时触发的事件
  		function checkUp(event, obj) {
  			//响应鼠标事件，允许左右方向键移动 
  			event = window.event || event;
  			if (event.keyCode == 37 | event.keyCode == 39) {
  				return;
  			}
  			//先把非数字的都替换掉，除了数字和. 
  			obj.value = obj.value.replace(/[^\d.]/g, "");
  			//必须保证第一个为数字而不是. 
  			obj.value = obj.value.replace(/^\./g, "");
  			//保证只有出现一个.而没有多个. 
  			obj.value = obj.value.replace(/\.{2,}/g, ".");
  			//保证.只出现一次，而不能出现两次以上 
  			obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
  					"$#$", ".");
  		}

  	//判断定向债转码
	function udapassFun(){
		var isattornlb = $("input[name='isattorn']:checked").val();//是否定向转让
		if(isattornlb==1){//判断定向债转码
			if(udapasslb==null || udapasslb==""){
				$("#udapasslb_yanz").text("请输入定向债转码");
			}else{
				$("#udapasslb_yanz").text("");
			}
		}
	}
	
	//提交前验证
	function ReleaseYanz(){
		var attornmoney = $("#attornmoneylb").val();//债转金额最高值
		var attornmoneylow = $("#attornmoneylowlb").val();//债转金额最低值
		var minattornratio = $("#minattornratiolb").val();//债转系数最低值
		var maxattornratio = $("#maxattornratiolb").val();//债转系数最高值
		var restprincipallb = $("#restprincipallb_input").text();//代收本金
		var udapasslb = $("#udapasslb").val();//定向债转码
		var isasplitlb = $("#isasplitlb").val();//是否全部
		var isasplitlb = $("#isasplitlb").val();//是否全部
		var isattornlb = $("input[name='isattorn']:checked").val();//是否定向转让
		var coefficientlb = $("#coefficientlb").val();//债转系数
		var daamountlb = $("#daamountlb").val();
		var day = $("#daylb").val();//可上架天数
		var deadline = $("#deadline").val();//上架天数
		if(isattornlb==1){//判断定向债转码
			if(udapasslb==null || udapasslb==""){
				$("#udapasslb_yanz").text("请输入定向债转码");
				return; 
			}else{
				$("#udapasslb_yanz").text("");
			}
		}
		if(deadline==null || deadline==""){
				$("#deadline_yanz").text("请输入上架天数");
				return;
			}else{
				if(parseFloat(deadline)>parseFloat(day)){
					$("#deadline_yanz").text("可上架天数超限,只能上架"+day+"及以下");
					return;
				}else{
					$("#deadline_yanz").text("");
				}
			}
		if(daamountlb==null || daamountlb==""){//判断债转金额
			$("#daamountlb_yanz").text("请输入合法金额");
			return;
		}else if(parseFloat(daamountlb)>parseFloat(restprincipallb)){
			$("#daamountlb_yanz").text("输入的金额大于剩余本金,请重新输入");
			return;
		}else if(parseFloat(daamountlb)<parseFloat(attornmoneylow) || parseFloat(daamountlb)>parseFloat(attornmoney)){
			$("#daamountlb_yanz").text("请输入"+attornmoneylow+"--"+attornmoney+"范围的值");
			return;
		}else{
			$("#daamountlb_yanz").text("");
		}
		
		if(coefficientlb==null || coefficientlb==""){//判断金额债转系数
			$("#coefficientlb_yanz").text("请输入合法值");
			return;
		}else if(parseFloat(coefficientlb)<parseFloat(minattornratio) || parseFloat(coefficientlb)>parseFloat(maxattornratio)){
			$("#coefficientlb_yanz").text("请输入"+minattornratio+"--"+maxattornratio+"范围的值");
			return;
		}else{
			$("#coefficientlb_yanz").text("");
			Release();
			
		}
	}
	/* 提交 */
	function Release(){
		$("#frozen").removeAttr("onclick").text("请求中...");
		var action =basePath+"/user/userdebtattorn/save.action";
		var baseidlb = $("#baseidlb").val();
		var periods = $("#periodslb").val();
		var udapasslb = $("#udapasslb").val();//定向债转码
		var ordernolb = $("#ordernolb_input").val();//投标订单号
		var restprincipal = $("#restprincipallb_input").text();//剩余本金
		var daamountlb = $("#daamountlb").val();//债转金额
		var coefficientlb = $("#coefficientlb").val();//金额转出系数
		var intcoefficient = $("#interestcoefficient").val();//利息转出系数
		var daproperty = $("#identifyinglb").val();//债转属性（1正常债转，2逾期债转）
		var intamount = $("#Collecting_interest").val();//转让利息
		var ocamount = $("#Collect_fines").val();//转让滞纳金
		var latecoefficient = $("#latecoefficient").val();//滞纳金转出系数
		var deadline = $("#deadline").val();//债转多少天要下架:债转期限
		var params = {
				"baseid":baseidlb,
				"periods": periods,
				"udapass": udapasslb,
				"orderno": ordernolb,
				"restprincipal": restprincipal,
				"daamount":daamountlb,
				"coefficient": coefficientlb,
				"intcoefficient": intcoefficient,
				"daproperty": daproperty,
				"intamount":intamount,
				"ocamount":ocamount,
				"latecoefficient":latecoefficient,
				"deadline": deadline
		};
			$.post(action,params,function(data){
				if(data =="quotafalse" || data =="minfeefalse" || data =="maxfeefalse" || data =="attornratefalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起!债转金额不够扣除手续费!</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data ==""){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>这笔债转不收手续费</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="audit"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>待审核......</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="initial"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>这笔债转不收手续费</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="tstatusfalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起!该标不可债转!</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="daysfalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起!该标持有时间不足!</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="ugradefalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起!您不在可债转的等级中</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="removenofalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起,您是黑名单用户!不能债转</font>");
				    $("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="success"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>恭喜!债转信息发布成功!</font>");
					$("#frozen").attr("onclick","refurbish2()").text("完成");
				}
				if(data =="datimesfalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起,债转次数超过限制</font>");
					$("#frozen").attr("onclick","refurbish2()").text("查看");
				}
				if(data =="Cdatimesfalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起,债转层数超过限制</font>");
					$("#frozen").attr("onclick","refurbish2()").text("查看");
				}
				if(data =="moneyfalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>债转金额超过限制</font>");
					$("#frozen").attr("onclick","refurbish2()").text("查看");
				}
				if(data =="restprincipalfalse"){
					$("#mymodal").html("");
					$("#mymodal").html("<font style='color:green;font-size:30px'>对不起,债转金额超过限制</font>");
					$("#frozen").attr("onclick","refurbish2()").text("查看");
				}
			},'json');
	}
	