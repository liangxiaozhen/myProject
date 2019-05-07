var updateTenderItem = {
	    //专门用来存放数据的
	    data: {
	        
	    },
	    init: {
	        //初始化数据
	        initData: function(){
	        	
	        },
	        //初始化事件
	        initEvent: function(){
	        	//为单笔允许投资金额最低增加blur事件
	        	$("#minoncetamount").unbind("blur");
	        	$("#minoncetamount").bind("blur",function(){
	        		updateTenderItem.other.onblur(4);
	        	});
	        	
	        	//为单笔允许投资金额最高增加blur事件
	        	$("#maxoncetamount").unbind("blur");
	        	$("#maxoncetamount").bind("blur",function(){
	        		updateTenderItem.other.onblur(3);
	        	});
	        	
	        	//为投标类型增加change事件
	        	$("#ttype").unbind("change");
	        	$("#ttype").bind("change",function(){
	        		updateTenderItem.other.ttypeChange.call(this);
	        	});
	        	
	        	//为单人投标次数限制
	        	$("#onettimes").unbind("blur");
	        	$("#onettimes").bind("blur",function(){
	        		updateTenderItem.other.onblur(1);
	        	});
	        	
	        	//为累投金额限制必须高于单笔允许投资最高金额增加blur事件
	        	$("#totalmoneyrestrict").unbind();
	        	$("#totalmoneyrestrict").bind("blur",function(){
	        		updateTenderItem.other.onblur(2);
	        	});
	        	
	        	//投资收益增加blur事件
	        	$("#tinterest").unbind("blur");
	        	$("#tinterest").bind("blur",function(){
	        		updateTenderItem.other.onblur(5);
	        	});
	        	
	        	//投资收益增加keyup事件
	        	$("#tinterest").unbind("keyup");
	        	$("#tinterest").bind("keyup",function(){
	        		updateTenderItem.other.clearNoNum(this);
	        	});
		        
	        	//为标的利息计算方式添加change事件
	        	$("#timode").unbind("change");
	        	$("#timode").bind("change",function(){
	        		updateTenderItem.other.timodeChange();
	        	});
	        	
	        	//投标是否允许撤回的change事件
	        	$("#isacancel").unbind("change");
	        	$("#isacancel").bind("change",function(){
	        		updateTenderItem.other.isacancelChange(this);
	        	});
	        	
	        	//允许撤回的会员等级：全部等级与选择等级的change监听事件
	        	$(".radio-allowcugrade").unbind("change");
	        	$(".radio-allowcugrade").bind("change",function(){
	        		updateTenderItem.other.allowcugradeChange();
	        	});
	        	
	        	//允许投标的会员等级：全部等级与选择等级的change监听事件
	        	$(".insert-ugrade-radio-ugrestrict").unbind("change");
	        	$(".insert-ugrade-radio-ugrestrict").bind("change",function(){
	        		updateTenderItem.other.ugrestrictChange();
	        	});
	        	
	        	//为保存按钮增加click事件
	        	$("#submitBtu").unbind("click");
	        	$("#submitBtu").bind("click",function(){
	        		updateTenderItem.other.saveClick();
	        	});
	        }
	    },
	    //页面的控制操作
	    other:{
	    	//初始化页面
	        initPage: function(){
	        	//把单笔允许投资金额最低与最高  和  累投金额最高限制的小数点去掉
	        	var minoncetamount=parseInt($("#minoncetamount").val());
	        	$("#minoncetamount").val(minoncetamount);
	        	var maxoncetamount=parseInt($("#maxoncetamount").val());
	        	$("#maxoncetamount").val(maxoncetamount);
	        	var totalmoneyrestrict=parseInt($("#totalmoneyrestrict").val());
	        	$("#totalmoneyrestrict").val(totalmoneyrestrict);
	    		//投标类型
	    		var ttype=$("#ttype").val();
	    		//投标是否允许撤回的状态
	    		var isacancel=$("#isacancel").val();
	    		//1为冻结投标
	    		if("1"==ttype){
	    			//投标是否允许撤回的状态：0为不可撤回
	    			if("0"==isacancel){
	    				//隐藏允许撤回的会员等级
	    				$("#acancel").hide();
	    			}
	    			if($("#timode").val()!=4){
	    				//隐藏起息日
	    				$("#valuedate").hide();
	    			}
	    			//0为及时投标
	    		}else if("0"==ttype){
	    			$("#timode").val(1);
	    			 $("#timode option[value='']").attr("disabled",true);
	    			 $("#timode option[value='2']").attr("disabled",true);
	    			 $("#timode option[value='3']").attr("disabled",true);
	    			 $("#timode option[value='4']").attr("disabled",true);
	    			 $("#issetgfundsint option[value='1']").attr("disabled",true);
	    			$("#issetgfundsint option[value='']").attr("disabled",true);
	    			//隐藏起息日
	    			$("#valuedate").hide();
	    			//隐藏允许撤回的会员等级
	    			$("#acancel").hide();
	    			//隐藏投标是否允许撤回
	    			$("#isacancel_div").hide();
	    		}
	    		
	    		/* 根据投标是否允许撤回回显相应的div */
	    		var isacancel=$("#isacancel").val();
	    		if(isacancel==0){
	    			$("#acancel").hide();
	    			/*初始化默认选中全部等级 */
	    			$("#selectAll1").attr("checked","checked");
	    			$("#ugrade_checkbox_acancel").hide();
	    		}
	    		
	    		/* 允许撤回: 当选中全部等级时 选择等级div隐藏，反之显示 */
	    		var $radioVal = $(".radio-allowcugrade:checked").val();
	    		if ($radioVal == 1) {
	    			$("#ugrade_checkbox_acancel").hide();
	    		} else {
	    			$("#ugrade_checkbox_acancel").show();
	    		}
	    		
	    		//约标回显
	    		var isappointtenderValue=$.trim($("#isappointtender").text());
	    		if(isappointtenderValue=="是"){
	    			$("#appointtender").show();
	    		}else{
	    			$("#appointtender").hide();
	    		}
	    		
	    		
	    		/*当选中全部等级时 选择等级div隐藏，反之显示 */
	    		var $radioVal2= $(".insert-ugrade-radio-ugrestrict:checked").val();
	    		if ($radioVal2== 1) {
	    			$("#insert-ugrade-checkbox-div-ugrestrict").hide();
	    		} else {
	    			$("#insert-ugrade-checkbox-div-ugrestrict").show();
	    		}
	        },
	        //失去焦点事件
	        onblur : function(t){
             switch(t){
             	//为单人投标次数限制增加blur事件
	             case 1 : 
	            	var onettimes=parseFloat($("#onettimes").val());
	 	        	var reg=/^[1-9]\d*$/;
	 	        	if(onettimes!=''){
	 			        	if(!reg.test(onettimes)){
	 			        		alert("请输入正整数")
	 			        		$("#onettimes").val("");
	 			        	}
	 	        	}
	 	        	break;
	 	        //为累投金额限制必须高于单笔允许投资最高金额增加blur事件
	             case 2 :
	            	 var maxoncetamount=$("#maxoncetamount").val();
	            	 var totalmoneyrestrict=$("#totalmoneyrestrict").val();
	            	 var reg=/^[1-9]\d*$/;
	            	 if(!reg.test(totalmoneyrestrict)){
	            		 alert("请输入正整数");
	            		 $("#totalmoneyrestrict").val("");
	            	 }
	            	 if(($("#maxoncetamount").val())!=""){
	            		 maxoncetamount=parseFloat(maxoncetamount);
		            	 totalmoneyrestrict=parseFloat(totalmoneyrestrict);
		            	 if(totalmoneyrestrict<maxoncetamount){
		            		 alert("累投金额限制必须高于单笔允许投资最高金额");
		            		 $("#totalmoneyrestrict").val("");
		            	 }
	            	 }else{
	            		 alert("请先录入单笔允许投资最高金额");
	            		 $("#maxoncetamount").focus();
	            	 }
	            	 
	            	 break;
	            //为单笔允许投资金额最高增加blur事件
	             case 3:
	            	//得到单笔最低的值
	            	 var minoncetamount=$("#minoncetamount").val();
	 	        	//得到单笔最高的值
	 	        	var maxoncetamount=$("#maxoncetamount").val();
	 	        	var reg=/^[1-9][0-9]*$/;
	 	        	if(!reg.test(maxoncetamount)){
	 	        		alert("请输入正整数");
	 	        		$("#maxoncetamount").val("");
	 	        	}
	 	        	//如果最低值不为Null时
	 	        	if(minoncetamount!=""){
	 	        		var minoncetamount=parseFloat(minoncetamount);
	 	        		var maxoncetamount=parseFloat(maxoncetamount);
	 	        		//如果最高的值小于最低的值，则提示最高值必须大于最低值
	 	        		if((maxoncetamount<minoncetamount)||(maxoncetamount==minoncetamount)){
	 	        			alert("最高值必须大于最低值");
	 	        			$("#maxoncetamount").val("");
	 	        			$("#maxoncetamount").focus();
	 	        		}
	 	        	}else{
	 	        		alert("请先填写最低值");
	 	        		$("#maxoncetamount").val("");
	 	        		$("#minoncetamount").focus();
	 	        	}
	 	        	break;
	             case 4:
	            	 	//得到单笔最低的值
	            	 	var minoncetamount=$("#minoncetamount").val();
		 	        	var reg=/^[1-9][0-9]*$/;
		 	        	if(!reg.test(minoncetamount)){
		 	        		alert("请输入正整数");
		 	        		var minoncetamount=$("#minoncetamount").val("");
		 	        	}
		 	        	break;
	             case 5:
	            	 	//投资收益只能输入整数或小数
	     	        	var tinterest=$("#tinterest").val();
	     	        	var reg=/^[0-9]+(.[0-9]+){0,1}$/;
	     	        	if(!reg.test(tinterest)){
	     	        		alert("只能输入数字");
	     	        		$("#tinterest").val("");
	     	        	}
	             default :
	                 break;
	             }
         },
         clearNoNum:function(obj){
               obj.value = obj.value.replace(/[^\d.]/g, "");//清除“数字”和“.”以外的字符 
               obj.value = obj.value.replace(/^\./g, "");//验证第一个字符是数字而不是. 
               obj.value = obj.value.replace(/\.{2,}/g, ".");//只保留第一个. 清除多余的. 
               obj.value = obj.value.replace(".", "$#$").replace(/\./g,"").replace("$#$", "."); 
         },
	    	//为投标类型增加change事件
	    	ttypeChange:function(){
        		var ttypeVal=$("#ttype").val();
        		//1为冻结投标
        		if("1"==ttypeVal){
        			$("#timode").val("");
        			$("#timode option[value='']").removeAttr("disabled");
        			$("#timode option[value='2']").removeAttr("disabled");
        			$("#timode option[value='3']").removeAttr("disabled");
        			$("#timode option[value='4']").removeAttr("disabled");
        			 $("#timode option[value='1']").attr("disabled",true);
        			$("#isacancel_div").show();
        			
        			$("#issetgfundsint").val("");
        			$("#issetgfundsint option[value='1']").removeAttr("disabled");
        			$("#issetgfundsint option[value='']").removeAttr("disabled");
        			//0为及时投标
        		}else if("0"==ttypeVal){
        			$("#isacancel_div input").val("");
        			$("#isacancel_div select").val(""); 
        			
        			$("#acancel").hide();
        			$("#isacancel_div").hide();
        			
        			$("#timode").val(1);
        			//如果不加下面这句，点了及时投标再点冻结投标再点回及时投标就会出问题：提交的时候会提示选择xxx
        			$("#timode option[value='1']").removeAttr("disabled");
        			 $("#timode option[value='']").attr("disabled",true);
        			 $("#timode option[value='2']").attr("disabled",true);
        			 $("#timode option[value='3']").attr("disabled",true);
        			 $("#timode option[value='4']").attr("disabled",true);
        			$("#valuedate input").val("");
        			$("#valuedate").hide();
        			
        			$("#issetgfundsint").val(0);
        			$("#issetgfundsint option[value='1']").attr("disabled",true);
        			$("#issetgfundsint option[value='']").attr("disabled",true);
        		}else{
        			$("#isacancel_div input").val("");
        			$("#isacancel_div select").val(""); 
        			
        			$("#acancel").hide();
        			$("#isacancel_div").hide();
        		}
        	},
        	
        	//为标的利息计算方式添加change事件
        	timodeChange:function(){
        		if($("#timode").val()==4){
        			$("#valuedate").show();
        		}else{
        			$("#valuedate input").val("");
        			$("#valuedate").hide();
        		}
        	},
        	
        	//投标是否允许撤回的change事件
        	isacancelChange:function(obj){
        		var $isacancelVal=$(obj).val();
        		if($isacancelVal==1){
        			$("#acancel").show();
        			$("#selectAll1").val("1");
        			$("#selectActivity1").val("2");
        			$("#selectAll1").attr("checked","checked");
        			$("#ugrade_checkbox_acancel").hide();
        		}else{
        			$("#acancel :input").val("");
        			$("#ugrade_checkbox_acancel :checkbox").each(function(){
        				this.checked=false;
        			});
        			$("#acancel").hide();
        		}
        	},
        	
        	//允许撤回的会员等级：全部等级与选择等级的change监听事件
        	allowcugradeChange:function(){
        		var $radioVal = $(".radio-allowcugrade:checked").val();
        		if ($radioVal == 1) {
        			$("#ugrade_checkbox_acancel").hide();
        			$("#ugrade_checkbox_acancel :checkbox").each(function(){
        				this.checked=false;
        			});
        			
        		} else {
        			$("#ugrade_checkbox_acancel").show();
        		}
        	},
        	 //标的结束时间应该大于标的开始时间
	        check_tendtime:function(dp){
	        	var tbegintime=$("#tbegintime").val();
	        	var tendtime=dp.cal.getDateStr();
	        	if(tbegintime!=""){
		        	if(tendtime<tbegintime){
		        		alert("标的结束时间必须大于标的开始时间");
		        		$("#tendtime").val("");
		        	}
	        	}else{
	        		alert("请先填写标的开始日期");
	        		$("#tendtime").val("");
	        		$("#tbegintime").focus();
	        	}
	        },
	        //首次还款日期必须大于标的结束日期
	        check_retdate:function(dp){
	        	var tendtime=$("#tendtime").val();
	        	var retdate=dp.cal.getDateStr();
	        	if(tendtime!=""){
		        	if(retdate<tendtime){
		        		alert("首次还款日期必须大于标的结束日期");
		        		$("#retdate").val("");
		        	}
	        	}else{
	        		alert("请先填写标的结束日期");
	        		$("#retdate").val("");
	        		$("#tendtime").focus();
	        	}
	        },
	        //标的最后还款日期必须大于或等于首次还款日期
	        check_lastretdate:function(dp){
	        	var retdate=$("#retdate").val();
	        	var lastretdate=dp.cal.getDateStr();
	        	if(retdate!=""){
	        		if(lastretdate<retdate){
	        			alert("标的最后还款日期必须大于或等于首次还款日期");
	        			$("#lastretdate").val("");
	        		}
	        	}else{
	        		alert("请先填写首次还款日期");
	        		$("#lastretdate").val("");
	        		$("#retdate").focus();
	        	}
	        },
	       
        	//检查起息日是否在符合区间要求
	    	check_valuedate:function(dp){
	    		/* 标的结束时间 */
	    		var tendtime = $("#tendtime").val();
	    		/* 首次还款日期 */
	    		var retdate = $("#retdate").val();
	    		/* 起息日 */
	    		var valuedate = dp.cal.getDateStr();
	    		
	    		if ((tendtime != '')&&(retdate!='')) {
	    			if ((valuedate < tendtime)||(valuedate>retdate)) {
	    				alert("起息日必须在标的结束时间之后，并且在第一次还款时间之前！");
	    			}
	    		}else{
	    			alert("请先填写标的结束时间和首次还款日期");
	    			$("#valuedate1").val("");
	    		}
	    	},
	    	
	    	//允许投标的会员等级：全部等级与选择等级的change监听事件
	    	ugrestrictChange:function(){
        		var $radioVal = $(".insert-ugrade-radio-ugrestrict:checked").val();
        		if ($radioVal == 1) {
        			$("#insert-ugrade-checkbox-div-ugrestrict").hide();
        			$("#insert-ugrade-checkbox-div-ugrestrict :checkbox").each(function(){
        				this.checked=false;
        			});
        			
        		} else {
        			$("#insert-ugrade-checkbox-div-ugrestrict").show();
        		}
        	},
        	
        	//为保存按钮增加click事件
        	saveClick:function(){
        		//表单提交时的验证
        		if((updateTenderItem.other.chenck_totalmoneyrestrict())&&(updateTenderItem.other.check().form())){
    				$("form:first").submit();
    			}
        	},
        	
        	//检查累投金额是否大于单笔允许投资金额最高
        	chenck_totalmoneyrestrict:function(){
            		 var maxoncetamount=parseFloat($("#maxoncetamount").val());
	            	 var totalmoneyrestrict=parseFloat($("#totalmoneyrestrict").val());
	            	 if(totalmoneyrestrict<maxoncetamount){
	            		 alert("累投金额限制必须高于单笔允许投资最高金额");
	            		 $("#totalmoneyrestrict").val("");
	            		 return false;
	            	 }
	            	 return true;
        	},
	        //表单提交时的验证
	        check:function(){
	        	jQuery.validator.addMethod("isZipCode", function(value, element) {   
	        	    var tel = /^[0-9]{6}$/;
	        	    return this.optional(element) || (tel.test(value));
	        	}, "请正确填写您的邮政编码");
	        	// 在键盘按下并释放及提交后验证提交表单
	        	return $("#updateTenderItemForm").validate({
	        		errorPlacement: function (error, element) {
	        			var p = $("<em></em>").append(error);
	        			p.appendTo(element.parent());
	        			p.css({"color":"red"});
	        		},
	        		  rules:{
	        			  tname:{
	        				  "required":true
	        			  },
	        			  timode:{
	        				  "required":true
	        			  },
	        			  valuedate:{
	        				  "required":true
	        			  },
	        			  finishtamount:{
	        				  "required":true,
	        				  "number":true
	        			  },
	        			  tpro:{
	        				  "required":true
	        			  },
	        			  tbegintime:{
	        				  "required":true
	        			  },
	        			  tendtime:{
	        				  "required":true
	        			  },
	        			  tinterest:{
	        				  "required":true,
	        				  "number":true
	        			  },
	        			  pmiscpayman:{
	        				  "required":true
	        			  },
	        			  pmiscrecman:{
	        				  "required":true
	        			  },
	        			  crestrict:{
	        				  "required":true
	        			  },
	        			  onettimes:{
	        				  "required":true,
	        				  "number":true,"maxlength":2
	        			  },
	        			  isfaketender:{
	        				  "required":true
	        			  },
	        			  isautotender:{
	        				  "required":true
	        			  },
	        			  ttype:{
	        				  "required":true
	        			  },
	        			  isacancel:{
	        				  "required":true
	        			  },
	        			  allowcugrades:{
	        				  "required":true
	        			  },
	        			  repaymenttype:{
	        				  "required":true
	        			  },
	        			  repayman:{
	        				  "required":true
	        			  },
	        			  isapartrepay:{
	        				  "required":true
	        			  },
	        			  repaytimepoint:{
	        				  "required":true
	        			  },
	        			  repaymode:{
	        				  "required":true
	        			  },
	        			  begininvestmoney:{
	        				  "required":true,
	        				  "number":true
	        			  },
	        			  ismultiple:{
	        				  "required":true
	        			  },
	        			  totalmoneyrestrict:{
	        				  "required":true,
	        				  "number":true
	        			  },
	        			  averagenum:{
	        				  "required":true,
	        				  "number":true,"maxlength":10
	        			  },
	        			  averagemoney:{
	        				  "required":true,
	        				  "number":true
	        			  },
	        			  isaudit:{
	        				  "required":true
	        			  },
	        			  bidprodtype:{
	        				  "required":true
	        			  },
	        			  retdate:{
	        				  "required":true
	        			  },
	        			  lastretdate:{
	        				  "required":true
	        			  },
	        			  istemplet:{
	        				  "required":true
	        			  },
	        			  tpass:{
	        				  "required":true
	        			  },
	        			  minoncetamount:{
	        				  "required":true,
	        				  "number":true
	        			  },
	        			  maxoncetamount:{
	        				  "required":true,
	        				  "number":true
	        			  },
	        			  ugrestricts:{
	        				  "required":true
	        			  },
	        			  issetgfundsint:{
	        				  "required":true
	        			  },
	        			  isfailtc:{
	        				  "required":true
	        			  },
	        			  isaoverduec:{
	        				  "required":true
	        			  },
	        			  isaaheadrepay:{
	        				  "required":true
	        			  },
	        			  isadebtattorn:{
	        				  "required":true
	        			  },
	        			  isaplus:{
	        				  "required":true
	        			  },
	        			  isamediacy:{
	        				  "required":true
	        			  },
	        			  isaguarantee:{
	        				  "required":true
	        			  },
	        			  isaintexp:{
	        				  "required":true
	        			  },
	        			  isariskgm:{
	        				  "required":true
	        			  },
	        		    },
	        		    messages :{
	        		    	tname:{
	        					  "required":"*请输入标的名称"
	        				  },
	        				  timode:{
	        					  "required":"*请选择标的利息计算方式"
	        				  },
	        				  valuedate:{
	        					  "required":"*请输入起息日"
	        				  },
	        				  finishtamount:{
	        					  "required":"*请输入已完成投标金额",
	        					  "number":"*请输入数字"
	        				  },
	        				  tpro:{
	        					  "required":"*请选择标的类型"
	        				  },
	        				  tbegintime:{
	        					  "required":"*请填写标的开始日期"
	        				  },
	        				  tendtime:{
	        					  "required":"*请填写标的结束时间"
	        				  },
	        				  tinterest:{
	        					  "required":"*请输入标的利息",
	        					  "number":"*请输入数字"
	        				  },
	        				  pmiscpayman:{
	        					  "required":"*请输入平台杂项付款人"
	        				  },
	        				  pmiscrecman:{
	        					  "required":"*请输入平台杂项收款人"
	        				  },
	        				  crestrict:{
	        					  "required":"*请选择"
	        				  },
	        				  onettimes:{
	        					  "required":"*请输入单人投标次数限制",
	        					  "number":"*请输入数字",
	        					  "maxlength":$.validator.format("*最多可以输入2个数字")
	        				  },
	        				  isfaketender:{
	        					  "required":"*请选择是否可以假投标"
	        				  },
	        				  isautotender:{
	        					  "required":"*请选择是否可以自动投标"
	        				  },
	        				  ttype:{
	        					  "required":"*请选择投标类型"
	        				  },
	        				  isacancel:{
	        					  "required":"*请选择投标是否允许撤回"
	        				  },
	        				  allowcugrades:{
	        					  "required":"*请选择会员等级"
	        				  },
	        				  repaymenttype:{
	        					  "required":"*请选择还款类型"
	        				  },
	        				  repayman:{
	        					  "required":"*请输入还款人设置"
	        				  },
	        				  isapartrepay:{
	        					  "required":"*请选择是否支持部分还款"
	        				  },
	        				  repaytimepoint:{
	        					  "required":"*请输入还款时间点"
	        				  },
	        				  repaymode:{
	        					  "required":"*请选择还款金额分配方式"
	        				  },
	        				  begininvestmoney:{
	        					  "required":"*请输入起投金额",
	        					  "number":"*请输入数字"
	        				  },
	        				  ismultiple:{
	        					  "required":"*请选择"
	        				  },
	        				  totalmoneyrestrict:{
	        					  "required":"*请输入累投金额限制",
	        					  "number":"*请输入数字"
	        				  },
	        				  averagenum:{
	        					  "required":"*请输入设置标的份数",
	        					  "number":"*请输入数字",
	        					  "maxlength":$.validator.format("*最多可以输入10个数字")
	        				  },
	        				  averagemoney:{
	        					  "required":"*请输入标的每份金额",
	        					  "number":"*请输入数字"
	        				  },
	        				  isaudit:{
	        					  "required":"*请选择审核状态"
	        				  },
	        				  bidprodtype:{
	        					  "required":"*请选择标产品类型"
	        				  },
	        				  retdate:{
	        					  "required":"*请填写首次还款日期"
	        				  },
	        				  lastretdate:{
	        					  "required":"*请填写最后还款日期"
	        				  },
	        				  istemplet:{
	        					  "required":"*请选择是否为模板"
	        				  },
	        				  tpass:{
	        					  "required":"*请输入约标码"
	        				  },
	        				  minoncetamount:{
	        					  "required":"*请输入单笔允许投资最低金额",
	        					  "number":"*请输入数字"
	        				  },
	        				  maxoncetamount:{
	        					  "required":"*请输入单笔允许投资最高金额",
	        					  "number":"*请输入数字"
	        				  },
	        				  ugrestricts:{
	        					  "required":"*请选择会员等级"
	        				  },
	        				  issetgfundsint:{
	        					  "required":"*请选择是否设置站岗利息"
	        				  },
	        				  isfailtc:{
	        					  "required":"*请选择是否设置流标补偿"
	        				  },
	        				  isaoverduec:{
	        					  "required":"*请选择是否允许逾期代偿"
	        				  },
	        				  isaaheadrepay:{
	        					  "required":"*请选择是否允许提前还款"
	        				  },
	        				  isadebtattorn:{
	        					  "required":"*请选择是否允许债权转让"
	        				  },
	        				  isaplus:{
	        					  "required":"*请选择是否允许使用增益"
	        				  },
	        				  isamediacy:{
	        					  "required":"*请选择是否设置居间费"
	        				  },
	        				  isaguarantee:{
	        					  "required":"*请选择是否设置担保"
	        				  },
	        				  isaintexp:{
	        					  "required":"*请选择是否设置利息管理费"
	        				  },
	        				  isariskgm:{
	        					  "required":"*请选择是否设置风险保证金"
	        				  },
	        		    }
	        	})
	        }
	    }
	};

$(function(){
	updateTenderItem.other.initPage();
	updateTenderItem.init.initEvent();
});

