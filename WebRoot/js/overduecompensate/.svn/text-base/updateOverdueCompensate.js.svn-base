function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#updateOverdueCompensateForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  cmanno: {
				  "required":true,
			  },
			  graceperiod: {
				  "required":true,
				  "number":true
			  },
			  daylatefeerate: {
				  "required":true,
				  "number":true
			  },
			  isprecoveryon: {
				  "required":true
			  },
			  pmiscrecman:{
				  "required":true
			  },
			  ocminmoney:{
				  "required":true,
				  "number":true
			  },
			  ocmaxmoney:{
				  "required":true,
				  "number":true
			  },
			  maxcompensate:{
				  "required":true,
				  "number":true
			  },
			  type:{
				  "required":true
			  },
			  occquota:{
				  "required":true,
				  "number":true
			  },
			  toccrate:{
				  "required":true,
				  "number":true
			  },
			  mincfees:{
				  "required":true,
				  "number":true
			  },
			  maxcfees:{
				  "required":true,
				  "number":true
			  },
			  isupayon:{
				  "required":true
			  },
			  ugrades:{
				"required":true  
			  },
			  pfprincipalrate:{
				  "required":true,
				  "number":true
			  },
			  maxpfprincipal:{
				  "required":true,
				  "number":true
			  },
			  pfintrate:{
				  "required":true,
				  "number":true
			  },
			  maxpfint:{
				  "required":true,
				  "number":true
			  },
			  latefeerate:{
				  "required":true,
				  "number":true
			  },
			  maxlatefee:{
				  "required":true,
				  "number":true
			  },
			  istemplet:{
				  "required":true
			  }
		    },
			messages :  {
				  cmanno: {
					  "required":"*请填写逾期代偿人",
				  },
				  graceperiod: {
					  "required":"*请输入逾期宽限期",
					  "number":"*请输入数字"
				  },
				  daylatefeerate: {
					  "required":"*日滞纳金率",
					  "number":"*请输入数字"
				  },
				  isprecoveryon: {
					  "required":"*请选择是否设置平台追偿"
				  },
				  pmiscrecman:{
					  "required":"*请填写平台追偿费收款人"
				  },
				  ocminmoney:{
					  "required":"*请输入逾期本金最低值",
					  "number":"*请输入数字"
				  },
				  ocmaxmoney:{
					  "required":"*请输入逾期本金最高值",
					  "number":"*请输入数字"
				  },
				  type:{
					  "required":"*请选择类型"
				  },
				  occquota:{
					  "required":"*请输入追偿定额收费金额",
					  "number":"*请输入数字"
					
				  },
				  toccrate:{
					  "required":"*请输入追偿收费费率",
					  "number":"*请输入数字"
				  },
				  mincfees:{
					  "required":"*请输入追偿该段金额最低收费",
					  "number":"*请输入数字"
				  },
				  maxcfees:{
					  "required":"*请输入追偿该段金额最高收费",
					  "number":"*请输入数字"
				  },
				  isupayon:{
					  "required":"*请选择是否开通会员垫付"
				  },
				  ugrades:{
						"required":"请选择会员等级"  
				  },
				  pfprincipalrate:{
					  "required":"*请输入本金垫付比例",
					  "number":"*请输入数字"
				  },
				  maxpfprincipal:{
					  "required":"*请输入本金垫付最高金额",
					  "number":"*请输入数字"
				  },
				  pfintrate:{
					  "required":"*请输入利息垫付比例",
					  "number":"*请输入数字"
				  },
				  maxpfint:{
					  "required":"*请输入利息垫付最高金额",
					  "number":"*请输入数字"
				  },
				  latefeerate:{
					  "required":"*请输入滞纳金垫付比例",
					  "number":"*请输入数字"
				  },
				  maxlatefee:{
					  "required":"*请输入滞纳金垫付最高金额",
					  "number":"*请输入数字"
				  },
				  istemplet:{
					  "required":"*请选择是否为模板"
				  }
			    }
	})
}




