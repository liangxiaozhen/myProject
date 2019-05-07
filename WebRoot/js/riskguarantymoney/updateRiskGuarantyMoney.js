function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#updateRiskGuarantyMoneyForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  rgmrecman: {
				  "required":true
			  },
			  chargetype: {
				  "required":true
			  },
			  type: {
				  "required":true
			  },
			  minrgmmoney: {
				  "required":true,
				  "number":true
			  },
			  maxrgmmoney: {
				  "required":true,
				  "number":true
			  },
			  rgmquota: {
				  "required":true,
				  "number":true
			  },
			  rgmpercent: {
				  "required":true,
				  "number":true
			  },
			  maxrgmfee: {
				  "required":true,
				  "number":true
			  },
			  ugrades: {
				  "required":true
			  },
			  rgmrate: {
				  "required":true,
				  "number":true
			  },
			  maxrgmamount: {
				  "required":true,
				  "number":true
			  },
			  istemplet: {
				  "required":true
			  },
		    },
			messages :  {
				  rgmrecman: {
					  "required":"*请输入风险保证金收款人"
				  },
				  chargetype: {
					  "required":"*请选择收费类型"
				  },
				  type: {
					  "required":"*请选择"
				  },
				  minrgmmoney: {
					  "required":"*请输入结标分段金额低值",
					  "number":"*请输入数字"
				  },
				  maxrgmmoney: {
					  "required":"*请输入结标分段金额高值",
					  "number":"*请输入数字"
				  },
				  rgmquota: {
					  "required":"*请输入定额",
					  "number":"*请输入数字"
				  },
				  rgmpercent: {
					  "required":"*请输入百份比",
					  "number":"*请输入数字"
				  },
				  maxrgmfee: {
					  "required":"*请输入该段最高风险保证金额",
					  "number":"*请输入数字"
				  },
				  ugrades: {
					  "required":"*请选择会员等级"
				  },
				  rgmrate: {
					  "required":"*请输入风险保证金费率",
					  "number":"*请输入数字"
				  },
				  maxrgmamount: {
					  "required":"*请输入风险保证金最高收费",
					  "number":"*请输入数字"
				  },
				  istemplet: {
					  "required":"*请选择是否为模板"
				  },
			    }
	})
}




