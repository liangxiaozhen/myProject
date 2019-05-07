function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#insertFailTCompensateForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  minmoney: {
				  "required":true,
				  "number":true
			  },
			  maxmoney: {
				  "required":true,
				  "number":true
			  },
			  isintcompensateon: {
				  "required":true
			  },
			  isawardcompensateon: {
				  "required":true
			  },
			  ugrades:{
					"required":true  
			  },
			  type:{
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
			  awardname:{
				  "required":true
			  },
			  istemplet:{
				  "required":true
			  }
		    },
			messages : {
				  minmoney: {
					  "required":"*请输入最低金额",
					  "number":"*请输入数字"
				  },
				  maxmoney: {
					  "required":"*请输入最低金额",
					  "number":"*请输入数字"
				  },
				  isintcompensateon: {
					  "required":"*请选择利息方式补偿开关"
				  },
				  isawardcompensateon: {
					  "required":"*请选择奖品补偿方式开关"
				  },
				  ugrades:{
						"required":"请选择会员等级"  
				  },
				  type:{
					  "required":"*请选择类型",
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
				  awardname:{
					  "required":"*请选择奖品名称"
				  },
				  istemplet:{
					  "required":"*请选择是否设为模板"
				  }
			    }
	})
}




