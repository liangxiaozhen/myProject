$().ready(function() {
	
		/*投标期限限制最低值和最高值*/
		jQuery.validator.addMethod("tdayLimit",function(value,element){
			
			var tdayLimitl = $('input[name="tdayLimitl"]').val();//收益限制最低值
			//alert("tdayLimitl==="+tdayLimitl);
			var tdayrestrict = $('input[name="tdayrestrict"]').val();//收益限制最高值
			//alert("tdayrestrict===="+tdayrestrict);
			var flag = true;
			if(parseFloat(tdayrestrict)<parseFloat(tdayLimitl)){
				flag = false;
			}
			
			return flag;
			
		},"投标期限限制最高值不能小于最低值");
	
	
		/*投标限制最低和最高值*/
		jQuery.validator.addMethod("moneyLimit",function(value,element){
			
			var tminmoney = $('input[name="tminmoney"]').val();//收益限制最低值
			//alert("tminmoney==="+tminmoney);
			var tmaxmoney = $('input[name="tmaxmoney"]').val();//收益限制最高值
			//alert("tmaxmoney===="+tmaxmoney);
			var flag = true;
			if(parseFloat(tmaxmoney)<parseFloat(tminmoney)){
				flag = false;
			}
			
			return flag;
			
		},$.validator.format("投标金额限制最高值不能小于最低值"));
	
		/*投标限制最低和最高值*/
		jQuery.validator.addMethod("restrict",function(value,element){
			
			var tmlrrestrict = $('input[name="tmlrrestrict"]').val();//收益限制最低值
			//alert("tmlrrestrict==="+tmlrrestrict);
			var tmhrrestrict = $('input[name="tmhrrestrict"]').val();//收益限制最高值
			//alert("tmhrrestrict===="+tmhrrestrict);
			var flag = true;
			if(parseFloat(tmhrrestrict)<parseFloat(tmlrrestrict)){
				flag = false;
			}
			
			return flag;
			
		},$.validator.format("投标限制最高值不能小于最低值"));
	
		/*奖品剩余数不能大于奖品总份数*/
		jQuery.validator.addMethod("awardQuantity",function(value,element){
			
			var quantityAll = $('input[name="quantityall"]').val();//奖品总份数
			var quantityRest = $('input[name="quantityrest"]').val();//奖品剩余数
			
			var flag = true;
			if(parseInt(quantityRest)>parseInt(quantityAll)){
				flag = false;
			}
			
			return flag;
			
		},$.validator.format("剩余数不能大于总份数"));
	
		/*验证金额可填可不填*/
		jQuery.validator.addMethod("fillCashorvoucher",function(value,element){
			
			var flag = true;
		 	var cashorvoucher = $('input[name="cashorvoucher"]').val();//金额
			var index = cashorvoucher.indexOf("0");//第一个为0的位置
			if(index==0 && cashorvoucher.length>1){/*0开头的数字串*/
				var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
					if(!reg.test(cashorvoucher)){
						flag = false;
					}else{
						flag = true;
					}
			}else{
				 var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/; 
				 if(!reg.test(cashorvoucher)){
					 flag = false;
				 }else{
					 flag = true;
				 }
			}
			
			return this.optional(element) || flag;
			
		},$.validator.format("请填入正确的数字"));
	
		/*验证大于0小于1的小数（保留5位）*/
		jQuery.validator.addMethod("tmlDecimal",function(value,element){
			
			var flag = true;
			var regex = /^0\.([0-9]){0,5}$/;
			var tmlrrestrict = $('input[name="tmlrrestrict"]').val();
			if(!regex.test(tmlrrestrict)){
				flag = false;
			}
			
			return flag;
			
		},"0~1之间的小数，最多5位");
		
		jQuery.validator.addMethod("tmhDecimal",function(value,element){
			
			var flag = true;
			var regex = /^0\.([0-9]){0,5}$/;
			var tmlrrestrict = $('input[name="tmhrrestrict"]').val();
			if(!regex.test(tmlrrestrict)){
				flag = false;
			}
			
			return flag;
			
		},"0~1之间的小数，最多5位");
	
		jQuery.validator.addMethod("insertAnameOnly",function(value,element){
			//验证奖品名称的唯一性
			var aname = $('input[name="aname"]').val().trim();
			//alert("aname==="+aname);
			var flag = true;
			$.ajax({
				url:base_path+"admin/award/insertAnameOnly.action",
				dataType:"json",
				data:{
					aname:encodeURI(aname)
				},
				async:false,
				success:function(data){
					if(data=="名称已存在"){
						flag= false;
					}
				}
			});
			return flag;
		},"名称已存在");
		
		/*jQuery.validator.addMethod("appointedBid",function(value,element){
			
				var flag = true;
				var num = document.getElementsByName("bid_bt").length;
				//alert("num===="+num);
				if(num>0){
					flag = true;
				}else{
					flag = false;
				}
				return flag;
			
		},"请添加");*/
		
		
		function validateForm(){
		
				return $("#insertAwardForm").validate({
				  // 在键盘按下并释放及提交后验证提交表单
				  rules: {
					  aname: {
						  required:true,
						  insertAnameOnly:true
					  },
					  atype: "required",
					  attribute: "required",
					  allswitch: "required",
					  subswitch: "required",
					  tradetype: "required",
					  endtime: "required",
					  associator:"required",
					  timeNo:"required",
					  businessNo:"required",
					  
					  quantityall: {
						  "required":true,
						  "digits":true
					  },
					  quantityrest: {
						  "required":true,
						  "digits":true,
						  awardQuantity:true
					  },
					  /*addtime: "required",*/
					  addman: "required",
					  wcrestdate: "required",
					  wcrestrict: {
						  "required":true,
						  "number":true
					  },
					  wctype: "required",
					  tattribute: "required",
					  tdayrestrict:{
						  "required":true,
						  "number":true,
						  tdayLimit:true
					  } ,
					  tmlrrestrict:{
						  "required":true,
						  tmlDecimal:true
					  } ,
					  tmhrrestrict:{
						  "required":true,
						  tmhDecimal:true,
						  restrict:true
					  } ,
					  trtype: "required",
					  tminmoney:{
						  "required":true,
						  "number":true
					  } ,
					  tmaxmoney:{
						  "required":true,
						  "number":true,
						  moneyLimit:true
					  } ,
					  iscancel: "required",
					  ugrade: "required",
					  removenameno: "required",
					  usagerights:"required",
					  /*appointed_bid:{
						  appointedBid:true
					  },*/
					  cashorvoucher:{
						  fillCashorvoucher:"required"
					  }
				    },
				
				    errorPlacement:function(error,element){
				    	
			    		/*全部写在父元素的后面*/
			    		error.appendTo(element.parent());//将错误信息添加当前元素的父结点后面
				    	
				    	/*if(element.is(":radio")||element.is(":checkbox")){
				    		error.appendTo(element.parent());//将错误信息添加当前元素的父结点后面
				    	}else{
				    		error.insertAfter(element);//否则直接插入到当前元素后面
				    	}*/
				    }
				    
			}).form();
		}
		
		//点击保存按钮
		$("#insert_award").click(function(){
			if(validateForm()){
				//判断是否隐藏
		    	var display = $("#bid_name_show").css("display");
		    	if(display=="none"){
		    		$("#insertAwardForm").submit();
		    	}else{
		    		var num = document.getElementsByName("bid_bt").length;
		    		if(num==0){
		    			alert("请添加指定的标号");
		    		}else if(num>0){
		    			$("#insertAwardForm").submit();
		    		}
		    	}
				
			}
		});
});

function select_attribute(obj){
	
	var attribute =$("#attribute2").val();
	 if(attribute==6){
		//$("#rateTarget").css('display','block');
		$("input[name='raiseIntObject'][value='1']").attr("checked","checked");
	}/*else{
		$("#rateTarget").css('display','none');
	}*/
	 
	//积分没有有效期限制
	if(attribute==4 || attribute==5){
		//系统积分   交易积分
		$("#validity_period").css("display","none");
	}else{
		$("#validity_period").css("display","block");
	}
	 
}
