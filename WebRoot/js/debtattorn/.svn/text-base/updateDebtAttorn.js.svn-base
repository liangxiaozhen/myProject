function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#updateDebtAttornForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  isdebtaudit: {
				  "required":true,
			  },
			  isocdebt: {
				  "required":true,
			  },
			  istemplet: {
				  "required":true,
			  },
			  datimes:{
					"required":true,  
					"number":true,
					"maxlength":5
			  },
			  aownergrades: {
				  "required":true,
			  },
			  holdday: {
				  "required":true,
				  "number":true
			  },
			  intervalday: {
				  "required":true,
				  "number":true
			  },
			  isasplit: {
				  "required":true,
			  },
			  attornmoney: {
				  "required":true,
				  "number":true
			  },
			  minattornratio: {
				  "required":true,
				  "number":true
			  },
			  maxattornratio: {
				  "required":true,
				  "number":true
			  },
			  apurchasergrades: {
				  "required":true,
			  },
			  isabuyallorpart: {
				  "required":true,
			  },
			  isadafeeon: {
				  "required":true,
			  },
			  ugrades: {
				  "required":true,
			  },
			  type: {
				  "required":true,
			  },
			  minattornmoney: {
				  "required":true,
				  "number":true
			  },
			  maxattornmoney: {
				  "required":true,
				  "number":true
			  },
			  quota: {
				  "required":true,
				  "number":true
			  },
			  attornrate: {
				  "required":true,
				  "number":true
			  },
			  minfee: {
				  "required":true,
				  "number":true
			  },
			  maxfee: {
				  "required":true,
				  "number":true
			  },
			 
		    },
			messages :  {
				isdebtaudit: {
					  "required":"*请选择是否需要债权转让审核",
				  },
				  isocdebt: {
					  "required":"*请选择是否支持逾期债转",
				  },
				  istemplet: {
					  "required":"*请选择是否为模板",
				  },
				  datimes:{
						"required":"*请输入债转限制次数",  
						"number":"*请输入数字",
						"maxlength":$.validator.format("最多可以输入 5个字符"),
				  },
				  aownergrades: {
					  "required":"*请选择选择等级",
				  },
				  holdday: {
					  "required":"*请输入持有时间",
					  "number":"*请输入数字"
				  },
				  intervalday: {
					  "required":"*请输入距离下个还款日天数",
					  "number":"*请输入数字"
				  },
				  isasplit: {
					  "required":"*请选择是否允许拆分",
				  },
				  attornmoney: {
					  "required":"*请输入转让金额",
					  "number":"*请输入数字"
				  },
				  minattornratio: {
					  "required":"*请输入转让最低系数",
					  "number":"*请输入数字"
				  },
				  maxattornratio: {
					  "required":"系数最低与最高",
					  "number":"*请输入转让最高系数"
				  },
				  apurchasergrades: {
					  "required":"*请选择会员等级",
				  },
				  isabuyallorpart: {
					  "required":"*请选择允许全额或部分购买",
				  },
				  isadafeeon: {
					  "required":"*请选择债转手续费开关",
				  },
				  ugrades: {
					  "required":"*请选择会员等级",
				  },
				  type: {
					  "required":"*请选择类型",
				  },
				  minattornmoney: {
					  "required":"*请输入债转金额起始段金额",
					  "number":"*请输入数字"
				  },
				  maxattornmoney: {
					  "required":"*请输入债转金额终止段金额",
					  "number":"*请输入数字"
				  },
				  quota: {
					  "required":"*请输入定额",
					  "number":"*请输入数字"
				  },
				  attornrate: {
					  "required":"*请输入百份比",
					  "number":"*请输入数字"
				  },
				  minfee: {
					  "required":"*请输入手续费最低",
					  "number":"*请输入数字"
				  },
				  maxfee: {
					  "required":"*请输入手续费最高",
					  "number":"*请输入数字"
				  },
				 
			    }
	})
}




