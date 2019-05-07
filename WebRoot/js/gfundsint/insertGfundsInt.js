function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#insertGfundsIntForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  clearmethod: {
				  "required":true
			  },
			  ugrades:{
					"required":true  
			  },
			  minmoney: {
				  "required":true,
				  "number":true
			  },
			  maxmoney: {
				  "required":true,
				  "number":true
			  },
			  type: {
				  "required":true
			  },
			  quota:{
				  "required":true,
				  "number":true
			  },
			  dayawardrate:{
				  "required":true,
				  "number":true
			  },
			  maxcompensate:{
				  "required":true,
				  "number":true
			  },
			  istemplet:{
				  "required":true
			  }
		    },
			messages : {
				  clearmethod: {
					  "required":"*请选择清算方式   "
				  },
				  ugrades:{
						"required":"请选择会员等级"  
				  },
				  minmoney: {
					  "required":"*请输入最低金额",
					  "number":"*请输入数字"
				  },
				  maxmoney: {
					  "required":"*请输入最高金额",
					  "number":"*请输入数字"
				  },
				  type: {
					  "required":"*请选择类型"
				  },
				  quota:{
					  "required":"*请输入定额补偿金",
					  "number":"*请输入数字"
				  },
				  dayawardrate:{
					  "required":"*请输入日奖励费率",
					  "number":"*请输入数字"
				  },
				  maxcompensate:{
					  "required":"*请输入最高补偿金额",
					  "number":"*请输入数字"
				  },
				  istemplet:{
					  "required":"*请选择是否为模板"
				  }
			}
	})
}




