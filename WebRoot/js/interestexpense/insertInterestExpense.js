function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#insertInterestExpenseForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  ierecman: {
				  "required":true
			  },
			  ugrades: {
				  "required":true
			  },
			  iepercent: {
				  "required":true,
				  "number":true
			  },
			  miniefee: {
				  "required":true,
				  "number":true
			  },
			  maxiefee: {
				  "required":true,
				  "number":true
				  
			  },
			  istemplet: {
				  "required":true
			  },
			   
		    },
			messages :  {
				  ierecman: {
					  "required":"*请输入利息管理费收款人"
				  },
				  ugrades: {
					  "required":"*请选择会员等级"
				  },
				  iepercent: {
					  "required":"*请输入百份比",
					  "number":"*请输入数字"
						  
				  },
				  miniefee: {
					  "required":"*请输入最低金额",
					  "number":"*请输入数字"
				  },
				  maxiefee: {
					  "required":"*请输入最高金额",
					  "number":"*请输入数字"
					 
				  },
				  istemplet: {
					  "required":"*请选择是否为模板"
				  },
				 
			    }
	})
}




